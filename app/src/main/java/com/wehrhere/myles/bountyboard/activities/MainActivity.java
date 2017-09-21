package com.wehrhere.myles.bountyboard.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.wehrhere.myles.bountyboard.R;
import com.wehrhere.myles.bountyboard.api.airtable.Record;
import com.wehrhere.myles.bountyboard.api.airtable.Table;
import com.wehrhere.myles.bountyboard.infrastructure.constants.TablesEnum;
import com.wehrhere.myles.bountyboard.infrastructure.records.Bounty;
import com.wehrhere.myles.bountyboard.infrastructure.tables.Bounties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public Bounties bountiesTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TableLoader().execute(this);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
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

    private class TableLoader extends AsyncTask<Activity, Void, Activity> {

        @Override
        protected Activity doInBackground(Activity[] params) {
            bountiesTable = new Bounties();
            return params[0];
        }

        @Override
        protected void onPostExecute(Activity activity) {
            final ListView listview = (ListView) findViewById(R.id.listview);

            final ArrayList<Bounty> bounties = new ArrayList<>(bountiesTable.getBounties().values());
            final ArrayList<String> list = new ArrayList<>();
            for (Bounty bounty : bounties) {
                if (bounty.getBounty() != null) {
                    list.add(bounty.getBounty());
                }
            }

            final StableArrayAdapter adapter = new StableArrayAdapter(activity,
                    android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, final View view,
                                        int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
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


}
