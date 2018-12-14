package com.kwykfyxapp.kwykfyx.solution;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.utils.KwykFyxUtils;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class SolutionsActivity extends AppCompatActivity {

    TextView solution_problemTitleTextView, solution_problemDescriptionTextView;
    ListView solution_solutionsListView;
    ArrayList<Solution> mSolutions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);

        Intent previousIntent = getIntent();

        if (previousIntent == null) {
            KwykFyxUtils.sendToMainActivity(SolutionsActivity.this, MainActivity.class);
        } else if (previousIntent.getIntExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_TYPE", -1) == -1) {
            Toast.makeText(SolutionsActivity.this, "Problem type invalid", Toast.LENGTH_SHORT).show();
            KwykFyxUtils.sendToMainActivity(SolutionsActivity.this, MainActivity.class);
        } else {
            int problemType = previousIntent.getIntExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_TYPE", -1);

            final String problemID = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_ID");

            String problemTitle = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_TITLE");

            String problemDesc = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity.PROBLEM_DESCRIPTION");

            solution_solutionsListView = findViewById(R.id.solution_solutionsListView);

            try {
                mSolutions = SolutionHelper.getAllSolutions(SolutionsActivity.this, problemID, problemType);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            solution_problemTitleTextView = findViewById(R.id.solution_problemTitleTextView);
            solution_problemDescriptionTextView = findViewById(R.id.solution_problemDescriptionTextView);

            CustomSolutionsListViewAdapter customSolutionsListViewAdapter = new CustomSolutionsListViewAdapter();

            solution_solutionsListView.setAdapter(customSolutionsListViewAdapter);
            customSolutionsListViewAdapter.notifyDataSetChanged();

            solution_solutionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent toSpecificSolutionActivity = new Intent(SolutionsActivity.this, SpecificSolutionActivity.class);

                    toSpecificSolutionActivity.putExtra("referrer", "SolutionsActivity");

                    toSpecificSolutionActivity.putExtra("com.kwykfyxapp.kwykfyx.solution.SOLUTION_TITLE", mSolutions.get(i).getSolutionName());
                    toSpecificSolutionActivity.putExtra("com.kwykfyxapp.kwykfyx.solution.SOLUTION_DESC", mSolutions.get(i).getSolutionDescription());
                    toSpecificSolutionActivity.putExtra("com.kwykfyxapp.kwykfyx.solution.SOLUTION_FULL_TEXT", mSolutions.get(i).getSolutionBody());
                    toSpecificSolutionActivity.putExtra("com.kwykfyxapp.kwykfyx.solution.SOLUTION_PROBLEM_ADDRESSED_ID", problemID);

                    startActivity(toSpecificSolutionActivity);
                }
            });

            solution_problemTitleTextView.setText(problemTitle);

            solution_problemDescriptionTextView.setText(problemDesc);
        }
    }

    class CustomSolutionsListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mSolutions.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.search_results_layout, null);

            TextView customLayout_titleTextView = view.findViewById(R.id.customLayout_titleTextView);
            customLayout_titleTextView.setText(mSolutions.get(i).getSolutionName());

            TextView customLayout_descTextView = view.findViewById(R.id.customLayout_descTextView);
            customLayout_descTextView.setText(mSolutions.get(i).getSolutionDescription());

            return view;
        }
    }
}
