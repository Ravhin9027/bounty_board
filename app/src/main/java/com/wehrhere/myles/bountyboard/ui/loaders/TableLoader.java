package com.wehrhere.myles.bountyboard.ui.loaders;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wehrhere.myles.bountyboard.R;
import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;
import com.wehrhere.myles.bountyboard.infrastructure.tables.Bounties;
import com.wehrhere.myles.bountyboard.ui.adapters.BountyAdapter;
import com.wehrhere.myles.bountyboard.ui.adapters.StableArrayAdapter;

import java.util.ArrayList;

/**
 * Created by mwehr on 9/21/17.
 */

public class TableLoader extends AsyncTask<Activity, Void, Activity> {

    private Bounties bountiesTable;

    @Override
    protected Activity doInBackground(Activity[] params) {
        bountiesTable = new Bounties();
        return params[0];
    }

    @Override
    protected void onPostExecute(Activity activity) {
        final ListView listview = activity.findViewById(R.id.listview);

        final ArrayList<Bounty> list = new ArrayList<>(bountiesTable.getBounties().values());

        final BountyAdapter adapter = new BountyAdapter(activity,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                //TODO: Replace this logic with a bounty form launcher
                final String item = (String) parent.getItemAtPosition(position).toString();
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }

        });
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onProgressUpdate(Void... values) {
    }

}
