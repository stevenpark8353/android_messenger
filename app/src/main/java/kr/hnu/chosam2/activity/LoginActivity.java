package kr.hnu.chosam2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.hnu.chosam2.navigationtest01.R;
import kr.hnu.chosam2.obj.Person;
import kr.hnu.chosam2.sql.PersonDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edit_id;
    private EditText edit_pw;
    private Button btn_signUp;
    private Button btn_login;
    private final static int REQCODE_ACTEDIT = 1001;
    private Person person;
    private String tempId;
    private String tempPw;

    private TextView dateView;
    // 로그인 상태 유지하기위해서 추가!
    private CheckBox checkBox;
    private boolean saveLoginData;
    private SharedPreferences appData;
    String id;
    String pw;

    // DB를 관리하기 위한 변수
    PersonDAO personDAO;
    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        // 데이터베이스 테이블 생성을 위한 변수
        personDAO = new PersonDAO();
        persons = personDAO.getAllPerson();
        Log.d("test123", "loginActivity가져왔다");

        edit_id = (EditText) findViewById(R.id.edit_id); // 로그인창에서 EditText의 edit_id 부분
        edit_pw = (EditText) findViewById(R.id.edit_pw);

        btn_signUp = (Button) findViewById(R.id.btn_signUp);
        btn_login = (Button) findViewById(R.id.btn_login);

        dateView = (TextView) findViewById(R.id.date);   // 날짜 추가!

        ///////////////////////////////
        // 로그인 아이디 유지
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        checkBox = (CheckBox) findViewById(R.id.check);

        if (saveLoginData) {
            edit_id.setText(id);
            edit_pw.setText(pw);
            checkBox.setChecked(saveLoginData);
        }

    }

    public void mOnClick(View v) {
        switch (v.getId()) {

            case R.id.btn_signUp:
                Intent signUpIntent = new Intent(this, SignUpActivity.class);
                //  signUpIntent.putExtra("PersonIn", new Person(edit_id.getText().toString(), edit_pw.getText().toString()));
                startActivityForResult(signUpIntent, REQCODE_ACTEDIT);

                break;

            case R.id.btn_login:
                Intent loginIntent = new Intent(this, MainActivity.class);

                if (edit_id.getText().toString().equals("") || edit_id.getText().toString().equals(null)) {
                    Toast.makeText(LoginActivity.this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show();
                    break;
                } else if (edit_pw.getText().toString().equals("") || edit_pw.getText().toString().equals(null)) {
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    break;
                }

                boolean hasPerson = false;
                for (Person p : persons) {
                    String id = edit_id.getText().toString();
                    String pw = edit_pw.getText().toString();
                    Log.d("test123", id + ":" + p.getId() + ", " + pw + ":" + p.getPassword());
                    if (id.equals(p.getId()) && pw.equals(p.getPassword())) {
                        hasPerson = true;
                        break;
                    }
                }
                if (hasPerson) {
                    String id = edit_id.getText().toString();
                    // 로그인 유지를 위해 추가한 부분
                    save();


                    loginIntent.putExtra("userId", edit_id.getText().toString());
                    LoginActivity.this.startActivity(loginIntent);
                    Toast.makeText(LoginActivity.this, id + "님이 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "잘못된 정보입니다...", Toast.LENGTH_SHORT).show();
                }
        }
    }

    ////////////////////////////////////// 로그인 유지를 위해 추가한 부분
    private void save() {
        SharedPreferences.Editor editor = appData.edit();

        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked());
        editor.putString("ID", edit_id.getText().toString().trim());
        editor.putString("PW", edit_pw.getText().toString().trim());

        editor.apply();
    }

    private void load() {
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
        pw = appData.getString("PW", "");
    }

    /////////////////////////////////////////
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQCODE_ACTEDIT:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(LoginActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                    person = (Person) intent.getSerializableExtra("PersonOut");
                    tempId = person.getId();
                    tempPw = person.getPassword();
                    persons.add(person);
                    break;
                } else {
                    Toast.makeText(LoginActivity.this, "무엇이 잘못됬나요?", Toast.LENGTH_SHORT).show();
                    Log.d("a", "hint:" + person.getId());
                    Log.d("a", "hint:" + person.getPassword());
                }
                break;
        }
    }


}

