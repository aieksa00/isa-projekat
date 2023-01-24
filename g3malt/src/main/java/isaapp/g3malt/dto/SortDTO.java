package isaapp.g3malt.dto;

public class SortDTO {
    public String sortValue;
    public Integer bloodBankId;
    public String email;

    public SortDTO () {}

    public SortDTO(String sortValue, Integer bloodBankId, String email) {
        this.sortValue = sortValue;
        this.bloodBankId = bloodBankId;
        this.email = email;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public Integer getBloodBankId() {
        return bloodBankId;
    }

    public void setBloodBankId(Integer bloodBankId) {
        this.bloodBankId = bloodBankId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
