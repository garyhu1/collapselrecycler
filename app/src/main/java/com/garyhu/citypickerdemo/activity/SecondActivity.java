package com.garyhu.citypickerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.garyhu.citypickerdemo.R;

/**
 * 作者： garyhu.
 * 时间： 2016/12/8.
 */

public class SecondActivity extends AppCompatActivity{

    private Button btn;
    private Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        btn = ((Button) findViewById(R.id.btn));
        nextBtn = ((Button) findViewById(R.id.next_btn));
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SecondActivity.this,MyFragment.class);
//                intent.putExtra("data","你好！");
//                setResult(2,intent);
//                finish();
//            }
//        });
//
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_pick_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, PickCityActivity.class));
            }
        });
        findViewById(R.id.btn_pick_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, PickContactActivity.class));
            }
        });
    }
}
