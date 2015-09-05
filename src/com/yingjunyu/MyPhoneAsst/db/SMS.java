package com.yingjunyu.MyPhoneAsst.db;

public class SMS {
	private int _id;
	private String address;
	private String person;
	private String body;
	private Long date;
	private int stype;
	private int calltime;

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}
	
	public int getCalltime() {
		return calltime;
	}

	public void setCalltime(int calltime) {
		this.calltime = calltime;
	}

	@Override  
    public String toString() {  
        return "SMS [id=" + _id + ", address=" + address + ", person=" + person + ", body=" + body + ", date=" + date + ",stype=" + stype + "]";  
    }
	
	public String sString(){
		return address + "," + calltime + "," + person;
	}
      
    public SMS() {  
        super();  
    }  
      
    public SMS(int _id, String address,String person,String body,Long date,int stype) {  
        super();  
        this._id = _id;  
        this.address = address;  
        this.person = person;
        this.body = body;
        this.date = date;
        this.stype = stype;
    }
    
    public SMS(String address,Long date,int stype){
    	super();
    	this.address = address;
    	this.date = date;
    	this.stype = stype;
    }

}
