package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

public class QtfFertilizerList extends AgriList {

    private static final String g = DHelper.qtf_fertilizer_g;
    private static final String kg = DHelper.qtf_fertilizer_kg;
    private static final String lb = DHelper.qtf_fertilizer_lb;
    private static final String bag = DHelper.qtf_fertilizer_bag;

    public QtfFertilizerList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(g);
        list.add(kg);
        list.add(lb);
        list.add(bag);
        return list;
    }


}