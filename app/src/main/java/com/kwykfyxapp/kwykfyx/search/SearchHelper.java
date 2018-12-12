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

        String title = "", desc = "";
        while (xmlPullParser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc)) {
                returnArrayList.add(new SearchResult(title, desc));
                title = "";
                desc = "";
            }

            if (xmlPullParser.getEventType() == XmlPullParser.START_TAG) {
                if (xmlPullParser.getName().equalsIgnoreCase("title")) {
                    xmlPullParser.next();
                    title = xmlPullParser.getText();
                } else if (xmlPullParser.getName().equalsIgnoreCase("description")) {
                    xmlPullParser.next();
                    desc = xmlPullParser.getText();
                }
            }
            xmlPullParser.next();
        }

        return returnArrayList;
    }
}
