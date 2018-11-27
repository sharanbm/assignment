package com.tcs.evaluate.auth;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class OauthServiceApplication {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	@Bean
	UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate) {
		RowMapper<User> userDetailsRowMapper = (rs, i) -> new User(rs.getString("ACCOUNT_NAME"),
				rs.getString("PASSWORD"), rs.getBoolean("ENABLED"), rs.getBoolean("ENABLED"), rs.getBoolean("ENABLED"),
				rs.getBoolean("ENABLED"), AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
		return username -> jdbcTemplate.queryForObject("select * from ACCOUNT where ACCOUNT_NAME = ?",
				userDetailsRowMapper, username);
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthServiceApplication.class, args);
	}

}