package com.kwykfyxapp.kwykfyx.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.kwykfyxapp.kwykfyx.MainActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.solution.SpecificSolutionActivity;
import com.kwykfyxapp.kwykfyx.utils.KwykFyxUtils;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

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
            KwykFyxUtils.sendToMainActivity(SearchActivity.this, MainActivity.class);
        } else {
            search_rootConstraintLayout = findViewById(R.id.search_rootConstraintLayout);
            search_searchQuerySearchView = findViewById(R.id.search_searchQuerySearchView);
            search_searchResultsListView = findViewById(R.id.search_searchResultsListView);

            // Get Search query from previous activity
            String searchQuery = mainActivityIntent.getStringExtra("com.kwykfyxapp.kwykfyx.MainActivity.SEARCH_QUERY");

            if (TextUtils.isEmpty(searchQuery) || searchQuery == null) {
                KwykFyxUtils.sendToMainActivity(SearchActivity.this, MainActivity.class);
            } else {
                try {
                    // Get search hits on res/xml/solutions.xml file
                    mSearchResults = SearchHelper.getSearchResults(SearchActivity.this);
                    mFilteredSeachResults = mSearchResults;
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                // Custom adapter for custom views in ListView
                final CustomSearchListViewAdapter customSearchListViewAdapter = new CustomSearchListViewAdapter();

                // Set custom adapter to ListView
                search_searchResultsListView.setAdapter(customSearchListViewAdapter);

                // Hide the soft keyboard
                search_rootConstraintLayout.requestFocus();

                // Filter ListView on SearchView submit and text change
                search_searchQuerySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        customSearchListViewAdapter.getFilter().filter(s);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        customSearchListViewAdapter.getFilter().filter(s);
                        return false;
                    }
                });

                search_searchQuerySearchView.setQuery(searchQuery, true);
                search_searchQuerySearchView.setIconified(false);

                search_searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent toSearchSpecificSolution = new Intent(SearchActivity.this, SpecificSolutionActivity.class);

                        //                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_TITLE",
                        // mFilteredSeachResults.get(i).getSearchTitle());
                        //
                        //                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_PROBLEM_ADDRESSED",
                        // mFilteredSeachResults.get(i)
                        //                                .getSearchProblemAddressedID());
                        //
                        //                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_FULL_TEXT", mFilteredSeachResults.get(i).getSearchFullText());

                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_TITLE", mFilteredSeachResults.get(i).getSearchTitle());

                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_DESC", mFilteredSeachResults.get(i).getSearchDescription());

                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_FULL_TEXT", mFilteredSeachResults.get(i)
                                .getSearchFullText());

                        toSearchSpecificSolution.putExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_PROBLEM_ADDRESSED_ID", mFilteredSeachResults.get(i)
                                .getSearchProblemAddressedID());

                        startActivity(toSearchSpecificSolution);
                    }
                });
            }
        }
    }

    // Inner class for the ListView Adapter
    class CustomSearchListViewAdapter extends BaseAdapter implements Filterable {
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

            TextView customLayout_titleTextView = view.findViewById(R.id.customLayout_titleTextView);
            customLayout_titleTextView.setText(mFilteredSeachResults.get(i).getSearchTitle());

            TextView customLayout_descTextView = view.findViewById(R.id.customLayout_descTextView);
            customLayout_descTextView.setText(mFilteredSeachResults.get(i).getSearchDescription());

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
