package se.juneday.android.storer.se.juneday.android.storer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MemberStoreHelper extends SQLiteOpenHelper {

  public static final String TABLE_MEMBERS = "members";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_MEMBER_NAME = "name";

  private static final String DATABASE_NAME = "members.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_MEMBERS + "( " + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_MEMBER_NAME
      + " text not null);";

  private static final String LOG_TAG = MemberStoreHelper.class.getSimpleName();


  public MemberStoreHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    Log.d(LOG_TAG, "MemberStoreHelper()");
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    Log.d(LOG_TAG, "onCreate()");
    sqLiteDatabase.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    Log.d(LOG_TAG,
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS);
    onCreate(sqLiteDatabase);
  }
}
