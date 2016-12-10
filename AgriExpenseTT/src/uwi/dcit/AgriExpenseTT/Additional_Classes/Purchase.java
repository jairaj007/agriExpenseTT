package uwi.dcit.AgriExpenseTT.Additional_Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import uwi.dcit.AgriExpenseTT.Additional_Classes.Purchases_Queries.PurchaseQueryDataHolder;
import uwi.dcit.AgriExpenseTT.cloud.CloudInterface;
import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DateFormatHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;
import uwi.dcit.AgriExpenseTT.helpers.TransactionLog;
import uwi.dcit.AgriExpenseTT.models.LocalCycleUse;
import uwi.dcit.AgriExpenseTT.models.LocalResourcePurchase;
import uwi.dcit.AgriExpenseTT.models.ResourcePurchaseContract;
import uwi.dcit.agriexpensesvr.rPurchaseApi.model.RPurchase;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;


public class Purchase extends ModelManager {
    UpAcc acc;
    SQLiteDatabase db;
    DbHelper dbh;

    TransactionLog tL;

    public Purchase(){
        super();
    }
    @Override
    public int insert(Context context, Object obj){
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        tL=new TransactionLog(dbh,db,context);
        acc=DbQuery.getUpAcc(db);
        RPurchase rp = RPurchase.class.cast(obj);
        //insert into database

        Log.i("Testing Object Override",rp.getQuantifier());

        long timeSet = rp.getTime();


        ContentValues cv= new ContentValues();
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, rp.getResourceId());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, rp.getType());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, rp.getQuantifier());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, rp.getQty());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, rp.getCost());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, rp.getQty());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESOURCE, DbQuery.findResourceName(db, dbh, rp.getResourceId()));
        if(timeSet!=-1)//Using this eliminates the need to have two methods just to cater for the other category
             cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_DATE, rp.getTime());
        db.insert(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, null, cv);
        int rowId=DbQuery.getLast(db, dbh, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME);
        tL.insertTransLog(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, rowId, TransactionLog.TL_INS);//records the insert of a purchase
        if(acc!=null){
            //insert into redo log table
            int i=DbQuery.insertRedoLog(db, dbh, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, rowId, "ins");
            //try to insert into cloud
            if(acc.getSignedIn()==1){
                CloudInterface c= new CloudInterface(context,db,dbh);//new CloudInterface(context);
                c.insertPurchase();
            }
        }
        return rowId;
    }


    @Override
    public  void update(Context context, Object obj, ContentValues cv){
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        tL=new TransactionLog(dbh,db,context);
        acc=DbQuery.getUpAcc(db);
        RPurchase p = RPurchase.class.cast(obj);

        //insert into database
        db.update(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, cv, ResourcePurchaseContract.ResourcePurchaseEntry._ID+"="+p.getPId(),null);
        //update the cloud
        TransactionLog tl=new TransactionLog(dbh, db,context);
        tl.insertTransLog(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, p.getPId(), TransactionLog.TL_UPDATE);
        if(acc!=null){
            DbQuery.insertRedoLog(db, dbh, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME,p.getPId(), TransactionLog.TL_UPDATE);
            //record in transaction log
            if(acc.getSignedIn()==1){
                CloudInterface cloud= new CloudInterface(context,db,dbh);// new CloudInterface(context);
                cloud.updatePurchase();
            }
        }
    }


    @Override
    public  void delete(Context context, Object obj){
        //get all cycleUse with the purchase's id
        //delete each one using the deleteCycleUse to remove the cost added to each cycle
        //delete the purchase (locally)
        //put the delete in the redo log
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        tL=new TransactionLog(dbh,db,context);
        acc=DbQuery.getUpAcc(db);
        RPurchase p = RPurchase.class.cast(obj);

        //getting all the cycleUse
        ArrayList<LocalCycleUse> list=new ArrayList<LocalCycleUse>();
        DbQuery.getCycleUseP(db, dbh, p.getPId(), list, null);
        Iterator<LocalCycleUse> itr=list.iterator();
        while(itr.hasNext()){
            LocalCycleUse l=itr.next();


            /////////////////////////////-------------NOTE----------------------///////////////////////////////////////////////////////////////////////////
            //Because we are not showing the complete redesign for Cycle and resources we have to use the DM old implementation to access the deleteCycleUse Method for Cycle//////////////////////////////////
            DataManager dm = new DataManager(context);
            dm.deleteCycleUse(l);//already does the recording into the redo log(cloud) and transaction log


        }
        //delete purchase
        db.delete(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, ResourcePurchaseContract.ResourcePurchaseEntry._ID+"="+p.getPId(), null);
        tL.insertTransLog(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, p.getPId(), TransactionLog.TL_DEL);
        if(acc!=null){
            //redo log (cloud)
            DbQuery.insertRedoLog(db, dbh, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, p.getPId(), TransactionLog.TL_DEL);
            if(acc.getSignedIn()==1){
                CloudInterface c= new CloudInterface(context,db,dbh);//new CloudInterface(context);
                c.deletePurchase();
            }
        }

    }



    public  Object get(Context context, Object obj,String query){

        if(query.equalsIgnoreCase("getARPurchases") || query.equalsIgnoreCase("getARPurchase") )
            return getARPurchases(context, obj);
        else if (query.equalsIgnoreCase("getPurchases") || query.equalsIgnoreCase("getPurchase") )
            return getPurchases(context, obj);
        else if(query.equalsIgnoreCase("getResourcePurchases") || query.equalsIgnoreCase("getResourcePurchase") )
            return  getResourcePurchases(context,obj);
        else
            return null;
    }


    public  Object get(Object obj,String query) {
        if(query.equalsIgnoreCase("updatePurchaseRecs"))
            return updatePurchaseRecs(obj);
        else
            return null;
    }



    private Object updatePurchaseRecs(Object obj){
        PurchaseQueryDataHolder p = PurchaseQueryDataHolder.class.cast(obj);
        SQLiteDatabase db= p.getDb();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, null);
        // Update Existing Dates to the current date
        while(cursor.moveToNext()){
            ContentValues cv = new ContentValues();
            cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_DATE,  DateFormatHelper.getDateUnix(new Date()) );
        }
        cursor.close();
        return null;
    }

    public static RPurchase getARPurchases (Context context, Object obj){//(SQLiteDatabase db, DbHelper dbh, int id){

        PurchaseQueryDataHolder p = PurchaseQueryDataHolder.class.cast(obj);

        SQLiteDatabase db = p.getDb();
        DbHelper dbh= p.getDbh();
        int id = p.getResId();

        String code="select * from "+ ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME+" where "
                + ResourcePurchaseContract.ResourcePurchaseEntry._ID+"="+id+";";
        Cursor cursor=db.rawQuery(code, null);
        if(cursor.getCount() < 1) {
            return null;

        }

        cursor.moveToFirst();

        RPurchase purchase=new RPurchase();
        purchase.setPId(id);
        purchase.setResourceId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID)));
        purchase.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
        purchase.setQty(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
        purchase.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
        purchase.setQtyRemaining(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
        purchase.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
        purchase.setElementName(DbQuery.findResourceName(db, dbh, purchase.getResourceId()));

        cursor.close();

        return purchase;
    }

    public static Object getPurchases(Context context, Object obj){//(SQLiteDatabase db, DbHelper dbh, ArrayList<LocalResourcePurchase> list, String type, String quantifier, boolean allowFinished){


        PurchaseQueryDataHolder p = PurchaseQueryDataHolder.class.cast(obj);
        SQLiteDatabase db= p.getDb();
        DbHelper dbh = p.getDbh();
        ArrayList<LocalResourcePurchase> list = p.getList();
        String type = p.getType();
        String quantifier = p.getQuantifier();
        boolean allowFinished = p.isAllowFinished();

        if (list == null)list = new ArrayList<>();

        String code;
        if(type == null)
            code="select * from "+ ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME+";";
        else
            code="select * from "+ ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME+" where "+ ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE+"='"+type+"';";

        Cursor cursor = db.rawQuery(code, null);
        if(cursor == null || cursor.getCount() < 1 ) {
            p.setList(list);
            return p;
        }

        while(cursor.moveToNext()){
            LocalResourcePurchase m=new LocalResourcePurchase();
            m.setpId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry._ID)));
            m.setResourceId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID)));
            m.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
            m.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
            m.setQty(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
            m.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
            m.setQtyRemaining(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
            m.setDate(cursor.getLong(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_DATE)));
            list.add(m);
        }

        cursor.close();
        p.setList(list);
        return p;
    }

    public static Object getResourcePurchases(Context context,Object obj){//(SQLiteDatabase db, DbHelper dbh, ArrayList<LocalResourcePurchase> list, int resId){

        PurchaseQueryDataHolder p = PurchaseQueryDataHolder.class.cast(obj);
        SQLiteDatabase db = p.getDb();
        DbHelper dbh = p.getDbh();
        ArrayList<LocalResourcePurchase> list = p.getList();
        int resId = p.getResId();

        if (list == null)list = new ArrayList<>();

        String code = "select * from "+ ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME+" where "+ ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID+"="+resId+";";
        Cursor cursor =db.rawQuery(code, null);
        if(cursor.getCount()<1) {
            p.setList(list);
            return p;
        }
        while(cursor.moveToNext()){
            LocalResourcePurchase m=new LocalResourcePurchase();
            m.setpId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry._ID)));
            m.setResourceId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID)));
            m.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
            m.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
            m.setQty(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
            m.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
            m.setQtyRemaining(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
            list.add(m);
        }
        cursor.close();
        p.setList(list);
        return p;
    }







}
