package com.kwykfyxapp.kwykfyx.solution;

public class Solution {
    String solutionName;
    String solutionBody;

    public Solution(String solutionName, String solutionBody) {
        this.solutionName = solutionName;
        this.solutionBody = solutionBody;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionBody() {
        return solutionBody;
    }

    public void setSolutionBody(String solutionBody) {
        this.solutionBody = solutionBody;
    }
}
