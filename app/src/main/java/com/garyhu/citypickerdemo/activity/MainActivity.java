package com.garyhu.citypickerdemo.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.garyhu.citypickerdemo.fragment.MyFragment;
import com.garyhu.citypickerdemo.R;

import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

public class MainActivity extends AppCompatActivity implements CityPickerListener{

    private CityPicker cityPicker;
    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        cityPicker = new CityPicker(MainActivity.this,this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MyFragment f = new MyFragment();
        ft.add(R.id.frame_layout,f);
        ft.commit();
    }

    @Override
    public void getCity(String name) {
        tv.setVisibility(View.VISIBLE);
        tv.setText(name);
    }

    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()){
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }

}
