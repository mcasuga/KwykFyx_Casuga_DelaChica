package com.kwykfyxapp.kwykfyx.solution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.utils.KwykFyxUtils;

public class SolutionsActivity extends AppCompatActivity {

    TextView solution_problemTitleTextView, solution_problemDescriptionTextView;
    ListView solution_solutionsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Intent previousIntent = getIntent();

        if (previousIntent == null) {
            KwykFyxUtils.sendToMainActivity(SolutionsActivity.this, MainActivity.class);
        } else {
            solution_problemTitleTextView = findViewById(R.id.solution_problemTitleTextView);
            solution_problemDescriptionTextView = findViewById(R.id.solution_problemDescriptionTextView);

            solution_solutionsListView = findViewById(R.id.solution_solutionsListView);

            solution_problemTitleTextView.setText(previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_TITLE"));

            solution_problemDescriptionTextView.setText(previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_DESCRIPTION"));
        }
    }

    class CustomSolutionsListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.search_results_layout, null);

            //            TextView customLayout_titleTextView = view.findViewById(R.id.customLayout_titleTextView);
            //            customLayout_titleTextView.setText(mProblems.get(i).getProblemTitle());
            //
            //            TextView customLayout_descTextView = view.findViewById(R.id.customLayout_descTextView);
            //            customLayout_descTextView.setText(mProblems.get(i).getProblemDescription());

            return null;
        }
    }
}
