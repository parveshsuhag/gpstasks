package org.chudsaviet.GPSTasks;

import android.content.*;
import android.database.sqlite.*;
import android.util.Log;

class DatabaseHelper extends SQLiteOpenHelper {
	public static String DATABASE_NAME="gpstasks";
	public static int DATABASE_VERSION=6;

	private Context context;
	
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL(context.getString(R.string.db_ddl_place_type));
    	db.execSQL(context.getString(R.string.db_ddl_place));
    	db.execSQL(context.getString(R.string.db_ddl_task));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(context.getString(R.string.app_name), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS place_type");
        db.execSQL("DROP TABLE IF EXISTS place");
        db.execSQL("DROP TABLE IF EXISTS task");
        onCreate(db);
    }
}