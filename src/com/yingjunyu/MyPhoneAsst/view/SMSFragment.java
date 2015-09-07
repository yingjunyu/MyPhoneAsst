package com.yingjunyu.MyPhoneAsst.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yingjunyu.MyPhoneAsst.db.CallSQLOperateImpl;
import com.yingjunyu.MyPhoneAsst.db.PhoneCall;
import com.yingjunyu.MyPhoneAsst.db.SMS;
import com.yingjunyu.MyPhoneAsst.db.SMSSQLOperateImpl;
import com.yingjunyu.MyPhoneAsst.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 * 
 * http://blog.csdn.net/guolin_blog/article/details/26365683
 * 
 * @author guolin
 */
public class SMSFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		String xdate,maxsnumber,maxsnumber1,max6snumber,max6snumber1,inname,outname,in6name,out6name;
        SimpleDateFormat xfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        xdate = xfd.format(new Date()).substring(0,7);
        maxsnumber = "无记录";
        maxsnumber1 = "无记录";
        max6snumber = "无记录";
        max6snumber1 = "无记录";
        inname = "无记录";
        outname = "无记录";
        in6name = "无记录";
        out6name = "无记录";
        
        View view = inflater.inflate(R.layout.sms_view, container, false);
        
        TextView tvtime = (TextView)view.findViewById(R.id.stime1);
        TextView tvtotal = (TextView)view.findViewById(R.id.stotal1);
        TextView tvincoming = (TextView)view.findViewById(R.id.sincoming1);
        //TextView tvin6coming = (TextView)view.findViewById(R.id.sin6coming1);
        TextView tvoutgoing = (TextView)view.findViewById(R.id.soutgoing1);
        //TextView tvout6going = (TextView)view.findViewById(R.id.sout6going1);
        //TextView tvsinmaxnumber = (TextView)view.findViewById(R.id.sinmaxnumber);
        //TextView tvsinmax6number = (TextView)view.findViewById(R.id.sinmax6number);
        //TextView tvsoutmaxnumber = (TextView)view.findViewById(R.id.soutmaxnumber);
        //TextView tvsoutmax6number = (TextView)view.findViewById(R.id.soutmax6number);
        //TextView tvfee = (TextView)view.findViewById(R.id.sfee);
        
        SMSSQLOperateImpl SDb = new SMSSQLOperateImpl(getActivity());
		List<SMS> spersons = SDb.find1();
		
        
        long incoming = 0L; 
        long incoming6 = 0L;
        long maxincoming = 0L;
        long maxincoming6 = 0L;
        long outgoing = 0L; 
        long outgoing6 = 0L;
        long maxoutgoing = 0L;
        long maxoutgoing6 = 0L;
        int count = 0; 
        
        for(int i = 0; i < spersons.size(); i ++) { 
        	SMS sms = (SMS)spersons.get(i);

        	Date date;
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            date = new Date(sms.getDate());
            String stime = sfd.format(date);
            stime = stime.substring(0, 7);
            
            String snumber;
            snumber = sms.getAddress();
			//sname = pc.getName();
            
            
            if(stime.equals(xdate)){
            	int type = sms.getStype(); 
            	//long duration = pc.getDuration(); 
        	
            	switch (type) { 
        			case 1: 
        				incoming ++;
        				
        				if(snumber.length()==6){
        					incoming6 ++;
        				}
        				break; 
        			case 2: 
        				outgoing ++; 
        				
        				if(snumber.length()==6){
        					outgoing6 ++;
        				}
        				break;
        			default: 
        				break; 
            	}
            	count++; 
            }	
        }
        
       
        tvtime.setText(xdate.substring(0,4) + "年" + xdate.substring(5) + "月短信情况");
        tvtotal.setText("共有短信" + count + "条。");
        tvincoming.setText("接收短信：" + incoming + "条。");
        //tvin6coming.setText("接收集团短信：" + incoming6 + "条。");
        tvoutgoing.setText("发送短信：" + outgoing + "条。");
        //tvout6going.setText("发送集团短信：" + outgoing6 + "条。");
       
        return view;
	}
}