package com.wehrhere.myles.bountyboard.infrastructure.constants;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;

/**
 * Created by mwehr on 8/26/17.
 */

public enum Size {

    CHORE("Chore", 0),
    TINY("Tiny", 1),
    SMALL("Small", 2),
    MEDIUM("Medium", 3),
    LARGE("Large", 5),
    HUGE("Huge", 8),
    ;

    private final String size;
    private final int pointValue;
    private static final HashMap<String, Size> MAP = new HashMap<>();

    static {
        for(Size size : Size.values()) {
            MAP.put(size.toString(), size);
        }
    }

    Size(String size, int pointValue) {
        this.size = size;
        this.pointValue = pointValue;
    }

    @Contract("null -> null")
    public static Size getSize(String size) {
        if (size != null) {
            return MAP.get(size.toString());
        } else {
            return null;
        }
    }

    @Override @Contract(pure = true)
    public String toString() {
        return size;
    }

    public int getPointValue() {
        return pointValue;
    }

}
