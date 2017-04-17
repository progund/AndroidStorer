package se.juneday.android.storer.se.juneday.android.storer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import se.juneday.android.storer.Member;
import se.juneday.android.storer.MemberStore;
import se.juneday.android.storer.MemberStoreException;


public class DBMemberStore implements MemberStore {

  private static final String LOG_TAG = DBMemberStore.class.getSimpleName();

  // Database fields
  private SQLiteDatabase database;
  private MemberStoreHelper dbHelper;
  private String[] allColumns = { MemberStoreHelper.COLUMN_ID,
      MemberStoreHelper.COLUMN_MEMBER_NAME };

  public DBMemberStore(Context context) {
    Log.d(LOG_TAG, "DBMemberStore()");
    dbHelper = new MemberStoreHelper(context);
  }

  public void open() throws MemberStoreException {
    Log.d(LOG_TAG, "open()");
    try {
      database = dbHelper.getWritableDatabase();
    } catch (SQLException e) {
      throw new MemberStoreException(e);
    }
  }

  public void close() {
    Log.d(LOG_TAG, "close()");
    dbHelper.close();
  }

  public Member createMember(Member member) {
    Log.d(LOG_TAG, "createMember()");
    ContentValues values = new ContentValues();
    values.put(MemberStoreHelper.COLUMN_MEMBER_NAME, member.name());
    long insertId = database.insert(MemberStoreHelper.TABLE_MEMBERS, null,
        values);
    Cursor cursor = database.query(MemberStoreHelper.TABLE_MEMBERS,
        allColumns, MemberStoreHelper.COLUMN_ID + " = " + insertId, null,
        null, null, null);
    cursor.moveToFirst();
    Member newMember = cursorToMember(cursor);
    cursor.close();
    return newMember;
  }

  public void deleteMember(Member m) {
    Log.d(LOG_TAG, "deleteMember()");
    database.delete(MemberStoreHelper.TABLE_MEMBERS, MemberStoreHelper.COLUMN_MEMBER_NAME
        + " = '" + m.name() + "'", null);
    System.out.println("Member deleted: " + m);
  }

  public List<Member> getAllMembers() {
    Log.d(LOG_TAG, "getAllMembers()");
/*
    Log.d(LOG_TAG, "getAllMembers()");
    List<Member> members = new ArrayList<>();
    members.add(new Member("Diego"));
    members.add(new Member("Batistuta"));
    members.add(new Member("Mario"));
    return members;
 */

    List<Member> members = new ArrayList<>();

    Cursor cursor = database.query(MemberStoreHelper.TABLE_MEMBERS,
        allColumns, null, null, null, null, null);

    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Member m = cursorToMember(cursor);
      Log.d(LOG_TAG, "Adding :" + m);
      members.add(m);
      cursor.moveToNext();
    }
    // make sure to close the cursor
    cursor.close();
    return members;
  }

  private Member cursorToMember(Cursor cursor) {
    Log.d(LOG_TAG, "cursorToMember()");
    String name = cursor.getString(1);
    return new Member(name);
  }

  @Override
  public void storeMember(Member m) {
    Log.d(LOG_TAG, "storeMember: " + m);
    createMember(m);
  }

}
