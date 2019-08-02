package co.evecon.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WeatherFragment extends Fragment {

    private TextView cityName;
    private LinearLayout weatherData;
    private Button sendEmail;
    private Button showTempDataButton;
    private MainActivity mainActivity;
    private TemperatureDataFragment tempDataFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);

        mainActivity = (MainActivity) getActivity();
        cityName = fragmentView.findViewById(R.id.cityName);
        weatherData = fragmentView.findViewById(R.id.weatherData);
        sendEmail = fragmentView.findViewById(R.id.forecastEmailButton);
        showTempDataButton = fragmentView.findViewById(R.id.temperatureDataButton);
        tempDataFragment = new TemperatureDataFragment();

        cityName.setText(mainActivity.getEnteredCityName());

        if (mainActivity.getShowTemperature()) {
            TextView textView = new TextView(getActivity());
            textView.setText(getResources().getString(R.string.temperature));
            weatherData.addView(textView);
        }

        if (mainActivity.getShowPressure()) {
            TextView textView = new TextView(getActivity());
            textView.setText(getResources().getString(R.string.pressure));
            weatherData.addView(textView);
        }

        if (mainActivity.getShowHumidity()) {
            TextView textView = new TextView(getActivity());
            textView.setText(getResources().getString(R.string.humidity));
            weatherData.addView(textView);
        }

        if (mainActivity.getShowWindSpeed()) {
            TextView textView = new TextView(getActivity());
            textView.setText(getResources().getString(R.string.windSpeed));
            weatherData.addView(textView);
        }

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        showTempDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, tempDataFragment)
                        .addToBackStack("")
                        .commit();
            }
        });

        return fragmentView;
    }

}
