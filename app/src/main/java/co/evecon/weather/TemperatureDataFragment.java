package co.evecon.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class TemperatureDataFragment extends Fragment {
    private final int tempDataNumber = 100;
    private ArrayList<String> tempData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_temperature_data, container, false);

        tempData = new ArrayList<>();
        for (int i = 0; i < tempDataNumber; i++) {
            tempData.add("Temperature " + i + " days ago: " + i + " C");
        }

        RecyclerView recyclerView = fragmentView.findViewById(R.id.recycler_view);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        TempDataAdapter adapter = new TempDataAdapter(tempData);
        recyclerView.setAdapter(adapter);


        return fragmentView;
    }
}
