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

	public UserCredentialsDTO() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
