package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

public class QtfChemicalList extends AgriList {

    private static final String ml = DHelper.qtf_chemical_ml;
    private static final String L = DHelper.qtf_chemical_L;
    private static final String oz = DHelper.qtf_chemical_oz;
    private static final String g = DHelper.qtf_chemical_g;
    private static final String kg = DHelper.qtf_chemical_kg;

    public QtfChemicalList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(ml);
        list.add(L);
        list.add(oz);
        list.add(g);
        list.add(kg);
        return list;
    }


}