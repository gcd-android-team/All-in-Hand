package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowMenuInfoActivity extends AppCompatActivity {

    ImageView menu_photo;
    TextView menu_name, menu_rate, menu_desc;
    RecyclerView recyclerView;
    TextView quantity, price;
    Button order, plus, minus;
    int menuprice=0; //메뉴 원래 가격
    int optionprice=0; //옵션 가격 합
    int sum=0;// 메뉴 원래 가격+옵션 가격 합 (수량은 고려 안함)
    int total_price=0;
    int input_quantity=1;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmenuinfo);
        intent = getIntent();
        menuprice = intent.getIntExtra("price", 0);
        menuprice = 10000;
        sum = menuprice;
        //TODO:: menu_id도 intent에서 받아서 데베에서 해당 메뉴 옵션 리스트 받아오기

        menu_desc = findViewById(R.id.menu_desc);
        menu_name = findViewById(R.id.menu_name);
        menu_photo = findViewById(R.id.menu_photo);
        menu_rate = findViewById(R.id.menu_rate);
        price = findViewById(R.id.price);
        order = findViewById(R.id.order);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        quantity = findViewById(R.id.quantity);

        menu_photo.setImageDrawable(getResources().getDrawable(R.drawable.miyeok));
        menu_name.setText("가자미 미역국");
        menu_rate.setText("4.0");
        menu_desc.setText("당일 잡은 가자미 한마리가 통째로! 기력 회복에 딱!\n리뷰 3000개가 증명하는 맛!");
        price.setText("10000원");



        ArrayList<MenuOption> optionlist = new ArrayList<>();
        for(int i=0;i<10;i++){
            MenuOption a = new MenuOption("test"+i, i*1000);
            optionlist.add(a);
        }

        RecyclerView recyclerView = findViewById(R.id.optionrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenuOptionAdapter adapter = new MenuOptionAdapter(optionlist);
        recyclerView.setAdapter(adapter);


        minus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(input_quantity<=1){
                    Toast.makeText(ShowMenuInfoActivity.this, "더 이상 수량을 줄일 수 없습니다. ", Toast.LENGTH_SHORT).show();
                }else{
                    input_quantity--;
                    total_price = sum * input_quantity;
                    price.setText(total_price+"원");
                    quantity.setText(Integer.toString(input_quantity));
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                input_quantity++;
                total_price = sum * input_quantity;
                price.setText(total_price+"원");
                quantity.setText(Integer.toString(input_quantity));
            }
        });

        order.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowMenuInfoActivity.this, PaymentActivity.class);
                intent.putExtra("price", total_price);
                startActivity(intent);
            }
        });


    }
}
