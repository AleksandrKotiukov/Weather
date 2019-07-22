package co.evecon.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        cityName = findViewById(R.id.cityName);
        weatherData = findViewById(R.id.weatherData);
        sendEmail = findViewById(R.id.forecastEmailButton);

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
    }
}
