package com.kwykfyxapp.kwykfyx.browse.problems;

public class Problem {
    String problemTitle;
    String problemDescription;

    public Problem(String problemTitle, String problemDescription) {
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
}
