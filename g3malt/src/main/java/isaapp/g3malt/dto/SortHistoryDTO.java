package isaapp.g3malt.dto;

public class SortHistoryDTO {
    public String sortValue;
    public String email;

    public SortHistoryDTO () {}

    public SortHistoryDTO(String sortValue, String email) {
        this.sortValue = sortValue;
        this.email = email;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
