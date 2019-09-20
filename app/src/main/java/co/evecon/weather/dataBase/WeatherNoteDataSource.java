package co.evecon.weather.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;

import co.evecon.weather.modelDB.weatherNote;

//  Источник данных, позволяет изменять данные в таблице
// Создает и держит в себе читатель данных
public class WeatherNoteDataSource implements Closeable {

    private final DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private WeatherNoteDataReader noteDataReader;

    public WeatherNoteDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Открывает базу данных
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        // создать читателя и открыть его
        noteDataReader = new WeatherNoteDataReader(database);
        noteDataReader.open();
    }

    // Закрыть базу данных
    public void close() {
        noteDataReader.close();
        dbHelper.close();
    }

    // Добавить новую запись
    public weatherNote addNote(int temperature, String city) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TEMPERATURE, temperature);
        values.put(DatabaseHelper.COLUMN_CITY, city);
        // Добавление записи
        long insertId = database.insert(DatabaseHelper.TABLE_NOTES, null,
                values);
        weatherNote newNote = new weatherNote();
        newNote.setTemperature(temperature);
        newNote.setCity(city);
        newNote.setId(insertId);
        return newNote;
    }

    // Изменить запись
    public void editNote(weatherNote note, int temperature, String city) {
        ContentValues editedNote = new ContentValues();
        editedNote.put(DatabaseHelper.COLUMN_ID, note.getId());
        editedNote.put(DatabaseHelper.COLUMN_TEMPERATURE, temperature);
        editedNote.put(DatabaseHelper.COLUMN_CITY, city);
        // изменение записи
        database.update(DatabaseHelper.TABLE_NOTES,
                editedNote,
                DatabaseHelper.COLUMN_ID + " = " + note.getId(),
                null);
    }

    // Удалить запись
    public void deleteNote(weatherNote note) {
        long id = note.getId();
        database.delete(DatabaseHelper.TABLE_NOTES, DatabaseHelper.COLUMN_ID
                + " = " + id, null);
    }

    // Очистить таблицу
    public void deleteAll() {
        database.delete(DatabaseHelper.TABLE_NOTES, null, null);
    }

    // вернуть читателя (он потребуется в других местах)
    public WeatherNoteDataReader getNoteDataReader() {
        return noteDataReader;
    }
}