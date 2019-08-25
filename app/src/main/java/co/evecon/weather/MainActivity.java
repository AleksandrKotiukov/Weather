package co.evecon.weather;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private String enteredCityName;
    private Boolean showTemperature;
    private Boolean showPressure;
    private Boolean showHumidity;
    private Boolean showWindSpeed;
    private MainFragment mainFragment;
    private DrawerLayout drawer;
    private AboutFragment aboutFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainFragment = new MainFragment();
        aboutFragment = new AboutFragment();
        enteredCityName = new String();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагмент
        fragmentTransaction.add(R.id.fragment_container, mainFragment);
        // закрыть транзакцию
        fragmentTransaction.commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_view) {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, mainFragment)
                    .addToBackStack("")
                    .commit();
        } else if (id == R.id.about_app) {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, aboutFragment)
                    .addToBackStack("")
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
