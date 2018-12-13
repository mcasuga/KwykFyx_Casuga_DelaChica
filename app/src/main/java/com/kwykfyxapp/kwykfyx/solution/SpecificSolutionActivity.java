package com.kwykfyxapp.kwykfyx.solution;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.kwykfyxapp.kwykfyx.browse.problems.Problem;
import com.kwykfyxapp.kwykfyx.kwykfyx.R;
import com.kwykfyxapp.kwykfyx.search.SearchActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class SpecificSolutionActivity extends AppCompatActivity {

    private TextView specific_problemTitleTextView, specific_solutionTitleTextView, specific_fullTextTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_solution);

        Intent previousIntent = getIntent();

        if (previousIntent == null) {
            startActivity(new Intent(SpecificSolutionActivity.this, SearchActivity.class));
            Toast.makeText(SpecificSolutionActivity.this, "Invalid Activity Invocation", Toast.LENGTH_SHORT).show();
        } else {
            //            String solutionTitle = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_TITLE");
            //            String solutionProblemAddressed = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_PROBLEM_ADDRESSED");
            //            String solutionFullText = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_FULL_TEXT");
            //
            //            if (TextUtils.isEmpty(solutionTitle) || TextUtils.isEmpty(solutionProblemAddressed) || TextUtils.isEmpty(solutionFullText)) {
            //                KwykFyxUtils.sendToMainActivity(SpecificSolutionActivity.this, MainActivity.class);
            //            } else {
            //                specific_problemTitleTextView = findViewById(R.id.specific_problemTitleTextView);
            //                specific_problemTitleTextView.setText(solutionProblemAddressed);
            //
            //                specific_solutionTitleTextView = findViewById(R.id.specific_solutionTitleTextView);
            //                specific_solutionTitleTextView.setText(solutionTitle);
            //
            //                specific_fullTextTextView = findViewById(R.id.specific_fullTextTextView);
            //                specific_fullTextTextView.setText(solutionFullText);
            //            }

            String solutionTitle = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_TITLE");
            String solutionDesc = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_DESC");
            String solutionFullText = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_FULL_TEXT");
            String solutionProblemAddressed = previousIntent.getStringExtra("com.kwykfyxapp.kwykfyx.search.SearchActivity.SOLUTION_PROBLEM_ADDRESSED_ID");

            if (solutionTitle != null && solutionDesc != null && solutionFullText != null && solutionProblemAddressed != null) {

                specific_problemTitleTextView = findViewById(R.id.specific_problemTitleTextView);
                try {
                    specific_problemTitleTextView.setText(getProblemObject(solutionProblemAddressed).getProblemTitle());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

                specific_solutionTitleTextView = findViewById(R.id.specific_solutionTitleTextView);
                specific_solutionTitleTextView.setText(solutionTitle);

                specific_fullTextTextView = findViewById(R.id.specific_fullTextTextView);
                specific_fullTextTextView.setText(solutionFullText);
            }
        }
    }

    private Problem getProblemObject(String problemID) throws IOException, SAXException, ParserConfigurationException {
        Problem problem = new Problem();

        InputStream inputStream = getResources().openRawResource(R.raw.problems);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);

        Element rootElement = document.getDocumentElement();
        rootElement.normalize();

        NodeList problemNodeList = document.getElementsByTagName("problem");

        for (int i = 0; i < problemNodeList.getLength(); i++) {
            Node currentProblemNode = problemNodeList.item(i);

            if (currentProblemNode.getNodeType() == Node.ELEMENT_NODE) {
                Element currentProblemElement = (Element) currentProblemNode;

                if (currentProblemElement.getAttribute("id").equals(problemID)) {
                    problem.setProblemID(currentProblemElement.getAttribute("id"));
                    problem.setProblemTitle(getValue("problemTitle", currentProblemElement));
                    problem.setProblemDescription(getValue("problemDescription", currentProblemElement));
                }
            }
        }

        return problem;
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
