package fr.escalade.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
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
		auth.inMemoryAuthentication().withUser("admin").password(bc.encode("Projet6")).roles("ADMIN","USER");
		auth.inMemoryAuthentication().passwordEncoder(bc);
		
		/*auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select utilisateur as principal, password as credentials, active from utilisateur where pseudo=?")
		.authoritiesByUsernameQuery("select pseudo as principal, statut as role from utilisateur where pseudo=?")
		.rolePrefix("ROLE_")
		.passwordEncoder(getBC());
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select nom, password, active from utilisateur where nom=?")
		.authoritiesByUsernameQuery("select nom, statut from utilisateur where nom=?")
		;*/
		
		//System.out.println(bc.encode("jean"));
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin();
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Bean(name = "bc")
	BCryptPasswordEncoder getBC() {
		return new BCryptPasswordEncoder();
	}

}
