package asu.turinggeeks.model;

public class Calendar {
	private String eventName;
	private String eventDescription;
	//private ArrayList<String> startDate;
	private String startTime;
	private String endTime;
	
	//private ArrayList<String> endDate;
	private String guestEmail;
	//private ArrayList<DateTime> dateTime;
	
	public Calendar() {
		super();
		//this.dateTime = new ArrayList<DateTime>();
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	
	/*public ArrayList<DateTime> getDateTime() {
		return this.dateTime;
	}
	public void setDateTime(ArrayList<DateTime> dateTime) {
		this.dateTime = dateTime;
	}*/
	/*public ArrayList<String> getStartDate() {
		return startDate;
	}
	public void setStartDate(ArrayList<String> startDate) {
		this.startDate = startDate;
	}*/
	/*public ArrayList<String> getStartTime() {
		return startTime;
	}
	public void setStartTime(ArrayList<String> startTime) {
		this.startTime = startTime;
	}
	public ArrayList<String> getEndTime() {
		return endTime;
	}
	public void setEndTime(ArrayList<String> endTime) {
		this.endTime = endTime;
	}
	public ArrayList<String> getEndDate() {
		return endDate;
	}
	public void setEndDate(ArrayList<String> endDate) {
		this.endDate = endDate;
	}*/
	
}
/*class DateTime{
	public String startDate;
	public String startTime;
	public String endDate;
	public String endTime;
	
	public DateTime() {
		super();
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}*/
