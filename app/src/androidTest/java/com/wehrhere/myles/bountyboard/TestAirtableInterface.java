package com.wehrhere.myles.bountyboard;

import android.support.test.runner.AndroidJUnit4;

import com.wehrhere.myles.bountyboard.api.airtable.Airtable;
import com.wehrhere.myles.bountyboard.infrastructure.constants.DateFormatter;
import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 * Testing Airtable API Calls
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestAirtableInterface {

    @Test
    public void testPopulateTables() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        assertEquals(bounties.getBounties().size() > 0, true);
    }

    @Test
    public void testAddRecord() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        Bounty bounty = new Bounty();
        int size = bounties.getBounties().size();
        bounty.setBounty("Mop Floor");
        bounties.addRecord(bounty);
        assertEquals(bounties.getBounties().size(), size + 1);
    }

    @Test
    public void testUpdateRecord() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        String recordId = (String) bounties.getBounties().keySet().toArray()[0];
        Bounty bounty = bounties.getBounties().get(recordId);
        String testDate = "2017-08-" + (new Random().nextInt(31 - 1) + 1);
        bounty.setDateIssued(DateFormatter.parseDate(testDate));
        bounties.updateRecord(bounty);
        assertEquals(bounties.getBounties().get(recordId).getDateIssued(), DateFormatter.parseDate(testDate));
    }

    @Test
    public void testDeleteRecord() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        String recordId = (String) bounties.getBounties().keySet().toArray()[0];
        int size = bounties.getBounties().size();
        Bounty bounty = bounties.getBounties().get(recordId);
        bounties.deleteRecord(bounty);
        assertEquals(bounties.getBounties().size(), size - 1);
    }

    @Test
    public void testSingleRecordFilter() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName("Bounties Testing");
        httpHandler.addFilter("Assigned", "Sully");
        JSONObject recordsObject = new JSONObject(httpHandler.getRecords());
        JSONArray records = recordsObject.getJSONArray("records");
        assertEquals(records.length() < bounties.getBounties().size(), true);
    }

    @Test
    public void testMultipleRecordFilters() throws Exception {
        BountiesTesting bounties = new BountiesTesting();
        Airtable httpHandler = new Airtable();
        httpHandler.setTableName("Bounties Testing");
        httpHandler.addFilter("Assigned", "Sully");
        httpHandler.addFilter("Status", "To Do");
        JSONObject recordsObject = new JSONObject(httpHandler.getRecords());
        JSONArray records = recordsObject.getJSONArray("records");
        assertEquals(records.length() < bounties.getBounties().size(), true);
    }

}
