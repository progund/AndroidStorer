package se.juneday.android.storer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import se.juneday.android.storer.se.juneday.android.storer.db.DBMemberStore;
import se.juneday.android.storer.se.juneday.android.storer.db.FakeMemberStore;

public class MainActivity extends AppCompatActivity {

  private static final String LOG_TAG = MainActivity.class.getSimpleName();

  private ArrayAdapter<Member> adapter;
  private ListView listView;
  private List<Member> members;
  private MemberStore memberStore;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // On the line below, we know we're dealing with a db
    // We can live with this, since there is only one line to change
    // The DBMemberStore reference is kept in a MemberStore ref variable -
    // - so we will treat the DBMemberStore as a MemberStore
    memberStore = new DBMemberStore(this);
    try {
      memberStore.open();
    } catch (MemberStoreException e) {
      Log.d(LOG_TAG, "Can't open MemberStore....");
      finish();
    }

    members = memberStore.getAllMembers();
    listView = (ListView) findViewById(R.id.member_list);
    adapter = new ArrayAdapter<>(this,
        android.R.layout.simple_list_item_1,
        android.R.id.text1,
        members);

    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new ListView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Member m = memberStore.getAllMembers().get(i);
        Log.d(LOG_TAG, "delete: " + m);
        memberStore.deleteMember(m);
        updateListView();
      }
    });

  }

  private void updateListView() {
    members = memberStore.getAllMembers();
    adapter.clear();
    adapter.addAll(members);
  }

  public void saveMember(View v) {
    String name = ((EditText)findViewById(R.id.name_field)).getText().toString();
    Member m = new Member(name);
    memberStore.storeMember(m);
    updateListView();
    ((EditText)findViewById(R.id.name_field)).setText("");
  }
  
  @Override
  protected void onResume() {
    try {
      memberStore.open();
    } catch (MemberStoreException e) {
      Log.d(LOG_TAG, "Can't open MemberStore....");
      finish();
    }
    super.onResume();
  }

  @Override
  protected void onPause() {
    try {
      memberStore.close();
    } catch (MemberStoreException e) {
      Log.d(LOG_TAG, "Can't close MemberStore....");
    }
    super.onPause();
  }

}

