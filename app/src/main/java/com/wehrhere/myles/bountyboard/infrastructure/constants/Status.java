package com.wehrhere.myles.bountyboard.infrastructure.constants;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;

/**
 * Created by mwehr on 8/26/17.
 */

public enum Status {

    UNCLAIMED("Unclaimed"),
    TO_DO("To Do"),
    REVIEW("Review"),
    COMPLETE("Complete"),
    ;

    private final String status;
    private static final HashMap<String, Status> MAP = new HashMap<>();

    static {
        for(Status status : Status.values()) {
            MAP.put(status.toString(), status);
        }
    }

    Status(String status) {
        this.status = status;
    }

    @Contract("null -> null")
    public static Status getStatus(String status) {
        if (status != null) {
            return MAP.get(status);
        } else {
            return null;
        }
    }

    @Override @Contract(pure = true)
    public String toString() {
        return status;
    }

}
