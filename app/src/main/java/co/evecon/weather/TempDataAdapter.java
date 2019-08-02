package co.evecon.weather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// Адаптер
public class TempDataAdapter extends RecyclerView.Adapter<TempDataAdapter.ViewHolder> {
    private ArrayList<String> data;

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    public TempDataAdapter(ArrayList<String> data) {
        this.data = data;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @Override
    public TempDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // Создаем новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        // Здесь можно установить всякие параметры
        ViewHolder vh = new ViewHolder((TextView) v);
        return vh;
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Получить элемент из источника данных (БД, интернет...)
        // Вынести на экран используя ViewHolder
        holder.textView.setText(data.get(position));

    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return data.size();
    }

    // Этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // Один пункт списка
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Каждый пункт списка в нашем случае это строка
        public TextView textView;

        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
