package com.wehrhere.myles.bountyboard.api.airtable;

import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mwehr on 8/24/17.
 */

public abstract class Record extends JSONObject {

    private static final String TAG = Record.class.getSimpleName();
    private static final String NAME_FIELD = "";

    /** Fields */
    private static final String ID = "id";
    private String id;
    private static final String FIELDS = "fields";
    private JSONObject fields;
    private static final String CREATED_TIME = "createdTime";
    private String createdTime;

    public Record(JSONObject record) throws JSONException {
        this.setId(record.getString(ID));
        this.setFields(record.getJSONObject(FIELDS));
        this.setCreatedTime(record.getString(CREATED_TIME));
    }

    public Record() {
        setFields(new JSONObject());
    }


    /** Public Accessors */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id != null) {
            this.id = id;
        }
    }

    public JSONObject getFields() {
        return fields;
    }

    public void setFields(JSONObject fields) {
        this.fields = fields;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        if (createdTime != null) {
            this.createdTime = createdTime;
        }
    }

    @Nullable
    protected String getValue(String fieldName) {
        try {
            return fields.getString(fieldName);
        } catch (Exception e) {
            return null;
        }
    }

    protected void putValue(String fieldName, String value) {
        try {
            fields.put(fieldName, value);
        } catch (JSONException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public JSONObject prepareRecordForOutput() {
        JSONObject json = new JSONObject();
        try {
            getFields().put(getPrimaryKey(), null);
            json.put(FIELDS, getFields());
        } catch (JSONException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
        return json;
    }

    public String serialize() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ID, getId());
            jsonObject.put(FIELDS, getFields());
            jsonObject.put(CREATED_TIME, getCreatedTime());
            return jsonObject.toString();
        } catch (JSONException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    public abstract String getPrimaryKey();

    protected abstract String getNameField();

    @Override
    public String toString() {
        return getValue(getNameField());
    }

}
