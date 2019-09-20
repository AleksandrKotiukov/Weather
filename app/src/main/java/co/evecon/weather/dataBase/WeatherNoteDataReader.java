package co.evecon.weather.dataBase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;

import co.evecon.weather.modelDB.weatherNote;

// Читатель источника данных на основе курсора.
// Этот класс был вынесен из NoteDataSource, чтобы разгрузить его ответственности.
public class WeatherNoteDataReader implements Closeable {

    // но сами данные подчитываются только по необходимости
    private final SQLiteDatabase database;
    private final String[] notesAllColumn = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_TEMPERATURE,
            DatabaseHelper.COLUMN_CITY
    };
    private Cursor cursor;              // Курсор, фактически это подготовенный запрос,

    public WeatherNoteDataReader(SQLiteDatabase database) {
        this.database = database;
    }

    // Подготовить к чтению таблицу
    public void open() {
        query();
        cursor.moveToFirst();
    }

    public void close() {
        cursor.close();
    }

    // Перечитать таблицу (если точно, то здесь саму таблицу мы не перечитываем,
    // а просто обновляем курсор.
    public void Refresh() {
        int position = cursor.getPosition();
        query();
        cursor.moveToPosition(position);
    }

    // создание запроса на курсор
    private void query() {
        cursor = database.query(DatabaseHelper.TABLE_NOTES,
                notesAllColumn, null, null,
                null, null, null);
    }

    // прочитать данные по определернной позиции
    public weatherNote getPosition(int position) {
        cursor.moveToPosition(position);
        return cursorToNote();
    }

    // получить количество строк в таблице
    public int getCount() {
        return cursor.getCount();
    }

    // преобразователь данных курсора в объект
    private weatherNote cursorToNote() {
        weatherNote note = new weatherNote();
        note.setId(cursor.getLong(0));
        note.setTemperature(cursor.getInt(1));
        note.setCity(cursor.getString(2));
        return note;
    }
}

