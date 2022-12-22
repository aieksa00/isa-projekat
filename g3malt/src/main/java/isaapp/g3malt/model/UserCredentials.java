package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import java.util.Collection;

@Entity
@Table(name="userCredentials")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserCredentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="email", unique=false, nullable=true)
	private String email;
	@Column(name="password", unique=false, nullable=true)
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(name = "verified", nullable=true)
	private Boolean verified;

	@Column(name = "verified_string", unique = true)
	private String verifiedString;
	
	public UserCredentials() {
		super();
	}
	public UserCredentials(Integer id, String email, String password, User user) {
		super();
		this.id = id;
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.user = user;
		this.verified = false;
		this.verifiedString = "";
	}
	public UserCredentials(Integer id, String email, String password, User user, boolean verified) {
		super();
		this.id = id;
		this.email = email;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.user = user;
		this.verified = verified;
		this.verifiedString = "";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getVerifiedString() {
		return verifiedString;
	}

	public void setVerifiedString(String verifiedString) {
		this.verifiedString = verifiedString;
	}
}