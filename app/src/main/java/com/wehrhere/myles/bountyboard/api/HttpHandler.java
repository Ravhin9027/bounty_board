package com.wehrhere.myles.bountyboard.api;

/**
 * Created by mwehr on 8/23/17.
 */

import android.support.annotation.Nullable;
import android.util.Log;

import org.jetbrains.annotations.Contract;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpHandler {

    /** Class Tag */
    private static final String TAG = HttpHandler.class.getSimpleName();

    /** Protected Values */
    protected HttpURLConnection connection;
    protected CallType callType;

    /**
     * Gets server response
     * @return - Server response
     */
    public String doGet() {
        connection = openConnection();
        try {
            return readResponse();
        } finally {
            closeConnection();
        }
    }

    /**
     * Posts the JSONObject parameter
     * @param json - The JSONObject to be posted
     * @return - Server response
     */
    public String doPost(JSONObject json) {
        connection = openConnection();
        try {
            connection.setDoInput(true);
            connection.setDoOutput(true);
            try {
                OutputStream out = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(out, "UTF-8"));
                writer.write(json.toString());
                writer.flush();
                writer.close();
                out.close();
            } catch (Exception e) {
                Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            return readResponse();
        } finally {
            closeConnection();
        }
    }

    private String readResponse() {
        String line;
        String response = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                response += line;
            }
        } catch (IOException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return response;
    }

    @Nullable
    private HttpURLConnection openConnection() {
        try {
            HttpURLConnection conn = (HttpURLConnection) getUrl().openConnection();
            conn.setRequestMethod(callType.toString());
            conn.setRequestProperty("Content-Type", "application/json");
            setAuthorization(conn);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            return conn;
        } catch (IOException e) {
            Log.e(TAG, e.getClass().getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    private void closeConnection() {
        connection.disconnect();
    }

    protected abstract void setAuthorization(HttpURLConnection conn);

    protected abstract URL getUrl();

    protected enum CallType {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE"),
        ;

        private String callType;

        CallType(String callType) {
            this.callType = callType;
        }

        @Override @Contract(pure = true)
        public String toString() {
            return callType;
        }
    }

}
