package isaapp.g3malt.model;

import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="requests")
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requestId;
	@Column(name="appointmentId", unique=false, nullable=true)
	private String appointmentID;
	@Column(name="customerId", unique=false, nullable=true)
	private String customerID;
	private List<Boolean> answersList;

	public Request(Integer requestId, String appointmentID, String customerID, List<Boolean> answersList) {
		super();
		this.appointmentID = appointmentID;
		this.customerID = customerID;
		this.answersList = answersList;
		this.requestId = requestId;
	}

	public Request() {
		super();
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
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