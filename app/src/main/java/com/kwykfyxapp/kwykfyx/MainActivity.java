package com.kwykfyxapp.kwykfyx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.search.SearchActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button main_browseButton, main_findMechanicButton, main_searchButton;
    private EditText main_searchQueryEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_browseButton = findViewById(R.id.main_browseButton);
        main_browseButton.setOnClickListener(this);

        main_findMechanicButton = findViewById(R.id.main_findMechanicButton);
        main_findMechanicButton.setOnClickListener(this);

        main_searchButton = findViewById(R.id.main_searchButton);
        main_searchButton.setOnClickListener(this);

        main_searchQueryEditText = findViewById(R.id.main_searchQueryEditText);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_searchButton:
                String searchQuery = main_searchQueryEditText.getText().toString();

                if (TextUtils.isEmpty(searchQuery)) {
                    Toast.makeText(MainActivity.this, "Search query is empty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent toSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                    toSearchActivity.putExtra("com.kwykfyxapp.kwykfyx.MainActivity.SEARCH_QUERY", searchQuery);
                    startActivity(toSearchActivity);
                }
                break;
            case R.id.main_findMechanicButton:
                Intent toFindMechanicActivity = new Intent(MainActivity.this, FindMechanicActivity.class);
                break;
            case R.id.main_browseButton:
                Intent toBrowseCategoriesActivity = new Intent(MainActivity.this, BrowseCategoriesActivity.class);
                break;
        }
    }
}
