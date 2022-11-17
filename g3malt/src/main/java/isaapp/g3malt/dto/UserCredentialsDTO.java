package isaapp.g3malt.dto;

public class UserCredentialsDTO {
	public String email;
	public String password;
	public int userId;
	public UserCredentialsDTO(String email, String password,int userId) {
		this.email = email;
		this.password = password;
		this.userId = userId;
	}
	
}
