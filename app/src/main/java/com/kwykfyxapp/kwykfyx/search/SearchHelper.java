package com.kwykfyxapp.kwykfyx.search;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;

import com.kwykfyxapp.kwykfyx.kwykfyx.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class SearchHelper {
    public static ArrayList<SearchResult> getSearchResults(Context context) throws XmlPullParserException, IOException {
        ArrayList<SearchResult> returnArrayList = new ArrayList<>();

        XmlPullParser xmlPullParser = context.getResources().getXml(R.xml.solutions);

        String title = "", desc = "", fullText = "", probAddressed = "";
        while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(fullText)) {
                returnArrayList.add(new SearchResult(title, desc, fullText, probAddressed));
                title = "";
                desc = "";
                fullText = "";
                probAddressed = "";
            }

            if (xmlPullParser.getEventType() == XmlPullParser.START_TAG) {
                if (xmlPullParser.getName().equalsIgnoreCase("solution_title")) {
                    xmlPullParser.next();
                    title = xmlPullParser.getText();
                } else if (xmlPullParser.getName().equalsIgnoreCase("solution_description")) {
                    xmlPullParser.next();
                    desc = xmlPullParser.getText();
                } else if (xmlPullParser.getName().equalsIgnoreCase("full_text")) {
                    xmlPullParser.next();
                    fullText = xmlPullParser.getText();
                } else if (xmlPullParser.getName().equalsIgnoreCase("problem_addressed")) {
                    xmlPullParser.next();
                    probAddressed = xmlPullParser.getText();
                }
            }
            xmlPullParser.next();
        }

        return returnArrayList;
    }
}
