package adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import handler.DatabaseHelper;

public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private static final String DATABASE_NAME = "Database_Demo";
    private static final String DATABASE_TABLE = "users";
    private static final int DATABASE_VERSION = 2;
    private final Context context;

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() {
        databaseHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public long createUser(String name) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        return sqLiteDatabase.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteUser(long rowId) {
        return sqLiteDatabase.delete(DATABASE_TABLE, KEY_ID + " = "
                + rowId, null) > 0;
    }

    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(DATABASE_TABLE, null, null) > 0;
    }

    public Cursor getAllUsers() {
        return sqLiteDatabase.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_NAME},
                null, null, null, null, null);
    }
}
