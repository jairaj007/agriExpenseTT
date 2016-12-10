package uwi.dcit.AgriExpenseTT.lists;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;

public class QtfPlantList extends AgriList {

    private static final String seed = DHelper.qtf_plantingMaterial_seed;
    private static final String seedling = DHelper.qtf_plantingMaterial_seedling;
    private static final String stick = DHelper.qtf_plantingMaterial_stick;
    private static final String tubes = DHelper.qtf_plantingMaterial_tubes;
    private static final String heads = DHelper.qtf_plantingMaterial_heads;
    private static final String slip = DHelper.qtf_plantingMaterial_slip;

    public QtfPlantList(){
        super();
    }

    public ArrayList<String> getList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(seed);
        list.add(seedling);
        list.add(slip);
        list.add(tubes);
        list.add(heads);
        list.add(stick);
        return list;
    }


}