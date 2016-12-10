package uwi.dcit.AgriExpenseTT.lists;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;

public class AgriListHandler {

    SQLiteDatabase db;
    DbHelper dbh;
    Context context;
    public AgriListHandler(Context context){
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        this.context=context;
    }


    public ArrayList<String> generateList(String content, String category, ArrayList<String> list){

        AgriList agriList;

        if (content.equals("category")){
            agriList = new CatList();
            list = agriList.getList();
        }

        else if (content.equals("resource")){
            list = (ArrayList<String>) DbQuery.getResources(db, dbh, category, list);
        }

        else if (isCat(content)){
             list = (ArrayList<String>) DbQuery.getResources(db, dbh, category, list);
        }

        else if (content.equals("land")){
            agriList = new QtfLandList();
            list = agriList.getList();
        }

        else if (content.equals("quantifier")){
            list = getQuantifiers(category);
        }

        else if (content.equals("measurement")){
            agriList = new MeasurementList();
            list = agriList.getList();
        }

        return list;
    }

    //checks if it is a cat item
    private static boolean isCat(String content){
        CatList catList = new CatList();
        ArrayList<String> list = catList.getList();
        for (String s: list)
            if (content.equals(s))return true;
        return false;
    }

    //return quantifiers based on category
    public static ArrayList<String> getQuantifiers(String category){
        AgriList agriList = null;

        if (category.equals(CatList.plantingMaterial))
            agriList = new QtfPlantList();

        else if (category.equals(CatList.fertilizer))
            agriList = new QtfFertilizerList();

        else if (category.equals(CatList.soilAmendment))
            agriList = new SoilAmendmentList();

        else if (category.equals(CatList.chemical))
            agriList = new QtfChemicalList();

        if (agriList != null)return agriList.getList();
        else return new ArrayList<String>();
    }

}