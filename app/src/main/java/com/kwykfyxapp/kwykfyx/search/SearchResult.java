package com.kwykfyxapp.kwykfyx.search;

public class SearchResult {
    String searchTitle;
    String searchDescription;
    String searchFullText;
    String searchProblemAddressedID;

    public SearchResult(String searchTitle, String searchDescription, String searchFullText, String searchProblemAddressedID) {
        this.searchTitle = searchTitle;
        this.searchDescription = searchDescription;
        this.searchFullText = searchFullText;
        this.searchProblemAddressedID = searchProblemAddressedID;
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

    public String getSearchProblemAddressedID() {
        return searchProblemAddressedID;
    }

    public void setSearchProblemAddressedID(String searchProblemAddressedID) {
        this.searchProblemAddressedID = searchProblemAddressedID;
    }
}
