package com.yingjunyu.MyPhoneAsst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yingjunyu.MyPhoneAsst.db.PhoneCall;
import com.yingjunyu.MyPhoneAsst.db.CallSQLOperateImpl;
import com.yingjunyu.MyPhoneAsst.db.SMS;
import com.yingjunyu.MyPhoneAsst.db.SMSSQLOperateImpl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.CallLog.Calls;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class Welcome extends Activity{
	private AlphaAnimation start_anima;
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		view = View.inflate(this, R.layout.welcome, null);
		setContentView(view);
		initView();
		initData();
	}
	private void initData() {
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectTo();
			}
		});
	}
	
	private void initView() {
		
	}

	private void redirectTo(){
		doCallDate();
		doSMSDate();
		//SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		//Date date = sdf2.parse( "2008-07-10 19:20:00" );
		/*try {
			insertCallLog("189 7157 7466","2070","2","1",0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		startActivity(new Intent(getApplicationContext(), MenuActivity.class));
		finish();
	}
	
	private void doCallDate(){
		CallSQLOperateImpl test = new CallSQLOperateImpl(this);
		String maxidandtime = test.maxidandtime();
		String mt[] = maxidandtime.split(",");
		int maxid = Integer.parseInt(mt[0]) + 1;
		long maxtime = Long.parseLong(mt[1]);
		
		Cursor cursor = getApplication().getContentResolver().query(CallLog.Calls.CONTENT_URI,
        		new String[] { Calls.DURATION, Calls.TYPE, Calls.DATE,Calls.NUMBER,Calls.CACHED_NAME},
        		"Calls.DATE > " + maxtime, null, CallLog.Calls.DEFAULT_SORT_ORDER);
		boolean hasRecord = cursor.moveToFirst();
    	int id = 0;
    	while(hasRecord){
    		long duration = cursor.getLong(cursor.getColumnIndexOrThrow(Calls.DURATION));
    		long date = cursor.getLong(cursor.getColumnIndexOrThrow(Calls.DATE));
    		int type = cursor.getInt(cursor.getColumnIndexOrThrow(Calls.TYPE));
    		String name = cursor.getString(cursor.getColumnIndexOrThrow(Calls.CACHED_NAME));
    		if(name == null){
    			name = "无记录";
    		}
    		String number = cursor.getString(cursor.getColumnIndexOrThrow(Calls.NUMBER));
    		
    		PhoneCall person = new PhoneCall(maxid, name, duration, date, type, number);
    		test.add(person);
    		
    		id ++;
    		maxid ++;
    		
    		hasRecord = cursor.moveToNext();	
    	}
	}
	
	private void doSMSDate(){
		
		Uri uri = Uri.parse("content://sms/");  
		
		SMSSQLOperateImpl test1 = new SMSSQLOperateImpl(this);
		String maxidandtime = test1.maxidandtime();
		String mt[] = maxidandtime.split(",");
		int maxid = Integer.parseInt(mt[0]) + 1;
		long maxtime = Long.parseLong(mt[1]);
		String sname = "";
		
		Cursor cursor = getApplicationContext().getContentResolver().query(uri,
	        		new String[] { "_id", "address", "person", "body", "date", "type" },
	        		"date > " + maxtime, null, CallLog.Calls.DEFAULT_SORT_ORDER);
		boolean hasRecord = cursor.moveToFirst();
    	int id = 0;
    	while(hasRecord){
    		long date1 = cursor.getLong(cursor.getColumnIndexOrThrow("date"));
    		int stype = cursor.getInt(cursor.getColumnIndexOrThrow("type"));
    		String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
    		String person1 = findName(address);
			//int person1 = cursor.getInt(cursor.getColumnIndexOrThrow("person"));
    		//sname = String.valueOf(person1);
    		String body1 = cursor.getString(cursor.getColumnIndexOrThrow("body"));
    		
    		SMS person = new SMS(maxid, address, person1, body1, date1, stype);
    		test1.add(person);
    		
    		id ++;
    		maxid ++;
    		
    		hasRecord = cursor.moveToNext();	
    	}
	}
	
	public String findName(String sNumber){
		String[] phoneNumberProjection = new String[] {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID };
		if(sNumber.length() == 14){
			sNumber = sNumber.substring(3);
		}
		Cursor cursor = getApplication().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
				phoneNumberProjection, "data1 = " + sNumber, null, null);
		String sss = "无记录";
		try{
			boolean hasRecord = cursor.moveToFirst();
			while(hasRecord){
				sss = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
				hasRecord = cursor.moveToNext();
			}
		}catch(Exception e){
			System.out.println("some error!");
		}
		
		return sss;
	}
	
	private void insertCallLog(String string,String string2, String string3, String string4, long i) throws ParseException
	{
	    // TODO Auto-generated method stub
		//SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		//Date date = new Date();
		//try {
		//	date = sdf2.parse( "2015-08-22 19:20:00" );
		//} catch (ParseException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		//long lSysTime1 = date.getTime() / 1000;
		//System.out.println(lSysTime1);
		String sDt = "08/19/2015 09:25:14";
		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dt2 = sdf.parse(sDt);
		//����ת���õ�������long��
		long lTime = dt2.getTime();
	    ContentValues values = new ContentValues(); 
	    values.put(CallLog.Calls.NUMBER, string);
	    //values.put(CallLog.Calls.DATE, System.currentTimeMillis()+i);
	    values.put(CallLog.Calls.DATE, lTime+i);
	    values.put(CallLog.Calls.DURATION, string2);
	    values.put(CallLog.Calls.TYPE,string3);//δ��
	    values.put(CallLog.Calls.NEW, string4);//0�ѿ�1δ��
		    
	    getContentResolver().insert(CallLog.Calls.CONTENT_URI, values);
	}
}
