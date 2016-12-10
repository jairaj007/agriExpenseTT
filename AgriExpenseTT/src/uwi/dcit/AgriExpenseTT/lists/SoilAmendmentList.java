package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

public class SoilAmendmentList extends AgriList {

    private static final String truck = DHelper.qtf_soilAmendment_truck;
    private static final String bag = DHelper.qtf_soilAmendment_bag;


    public SoilAmendmentList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(truck);
        list.add(bag);
        return list;
    }


}