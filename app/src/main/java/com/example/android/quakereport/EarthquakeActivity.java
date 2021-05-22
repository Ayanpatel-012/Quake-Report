/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;


import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<CityInfo>> {



    static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private CityInfoAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        android.support.v4.app.LoaderManager loaderManager=getSupportLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
         ListView earthquakeListView = (ListView) findViewById(R.id.list);
        TextView emptyTxt= (TextView) findViewById(R.id.TxtN);
        earthquakeListView.setEmptyView(emptyTxt);
        mAdapter=new CityInfoAdapter(this,new ArrayList<CityInfo>());

         earthquakeListView.setAdapter(mAdapter);
         earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 CityInfo currentCity= mAdapter.getItem(position);
                 Uri websiteurl= Uri.parse(currentCity.getmUrl());

                 Intent website=new Intent(Intent.ACTION_VIEW,websiteurl);
                 startActivity(website);


             }
         });



    }

    @Override
    public Loader<List<CityInfo>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<CityInfo>> loader, List<CityInfo> data) {
        ProgressBar progressBar=(ProgressBar) findViewById(R.id.ProgressBar);
        TextView empty=(TextView) findViewById(R.id.TxtN);
        empty.setText("NOTHING TO SHOW!!");
        progressBar.setVisibility(View.GONE);
        mAdapter.clear();
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CityInfo>> loader) {
        mAdapter.clear();
    }


}
