package se.juneday.android.storer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;
import se.juneday.android.storer.se.juneday.android.storer.db.DBMemberStore;

public class MainActivity extends AppCompatActivity {

  private ArrayAdapter<Member> adapter;
  private ListView listvView;

  private MemberStore memberStore;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    memberStore = new DBMemberStore();

    listvView = (ListView) findViewById(R.id.member_list);
    adapter = new ArrayAdapter<Member>(this,
        android.R.layout.simple_list_item_1,
        android.R.id.text1,
        memberStore.getAllMembers());

    listvView.setAdapter(adapter);
  }

  public void saveMember(View v) {
      String name = ((EditText)findViewById(R.id.name_field)).getText().toString();
      memberStore.storeMember(new Member(name));
  }
}
