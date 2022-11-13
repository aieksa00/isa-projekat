package isaapp.g3malt.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@ElementCollection
	@CollectionTable(name="answers", joinColumns = @JoinColumn(name = "request_id"))
	@Column(name="answersList")
	//@OneToMany(mappedBy = "answersList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Boolean> answersList;

	public Request(Integer requestId, String appointmentID, String customerID, Set<Boolean> answersList) {
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

	public Set<Boolean> getAnswersList() {
		return answersList;
	}

	public void setAnswersList(Set<Boolean> answersList) {
		this.answersList = answersList;
	}

}