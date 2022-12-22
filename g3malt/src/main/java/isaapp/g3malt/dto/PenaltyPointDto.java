package isaapp.g3malt.dto;

public class PenaltyPointDto {
	private Integer customerId;
	private Integer penaltyPoint;
	
	public PenaltyPointDto() {}
			
	public PenaltyPointDto(Integer customerId, Integer penaltyPoint) {
		super();
		this.customerId = customerId;
		this.penaltyPoint = penaltyPoint;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getPenaltyPoint() {
		return penaltyPoint;
	}

	public void setPenaltyPoint(Integer penaltyPoint) {
		this.penaltyPoint = penaltyPoint;
	}
}
