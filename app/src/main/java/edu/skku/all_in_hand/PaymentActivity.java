package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    Button payment;
    TextView orderlist;
    EditText request;
    Intent intent;
    int total_price;
    String request_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        payment = findViewById(R.id.kakaopay);
        orderlist = findViewById(R.id.orderlist);
        request = findViewById(R.id.request);

        intent = getIntent();
        total_price = intent.getIntExtra("price", 0);
        payment.setText(total_price+"원 카카오페이로 결제하기");
        orderlist.setText("주문내역..추후 추가");

        request_content = request.getText().toString();



    }
}
