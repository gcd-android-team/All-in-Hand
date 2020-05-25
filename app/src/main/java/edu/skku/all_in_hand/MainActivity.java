package edu.skku.all_in_hand;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;

    private FloatingActionButton fab_main, fab_sub1;
    private Animation fab_open, fab_close;
    private boolean isFabOpen = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);

        fab_main = (FloatingActionButton) findViewById(R.id.fab_main);
        fab_sub1 = (FloatingActionButton) findViewById(R.id.fab_sub1);

        fab_main.setOnClickListener(this);
        fab_sub1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_main:
                toggleFab();
                break;
            case R.id.fab_sub1:
                toggleFab();
                Toast.makeText(this, "Goto Mypage - 개발하장", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void toggleFab() {
        if (isFabOpen) {
            fab_main.setImageResource(R.drawable.ic_more);
            fab_sub1.startAnimation(fab_close);
            fab_sub1.setClickable(false);
            isFabOpen = false;
        } else {
            fab_main.setImageResource(R.drawable.ic_close);
            fab_sub1.startAnimation(fab_open);
            fab_sub1.setClickable(true);
            isFabOpen = true;
        }
    }
}
