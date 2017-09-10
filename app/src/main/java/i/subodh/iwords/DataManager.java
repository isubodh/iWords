package i.subodh.iwords;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Tintin on 10/09/2017.
 */

public class DataManager  {

    private SQLiteDatabase iWordsDB;
    private static final String TAG = "iWORDs-SQL";
    private Context context;

    private static final String DB_NAME = "iWordsDB.sqlite";
    private static final int DB_VERSION = 1;
    private static final String TB_WORDS = "words";

    public static final String WORDS_TC_WORD = "word";
    public static final String WORDS_TC_COUNT = "count";

    /* Constructer*/
    public DataManager(Context context ){
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        iWordsDB = helper.getWritableDatabase();
        this.context = context;
    }

    /*
    INSERT a word
     */
    public void insertWord(String word){
        String query = "INSERT into " + TB_WORDS +
                "( " + WORDS_TC_WORD + ", " + WORDS_TC_COUNT + " ) values ( '" +
                word + "', " + word.length() +  ");";

        Log.d(TAG,"insertWord : "+ query);

        iWordsDB.execSQL(query);

    }

    public String getWord(int count){
        String query = "SELECT " + WORDS_TC_WORD + " from "+
                TB_WORDS + " where "+ WORDS_TC_COUNT +
                " = "+ count +" order by RANDOM() LIMIT 1;";

        Log.d(TAG,"getWord : "+ query);

        Log.d(TAG,"getWord : "+ iWordsDB.getPath() + iWordsDB.toString());

        Cursor c = iWordsDB.rawQuery(query,null);
        Log.d(TAG, "Cursor : " + c.toString());
        if (c != null && c.moveToFirst())
            return c.getString(0);
        else
            return  context.getString(R.string.noWord); //"End of iWords DB";
    }

    /*
    Private sub class with SQLiteAssetHelper for a external database
     */
    private class CustomSQLiteOpenHelper extends SQLiteAssetHelper {

        public CustomSQLiteOpenHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(TAG, "Done initializing the DB : "+ DB_NAME);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int OldVersion, int NewVersion){
            //nothing for now
        }

    }

}
