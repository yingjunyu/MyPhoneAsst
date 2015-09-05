package com.yingjunyu.MyPhoneAsst.db;

import java.util.List;

public interface SQLOperate {  
    public void add(PhoneCall p);
    public void add(SMS s);
    public void delete(int id);  
    public void updata(PhoneCall p); 
    public void updata(SMS s);
    public List<PhoneCall> find();  
    public List<SMS> find1();
    public PhoneCall findById(int id);  
    public SMS findById1(int id);
}  
