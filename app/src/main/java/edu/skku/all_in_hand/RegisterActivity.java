package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/*
from LoginActivity
회원가입 페이지
 */

public class RegisterActivity extends AppCompatActivity {
    int use_id;
    EditText editTextID, editTextPW, editPhone, editEmail;
    Button btn_register;
    Button check_id;

    String phone, email, id, pw;
    Intent intent;

    String REQUEST_URL ="http://54.180.99.61:8080/stores/test2";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        use_id = 0;

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPW = (EditText) findViewById(R.id.editTextPW);
        editPhone = (EditText) findViewById(R.id.phonenum);
        editEmail = (EditText)findViewById(R.id.email);
        btn_register = (Button) findViewById((R.id.btn_register));
        check_id = (Button)findViewById(R.id.check_id);

        check_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextID.getText().toString().equals("") || TextUtils.isEmpty(editTextID.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "사용할 아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                if (onlyNumCheck(editTextID.getText().toString()) == true) {
                    Toast.makeText(RegisterActivity.this, "숫자로만 구성된 ID는 사용이 불가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    use_id=1;
                }
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = editTextID.getText().toString();
                pw = editTextPW.getText().toString();
                phone = editPhone.getText().toString();
                email = editEmail.getText().toString();
                if ((use_id == 0 )) {
                    Toast.makeText(RegisterActivity.this, "ID 중복체크 또는 닉네임 체크를 먼저 해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if (id.length() == 0 || pw.length() == 0 || phone.length() == 0 || email.length() == 0||spaceCheck(id) == true || spaceCheck(pw) == true || spaceCheck(email) == true || spaceCheck(phone) == true) {
                    Toast.makeText(RegisterActivity.this, "모든 칸을 채워주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    //TODO POST DB
                    Toast.makeText(RegisterActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                    intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    /*
    private ValueEventListener checkIDRegister = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterator<DataSnapshot> child = dataSnapshot.child("user_list").getChildren().iterator();
            while (child.hasNext()) {
                if (editTextID.getText().toString().equals(child.next().getKey())) {
                    Toast.makeText(getApplicationContext(), "이미 존재하는 ID입니다.", Toast.LENGTH_SHORT).show();
                    mPostReference.removeEventListener(this);
                    return;
                }
            }
            Toast.makeText(getApplicationContext(), "사용할 수 있는 ID입니다.", Toast.LENGTH_SHORT).show();
            use_id = 1;
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
        }
    };
    */

    public boolean onlyNumCheck(String spaceCheck) {
        for (int i = 0 ; i < spaceCheck.length() ; i++) {
            if (spaceCheck.charAt(i) == '1' || spaceCheck.charAt(i) == '2' || spaceCheck.charAt(i) == '3' || spaceCheck.charAt(i) == '4' || spaceCheck.charAt(i) == '5'
                    || spaceCheck.charAt(i) == '6' || spaceCheck.charAt(i) == '7' || spaceCheck.charAt(i) == '8' || spaceCheck.charAt(i) == '9' || spaceCheck.charAt(i) == '0') {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public boolean spaceCheck(String spaceCheck) {
        for (int i = 0 ; i < spaceCheck.length() ; i++) {
            if (spaceCheck.charAt(i) == ' ')
                continue;
            else if (spaceCheck.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}