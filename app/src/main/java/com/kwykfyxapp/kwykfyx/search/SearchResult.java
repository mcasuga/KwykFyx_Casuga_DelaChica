package com.kwykfyxapp.kwykfyx.search;

import com.kwykfyxapp.kwykfyx.solution.Solution;

public class SearchResult {
    String searchTitle;
    String searchDescription;
    String searchFullText;
    String searchProblemAddressed;

    public SearchResult(String searchTitle, String searchDescription, String searchFullText, String searchProblemAddressed) {
        this.searchTitle = searchTitle;
        this.searchDescription = searchDescription;
        this.searchFullText = searchFullText;
        this.searchProblemAddressed = searchProblemAddressed;
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

    public String getSearchFullText() {
        return searchFullText;
    }

    public void setSearchFullText(String searchFullText) {
        this.searchFullText = searchFullText;
    }

    public String getSearchProblemAddressed() {
        return searchProblemAddressed;
    }

    public void setSearchProblemAddressed(String searchProblemAddressed) {
        this.searchProblemAddressed = searchProblemAddressed;
    }
}
