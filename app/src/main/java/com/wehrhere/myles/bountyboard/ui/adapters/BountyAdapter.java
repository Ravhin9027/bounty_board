package com.wehrhere.myles.bountyboard.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mwehr on 9/22/17.
 */

public class BountyAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<Bounty> bounties) {
        ArrayList<String> objects = new ArrayList<>();
        for (Bounty bounty : bounties) {
            objects.add(bounty.getBounty());
            mIdMap.put(bounty.getBounty(), new Integer(bounty.getBountyId()));
        }
        super(context, textViewResourceId, objects);
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
