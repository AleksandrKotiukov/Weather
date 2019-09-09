package co.evecon.weather;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class WeatherFragment extends Fragment {

    private TextView cityName;
    private TextView mediumYearTemperature;
    private LinearLayout weatherData;
    private Button sendEmail;
    private Button showTempDataButton;
    private Button showMediumYearTemp;
    private MainActivity mainActivity;
    private TemperatureDataFragment tempDataFragment;
    private FloatingActionButton sendMailFAB;
    private Requester tempRequester;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);

        mainActivity = (MainActivity) getActivity();
        cityName = fragmentView.findViewById(R.id.cityName);
        mediumYearTemperature = fragmentView.findViewById(R.id.mediumTemp);
        weatherData = fragmentView.findViewById(R.id.weatherData);
        sendEmail = fragmentView.findViewById(R.id.forecastEmailButton);
        showTempDataButton = fragmentView.findViewById(R.id.temperatureDataButton);
        showMediumYearTemp = fragmentView.findViewById(R.id.mediumTempDataButton);
        sendMailFAB = fragmentView.findViewById(R.id.fab);
        tempDataFragment = new TemperatureDataFragment();
        tempRequester = new Requester(mediumYearTemperature);

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

        showMediumYearTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempRequester.execute();
            }
        });


        sendMailFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return fragmentView;
    }

}
