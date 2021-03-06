package com.example.ben.kameleon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class WidgetsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // When view is being created, do this:

        // Gets the current view on the device and allows for easier access of resources
        View v = inflater.inflate(R.layout.fragment_widgets, container, false);

        // Finds the widget spinner in the XML and gives it a variable to access
        final Spinner widgetSpinner = v.findViewById(R.id.weather_widget_spinner);

        // Defines new array list
        ArrayList<String> widgetArray = new ArrayList<>();

        // Adds each item to the widget array
        widgetArray.add("None");
        widgetArray.add("Temperature");
        widgetArray.add("Humidity");
        widgetArray.add("Wind Speed");

        // Creates an adapter that allows an array to be used as the contents of the spinner
        ArrayAdapter<String> widgetAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, widgetArray);
        // Uses the adapter to set the values of the array to the contents and style of the spinner
        widgetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        widgetSpinner.setAdapter(widgetAdapter);

        // Sets the action bar at the top of the app to say the current mode
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Widgets");

        // Creates a new shared preferences file that allows user preferences to be stored within the application
        SharedPreferences mPreferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        // Allows preferences file to be edited using 'editor'
        final SharedPreferences.Editor editor = mPreferences.edit();
        // Restores user selected value in spinner from value in shared preferences
        widgetSpinner.setSelection(mPreferences.getInt("selected_widget",0));

        // Method for listening which item has been selected in the spinner
        widgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                // When an item in the list is selected, store the position value in the shared preferences, so selection is stored for next time.
                int widgetSpinnerValue = widgetSpinner.getSelectedItemPosition();
                editor.putInt("selected_widget", widgetSpinnerValue);
                editor.apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // If nothing is selected, do this:
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
