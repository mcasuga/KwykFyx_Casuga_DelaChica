package com.kwykfyxapp.kwykfyx.solution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.search.SearchActivity;
import com.kwykfyxapp.kwykfyx.utils.KwykFyxUtils;

public class SpecificSolutionActivity extends AppCompatActivity {

    private TextView specific_problemTitleTextView, specific_solutionTitleTextView, specific_fullTextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_solution);

        Intent previousIntent = getIntent();

        if (previousIntent == null) {
            startActivity(new Intent(SpecificSolutionActivity.this, SearchActivity.class));
            Toast.makeText(SpecificSolutionActivity.this, "Invalid Activity Invocation", Toast.LENGTH_SHORT).show();
        } else {
            String solutionTitle = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_TITLE");
            String solutionProblemAddressed = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_PROBLEM_ADDRESSED");
            String solutionFullText = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_FULL_TEXT");

            if (TextUtils.isEmpty(solutionTitle) || TextUtils.isEmpty(solutionProblemAddressed) || TextUtils.isEmpty(solutionFullText)) {
                KwykFyxUtils.sendToMainActivity(SpecificSolutionActivity.this, MainActivity.class);
            } else {
                specific_problemTitleTextView = findViewById(R.id.specific_problemTitleTextView);
                specific_problemTitleTextView.setText(solutionProblemAddressed);

                specific_solutionTitleTextView = findViewById(R.id.specific_solutionTitleTextView);
                specific_solutionTitleTextView.setText(solutionTitle);

                specific_fullTextTextView = findViewById(R.id.specific_fullTextTextView);
                specific_fullTextTextView.setText(solutionFullText);
            }
        }
    }
}
