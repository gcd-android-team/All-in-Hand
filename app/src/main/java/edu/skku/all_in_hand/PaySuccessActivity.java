package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaySuccessActivity extends AppCompatActivity {

    Button gohome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysuccess);

        gohome=findViewById(R.id.gotohome);

        gohome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaySuccessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
