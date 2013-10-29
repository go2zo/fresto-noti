package OpenSourceAPM.esper;

public class PageCallEvent {
	private String ipAddress;
	private String uuid;
	private long responseTime;
	private String url;
	
	public PageCallEvent(String IP_Address, String UUID, long ResponseTime,String URL){
		super();
		this.ipAddress = IP_Address;
		this.uuid = UUID;	
		this.responseTime = ResponseTime;
		this.url = URL;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public String getUuid(){
		return uuid;
	}
	public long getResponseTime(){
		return responseTime;
	}
	public String getUrl(){
		return url;
	}
	
	
	
	

}
