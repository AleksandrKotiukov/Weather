package co.evecon.weather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import co.evecon.weather.Interfaces.OpenWeather;
import co.evecon.weather.model.WeatherRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherFragment extends Fragment {

    private TextView cityName;
    private TextView mediumYearTemperature;
    private TextView tempData;
    private TextView pressureData;
    private TextView humidityData;
    private TextView windSpeedData;
    private LinearLayout weatherData;
    private Button sendEmail;
    private Button showTempDataButton;
    private Button showMediumYearTemp;
    private MainActivity mainActivity;
    private TemperatureDataFragment tempDataFragment;
    private FloatingActionButton sendMailFAB;
    private Requester tempRequester;
    private OpenWeather openWeather;
    private SharedPreferences sharedPref;
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);

        init();
        initRetorfit();
        requestRetrofit(mainActivity.getEnteredCityName(), sharedPref.getString("apiKey", "879c31c9ee88b8a03c74af6e7c7677ce"));

        cityName.setText(mainActivity.getEnteredCityName());

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


    private void init() {
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
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
        tempData = new TextView(getActivity());
        pressureData = new TextView(getActivity());
        humidityData = new TextView(getActivity());
        windSpeedData = new TextView(getActivity());
    }


    private void initRetorfit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/") // Базовая часть адреса
                // Конвертер, необходимый для преобразования JSON'а в объекты
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Создаем объект, при помощи которого будем выполнять запросы
        openWeather = retrofit.create(OpenWeather.class);
    }

    private void requestRetrofit(String city, String keyApi) {
        openWeather.loadWeather(city, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call,
                                           Response<WeatherRequest> response) {
                        if (response.body() != null) {
                            if (mainActivity.getShowTemperature()) {
                                tempData.setText("Temperature: " + Float.toString(response.body().getMain().getTemp() - 273) + " C");
                                weatherData.addView(tempData);
                            }

                            if (mainActivity.getShowPressure()) {
                                pressureData.setText("Pressure: " + Float.toString(response.body().getMain().getPressure()) + " hpa");
                                weatherData.addView(pressureData);
                            }

                            if (mainActivity.getShowHumidity()) {
                                humidityData.setText("Humidity: " + Float.toString(response.body().getMain().getHumidity()) + " %");
                                weatherData.addView(humidityData);
                            }

                            if (mainActivity.getShowWindSpeed()) {
                                windSpeedData.setText("Wind speed: " + Float.toString(response.body().getWind().getSpeed()) + " MpH");
                                weatherData.addView(windSpeedData);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        Snackbar.make(fragmentView, "Error loading data from weather server", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                });
    }

}
