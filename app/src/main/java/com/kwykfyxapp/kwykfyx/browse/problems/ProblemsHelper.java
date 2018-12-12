package com.kwykfyxapp.kwykfyx.browse.problems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.kwykfyxapp.kwykfyx.kwykfyx.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ProblemsHelper {
    public static ArrayList<Problem> getAllProblems(Context context, int categoryType) throws XmlPullParserException, IOException, ParserConfigurationException,
            SAXException {
        ArrayList<Problem> returnArrayList = new ArrayList<>();

        String categoryDesired = "";

        switch (categoryType) {
            case ProblemsActivity.CAR_PROBLEMS:
                categoryDesired = "car_problems_and_repair";
                break;
            case ProblemsActivity.AIRCONDITIONING_PROBLEMS:
                categoryDesired = "airconditioning";
                break;
            case ProblemsActivity.BATTERY_CHARGING_PROBLEMS:
                categoryDesired = "battery_charging";
                break;
            case ProblemsActivity.BRAKE_ANTILOCK_BRAKE_PROBLEMS:
                categoryDesired = "brake_and_antilock";
                break;
            case ProblemsActivity.ENGINE_COOLING_PROBLEMS:
                categoryDesired = "engine_cooling_system";
                break;
            case ProblemsActivity.EMISSION_CONTROL_PROBLEMS:
                categoryDesired = "emission_control";
                break;
            case ProblemsActivity.ENGINE_DIAGNOSIS:
                categoryDesired = "engine_diagnosis";
                break;
            case ProblemsActivity.ENGINE_SENSOR_DIAGNOSIS:
                categoryDesired = "engine_sensor";
                break;
        }

        InputStream inputStream = context.getResources().openRawResource(R.raw.solutions);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        Element rootElement = document.getDocumentElement();
        rootElement.normalize();

        NodeList categoryNodeList = document.getElementsByTagName("category");

        for (int i = 0; i < categoryNodeList.getLength(); i++) {
            Node currentCategoryNode = categoryNodeList.item(i);

            if (currentCategoryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currentCategoryElement = (Element) currentCategoryNode;

                if (categoryDesired.equals(currentCategoryElement.getAttribute("type"))) {

                    NodeList solutionNodeList = currentCategoryElement.getElementsByTagName("solution");

                    for (int j = 0; j < solutionNodeList.getLength(); j++) {
                        Node currentSolutionNode = solutionNodeList.item(j);

                        if (currentSolutionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element currentSolutionElement = (Element) currentSolutionNode;

                            String problemTitle = getValue("problem_addressed", currentSolutionElement);
                            String problemDescription = getValue("problem_description", currentSolutionElement);

                            if (problemTitle != null && problemDescription != null) {
                                returnArrayList.add(new Problem(problemTitle, problemDescription));
                            }
                        }
                    }
                }
            }
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
