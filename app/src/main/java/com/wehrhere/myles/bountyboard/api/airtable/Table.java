package com.wehrhere.myles.bountyboard.api.airtable;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mwehr on 8/23/17.
 */

public abstract class Table {

    protected static String DELETED = "deleted";
    protected static String RECORDS = "records";

    public void addRecord(Record record) {
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName(getTableName());
        try {
            String json = httpHandler.addRecord(record);
            if (json != null) {
                buildRecord(new JSONObject(json));
            }
        } catch (JSONException e) {
            Log.e(getTag(), e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public void updateRecord(Record record) {
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName(getTableName());
        try {
            String json = httpHandler.updateRecord(record);
            if (json != null) {
                buildRecord(new JSONObject(json));
            }
        } catch (JSONException e) {
            Log.e(getTag(), e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public void deleteRecord(Record record) {
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName(getTableName());
        try {
            JSONObject response = new JSONObject(httpHandler.deleteRecord(record));
            if (response.getBoolean(DELETED)) {
                removeRecord(record);
            }
        } catch (JSONException e) {
            Log.e(getTag(), e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    protected void populate() {
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName(getTableName());
        try {
            JSONObject recordsObject = new JSONObject(httpHandler.getRecords());
            JSONArray recordsArray = recordsObject.getJSONArray(RECORDS);
            for (int i = 0; i < recordsArray.length(); i++) {
                buildRecord((JSONObject) recordsArray.get(i));
            }
        } catch (JSONException e) {
            Log.e(getTag(), e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    protected abstract String getTag();

    protected abstract void buildRecord(JSONObject record) throws JSONException;

    protected abstract void removeRecord(JSONObject record) throws JSONException;

    public abstract String getTableName();

}
