package isaapp.g3malt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Servis koji se koristi za citanje podataka o korisnicima aplikacije
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}

	// Injektujemo implementaciju iz TokenUtils klase kako bismo mogli da koristimo njene metode za rad sa JWT u TokenAuthenticationFilteru
//	@Autowired
//	private TokenUtils tokenUtils;

	// Definisemo prava pristupa za zahteve ka odredjenim URL-ovima/rutama
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // sve neautentifikovane zahteve obradi uniformno i posalji 401 gresku
       // http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
    	http.authorizeRequests().antMatchers("/UserCredentialsController/**").permitAll()
			.antMatchers("/userController/**").permitAll()
				// ukoliko ne zelimo da koristimo @PreAuthorize anotacije nad metodama kontrolera, moze se iskoristiti hasRole() metoda da se ogranici
			// koji tip korisnika moze da pristupi odgovarajucoj ruti. Npr. ukoliko zelimo da definisemo da ruti 'admin' moze da pristupi
			// samo korisnik koji ima rolu 'ADMIN', navodimo na sledeci nacin:
			// .antMatchers("/admin").hasRole("ADMIN") ili .antMatchers("/admin").hasAuthority("ROLE_ADMIN")

			// za svaki drugi zahtev korisnik mora biti autentifikovan
			.anyRequest().authenticated().and()
			// za development svrhe ukljuci konfiguraciju za CORS iz WebConfig klase
			.cors().and();

			// umetni custom filter TokenAuthenticationFilter kako bi se vrsila provera JWT tokena umesto cistih korisnickog imena i lozinke (koje radi BasicAuthenticationFilter)
//			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils,  userDetailsService()), BasicAuthenticationFilter.class);

		// zbog jednostavnosti primera ne koristimo Anti-CSRF token (https://cheatsheetseries.owasp.org/cheatsheets/Cross-Site_Request_Forgery_Prevention_Cheat_Sheet.html)
		http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    	web.ignoring().antMatchers(HttpMethod.POST, "/UserCredentialsController/registreUserCredentials", "/userController/addRegisteredUser");

		web.ignoring().antMatchers(HttpMethod.PUT, "/UserCredentialsController/updateUserCredentials");
		web.ignoring().antMatchers(HttpMethod.GET, "/UserCredentialsController/getAllUsersCredentials", "/", "/BloodBankController/BloodBank/{id}",  "UserCredentialsController/GetUser/{id}", "/webjars/**", "/*.html", "favicon.ico",
		"/**/*.html", "/**/*.css", "/**/*.js");

    }

}
