package com.wehrhere.myles.bountyboard.ui.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mwehr on 9/22/17.
 */

public class BountyAdapter extends ArrayAdapter<Bounty> {

    HashMap<String, Integer> mIdMap = new HashMap<>();

    public BountyAdapter(Context context, int textViewResourceId,
                              List<Bounty> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i).toString(), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position).toString();
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
