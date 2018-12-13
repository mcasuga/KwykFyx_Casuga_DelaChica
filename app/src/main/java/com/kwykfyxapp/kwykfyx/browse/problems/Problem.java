package com.kwykfyxapp.kwykfyx.browse.problems;

public class Problem {
    String problemID;
    String problemTitle;
    String problemDescription;

    public Problem() {

    }

    public Problem(String problemID, String problemTitle, String problemDescription) {
        this.problemID = problemID;
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getProblemID() {
        return problemID;
    }

    public void setProblemID(String problemID) {
        this.problemID = problemID;
    }
}
