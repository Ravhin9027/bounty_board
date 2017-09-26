package com.wehrhere.myles.bountyboard.infrastructure.records;

import com.wehrhere.myles.bountyboard.api.airtable.Record;
import com.wehrhere.myles.bountyboard.infrastructure.constants.Size;
import com.wehrhere.myles.bountyboard.infrastructure.constants.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import static com.wehrhere.myles.bountyboard.infrastructure.constants.DateFormatter.dateToString;
import static com.wehrhere.myles.bountyboard.infrastructure.constants.DateFormatter.parseDate;

/**
 * Created by mwehr on 8/22/17.
 */

public class Bounty extends Record {

    /** Fields */
    private static final String BOUNTY_ID = "Bounty Id";
    private static final String BOUNTY = "Bounty";
    private static final String SIZE = "Size";
    private static final String STATUS = "Status";
    private static final String ASSIGNED = "Assigned";
    private static final String DESCRIPTION = "Description";
    private static final String DATE_ISSUED = "Date Issued";
    private static final String DATE_COMPLETED = "Date Completed";

    public Bounty(JSONObject record) throws JSONException {
        super(record, BOUNTY_ID, BOUNTY);
    }

    public Bounty() {
        super();
    }

    /** Public accessors */
    public String getBountyId() {
        return getValue(BOUNTY_ID);
    }

    public void setBountyId(String bountyId) {
        putValue(BOUNTY_ID, bountyId);
    }

    public String getBounty() {
        return getValue(BOUNTY);
    }

    public void setBounty(String bounty) {
        putValue(BOUNTY, bounty);
    }

    public Size getSize() {
        return Size.getSize(getValue(SIZE));
    }

    public void setSize(Size size) {
        putValue(SIZE, size.toString());
    }

    public Status getStatus() {
        return Status.getStatus(getValue(STATUS));
    }

    public void setStatus(Status status) {
        putValue(STATUS, status.toString());
    }

    public String getAssigned() {
        return getValue(ASSIGNED);
    }

    public void setAssigned(String assigned) {
        putValue(ASSIGNED, assigned);
    }

    public String getDescription() {
        return getValue(DESCRIPTION);
    }

    public void setDescription(String description) {
        putValue(DESCRIPTION, description);
    }

    public Date getDateIssued() {
        return parseDate(getValue(DATE_ISSUED));
    }

    public void setDateIssued(Date dateIssued) {
        putValue(DATE_ISSUED, dateToString(dateIssued));
    }

    public Date getDateCompleted() {
        return parseDate(getValue(DATE_COMPLETED));
    }

    public void setDateCompleted(Date dateCompleted) {
        putValue(DATE_COMPLETED, dateToString(dateCompleted));
    }

}
