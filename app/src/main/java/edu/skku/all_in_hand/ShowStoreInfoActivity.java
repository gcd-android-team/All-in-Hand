package edu.skku.all_in_hand;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowStoreInfoActivity extends AppCompatActivity {

    ImageView store_photo, star1, star2, star3, star4, star5;
    TextView store_name, store_rate, store_desc;
    RecyclerView recyclerView;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstoreinfo);

        store_desc=findViewById(R.id.store_desc);
        store_rate=findViewById(R.id.store_rate);
        store_name=findViewById(R.id.store_name);
        store_photo=findViewById(R.id.store_photo);

        star1=findViewById(R.id.star1);
        star2=findViewById(R.id.star2);
        star3=findViewById(R.id.star3);
        star4=findViewById(R.id.star4);
        star5=findViewById(R.id.star5);

        store_name.setText("오복미역");
        store_rate.setText("4.7");
        store_desc.setText("대한민국 대표음식\n해산물 미역국 #몸보신 #가자기 #전복 #떡갈비\n항상 정성으로 요리합니다.");
        store_photo.setImageDrawable(getResources().getDrawable(R.drawable.obok));
//        store_photo.setBackground(new ShapeDrawable(new OvalShape()));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            store_photo.setClipToOutline(true);
//        }

        ArrayList<MenuItem> menulist = new ArrayList<>();
        /*
        for(int i=0;i<10;i++){
            MenuItem a = new MenuItem("test"+i, i*1000+"원", "descTEST"+i);
            menulist.add(a);
        }
        */
        MenuItem a = new MenuItem("가자미 미역국", 10000, "당일 잡은 가자미 한마리가 통째로! 기력 회복에 딱");
        menulist.add(a);
        MenuItem a2 = new MenuItem("(특)가자미 미역국", 14000, "가자미 두마리");
        menulist.add(a2);
        MenuItem a3 = new MenuItem("전복 미역국", 12000, "깊게 우러난 전복 육수와 통전복");
        menulist.add(a3);
        MenuItem a4 = new MenuItem("황태 미역국", 9000, "해장에도 좋아요");
        menulist.add(a4);
        MenuItem a7 = new MenuItem("소고기 미역국", 10000, "한우로 우러낸 육수, 깊은 맛");
        menulist.add(a7);
        MenuItem a5 = new MenuItem("떡갈비", 12000, "매콤달콤 매장에서 직접 빚은 떡갈비");
        menulist.add(a5);
        MenuItem a6 = new MenuItem("톳무침", 3000, "오복미역의 베스트 사이드메뉴");
        menulist.add(a6);
        MenuItem a8 = new MenuItem("[A SET] 가자미 미역국 + 떡갈비", 20000, "2000원 할인");
        menulist.add(a8);
        MenuItem a9 = new MenuItem("[B SET] (특)가자미 미역국 + 전복 미역국", 24000, "2000원 할인, 음료수 서비스");
        menulist.add(a9);


        recyclerView = findViewById(R.id.menurecycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MenuItemAdapter adapter = new MenuItemAdapter(menulist);
        adapter.setOnItemClickListener(new MenuItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(ShowStoreInfoActivity.this, ShowMenuInfoActivity.class);
                intent.putExtra("menu_id", 5);
                intent.putExtra("price", 10000);
                startActivity(intent);
                finish();
            }
        });
        recyclerView.setAdapter(adapter);

    }


    private AdapterView.OnItemClickListener mListener = null;


}
