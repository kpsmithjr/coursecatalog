package org.generation.coursecatalog.service;

import org.generation.coursecatalog.entity.AppUser;
import org.generation.coursecatalog.entity.AppUserRole;
import org.generation.coursecatalog.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
  private final AppUserRepository appUserRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  private final String USER_NOT_FOUND_MSG = "user with email %s not found";

  public String register(AppUser appUser) {
    boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

    if (userExists) {
      throw new IllegalStateException("Email is already used");
    }

    String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
    appUser.setPassword(encodedPassword);

    appUser.setRole(AppUserRole.USER);
    appUserRepository.save(appUser);
    
    return "Registered";
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return appUserRepository.findByEmail(email)
    .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
  }
  
}
