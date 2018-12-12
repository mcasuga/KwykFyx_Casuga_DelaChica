package com.kwykfyxapp.kwykfyx.search;

public class SearchResult {
    String searchTitle;
    String searchDescription;

    public SearchResult(String searchTitle, String searchDescription) {
        this.searchTitle = searchTitle;
        this.searchDescription = searchDescription;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setSearchTitle(String searchTitle) {
        this.searchTitle = searchTitle;
    }

    public String getSearchDescription() {
        return searchDescription;
    }

    public void setSearchDescription(String searchDescription) {
        this.searchDescription = searchDescription;
    }
}
