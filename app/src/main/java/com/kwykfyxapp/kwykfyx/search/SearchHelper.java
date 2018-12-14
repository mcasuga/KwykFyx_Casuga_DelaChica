package com.kwykfyxapp.kwykfyx.search;

import android.content.Context;
import android.support.annotation.NonNull;

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

public class SearchHelper {
    public static ArrayList<SearchResult> getSearchResults(Context context) throws IOException, ParserConfigurationException, SAXException {
        ArrayList<SearchResult> returnArrayList = new ArrayList<>();

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

                NodeList solutionNodeList = currentCategoryElement.getElementsByTagName("solution");

                for (int j = 0; j < solutionNodeList.getLength(); j++) {
                    Node currentSolutionNode = solutionNodeList.item(j);

                    if (currentSolutionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element currentSolutionElement = (Element) currentSolutionNode;

                        String searchProblemAddressedID = currentSolutionElement.getAttribute("problemaddressed");
                        String searchTitle = getValue("solution_title", currentSolutionElement);
                        String searchDescription = getValue("solution_description", currentSolutionElement);
                        String searchFullText = getValue("full_text", currentSolutionElement);

                        if (searchProblemAddressedID != null && searchTitle != null && searchDescription != null && searchFullText != null) {
                            returnArrayList.add(new SearchResult(searchTitle, searchDescription, searchFullText, searchProblemAddressedID));
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
