package com.kwykfyxapp.kwykfyx.solution;

import android.content.Context;
import android.support.annotation.NonNull;

import com.kwykfyxapp.kwykfyx.browse.problems.ProblemsActivity;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SolutionHelper {
    public static ArrayList<Solution> getAllSolutions(Context context, String problemID, int problemType) throws ParserConfigurationException, IOException,
            SAXException {
        ArrayList<Solution> returnArrayList = new ArrayList<>();

        InputStream inputStream = context.getResources().openRawResource(R.raw.solutions);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        Element rootElement = document.getDocumentElement();
        rootElement.normalize();

        String problemTypeStr = "";

        switch (problemType) {
            case ProblemsActivity.CAR_PROBLEMS:
                problemTypeStr = "car_problems_and_repair";
                break;
            case ProblemsActivity.AIRCONDITIONING_PROBLEMS:
                problemTypeStr = "airconditioning";
                break;
            case ProblemsActivity.BATTERY_CHARGING_PROBLEMS:
                problemTypeStr = "battery_charging";
                break;
            case ProblemsActivity.BRAKE_ANTILOCK_BRAKE_PROBLEMS:
                problemTypeStr = "brake_and_antilock";
                break;
            case ProblemsActivity.ENGINE_COOLING_PROBLEMS:
                problemTypeStr = "engine_cooling_system";
                break;
            case ProblemsActivity.EMISSION_CONTROL_PROBLEMS:
                problemTypeStr = "emission_control";
                break;
            case ProblemsActivity.ENGINE_DIAGNOSIS:
                problemTypeStr = "engine_diagnosis";
                break;
            case ProblemsActivity.ENGINE_SENSOR_DIAGNOSIS:
                problemTypeStr = "engine_sensor";
                break;
        }

        NodeList categoryNodeList = document.getElementsByTagName("category");

        for (int i = 0; i < categoryNodeList.getLength(); i++) {
            Node currentCategoryNode = categoryNodeList.item(i);

            if (currentCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currentCategoryElement = (Element) currentCategoryNode;

                if (problemTypeStr.equals(((Element) currentCategoryNode).getAttribute("type"))) {
                    NodeList solutionNodeList = currentCategoryElement.getElementsByTagName("solution");

                    for (int j = 0; j < solutionNodeList.getLength(); j++) {
                        Node currentSolutionNode = solutionNodeList.item(j);

                        if (currentSolutionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element currentProblemElement = (Element) currentSolutionNode;

                            if (problemID.equals(currentProblemElement.getAttribute("problemaddressed"))) {
                                String solutionTitle = getValue("solution_title", currentProblemElement);

                                String solutionDesc = getValue("solution_description", currentProblemElement);

                                String solutionFullText = getValue("full_text", currentProblemElement);

                                returnArrayList.add(new Solution(solutionTitle, solutionDesc, solutionFullText));
                            }
                        }
                    }
                }
            }
        }

        for (Solution solution : returnArrayList) {
            System.out.println(solution.getSolutionName());
            System.out.println(solution.getSolutionDescription());
            System.out.println(solution.getSolutionBody());
            System.out.println("-------------------\n");
        }

        return returnArrayList;
    }

    private static String getValue(@NonNull String tag, @NonNull Element element) {
        if (tag != null && element != null) {
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }

        return null;
    }
}
