package com.kwykfyxapp.kwykfyx.search;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ConstraintLayout search_rootConstraintLayout;
    private SearchView search_searchQuerySearchView;
    private ListView search_searchResultsListView;

    private ArrayList<SearchResult> mSearchResults, mFilteredSeachResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent mainActivityIntent = getIntent();

        if (mainActivityIntent == null) {
            sendToMainActivity();
        } else {
            search_rootConstraintLayout = findViewById(R.id.search_rootConstraintLayout);
            search_searchQuerySearchView = findViewById(R.id.search_searchQuerySearchView);
            search_searchResultsListView = findViewById(R.id.search_searchResultsListView);

            String searchQuery = mainActivityIntent.getStringExtra("com.kwykfyxapp.kwykfyx.MainActivity.SEARCH_QUERY");

            if (TextUtils.isEmpty(searchQuery) || searchQuery == null) {
                sendToMainActivity();
            } else {
                try {
                    mSearchResults = SearchHelper.getSearchResults(SearchActivity.this);
                    mFilteredSeachResults = mSearchResults;
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter();
                search_searchResultsListView.setAdapter(customListViewAdapter);
                search_rootConstraintLayout.requestFocus();

                search_searchQuerySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        customListViewAdapter.getFilter().filter(s);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        customListViewAdapter.getFilter().filter(s);
                        return false;
                    }
                });

                search_searchQuerySearchView.setQuery(searchQuery, true);
                search_searchQuerySearchView.setIconified(false);
            }
        }
    }

    private void sendToMainActivity() {
        startActivity(new Intent(SearchActivity.this, MainActivity.class));
        Toast.makeText(SearchActivity.this, "Invalid Activity Invocation", Toast.LENGTH_SHORT).show();
    }

    // Inner class for the ListView Adapter
    class CustomListViewAdapter extends BaseAdapter implements Filterable {
        @Override
        public int getCount() {
            return mFilteredSeachResults.size();
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

            TextView searchRes_titleTextView = view.findViewById(R.id.searchRes_titleTextView);
            searchRes_titleTextView.setText(mFilteredSeachResults.get(i).getSearchTitle());

            TextView searchRes_descTextView = view.findViewById(R.id.searchRes_descTextView);
            searchRes_descTextView.setText(mFilteredSeachResults.get(i).getSearchDescription());

            return view;
        }

        @Override
        public Filter getFilter() {

            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        results.count = mSearchResults.size();
                        results.values = mSearchResults;
                    } else {
                        List<SearchResult> resultsData = new ArrayList<>();
                        String searchStr = constraint.toString().toUpperCase();
                        for (SearchResult searchResult : mSearchResults)
                            if (searchResult.getSearchTitle().toUpperCase().contains(searchStr))
                                resultsData.add(searchResult);
                        results.count = resultsData.size();
                        results.values = resultsData;
                    }
                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    mFilteredSeachResults = (ArrayList<SearchResult>) results.values;
                    notifyDataSetChanged();
                }
            };
        }
    }
}
