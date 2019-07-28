package co.evecon.weather;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WeatherFragment extends Fragment {

    private TextView cityName;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);

        cityName = fragmentView.findViewById(R.id.cityWeatherFragmentName);
        mainActivity = (MainActivity) getActivity();
        cityName.setText(mainActivity.enteredCityName);

        return fragmentView;
    }

}
