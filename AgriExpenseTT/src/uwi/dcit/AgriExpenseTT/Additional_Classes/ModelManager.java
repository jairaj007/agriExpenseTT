package uwi.dcit.AgriExpenseTT.Additional_Classes;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by Jairaj on 12/3/2016.
 */
public abstract class ModelManager {

    public ModelManager(){

    }


    public abstract int insert(Context context, Object obj);
    public abstract void update(Context context, Object obj, ContentValues cv);
    public abstract void delete(Context context, Object obj);
    public abstract Object get (Context context,Object obj,String query)throws java.lang.Exception;
    public abstract Object get (Object obj,String query)throws java.lang.Exception;



}
