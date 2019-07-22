package co.evecon.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ForecastActivity extends AppCompatActivity {
    private TextView cityName;
    private String sendCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        cityName = findViewById(R.id.cityName);

        Intent intent = getIntent();

        sendCityName = intent.getStringExtra("cityName");

        cityName.setText(sendCityName);
    }
}
