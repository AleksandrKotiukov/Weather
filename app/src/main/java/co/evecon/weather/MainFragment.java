package co.evecon.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    private Button forecastButton;
    private WeatherFragment weatherFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        forecastButton = fragmentView.findViewById(R.id.forecastButton);
        weatherFragment = new WeatherFragment();

        forecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, weatherFragment)
                        .addToBackStack("")
                        .commit();
            }
        });

        return fragmentView;
    }
}
