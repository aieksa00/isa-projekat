package isaapp.g3malt.dto;

import java.util.Date;

import isaapp.g3malt.model.Customer;

public class CalenderEventDto {
	private String id;
    private Date start;
    private Date end;
    private String text;
    
    public CalenderEventDto(String id,Date start, int duration, Customer customer) {
    	this.id = id;
    	this.start = start;
    	this.end = new Date(start.getTime());
    	this.end.setMinutes(start.getMinutes()+duration);
    	if(customer == null)
    		this.text = "Free appointment";
    	else
    		this.text = "Reserved by " + customer.getName() + " " + customer.getSurname();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
