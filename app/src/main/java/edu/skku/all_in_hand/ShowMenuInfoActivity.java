package edu.skku.all_in_hand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
    Button order;
    int menuprice;
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmenuinfo);
        intent = getIntent();
        menuprice = Integer.parseInt(intent.getStringExtra("price"));

        getIntent().getPackage();

        menu_photo.setImageDrawable(getResources().getDrawable(R.drawable.miyeok));
        menu_name.setText("가자미 미역국");
        menu_rate.setText("4.0");
        menu_desc.setText("당일 잡은 가자미 한마리가 통째로! 기력 회복에 딱!\n리뷰 3000개가 증명하는 맛!");
        price.setText(menuprice+"원");


        ArrayList<MenuItem> menulist = new ArrayList<>();
        for(int i=0;i<10;i++){
            MenuItem a = new MenuItem("test"+i, i*1000+"원", "descTEST"+i);
            menulist.add(a);
        }

        RecyclerView recyclerView = findViewById(R.id.menurecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenuItemAdapter adapter = new MenuItemAdapter(menulist);
        recyclerView.setAdapter(adapter);





    }
}
