package isaapp.g3malt.dto;

public class AppointmentReviewDto {
	
	private Integer bloodBankId;
    private Integer customerId;
    
    public AppointmentReviewDto() {}
    
	public AppointmentReviewDto(Integer bloodBankId, Integer customerId) {
		super();
		this.bloodBankId = bloodBankId;
		this.customerId = customerId;
	}
	
	public Integer getBloodBankId() {
		return bloodBankId;
	}
	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
}
