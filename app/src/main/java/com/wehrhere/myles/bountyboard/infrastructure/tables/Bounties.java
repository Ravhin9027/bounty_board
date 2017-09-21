package com.wehrhere.myles.bountyboard.infrastructure.tables;

import com.wehrhere.myles.bountyboard.api.airtable.Record;
import com.wehrhere.myles.bountyboard.api.airtable.Table;
import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mwehr on 8/22/17.
 */

public class Bounties extends Table {

    private static final String TAG = Bounties.class.getSimpleName();
    private static final String TABLE_NAME = "Bounties Testing";

    private HashMap<String, Bounty> bounties = new HashMap<>();

    public Bounties() {
        populate();
    }

    public HashMap<String, Bounty> getBounties() {
        return bounties;
    }

    public void setBounties(HashMap<String, Bounty> bounties) {
        this.bounties = bounties;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected void buildRecord(JSONObject record) throws JSONException {
        Bounty bounty = new Bounty(record);
        bounties.put(bounty.getBountyId(), bounty);
    }

    @Override
    protected void removeRecord(JSONObject record) {
        Bounty bounty = (Bounty) record;
        bounties.remove(bounty.getBountyId());
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

}
