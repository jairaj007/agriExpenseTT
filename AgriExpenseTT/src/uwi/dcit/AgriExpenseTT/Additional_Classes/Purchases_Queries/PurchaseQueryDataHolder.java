package uwi.dcit.AgriExpenseTT.Additional_Classes.Purchases_Queries;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.LocalResourcePurchase;

/**
 * Created by Jairaj on 12/4/2016.
 */
public class PurchaseQueryDataHolder {

    SQLiteDatabase db;
    DbHelper dbh;
    ArrayList<LocalResourcePurchase> list;
    String type;
    String quantifier;
    boolean allowFinished;
    int resId;

    public PurchaseQueryDataHolder(){

    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }

    public ArrayList<LocalResourcePurchase> getList() {
        return list;
    }

    public void setList(ArrayList<LocalResourcePurchase> list) {
        this.list = list;
    }

    public DbHelper getDbh() {
        return dbh;
    }

    public void setDbh(DbHelper dbh) {
        this.dbh = dbh;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }



    public boolean isAllowFinished() {
        return allowFinished;
    }

    public void setAllowFinished(boolean allowFinished) {
        this.allowFinished = allowFinished;
    }
}
