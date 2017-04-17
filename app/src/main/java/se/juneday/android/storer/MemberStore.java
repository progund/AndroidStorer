package se.juneday.android.storer;

import java.util.List;

public interface MemberStore {

  public List<Member> getAllMembers();
  public void storeMember(Member m);

}
