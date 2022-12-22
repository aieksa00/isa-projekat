package isaapp.g3malt.dto;

public class SearchBanksDTO {
    private String search;
    private String searchByName;
    private String filterValue;
    private String sortValue;
   
    public SearchBanksDTO() {}

    public SearchBanksDTO(String search, String searchByName, String filterValue, String sortValue) {
        this.search = search;
        this.searchByName = searchByName;
        this.filterValue = filterValue;
        this.sortValue = sortValue;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearchByName() {
        return this.searchByName;
    }

    public void setSearchByName(String searchByName) {
        this.searchByName = searchByName;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public String getSortValue() {
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }
}
