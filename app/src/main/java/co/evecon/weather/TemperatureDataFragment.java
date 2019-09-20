package co.evecon.weather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.evecon.weather.dataBase.WeatherNoteDataReader;
import co.evecon.weather.dataBase.WeatherNoteDataSource;
import co.evecon.weather.modelDB.weatherNote;


public class TemperatureDataFragment extends Fragment {
    private final int tempDataNumber = 100;
    private ArrayList<String> tempData;
    private WeatherNoteDataSource weatherSource;
    private WeatherNoteDataReader weatherReader;
    private weatherNote wNote;
    private int noteCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_temperature_data, container, false);

        weatherSource = new WeatherNoteDataSource(getContext());
        weatherSource.open();
        weatherReader = weatherSource.getNoteDataReader();
        wNote = new weatherNote();
        noteCount = weatherReader.getCount();

        tempData = new ArrayList<>();
        for (int i = 0; i < noteCount; i++) {
            tempData.add("City: " + weatherReader.getPosition(i).getCity() + " temperature: " + weatherReader.getPosition(i).getTemperature() + " C");
        }

        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        TempDataAdapter adapter = new TempDataAdapter(tempData);
        recyclerView.setAdapter(adapter);

        weatherSource.close();


        return fragmentView;
    }
}
