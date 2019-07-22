package co.evecon.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ForecastActivity extends AppCompatActivity {
    private TextView cityName;
    private String sendCityName;
    private int showTemperature;
    private int showPressure;
    private int showHumidity;
    private int showWindSpeed;
    private LinearLayout weatherData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        cityName = findViewById(R.id.cityName);
        weatherData = findViewById(R.id.weatherData);

        Intent intent = getIntent();

        sendCityName = intent.getStringExtra("cityName");
        cityName.setText(sendCityName);

        showTemperature = intent.getIntExtra("showTemperature", 2);
        if (showTemperature == 1) {
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(R.string.temperature));
            textView.setTextSize(15);
            weatherData.addView(textView);
        }

        showPressure = intent.getIntExtra("showPressure", 2);
        if (showPressure == 1) {
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(R.string.pressure));
            textView.setTextSize(15);
            weatherData.addView(textView);
        }

        showHumidity = intent.getIntExtra("showHumidity", 2);
        if (showHumidity == 1) {
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(R.string.humidity));
            textView.setTextSize(15);
            weatherData.addView(textView);
        }

        showWindSpeed = intent.getIntExtra("showWindSpeed", 2);
        if (showWindSpeed == 1) {
            TextView textView = new TextView(this);
            textView.setText(getResources().getString(R.string.windSpeed));
            textView.setTextSize(15);
            weatherData.addView(textView);
        }
    }
}
