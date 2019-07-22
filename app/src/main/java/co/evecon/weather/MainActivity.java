package co.evecon.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button forecastButton;
    private EditText cityName;
    private String enteredCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.city);

        forecastButton = findViewById(R.id.forecastButton);
        forecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredCityName = cityName.getText().toString();
                if (enteredCityName.equals("")) {
                    Toast.makeText(getApplicationContext(), "Enter city", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
                    intent.putExtra("cityName", enteredCityName);
                    startActivity(intent);
                }
            }
        });
    }
}
