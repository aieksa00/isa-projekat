package isaapp.g3malt.model;

import java.util.*;

public class Request {

	private String appointmentID;
	private String customerID;
	private List<Boolean> answersList;

	public Request(String appointmentID, String customerID, List<Boolean> answersList) {
		super();
		this.appointmentID = appointmentID;
		this.customerID = customerID;
		this.answersList = answersList;
	}

	public String getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(String appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public List<Boolean> getAnswersList() {
		return answersList;
	}

	public void setAnswersList(List<Boolean> answersList) {
		this.answersList = answersList;
	}

}