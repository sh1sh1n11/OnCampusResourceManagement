package msisproject1.com.project1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shanky on 4/5/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ocrm.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_ASSIGNSHIFT = "assignShift";

    private static final String ASSIGNSHIFT_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS assignShift" + "(employeeId integer primary key, " +
            "employeeName VARCHAR, shiftDate TEXT, startTime integer";
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ASSIGNSHIFT_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
