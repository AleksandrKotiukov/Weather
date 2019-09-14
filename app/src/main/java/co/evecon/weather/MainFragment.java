package co.evecon.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainFragment extends Fragment {

    private String enteredCityName;
    private CheckBox showTemperature;
    private CheckBox showPressure;
    private CheckBox showHumidity;
    private CheckBox showWindSpeed;
    private Button forecastButton;
    private WeatherFragment weatherFragment;
    private MainActivity mainActivity;
    private TextInputEditText inputCityName;
    private SharedPreferences sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);

        showTemperature = fragmentView.findViewById(R.id.checkBoxTemperature);
        showPressure = fragmentView.findViewById(R.id.checkBoxPressure);
        showHumidity = fragmentView.findViewById(R.id.checkBoxHumidity);
        showWindSpeed = fragmentView.findViewById(R.id.checkBoxWindSpeed);
        forecastButton = fragmentView.findViewById(R.id.forecastButton);
        inputCityName = fragmentView.findViewById(R.id.inputCityName);
        weatherFragment = new WeatherFragment();
        mainActivity = (MainActivity) getActivity();
        sharedPreference = getActivity().getPreferences(Context.MODE_PRIVATE);

        if (!sharedPreference.contains("Default city")) {
            SharedPreferences.Editor editor = sharedPreference.edit();
            // теперь в Editor утановим значения
            editor.putString("Default city", "Pskov");
            editor.commit();
        }

        forecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredCityName = inputCityName.getText().toString();

                if (enteredCityName.equals("")) {
                    enteredCityName = sharedPreference.getString("Default city", "value");
                }

                mainActivity.setEnteredCityName(enteredCityName);
                mainActivity.setShowTemperature(showTemperature.isChecked());
                mainActivity.setShowPressure(showPressure.isChecked());
                mainActivity.setShowHumidity(showHumidity.isChecked());
                mainActivity.setShowWindSpeed(showWindSpeed.isChecked());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, weatherFragment)
                        .addToBackStack("")
                        .commit();
            }
        });

        return fragmentView;
    }
}
