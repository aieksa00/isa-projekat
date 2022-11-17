package isaapp.g3malt.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCredentialsDTO {
	@NotNull
	@NotEmpty
	@Email
	public String email;
	@NotNull
	@NotEmpty
	@Min(5)
	public String password;
	public int userId;
	public UserCredentialsDTO(String email, String password,int userId) {
		this.email = email;
		this.password = password;
		this.userId = userId;
	}
	
}
