package com.yingjunyu.MyPhoneAsst.db;

import android.content.Context;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {  
	  
    private static final int VERSION = 3;  
    private static final String DB_NAME = "MyPhoneAsst_Content"; 
    
    public static final String _TABLE = "phone_call";  
    public static final String _ID = "_id";  
    public static final String NAME = "name"; 
    public static final String DURATION = "duration";
    public static final String DATE = "date";
    public static final String TYPE = "type";
    public static final String NUMBER = "number";
    
    public static final String _TABLE1 = "sms_call";
    public static final String _ID1 = "_id";
    public static final String ADDRESS = "address";
    public static final String PERSON1 = "person";
    public static final String DATE1 = "date";
    public static final String BODY1 = "body";
    public static final String STYPE = "stype";
   
    private static final String CREATE_TABLE_CALL = "create table " + _TABLE + " ( " + _ID + " Integer primary key autoincrement," + 
    NAME + " text, " + DURATION +" double, " + DATE + " double, " + TYPE + " interger, " + NUMBER + " vachar(20))";  
    
    private static final String CREATE_TABLE_SMS = "create table " + _TABLE1 + " ( " + _ID1 + " Integer primary key autoincrement," + 
    ADDRESS + " text, " + PERSON1 +" text, " + DATE1 + " double, " + BODY1 + " text," + STYPE + " Integer)";  
      
    public DBOpenHelper(Context context) {  
        super(context, DB_NAME, null, VERSION);  
    }  
  
    @Override  
    public void onCreate(SQLiteDatabase db) {  
        db.execSQL(CREATE_TABLE_CALL);
        db.execSQL(CREATE_TABLE_SMS);
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  

    }  
    
    public void onDestroy(SQLiteDatabase db){
    	db.close();
    }
}
