package se.juneday.android.storer.se.juneday.android.storer.db;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.juneday.android.storer.Member;
import se.juneday.android.storer.MemberStore;
import se.juneday.android.storer.MemberStoreException;


public class FakeMemberStore implements MemberStore {

  private static final String LOG_TAG = FakeMemberStore.class.getSimpleName();


  @Override
  public List<Member> getAllMembers() {
    Log.d(LOG_TAG, "getAllMembers()");
    List<Member> members = new ArrayList<>();
    members.add(new Member("Diego"));
    members.add(new Member("Batistuta"));
    members.add(new Member("Mario"));
    return members;
  }

  public FakeMemberStore(Context context) {
    Log.d(LOG_TAG, "DBMemberStore()");
  }

  @Override
  public void storeMember(Member m) {
    Log.d(LOG_TAG, "storeMember()");

  }

  @Override
  public void deleteMember(Member m) {
    Log.d(LOG_TAG, "deleteMember()");
  }

  @Override
  public void open() throws MemberStoreException {
    Log.d(LOG_TAG, "open()");

  }

  @Override
  public void close() throws MemberStoreException {
    Log.d(LOG_TAG, "close()");

  }
}
