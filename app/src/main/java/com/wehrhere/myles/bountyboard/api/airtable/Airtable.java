package com.wehrhere.myles.bountyboard.api.airtable;

import android.support.annotation.Nullable;
import android.util.Log;

import com.wehrhere.myles.bountyboard.api.HttpHandler;

import org.jetbrains.annotations.Contract;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by mwehr on 9/4/17.
 */

public class Airtable extends HttpHandler {

    /** Class tag */
    private static final String TAG = Airtable.class.getSimpleName();

    /** Static Config Values */
    private static final String HOST = "https://api.airtable.com";
    private static final String API_VERSION = "v0";
    private static final String API_KEY = "keyROidbvfRqR2U3B";
    private static final String APP_ID = "appkGjOvVXJRWUSRG";
    private static final String ENCODING = "UTF-8";

    /** Static strings */
    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final String FILTERS = "?&filterByFormula=AND(";

    /** Private values */
    private String tableName;
    private String recordId;
    private ArrayList<String[]> filters = new ArrayList();

    /** Public setters */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void addFilter(String fieldName, String value) {
        String[] filter = new String[2];
        filter[0] = fieldName;
        filter[1] = value;
        filters.add(filter);
    }

    @Override @Nullable
    protected URL getUrl() {
        String urlString = HOST + "/"
                + API_VERSION + "/"
                + APP_ID + "/";
        if (tableName != null) {
            urlString += tableName + "/";
        }
        if (recordId != null) {
            urlString += recordId + "/";
        }
        urlString += prepareEncodedFilters();
        try {
            URL url = new URL(urlString);
            return url;
        } catch (MalformedURLException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    private String prepareEncodedFilters() {
        int size = filters.size();
        if (size > 0) {
            String filterString = "";
            int counter = 0;
            for (String[] filter : filters) {
                counter ++;
                filterString += "{"
                        + filter[0]
                        + "}=\""
                        + filter [1]
                        + "\"";
                if (counter != size) {
                    filterString += ",";
                }
            }
            try {
                filterString = URLEncoder.encode(filterString, ENCODING);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
                return "";
            }
            return FILTERS
                    + filterString
                    + ")";
        } else {
            return "";
        }
    }

    public String getRecords() {
        callType = CallType.GET;
        return super.doGet();
    }

    public String addRecord(Record record) {
        callType = CallType.POST;
        return super.doPost(record.prepareRecordForOutput());
    }

    public String updateRecord(Record record) {
        callType = CallType.PUT;
        this.recordId = record.getId();
        return super.doPost(record.prepareRecordForOutput());
    }

    public String deleteRecord(Record record) {
        callType = CallType.DELETE;
        this.recordId = record.getId();
        return super.doGet();
    }

    @Override
    protected void setAuthorization(HttpURLConnection conn) {
        conn.setRequestProperty(AUTHORIZATION, getBearerToken());
    }

    @Contract(pure = true)
    private static String getBearerToken() {
        return BEARER + API_KEY;
    }

}
