package com.yingjunyu.MyPhoneAsst.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yingjunyu.MyPhoneAsst.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yingjunyu.MyPhoneAsst.db.*;

public class ContactsDbFragment extends Fragment{
	
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
        
        View view = inflater.inflate(R.layout.contacts_view, container, false);
        
        TextView tvtime = (TextView)view.findViewById(R.id.stime);
        TextView tvtotal = (TextView)view.findViewById(R.id.stotal);
        TextView tvincoming = (TextView)view.findViewById(R.id.sincoming);
        TextView tvin6coming = (TextView)view.findViewById(R.id.sin6coming);
        TextView tvoutgoing = (TextView)view.findViewById(R.id.soutgoing);
        TextView tvout6going = (TextView)view.findViewById(R.id.sout6going);
        TextView tvsinmaxnumber = (TextView)view.findViewById(R.id.sinmaxnumber);
        TextView tvsinmax6number = (TextView)view.findViewById(R.id.sinmax6number);
        TextView tvsoutmaxnumber = (TextView)view.findViewById(R.id.soutmaxnumber);
        TextView tvsoutmax6number = (TextView)view.findViewById(R.id.soutmax6number);
        TextView tvfee = (TextView)view.findViewById(R.id.sfee);
        
        CallSQLOperateImpl CDb = new CallSQLOperateImpl(getActivity());
		List<PhoneCall> gpersons = CDb.find();
		
        
        long incoming = 0L; 
        long incoming6 = 0L;
        long maxincoming = 0L;
        long maxincoming6 = 0L;
        long outgoing = 0L; 
        long outgoing6 = 0L;
        long maxoutgoing = 0L;
        long maxoutgoing6 = 0L;
        int count = 0; 
        
        for(int i = 0; i < gpersons.size(); i ++) { 
        	PhoneCall pc = (PhoneCall)gpersons.get(i);

        	Date date;
            SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            date = new Date(pc.getDate());
            String stime = sfd.format(date);
            stime = stime.substring(0, 7);
            
            String snumber,sname;
            snumber = pc.getNumber();
			sname = pc.getName();
            
            
            if(stime.equals(xdate)){
            	int type = pc.getType(); 
            	long duration = pc.getDuration(); 
        	
            	switch (type) { 
        			case 1: 
        				incoming += duration;
        				if(duration > maxincoming){
        					maxincoming = duration;
        					maxsnumber = snumber;
        					inname = sname;
        				}
        				if(snumber.length()==6){
        					incoming6 += duration;
        					if(duration > maxincoming6){
        						maxincoming6 = duration;
        						max6snumber = snumber;
        						in6name = sname;
        					}
        				}
        				break; 
        			case 2: 
        				outgoing += duration; 
        				if(duration > maxoutgoing){
        					maxoutgoing = duration;
        					maxsnumber1 = snumber;
        					outname = sname;
        				}
        				if(snumber.length()==6){
        					outgoing6 += duration;
        					if(duration > maxoutgoing6){
        						maxoutgoing6 = duration;
        						max6snumber1 = snumber;
        						out6name = sname;
        					}
        				}
        				break;
        			default: 
        				break; 
            	}
            	count++; 
            }	
        }
        
       
        tvtime.setText(xdate.substring(0,4) + "年" + xdate.substring(5) + "月通话情况");
        tvtotal.setText(count + "次通话，" + (incoming+outgoing)/60 + "分钟时长。");
        tvincoming.setText("接听时间：" + incoming/60 + "分钟时长。");
        //tvin6coming.setText("集团接听：" + incoming6/60 + "分钟时长。");
        tvoutgoing.setText("拨打时间：" + outgoing/60 + "分钟时长。");
        //tvout6going.setText("集团拨打：" + outgoing6/60 + "分钟时长。");
        
        if(inname == null) {inname = "无记录";}
        tvsinmaxnumber.setText("通话号码：" + maxsnumber + "（" + inname + "），通话时间：" + maxincoming/60 + "分钟时长。");
        
        //if(in6name == null) {in6name = "无记录";}
        //tvsinmax6number.setText("通话号码：" + max6snumber + "（" + in6name + "），通话时间：" + maxincoming6/60 + "分钟时长。");
        
        if(outname == null) {outname = "无记录";}
        tvsoutmaxnumber.setText("通话号码：" + maxsnumber1 + "（" + outname + "），通话时间：" + maxoutgoing/60 + "分钟时长。");
        
        //if(out6name == null) {out6name = "无记录";}
        //tvsoutmax6number.setText("通话号码：" + max6snumber1 + "（" + out6name + "），通话时间：" + maxoutgoing6/60 + "分钟时长。");
        
        //tvfee.setText("合计收费通话时间：" + (((incoming+outgoing)/60)-((incoming6+outgoing6)/60)) + "分钟时长。");
        tvfee.setText("合计收费通话时间：" + (outgoing/60) + "分钟时长。");
        return view;
	}

}
