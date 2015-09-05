package com.yingjunyu.MyPhoneAsst.db;

import java.util.ArrayList;  
import java.util.List;  
  
import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;

public class CallSQLOperateImpl implements SQLOperate{  
	  
    private DBOpenHelper dbOpenHelper;  
      
    public CallSQLOperateImpl(Context context) {  
        dbOpenHelper = new DBOpenHelper(context);  
    } 
    
    public void dosql(String sql){
    	SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
    	db.execSQL(sql);
    	db.close();
    }
  
    
    public void add(PhoneCall p) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        ContentValues values = new ContentValues();  
        values.put(DBOpenHelper._ID, p.getId());  
        values.put(DBOpenHelper.NAME, p.getName()); 
        values.put(DBOpenHelper.DURATION, p.getDuration());
        values.put(DBOpenHelper.DATE, p.getDate());
        values.put(DBOpenHelper.TYPE, p.getType());
        values.put(DBOpenHelper.NUMBER, p.getNumber());
        db.insert(DBOpenHelper._TABLE, null, values);  
        db.close();
    }  
  
    
    public void delete(int id) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        db.delete(DBOpenHelper._TABLE, DBOpenHelper._ID + "=?", new String[]{String.valueOf(id)});  
        db.close();
    }  
  
   
    public void updata(PhoneCall p) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        ContentValues values = new ContentValues();  
        values.put(DBOpenHelper._ID, p.getId());  
        values.put(DBOpenHelper.NAME, p.getName());  
        values.put(DBOpenHelper.DURATION, p.getDuration());
        values.put(DBOpenHelper.DATE, p.getDate());
        values.put(DBOpenHelper.TYPE, p.getType());
        values.put(DBOpenHelper.NUMBER, p.getNumber());
        db.update(DBOpenHelper._TABLE, values, DBOpenHelper._ID + "=?", new String[]{String.valueOf(p.getId())});  
        db.close();
    }  
  
    
    public List<PhoneCall> find() {  
        List<PhoneCall> persons = null;  
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();  
        Cursor cursor = db.query(DBOpenHelper._TABLE, null, null, null, null, null, null);  
        if(cursor != null){  
            persons = new ArrayList<PhoneCall>();  
            while(cursor.moveToNext()){  
                PhoneCall person = new PhoneCall();  
                int _id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));  
                String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NAME));
                Long duration = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DURATION));
                Long date = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DATE));
                int type = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.TYPE));
                String number = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NUMBER));
                person.setId(_id);  
                person.setName(name); 
                person.setDuration(duration);
                person.setDate(date);
                person.setType(type);
                person.setNumber(number);
                persons.add(person);  
            }  
        } 
        db.close();
        return persons;  
    }  
  
    
    public PhoneCall findById(int id) {  
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();  
        Cursor cursor = db.query(DBOpenHelper._TABLE, null, DBOpenHelper._ID + "=?", new String[]{String.valueOf(id)}, null, null, null);  
        PhoneCall person = null;  
        if(cursor != null && cursor.moveToFirst()){  
            person = new PhoneCall();  
            int _id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));  
            String name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NAME));  
            Long duration = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DURATION));
            Long date = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DATE));
            int type = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.TYPE));
            String number = cursor.getString(cursor.getColumnIndex(DBOpenHelper.NUMBER));
            person.setId(_id);  
            person.setName(name);  
            person.setDuration(duration);
            person.setDate(date);
            person.setType(type);
            person.setNumber(number);
        } 
        db.close();
        return person;  
    }
    
    public List<PhoneCall> groupbyname(){
    	SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); 
    	Cursor gcur = db.rawQuery("select count(*) as calltime,name,number from phone_call group by name,number order by calltime desc", 
    			null);
    	List<PhoneCall> gpersons = null;
    	if(gcur != null){  
             gpersons = new ArrayList<PhoneCall>();  
             while(gcur.moveToNext()){  
                 PhoneCall gperson = new PhoneCall();  
                 int calltime = gcur.getInt(gcur.getColumnIndex("calltime"));  
                 String name = gcur.getString(gcur.getColumnIndex("name"));
                 String number = gcur.getString(gcur.getColumnIndex("number"));
                 gperson.setCalltime(calltime);  
                 gperson.setName(name); 
                 gperson.setNumber(number);
                 gpersons.add(gperson);  
             }  
         }
    	db.close();
        return gpersons; 
    }
    
    public String maxidandtime(){
    	String maxidandtime = "";
    	int maxid;
    	long maxtime;
    	
    	SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); 
    	Cursor gcur = db.rawQuery("select max(_ID) as maxid from phone_call", null);
    	gcur.moveToFirst();
    	maxid = gcur.getInt(gcur.getColumnIndex("maxid"));
    	
    	Cursor gcur1 = db.rawQuery("select max(DATE) as maxtime from phone_call", null);
    	gcur1.moveToFirst();
    	maxtime = gcur1.getLong(gcur1.getColumnIndex("maxtime"));
    	db.close();
    	
    	maxidandtime = Integer.toString(maxid) + "," + Long.toString(maxtime);
    	return maxidandtime;
    }
    
    public void add(SMS s){}
    
    public void updata(SMS s){}
    
    public List<SMS> find1(){return null;}
    
    public SMS findById1(int id){return null;}
}
