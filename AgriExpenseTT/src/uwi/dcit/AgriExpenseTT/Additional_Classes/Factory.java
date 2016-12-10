package uwi.dcit.AgriExpenseTT.Additional_Classes;

import android.content.Context;




//This Factory is used to determine which Manager to create and return to the DataManager Facade.

public class Factory {

    String manager;

    public static ModelManager getManager(String manager){
        if(manager.equalsIgnoreCase("purchase")) {
            return new Purchase();
        }
        /*These else if are used as examples to show what it would look like if we did all
        else if(manager.equalsIgnoreCase("cycle")){
            return new Cylce();
        }
        else if(manager.equalsIgnoreCase("resource")){
            return new Resource();
        }
        */
        else
            return null;
    }



}
