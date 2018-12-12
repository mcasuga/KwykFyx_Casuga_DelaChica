package com.kwykfyxapp.kwykfyx.browse.problems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.utils.KwykFyxUtils;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

public class ProblemsActivity extends AppCompatActivity {

    public static final int CAR_PROBLEMS = 0;
    public static final int AIRCONDITIONING_PROBLEMS = 1;
    public static final int BATTERY_CHARGING_PROBLEMS = 2;
    public static final int BRAKE_ANTILOCK_BRAKE_PROBLEMS = 3;
    public static final int ENGINE_COOLING_PROBLEMS = 4;
    public static final int EMISSION_CONTROL_PROBLEMS = 5;
    public static final int ENGINE_DIAGNOSIS = 6;
    public static final int ENGINE_SENSOR_DIAGNOSIS = 7;

    private ArrayList<Problem> mProblems;

    private ListView problems_problemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);

        Intent previousIntent = getIntent();

        if (previousIntent == null) {
            KwykFyxUtils.sendToMainActivity(ProblemsActivity.this, MainActivity.class);
        } else {
            problems_problemsListView = findViewById(R.id.problems_problemsListView);

            try {
                mProblems = ProblemsHelper.getAllProblems(ProblemsActivity.this, previousIntent.getIntExtra("com.kwykfyxapp.kwykfyx.browse.BrowseCategoriesActivity" +
                        ".PROBLEM_TYPE" +
                            "", -1));
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

            CustomBrowseListViewAdapter customBrowseListViewAdapter = new CustomBrowseListViewAdapter();
            problems_problemsListView.setAdapter(customBrowseListViewAdapter);
            customBrowseListViewAdapter.notifyDataSetChanged();
        }
    }

    class CustomBrowseListViewAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mProblems.size();
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
            customLayout_titleTextView.setText(mProblems.get(i).getProblemTitle());

            TextView customLayout_descTextView = view.findViewById(R.id.customLayout_descTextView);
            customLayout_descTextView.setText(mProblems.get(i).getProblemDescription());

            return view;
        }
    }
}
