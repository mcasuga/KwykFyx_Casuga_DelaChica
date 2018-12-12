package com.kwykfyxapp.kwykfyx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kwykfyxapp.kwykfyx.kwykfyx.R;

public class BrowseCategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_categories);

        TextView firstRowLeft = findViewById(R.id.firstRowLeft);

        firstRowLeft.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=  new Intent(BrowseCategoriesActivity.this, ProblemsActivity.class);
                startActivity(i);
            }
        });
    }
}
