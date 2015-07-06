package com.example.llewis.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] forecastArray = {
            "Today - Sunny - 88 / 63",
            "Tomorrow - Foggy - 70 / 46",
            "Wed - Cloudy - 72 / 63",
            "Thurs - Rain - 64 / 51",
            "Fri - Cloudy - 72 / 63",
            "Sat - Sunny - 85 / 73"
        };

        List<String> weekForecast = new ArrayList<String>(Arrays.asList(forecastArray));

        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
