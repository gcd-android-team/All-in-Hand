package edu.skku.all_in_hand;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.Logger;

/*
first
로그인 페이지
 */

public class LoginActivity extends AppCompatActivity {
    EditText editTextID, editTextPW;
    String id, pw;
    Button buttonLogin, buttonRegister;
    Intent intent;
    int flag = 0;
    int kakaoflag = 0, overlapflag = 0;
    String savedKakao, data;
    Intent intent_kakaoregister;

    CheckBox checkbox;
    SharedPreferences setting;
    SharedPreferences.Editor editor;

    String REQUEST_URL ="http://54.180.99.61:8080/auth/login";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextPW = (EditText) findViewById(R.id.editTextPW);


        checkbox = (CheckBox) findViewById(R.id.checkBox);
        setting = getSharedPreferences("setting", 0);
        editor = setting.edit();


        // 회원가입 버튼 이벤트
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 로그인 버튼 이벤트
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = editTextID.getText().toString();
                pw = editTextPW.getText().toString();

                if(id.length() == 0 || pw.length() == 0) {
                    Toast.makeText(LoginActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                } else {
                    //sendPostRequest(id, pw);
                    //TODO:: POST request, goto Main only if POST request success
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // 자동 로그인
        if (setting.getBoolean("Auto login is enabled", false)) {
            editTextID.setText(setting.getString("ID", ""));
            editTextPW.setText(setting.getString("PW", ""));
            checkbox.setChecked(true);
        }
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("Auto login is enabled", true);
                    editor.commit();
                } else {
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }
}
