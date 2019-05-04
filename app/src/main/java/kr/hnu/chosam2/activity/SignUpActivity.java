package kr.hnu.chosam2.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import kr.hnu.chosam2.navigationtest01.R;
import kr.hnu.chosam2.obj.Person;
import kr.hnu.chosam2.sql.PersonDAO;

public class SignUpActivity extends AppCompatActivity {

    EditText edit_id;
    EditText edit_pw;
    EditText edit_name;
    Button btn_signCheck;

    // DB를 위해서 사용

    ContentValues row;
    PersonDAO personDAO;
    ArrayList<Person> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d("test123", "SignUpActivity가져왔다");
        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_pw = (EditText) findViewById(R.id.edit_pw);
        edit_name = (EditText) findViewById(R.id.edit_name);

        btn_signCheck = (Button) findViewById(R.id.btn_signCheck);  /* 가입하기 체크버튼*/

        // DB를 조회해오기 위해 사용
        personDAO = new PersonDAO();
        persons = personDAO.getAllPerson();



        Intent signUpIntent = getIntent();
        // Login에서 던져준 intent를 받는다.
        //Person person = (Person) getIntent().getSerializableExtra("PersonIn");

        btn_signCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // DB Insert를 위한 부분

                //db = dbHelper.getWritableDatabase();

//                row = new ContentValues();
//                row.put("_id", edit_id.getText().toString());
//                row.put("pw", edit_pw.getText().toString());
//                row.put("name", edit_name.getText().toString());
//                db.insert("tb_person", null, row);
                personDAO.addPerson(new Person(edit_id.getText().toString(), edit_pw.getText().toString(), edit_name.getText().toString()));

                //db.close();
                // 응답 보내기 --> 회원가입시 입력한 id, pw 전달
                Intent result = new Intent();
                result.putExtra("PersonOut", new Person(edit_id.getText().toString(), edit_pw.getText().toString(), edit_name.getText().toString()));

                // RESULT_OK = 성공
                setResult(RESULT_OK, result);
                finish();
            }
        });
    }

    public void checkClick(View v) {
        for (Person p : persons) {
            String id = edit_id.getText().toString();
            if (p.getId().equals(id)) {
                Toast.makeText(SignUpActivity.this, "이미 존재하는 아이디입니다!", Toast.LENGTH_SHORT).show();
                break;
            } else {
                String str = "Good ID";
                Toast.makeText(SignUpActivity.this, str, Toast.LENGTH_SHORT).show();
            }

        }

    }

}