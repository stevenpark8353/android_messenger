package kr.hnu.chosam2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.hnu.chosam2.fragment.EditFragment;
import kr.hnu.chosam2.fragment.MessageDetailFragment;
import kr.hnu.chosam2.fragment.SendMessageFragment;
import kr.hnu.chosam2.navigationtest01.R;
import kr.hnu.chosam2.obj.Message;
import kr.hnu.chosam2.sql.MessageDAO;
import kr.hnu.chosam2.util.Utils;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "MainActivity";
//    ArrayList<HashMap<String, Object>> arItem; // list추가!
    Menu menu;
    SimpleAdapter sAdapter;
    private String userId;
    // Fragment를 위해 추가합니다
    LinearLayout firstContainer, fragmentContainer;


    EditText edit_id, edit_pw;

    TextView name;
    TextView title;
    MessageDAO messageDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Utils.hideSoftKeyboard(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("받은 메시지 함");

        messageDAO = new MessageDAO();

        //////////////////////
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        ///////////////////

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        inflateListView();

////////////////////////////////////////////////////////////////
        // Fragment를 위한 추가
        firstContainer = findViewById(R.id.firstContainer);
        fragmentContainer = findViewById(R.id.fragmentContainer);

    }

    ////////////////////////////////////////// List용 코드 추가
    private Map<String, Object> putItem(Message message) {
        Map<String, Object> item = new HashMap<String, Object>();
        item.put("writer", message.getSender());
        item.put("title", message.getTitle());
        item.put("date", message.getDate());
        item.put("message", message);
        return item;
    }

    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String mes;
//            mes = "Select Item = " + arItem.get(position).get("name");
//            Toast.makeText(MainActivity.this, mes, Toast.LENGTH_SHORT).show();

            Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
            Message message = (Message) map.get("message");

            Bundle bundle = new Bundle();
//            bundle.putString("writer", (String) map.get("writer"));
//            bundle.putString("title", (String) map.get("title"));
//            bundle.putString("date", (String) map.get("date"));
//            bundle.putString("date", (String) map.get("contents"));

            bundle.putSerializable("message", message);

            Fragment fragment = new MessageDetailFragment();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(MessageDetailFragment.TAG);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("메시지 작성");
        }
    };

    //////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        String tag = null;
        if (id == R.id.nav_write) {
            fragment = new SendMessageFragment();
            tag = SendMessageFragment.TAG;
            getSupportActionBar().setTitle("메세지 작성");
        } else if (id == R.id.nav_message) {
//            fragmentTransaction.replace(R.id.firstContainer, new ReceiveMessageFragment());
            getSupportActionBar().setTitle("받은 메시지 함");
        } else if (id == R.id.nav_setting) {
            fragment = new EditFragment();
            tag = EditFragment.TAG;
            getSupportActionBar().setTitle("개인 정보 설정");
        } else if (id == R.id.nav_logout) {
            this.finish();
        }

        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 12321) {
            Log.d(TAG, "result code: " + resultCode);
        }
    }

    public String getUserId() {
        return userId;
    }

    public void inflateListView() {
        Log.d(TAG, "inflateView started");

        List<Map<String, Object>> arItem = new ArrayList<Map<String, Object>>();

        for (Message message : messageDAO.getAllMessage()) {
            arItem.add(putItem(message));
        }

        String[] from = {"icon", "writer", "title", "date"};
        int[] to = {R.id.img, R.id.writer, R.id.title, R.id.date};
        sAdapter = new SimpleAdapter(this, arItem, R.layout.listitem, from, to);

        ListView myList = (ListView) findViewById(R.id.list);

        myList.setAdapter(sAdapter);
        myList.setOnItemClickListener(mItemClickListener);
    }
}
