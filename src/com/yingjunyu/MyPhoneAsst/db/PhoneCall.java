package com.yingjunyu.MyPhoneAsst.db;

public class PhoneCall {
	private int _id;
	private Long duration;
	private int type;
	private Long date;
	private String number;
	private String name;
	private int calltime;
	
    public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
      
    public int getId() {  
        return _id;  
    }  
      
    public void setId(int _id) {  
        this._id = _id;  
    }  
      
    public String getName() {  
        return name;  
    }  
      
    public void setName(String name) {  
        this.name = name;  
    } 
      
    public int getCalltime() {
		return calltime;
	}

	public void setCalltime(int calltime) {
		this.calltime = calltime;
	}

	@Override  
    public String toString() {  
        return "Person [id=" + _id + ", duration=" + duration + ", type=" + type + ", number=" + number + ", date=" + date + ",name=" + name + "]";  
    }
	
	public String gString(){
		return number + "," + name + "," + calltime;
	}
      
    public PhoneCall() {  
        super();  
    }  
      
    public PhoneCall(int _id, String name,Long duration,Long date,int type,String number) {  
        super();  
        this._id = _id;  
        this.name = name;  
        this.duration = duration;
        this.date = date;
        this.type = type;
        this.number = number;
    }
    
    public PhoneCall(String name,String number,int calltime){
    	super();
    	this.name = name;
    	this.number = number;
    	this.calltime = calltime;
    }

}
