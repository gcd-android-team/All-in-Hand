package edu.skku.all_in_hand;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowStoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstoreinfo);

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
