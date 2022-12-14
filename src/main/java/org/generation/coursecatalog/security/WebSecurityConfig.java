// package org.generation.coursecatalog.security;

// import org.generation.coursecatalog.filter.CustomAuthenticationFilter;
// import org.generation.coursecatalog.filter.CustomAuthorizationFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.authentication.AuthenticationFilter;

// import lombok.AllArgsConstructor;

// @Configuration
// @AllArgsConstructor
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//   private final UserDetailsService userDetailsService;
//   private final BCryptPasswordEncoder bCryptPasswordEncoder;

//   @Override
//   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//   }

//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//     http.csrf().disable();
//     //http.authorizeHttpRequests().anyRequest().permitAll();
//     http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/api/v1/courses/*").hasAnyAuthority("USER","ADMIN");
//     http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/v1/courses/*").hasAnyAuthority("ADMIN");
//     http.authorizeHttpRequests().anyRequest().authenticated();
//     http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//     http.addFilterBefore(new CustomAuthorizationFilter(), CustomAuthorizationFilter.class);
//   }
  
//   @Bean
//   public AuthenticationManager AuthenticationManagerBean() throws Exception {
//     return super.authenticationManager();
//   }
// }
package org.generation.coursecatalog.security;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.generation.coursecatalog.filter.CustomAuthenticationFilter;
import org.generation.coursecatalog.filter.CustomAuthorizationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/v1/registration").permitAll();
    http.authorizeHttpRequests().antMatchers(HttpMethod.GET, "/api/v1/courses/*").hasAnyAuthority("USER","ADMIN");
    http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/api/v1/courses/*").hasAnyAuthority("ADMIN");
    http.authorizeHttpRequests().anyRequest().authenticated();
    // http.authorizeHttpRequests().anyRequest().permitAll();
    http.addFilter(new CustomAuthenticationFilter (authenticationManagerBean()));
    http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    // http.authorizeHttpRequests().antMatchers("*").permitAll();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
