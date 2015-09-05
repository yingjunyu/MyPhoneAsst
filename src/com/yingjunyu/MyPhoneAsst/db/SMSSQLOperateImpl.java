package com.yingjunyu.MyPhoneAsst.db;

import java.util.ArrayList;  
import java.util.List;  
  
import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;

public class SMSSQLOperateImpl implements SQLOperate{  
	  
    private DBOpenHelper dbOpenHelper;  
      
    public SMSSQLOperateImpl(Context context) {  
        dbOpenHelper = new DBOpenHelper(context);  
    } 
    
    public void dosql(String sql){
    	SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
    	db.execSQL(sql);
    	db.close();
    }
  
    
    public void add(SMS s) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        ContentValues values = new ContentValues();  
        values.put(DBOpenHelper._ID, s.getId());  
        values.put(DBOpenHelper.ADDRESS, s.getAddress()); 
        values.put(DBOpenHelper.PERSON1, s.getPerson());
        values.put(DBOpenHelper.DATE1, s.getDate());
        values.put(DBOpenHelper.BODY1, s.getBody());
        values.put(DBOpenHelper.STYPE, s.getStype());
        db.insert(DBOpenHelper._TABLE1, null, values);  
        db.close();
    }  
  
    
    public void delete(int id) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        db.delete(DBOpenHelper._TABLE1, DBOpenHelper._ID + "=?", new String[]{String.valueOf(id)});  
        db.close();
    }  
  
   
    public void updata(SMS s) {  
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();  
        ContentValues values = new ContentValues();  
        values.put(DBOpenHelper._ID, s.getId());  
        values.put(DBOpenHelper.ADDRESS, s.getAddress()); 
        values.put(DBOpenHelper.PERSON1, s.getPerson());
        values.put(DBOpenHelper.DATE1, s.getDate());
        values.put(DBOpenHelper.BODY1, s.getBody());
        values.put(DBOpenHelper.STYPE, s.getStype());
        db.update(DBOpenHelper._TABLE1, values, DBOpenHelper._ID + "=?", new String[]{String.valueOf(s.getId())});  
        db.close();
    }  
  
    
    public List<SMS> find1() {  
        List<SMS> persons = null;  
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();  
        Cursor cursor = db.query(DBOpenHelper._TABLE1, null, null, null, null, null, null);  
        if(cursor != null){  
            persons = new ArrayList<SMS>();  
            while(cursor.moveToNext()){  
                SMS person = new SMS();  
                int _id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));  
                String address = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS));
                int person1 = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.PERSON1));
                String body1 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.BODY1));
                Long date1 = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DATE1));
                int stype = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.STYPE));
                person.setId(_id);  
                person.setAddress(address); 
                person.setPerson(String.valueOf(person1));
                person.setBody(body1);
                person.setDate(date1);
                person.setStype(stype);
                persons.add(person);  
            }  
        } 
        db.close();
        return persons;  
    }  
  
    
    public SMS findById1(int id) {  
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();  
        Cursor cursor = db.query(DBOpenHelper._TABLE1, null, DBOpenHelper._ID + "=?", new String[]{String.valueOf(id)}, null, null, null);  
        SMS person = null;  
        if(cursor != null && cursor.moveToFirst()){  
            person = new SMS();  
            int _id = cursor.getInt(cursor.getColumnIndex(DBOpenHelper._ID));  
            String address = cursor.getString(cursor.getColumnIndex(DBOpenHelper.ADDRESS));
            int person1 = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.PERSON1));
            String body1 = cursor.getString(cursor.getColumnIndex(DBOpenHelper.BODY1));
            Long date1 = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.DATE1));
            int stype = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.STYPE));
            person.setId(_id);  
            person.setAddress(address); 
            person.setPerson(String.valueOf(person1));
            person.setBody(body1);
            person.setDate(date1);
            person.setStype(stype);
        } 
        db.close();
        return person;  
    }
    
    public List<SMS> groupbyname(){
    	SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); 
    	Cursor gcur = db.rawQuery("select count(*) as calltime,address,person from sms_call group by address,person order by calltime desc", 
    			null);
    	List<SMS> spersons = null;
    	if(gcur != null){  
             spersons = new ArrayList<SMS>();  
             while(gcur.moveToNext()){  
                 SMS sperson = new SMS();  
                 int calltime = gcur.getInt(gcur.getColumnIndex("calltime"));  
                 String address = gcur.getString(gcur.getColumnIndex("address"));
                 String person1 = gcur.getString(gcur.getColumnIndex("person"));
                 sperson.setCalltime(calltime);  
                 sperson.setAddress(address); 
                 sperson.setPerson(person1);
                 spersons.add(sperson);  
             }  
         }
    	db.close();
        return spersons; 
    }
    
    public String maxidandtime(){
    	String maxidandtime = "";
    	int maxid;
    	long maxtime;
    	
    	SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); 
    	Cursor gcur = db.rawQuery("select max(_ID) as maxid from sms_call", null);
    	gcur.moveToFirst();
    	maxid = gcur.getInt(gcur.getColumnIndex("maxid"));
    	
    	Cursor gcur1 = db.rawQuery("select max(DATE) as maxtime from sms_call", null);
    	gcur1.moveToFirst();
    	maxtime = gcur1.getLong(gcur1.getColumnIndex("maxtime"));
    	db.close();
    	
    	maxidandtime = Integer.toString(maxid) + "," + Long.toString(maxtime);
    	return maxidandtime;
    }
    
    public void add(PhoneCall p){}
    
    public void updata(PhoneCall p){}
    
    public List<PhoneCall> find(){return null;}
    
    public PhoneCall findById(int id){return null;}
}
