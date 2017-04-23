package edu.infsci2560;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
			.authorizeRequests()
				.antMatchers("/", "/home", "/about", "/contact-us", "/about.html", "/public/**", "/login", "/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
				.loginPage("/login")
                .failureUrl("/login?error=true")
				.defaultSuccessUrl("/")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
            .logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
                .permitAll()
                .and()
            .exceptionHandling()
				.accessDeniedPage("/access-denied");
    }

    @Override
	public void configure(WebSecurity web) throws Exception {
        
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
			.jdbcAuthentication()
				.usersByUsernameQuery("select email, password, active from users where email=?")
				.authoritiesByUsernameQuery("select u.email, ur.roles from users u inner join user_roles ur on(u.id=ur.user_id) where u.email=?")
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
				
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		//auth.inMemoryAuthentication().withUser("user").password("$2a$10$3/VlLaILAG45pU/SV3KDoe1/PFRLiOYzrumwUDdZL.YNpUGZhMKie").roles("USER");
    }
}