package se.juneday.android.storer.se.juneday.android.storer.db;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import se.juneday.android.storer.Member;
import se.juneday.android.storer.MemberStore;


public class DBMemberStore implements MemberStore {


  private static final String LOG_TAG = DBMemberStore.class.getSimpleName();

  @Override
  public List<Member> getAllMembers() {
    Log.d(LOG_TAG, "getAllMembers()");
    List<Member> members = new ArrayList<>();
    members.add(new Member("Diego"));
    members.add(new Member("Batistuta"));
    members.add(new Member("Mario"));
    return members;
  }

  @Override
  public void storeMember(Member m) {
    Log.d(LOG_TAG, "storeMember: " + m);
    return;
  }
}
