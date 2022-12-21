package isaapp.g3malt.config;

import isaapp.g3malt.security.RestAuthenticationEntryPoint;
import isaapp.g3malt.security.TokenAuthenticationFilter;
import isaapp.g3malt.services.UserCredentialsService;
import isaapp.g3malt.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import isaapp.g3malt.services.CustomUserDetailsService;

@Configuration
// Injektovanje bean-a za bezbednost
@EnableWebSecurity

// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Servis koji se koristi za citanje podataka o korisnicima aplikacije
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Autowired
	public UserCredentialsService userCredentialsService;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		// 1. koji servis da koristi da izvuce podatke o korisniku koji zeli da se autentifikuje
		// prilikom autentifikacije, AuthenticationManager ce sam pozivati loadUserByUsername() metodu ovog servisa
		authProvider.setUserDetailsService(userDetailsService());
		// 2. kroz koji enkoder da provuce lozinku koju je dobio od klijenta u zahtevu
		// da bi adekvatan hash koji dobije kao rezultat hash algoritma uporedio sa onim koji se nalazi u bazi (posto se u bazi ne cuva plain lozinka)
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	// Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
	@Autowired
	private TokenUtils tokenUtils;

	// Definisemo prava pristupa za zahteve ka odredjenim URL-ovima/rutama
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);

    	http.authorizeRequests().antMatchers("/UserCredentialsController/**").permitAll()
			.antMatchers("/userController/**").permitAll()
				// ukoliko ne zelimo da koristimo @PreAuthorize anotacije nad metodama kontrolera, moze se iskoristiti hasRole() metoda da se ogranici
			// koji tip korisnika moze da pristupi odgovarajucoj ruti. Npr. ukoliko zelimo da definisemo da ruti 'admin' moze da pristupi
			// samo korisnik koji ima rolu 'ADMIN', navodimo na sledeci nacin:
			// .antMatchers("/admin").hasRole("ADMIN") ili .antMatchers("/admin").hasAuthority("ROLE_ADMIN")

			// za svaki drugi zahtev korisnik mora biti autentifikovan
			.anyRequest().authenticated().and()
			// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
			.cors().and()

			// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils,  userDetailsService(), userCredentialsService), BasicAuthenticationFilter.class);

		// zbog jednostavnosti primera ne koristimo Anti-CSRF token (https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html)
		http.csrf().disable();

		// ulancavanje autentifikacije
		http.authenticationProvider(authenticationProvider());

		return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
		return (web) ->
    	web.ignoring().antMatchers(HttpMethod.POST, "/UserCredentialsController/registreUserCredentials", "/emailController/sendMail", "/userController/addRegisteredUser", "/UserCredentialsController/logIn")
		.antMatchers(HttpMethod.PUT, "/UserCredentialsController/updateUserCredentials")
		.antMatchers(HttpMethod.GET, "/UserCredentialsController/checkCredeentials", "/","/BloodBankController//getAllAppointmentsForBloodBank/{email}",
				"UserCredentialsController/GetUser/{id}", "/webjars/**", "/*.html", "favicon.ico",  "/**/*.html", "/**/*.css", "/**/*.js");
    }

}
