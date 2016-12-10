package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

public class QtfLandList extends AgriList {

    private static final String acre = "acre";
    private static final String hectare = "hectare";
    private static final String bed = "bed";

    public QtfLandList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(acre);
        list.add(hectare);
        list.add(bed);
        return list;
    }


}