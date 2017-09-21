package com.wehrhere.myles.bountyboard.infrastructure.constants;

import com.wehrhere.myles.bountyboard.api.airtable.Table;
import com.wehrhere.myles.bountyboard.infrastructure.tables.Bounties;

import java.util.HashMap;

/**
 * Created by mwehr on 9/8/17.
 */

public enum TablesEnum {

    BOUNTIES("Bounties", new Bounties());

    private String tableName;
    private Table table;
    private static final HashMap<String, TablesEnum> MAP = new HashMap<>();

    static {
        for (TablesEnum table : TablesEnum.values()) {
            MAP.put(table.toString(), table);
        }
    }

    TablesEnum(String tableName, Table table) {
        this.tableName = tableName;
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public static Table getTable(String tableName) {
        if (tableName != null) {
            return MAP.get(tableName).getTable();
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return this.tableName;
    }

}
