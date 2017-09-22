package com.wehrhere.myles.bountyboard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wehrhere.myles.bountyboard.R;
import com.wehrhere.myles.bountyboard.ui.loaders.BountiesTableLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new BountiesTableLoader().execute(this);
    }

}
