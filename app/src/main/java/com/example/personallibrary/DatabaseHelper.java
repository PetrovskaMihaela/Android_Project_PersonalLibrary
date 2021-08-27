package com.example.personallibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, "library.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
        db.execSQL("Create table book(key_id integer primary key, title text, author text, date_added text, date_finish text, username text)");
        db.execSQL("Create table goal(user text primary key, books integer, hours integer, pages integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion >= newVersion)
            return;
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists book");
        db.execSQL("drop table if exists goal");
        onCreate(db);
    }

    public boolean insert_1(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public Boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{email});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public long addBook(BookA book, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",book.getTitle() );
        cv.put("author",book.getAuthor() );
        cv.put("date_added",book.getDate() );
        cv.put("date_finish", book.getFinishDate());
        cv.put("username",user);
        long ID = db.insert("book", null, cv);
        return ID;
    }

    public BookA getBook(long ID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from book where key_id=?", new String[]{String.valueOf(ID)});

        if(cursor != null)
            cursor.moveToFirst();

        BookA book = new BookA(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return book;
    }

    public List<BookA> getBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<BookA> allBooks = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from book", null);

        if(cursor.moveToFirst()){
            do{
                BookA book = new BookA();
                book.setBookId(cursor.getLong(0));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setDate(cursor.getString(3));
                book.setFinishDate(cursor.getString(4));

                allBooks.add(book);

            }while(cursor.moveToNext());
        }

        return allBooks;
    }

    public int editBook(BookA book, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("title",book.getTitle() );
        cv.put("author",book.getAuthor() );
        cv.put("date_added",book.getDate() );
        cv.put("date_finish", book.getFinishDate());
        cv.put("username",user);

        return db.update("book", cv, "key_id"+"=?",new String[]{String.valueOf(book.getBookId())} );
    }

    void deleteBook(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("book", "key_id" + "=?", new String []{String.valueOf(id)});
    }

    public boolean insertGoal(Goal goal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", goal.getUser());
        contentValues.put("books", goal.getBooks());
        contentValues.put("hours", goal.getHours());
        contentValues.put("pages", goal.getPages());
        long ins = db.insert("goal", null, contentValues);
        if(ins == -1)
            return false;
        else return true;
    }

    public Boolean checkGoal(String user){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from goal where user=?", new String[]{user});
        if (cursor.getCount() > 0) {
            return false;
        } else return true;
    }


    public int updateGoal(Goal goal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("user", goal.getUser());
        contentValues.put("books", goal.getBooks());
        contentValues.put("hours", goal.getHours());
        contentValues.put("pages", goal.getPages());

        return db.update("goal", contentValues, "user"+"=?",new String[]{goal.getUser()} );
    }

    public Goal getGoal(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from goal where user=?", new String[]{username});

        if(cursor != null)
            cursor.moveToFirst();

        Goal goal = new Goal(cursor.getString(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3));
        return goal;
    }
}
