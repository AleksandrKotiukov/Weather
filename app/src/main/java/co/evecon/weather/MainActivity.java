package co.evecon.weather;


import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button forecastButton;
    private EditText cityName;
    private String enteredCityName;
    private CheckBox showTemperature;
    private CheckBox showPressure;
    private CheckBox showHumidity;
    private CheckBox showWindSpeed;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагмент
        fragmentTransaction.add(R.id.fragment_container, mainFragment);
        // закрыть транзакцию
        fragmentTransaction.commit();


//        cityName = findViewById(R.id.city);
//        showTemperature = findViewById(R.id.checkBoxTemperature);
//        showPressure = findViewById(R.id.checkBoxPressure);
//        showHumidity = findViewById(R.id.checkBoxHumidity);
//        showWindSpeed = findViewById(R.id.checkBoxWindSpeed);
//        forecastButton = findViewById(R.id.forecastButton);
//
//        forecastButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enteredCityName = cityName.getText().toString();
//                if (enteredCityName.equals("")) {
//                    Toast.makeText(getApplicationContext(), "Enter city", Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
//                    if (showTemperature.isChecked()) {
//                        intent.putExtra("showTemperature", 1);
//                    } else {
//                        intent.putExtra("showTemperature", 0);
//                    }
//                    if (showPressure.isChecked()) {
//                        intent.putExtra("showPressure", 1);
//                    } else {
//                        intent.putExtra("showPressure", 0);
//                    }
//                    if (showHumidity.isChecked()) {
//                        intent.putExtra("showHumidity", 1);
//                    } else {
//                        intent.putExtra("showHumidity", 0);
//                    }
//                    if (showWindSpeed.isChecked()) {
//                        intent.putExtra("showWindSpeed", 1);
//                    } else {
//                        intent.putExtra("showWindSpeed", 0);
//                    }
//                    intent.putExtra("cityName", enteredCityName);
//                    startActivity(intent);
//                }
//            }
//        });
    }
}
