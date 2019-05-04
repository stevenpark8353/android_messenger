package kr.hnu.chosam2.navigationtest01;

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

import kr.hnu.chosam2.sql.MessageDAO;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "MainActivity";
//    ArrayList<HashMap<String, Object>> arItem; // list추가!
    Menu menu;
    SimpleAdapter sAdapter;
    String userId;
    // Fragment를 위해 추가합니다
    LinearLayout firstContainer, fragmentContainer;

    ///////////////////////////
    // Intent를 받기 위해서 추가
    EditText edit_id, edit_pw;

    TextView name;
    TextView title;
    MessageDAO messageDAO;

    ////////////////

    public void inflateListView() {
        Log.d(TAG, "inflateView started");

        List<HashMap<String, Object>> arItem = new ArrayList<HashMap<String, Object>>();
        List<Message> messages = messageDAO.getAllMessage();

        for (Message message : messages) {
            Log.d(TAG, "message title: " + message);
            arItem.add(putItem(R.drawable.ic_email_red, message.getSender(), message.getTitle(), message.getToday()));
        }

        String[] from = {"icon", "writer", "title", "date"};
        int[] to = {R.id.img, R.id.writer, R.id.title, R.id.date};
        sAdapter = new SimpleAdapter(this, arItem, R.layout.listitem, from, to);

        ListView myList = (ListView) findViewById(R.id.list);

        myList.setAdapter(sAdapter);
        myList.setOnItemClickListener(mItemClickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    private HashMap<String, Object> putItem(int icon, String name, String title, String date) {
        HashMap<String, Object> item = new HashMap<String, Object>();
        item.put("icon", icon);
        item.put("writer", name);
        item.put("title", title);
        item.put("date", date);
        return item;
    }

    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            String mes;
//            mes = "Select Item = " + arItem.get(position).get("name");
//            Toast.makeText(MainActivity.this, mes, Toast.LENGTH_SHORT).show();

            Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ReceiveActivity.class);

            intent.putExtra("writer", (String) map.get("writer"));
            intent.putExtra("title", (String) map.get("title"));
            intent.putExtra("date", (String) map.get("date"));


            Bundle bundle = new Bundle();

            bundle.putString("writer", (String) map.get("writer"));
            bundle.putString("title", (String) map.get("title"));
            bundle.putString("date", (String) map.get("date"));

            Fragment fragment = new ReceiveFragment();
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(ReceiveFragment.TAG);
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
        getMenuInflater().inflate(R.menu.main, menu);
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
}
