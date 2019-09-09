package co.evecon.weather;

import android.os.AsyncTask;
import android.widget.TextView;

public class Requester extends AsyncTask<Void, Void, Void> {

    private TextView mediumTempView;
    private int tempData;

    public Requester(TextView textView) {
        mediumTempView = textView;
    }

    // Обновление прогресса, работает в основном потоке UI
    @Override
    protected void onProgressUpdate(Void... arg0) {
    }

    // Выполнить таск в фоновом потоке
    @Override
    protected Void doInBackground(Void... arg0) {
        tempData = calculate();
        return null;
    }

    // Выдать результат, работает в основном потоке UI
    @Override
    protected void onPostExecute(Void result) {
        mediumTempView.setText("Среднегодовая температура: " + tempData);
    }

    private int calculate() {
        double r = 1;
        for (int j = 0; j < 100000000; j++)
            r = j * 0.01 + r / 0.01;
        return 42;
    }
}
