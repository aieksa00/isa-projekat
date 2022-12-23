package isaapp.g3malt.dto;

public class SortDTO {
    public String sortValue;
    public Integer bloodBankId;

    public SortDTO () {}

    public SortDTO(String sortValue, Integer bloodBankId) {
        this.sortValue = sortValue;
        this.bloodBankId = bloodBankId;
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
}
