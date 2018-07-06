package com.example.faithmumbua.workit.activities.locations;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;

public class GetNearbyPlaces extends AsyncTask<Object, String, String>
{
    String googlePlacesData;
    GoogleMap myMap;
    String url;

    @Override
    protected String doInBackground(Object... objects)
    {
        myMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try
        {
            googlePlacesData = downloadUrl.readUrl(url);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return googlePlacesData;
    }
}
