package com.example.a100580683.panelprototype;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 100585588 on 12/4/2017.
 */

public class HighscoreDB extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String  TABLE = "Scores";

    static final String CREATE_STATEMENT = "CREATE TABLE Scores(\n" +
            "id int primary key, \n"+
            "levelNo int not null, \n" +
            "name varchar(100) not null, \n"+
            "turns int not null\n"+
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE Scores";

    public HighscoreDB(Context context){super(context, "Scores", null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_STATEMENT);
        db.execSQL(CREATE_STATEMENT);
    }

    public Highscore createScore(int level, String name, int turns){
        Highscore toAdd = new Highscore(level, name, turns);

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("levelNo", level);
        newValues.put("name", name);
        newValues.put("turns", turns);

        long id = db.insert(TABLE, null, newValues);

        toAdd.setId(id);

        return toAdd;
    }

    public Highscore[] getScore(int lev){
        Highscore[] scores = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"id", "levelNo", "name", "turns"};
        String where = "levelNo = ?";
        String[] whereArgs = new String[] { "" + lev };
        //Figure out how to format the orderBy string
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if(cursor.getCount() > 0) {
            scores = new Highscore[cursor.getCount()];
        }

        int i = 0;
        cursor.moveToFirst();
        do{
            if(!cursor.isAfterLast()){
                int ide = cursor.getInt(0);
                int lvl = cursor.getInt(1);
                String nam = cursor.getString(2);
                int trn = cursor.getInt(3);
                scores[i] = new Highscore(lvl, nam, trn);
                scores[i].setId(ide);
               i++;
            }
        }while (!cursor.isAfterLast());

        return scores;

    }

    public boolean clearLevel(int level){
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "levlNo = ?", new String[] { "" + level });

        return (numRows == 1);
    }

    public void clearAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }

}
