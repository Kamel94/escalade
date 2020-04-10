package fr.escalade.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		BCryptPasswordEncoder bc = getBC();

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select pseudo as principal, password as credentials, actif from utilisateur where pseudo=?")
		.authoritiesByUsernameQuery("select pseudo as principal, statut as role from utilisateur where pseudo=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(getBC());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.authorizeRequests().antMatchers("/").permitAll()
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/accueil")
			.failureUrl("/login?error=true").permitAll()
		.and()
		.logout()
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true)
			.invalidateHttpSession(true)
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login")
		.and()
		.sessionManagement()
		.maximumSessions(1)
		.expiredUrl("/login");

		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/membre/*").hasAnyRole("ADMIN", "MEMBRE");
		http.authorizeRequests().antMatchers("/user/*").hasAnyRole("ADMIN", "MEMBRE", "USER");
		http.exceptionHandling().accessDeniedPage("/403");

	}

	@Bean(name = "bc")
	BCryptPasswordEncoder getBC() {
		return new BCryptPasswordEncoder();
	}

}
