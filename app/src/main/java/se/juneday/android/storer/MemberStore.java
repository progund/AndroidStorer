package se.juneday.android.storer;

import java.util.List;

public interface MemberStore {

  List<Member> getAllMembers();
  void storeMember(Member m);
  void deleteMember(Member m);
  void open() throws MemberStoreException;
  void close() throws MemberStoreException;

}
