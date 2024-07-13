package handler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentHelper extends SQLiteOpenHelper {
    public StudentHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql)
    {
        SQLiteDatabase database = getWritableDatabase(); // Write and Read
        database.execSQL(sql);
    }

    // Query result
    public Cursor GetData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase(); // Read only
        return database.rawQuery(sql, null); // Return database
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}