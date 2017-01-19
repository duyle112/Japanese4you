package com.example.hoangduy.japanese4you.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hoangduy.japanese4you.models.Example;
import com.example.hoangduy.japanese4you.models.Exercise;
import com.example.hoangduy.japanese4you.models.Quiz;
import com.example.hoangduy.japanese4you.models.Word;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangDuy on 15/01/2017.
 */
public class DataHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "japanesev05.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    private static final String TABLE_WORD = "word";
    static Context mContext;
    private static final String KANA = "kana";
    private static final String KANJI = "kanji";
    private static final String ROMAN = "romanji";
    private static final String TYPE = "type";
    private static final String MEANING = "meaning";
    private static final String CATE = "category";
    private static final String FAV = "isFavorite";
    private static final String TABLE_EXAMPLE = "example";
    private static final String JAPSENTENCE = "japsentence";
    private static final String ROMANJISENTENCE = "romanjisentence";
    private static final String ENGSENTENCE = "engsentence";
    private static final String TABLE_QUIZ = "quiz";
    private static final String TABLE_EXERCISE = "exercise";

    private static String getDatabasePath() {
        return mContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }


    public DataHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public List<Example> getExamples(int idword) {
        List<Example> examples = new ArrayList<>();
        String selectQuery = "SELECT " + TABLE_EXAMPLE + "." + JAPSENTENCE + ","
                + TABLE_EXAMPLE + "." + ROMANJISENTENCE + "," +
                TABLE_EXAMPLE + "." + ENGSENTENCE + " FROM " + TABLE_EXAMPLE
                + " INNER JOIN word_example on " + TABLE_EXAMPLE + ".idexample=word_example.idword_example WHERE "
                + "word_example.idword " + "=" + idword;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String japsentence = cursor.getString(0);
                String romanjisentence = cursor.getString(1);
                String engsentence = cursor.getString(2);
                examples.add(new Example(japsentence, romanjisentence, engsentence));
            } while (cursor.moveToNext());
        }
        return examples;
    }

    public void setFavorite(int id, int favorite) {
        SQLiteDatabase db = this.getReadableDatabase();
        String updateQuerry = "UPDATE " + TABLE_WORD + " SET isFavorite= " + favorite + " WHERE " + TABLE_WORD + ".idword=" + id;
        db.execSQL(updateQuerry);
    }

    public List<Quiz> getQuizes(String category, int group) {
        List<Quiz> quizes = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QUIZ + " WHERE gr=" + group + " and category='" + category + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String question = cursor.getString(1);
                String answerA = cursor.getString(2);
                String answerB = cursor.getString(3);
                String answerC = cursor.getString(4);
                String answerD = cursor.getString(5);
                int rightAnswer = cursor.getInt(6);
                quizes.add(new Quiz(id, question, answerA, answerB, answerC, answerD, 5, rightAnswer));
            } while (cursor.moveToNext());
        }
        return quizes;
    }

    public List<Exercise> getExercises(String category) {
        List<Exercise> exercises = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE + " WHERE category='" + category + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String cate = cursor.getString(2);
                int group = cursor.getInt(3);
                int testTaken=cursor.getInt(4);
                String lastscore=cursor.getString(5);
                exercises.add(new Exercise(id, name, cate, group,testTaken,lastscore));
            } while (cursor.moveToNext());
        }
        return exercises;
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String cate = cursor.getString(2);
                int group = cursor.getInt(3);
                int testTaken=cursor.getInt(4);
                String lastscore=cursor.getString(5);
                exercises.add(new Exercise(id, name, cate, group,testTaken,lastscore));
            } while (cursor.moveToNext());
        }
        return exercises;
    }

    public List<Exercise> getTakenExercise() {
        List<Exercise> exercises = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EXERCISE + " WHERE testtaken >0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String cate = cursor.getString(2);
                int group = cursor.getInt(3);
                int testTaken=cursor.getInt(4);
                String lastscore=cursor.getString(5);
                exercises.add(new Exercise(id, name, cate, group,testTaken,lastscore));
            } while (cursor.moveToNext());
        }
        return exercises;
    }

    public List<Word> getWordFavorited() {
        List<Word> words = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORD + " WHERE isFavorite=1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int idword = cursor.getInt(0);
                String kana = cursor.getString(1);
                String romanji = cursor.getString(2);
                String kanji = cursor.getString(3);
                String type = cursor.getString(4);
                String meaning = cursor.getString(5);
                String category = cursor.getString(6);
                int favorite = cursor.getInt(7);
                words.add(new Word(idword, kana, kanji, romanji, type, meaning, category, favorite));
            } while (cursor.moveToNext());
        }
        return words;
    }

    public void updateProgress(int id,String lastscore) {
        SQLiteDatabase db = this.getReadableDatabase();
        String updateQuerry = "UPDATE " + TABLE_EXERCISE + " SET lastscore= " + lastscore +",testtaken=testtaken+1"+ " WHERE " + TABLE_EXERCISE + ".id=" + id;
        db.execSQL(updateQuerry);
    }

//    public long insertWord(Word word) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KANA, word.getKana());
//        values.put(KANJI, word.getKanji());
//        values.put(ROMAN, word.getRomanji());
//        values.put(TYPE, word.getType());
//        values.put(MEANING, word.getMeaning());
//        values.put(CATE, word.getCategory());
//        values.put(FAV, word.getFavorite());
//
//        // Inserting Row
//        long id = db.insert(TABLE_WORD, null, values);
//        db.close(); // Closing database connection
//        return id;
//    }
//
//    public long insertExample(Example example) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(JAPSENTENCE, example.getJapsentence());
//        values.put(ROMANJISENTENCE, example.getRomanjisentence());
//        values.put(ENGSENTENCE, example.getEngsentence());
//
//        // Inserting Row
//        long id = db.insert(TABLE_EXAMPLE, null, values);
//        db.close(); // Closing database connection
//        return id;
//    }
//
//    public void insertword_example(long idword, long idexample) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put("idword", idword);
//        values.put("idexample", idexample);
//        // Inserting Row
//        long id = db.insert("word_example", null, values);
//        Log.i("idwe", id + "");
//        db.close(); // Closing database connection
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

        String outFileName = getDatabasePath();

        File f = new File(mContext.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = mContext.getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_WORD;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int idword = cursor.getInt(0);
                String kana = cursor.getString(1);
                String romanji = cursor.getString(2);
                String kanji = cursor.getString(3);
                String type = cursor.getString(4);
                String meaning = cursor.getString(5);
                String category = cursor.getString(6);
                int favorite = cursor.getInt(7);
                words.add(new Word(idword, kana, kanji, romanji, type, meaning, category, favorite));
            } while (cursor.moveToNext());
        }
        return words;
    }

}