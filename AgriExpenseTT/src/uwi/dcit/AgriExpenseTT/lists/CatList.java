package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

public class CatList extends AgriList {

    public static final String plantingMaterial = DHelper.cat_plantingMaterial;
    public static final String chemical = DHelper.cat_chemical;
    public static final String fertilizer = DHelper.cat_fertilizer;
    public static final String soilAmendment = DHelper.cat_soilAmendment;
    public static final String labour = DHelper.cat_labour;
    public static final String other = DHelper.cat_other;

    public CatList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(plantingMaterial);
        list.add(chemical);
        list.add(fertilizer);
        list.add(soilAmendment);
        list.add(labour);
        list.add(other);
        return list;
    }




}