package com.kwykfyxapp.kwykfyx.solution;

public class Solution {
    String solutionName;
    String solutionDescription;
    String solutionBody;

    public Solution(String solutionName, String solutionDescription, String solutionBody) {
        this.solutionName = solutionName;
        this.solutionDescription = solutionDescription;
        this.solutionBody = solutionBody;
    }

    public String getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public String getSolutionBody() {
        return solutionBody;
    }

    public void setSolutionBody(String solutionBody) {
        this.solutionBody = solutionBody;
    }
}
