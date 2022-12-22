package isaapp.g3malt.dto;

public class UpdateBloodBankStorageDto {
	
	private Integer bloodBankId;
	private String bloodType;
	
	public UpdateBloodBankStorageDto() {}
	
	public UpdateBloodBankStorageDto(Integer bloodBankId, String bloodType) {
		super();
		this.bloodBankId = bloodBankId;
		this.bloodType = bloodType;
	}

	public Integer getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Integer bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
}
