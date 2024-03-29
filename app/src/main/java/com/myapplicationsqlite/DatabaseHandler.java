package com.myapplicationsqlite;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "miniProjectDB";
    private static final String TABLE_CLIENT = "client";

    public static String usernameDB, useremailDB;

    Context context;

    private Client client;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CLIENT + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "email TEXT," +
                "password TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);

        onCreate(db);
    }

    Client getClient(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor;
        cursor = db.query(TABLE_CLIENT,
                new String[] {"id", "name", "email", "password"},
                "email=?", new String[]{ email },
                null, null,null,null );
        if (cursor != null)
            cursor.moveToFirst();

        Client client = new Client(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return client;
    }

    public List<Client> getAllClients() {
        List<Client> clientList = new ArrayList<Client>();
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_CLIENT + " ORDER BY id DESC";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Client client =new Client();
                client.setId(cursor.getInt(0));
                client.setName(cursor.getString(1));
                client.setEmail(cursor.getString(2));
                client.setPassword(cursor.getString(3));
                clientList.add(client);
            } while (cursor.moveToNext());
        }

        return clientList;
    }

    public boolean login(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENT + " WHERE email=? AND password=?";
        Cursor cursor = db.rawQuery(query, new String[]{email,password});
        if (cursor.moveToFirst()){
            usernameDB = cursor.getString(1);
            useremailDB = cursor.getString(2);
        }
        return cursor.moveToFirst();

    }

    public void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", client.getName());
        values.put("email", client.getEmail());
        values.put("password", client.getPassword());

        db.insert(TABLE_CLIENT, null, values);
        db.close();
    }

    public Client findByEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_CLIENT + " WHERE email=?";

        Cursor cursor = db.rawQuery(query, new String[] {email});

        if (cursor.moveToFirst()){
            return new Client(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
        }
        else return client;

    }

    public void updateClient(String name, String email, String password, String emailA){
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "UPDATE "+ TABLE_CLIENT+ " SET name=?, email=?, password=? WHERE id=?";

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);

        db.update(TABLE_CLIENT, values, "email=?", new String[]{emailA});

    }

    public void delete(String email){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CLIENT, "email=?", new String[]{email});
    }

}
