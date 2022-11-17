package isaapp.g3malt.dto;

import javax.validation.constraints.*;

public class UserCredentialsDTO {
	@NotNull
	@NotEmpty
	@Email
	public String email;
	@NotNull
	@NotEmpty
	@Size(min=5)
	public String password;
	public int userId;
	public UserCredentialsDTO(String email, String password,int userId) {
		this.email = email;
		this.password = password;
		this.userId = userId;
	}
	
}
