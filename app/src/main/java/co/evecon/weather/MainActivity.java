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


    private String enteredCityName;
    private Boolean showTemperature;
    private Boolean showPressure;
    private Boolean showHumidity;
    private Boolean showWindSpeed;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        enteredCityName = new String();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагмент
        fragmentTransaction.add(R.id.fragment_container, mainFragment);
        // закрыть транзакцию
        fragmentTransaction.commit();
    }

    public String getEnteredCityName() {
        return enteredCityName;
    }

    public void setEnteredCityName(String enteredCityName) {
        this.enteredCityName = enteredCityName;
    }

    public Boolean getShowTemperature() {
        return showTemperature;
    }

    public void setShowTemperature(Boolean showTemperature) {
        this.showTemperature = showTemperature;
    }

    public Boolean getShowPressure() {
        return showPressure;
    }

    public void setShowPressure(Boolean showPressure) {
        this.showPressure = showPressure;
    }

    public Boolean getShowHumidity() {
        return showHumidity;
    }

    public void setShowHumidity(Boolean showHumidity) {
        this.showHumidity = showHumidity;
    }

    public Boolean getShowWindSpeed() {
        return showWindSpeed;
    }

    public void setShowWindSpeed(Boolean showWindSpeed) {
        this.showWindSpeed = showWindSpeed;
    }


}
