package uwi.dcit.AgriExpenseTT.lists;


import java.util.ArrayList;

public class MeasurementList extends AgriList {

    private static final String lb = "Lb";
    private static final String kg = "Kg";
    private static final String bag = "Bag";
    private static final String bundle = "Bundle";
    private static final String head = "Head";
    private static final String hundreds = "100's";
    private static final String bundle51 = "5lb Bundle";

    public MeasurementList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(lb);
        list.add(kg);
        list.add(bag);
        list.add(bundle);
        list.add(head);
        list.add(hundreds);
        list.add(bundle51);
        return list;
    }


}