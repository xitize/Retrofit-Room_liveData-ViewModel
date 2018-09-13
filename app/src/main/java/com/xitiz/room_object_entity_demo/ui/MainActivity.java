package com.xitiz.room_object_entity_demo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xitiz.room_object_entity_demo.R;
import com.xitiz.room_object_entity_demo.db.entity.Response;

public class MainActivity extends AppCompatActivity {
    ResponseViewModel responseViewModel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        responseViewModel = ViewModelProviders.of(this).get(ResponseViewModel.class);
        textView = findViewById(R.id.textView_show);
        /*
         * loads the webResponse*/
        //  ResponseRepository.getWebResponseInfo();
        /*
         * loads after some changes*/
        responseViewModel.getDbResponse().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                textView.setText("database : " + response.toString());
            }
        });

  /*      responseViewModel.getWebResponse().observe(this, new Observer<Response>() {
            @Override
            public void onChanged(@Nullable Response response) {
                textView.setText("data is : " + response.toString());
            }
        });*/


    }
}
