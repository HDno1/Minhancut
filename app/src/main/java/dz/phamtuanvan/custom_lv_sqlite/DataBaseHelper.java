package dz.phamtuanvan.custom_lv_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table StudentInfo (Id integer primary key , name text, phone text )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists StudentInfo");
        onCreate(db);
    }

    public boolean insertData(String id, String name, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Id",id);
        values.put("name",name);
        values.put("phone",phone);

        long result = db.insert("StudentInfo",null,values);

        if (result == -1) return false;
        else
            return true;
    }

    public boolean updateData(int id,String name, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        Cursor c = db.rawQuery("select * from StudentInfo where Id = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.update("StudentInfo",values,"Id=?",new String[]{String.valueOf(id)});

        if (result == -1) return false;
        else
            return true;
        }else {
            return false;
        }
    }

    public boolean deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from StudentInfo where Id = ?",new String[]{String.valueOf(id)});
        if (c.getCount()>0){
            long result = db.delete("StudentInfo","Id=?",new String[]{String.valueOf(id)});
            if (result == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }

    public ArrayList<Student> getaAllData(){
        ArrayList<Student> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM StudentInfo order by name ASC",null);

        while (c.moveToNext()){
            Integer id = c.getInt(0);
            String name = c.getString(1);
            String phone = c.getString(2);

            Student student = new Student(id,name,phone);

            arrayList.add(student);
        }

        return arrayList;

    }
}
