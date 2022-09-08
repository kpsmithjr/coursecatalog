package org.generation.coursecatalog.controller;

import org.generation.coursecatalog.entity.AppUser;
import org.generation.coursecatalog.service.AppUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AppUserController {
  private final AppUserService appUserService;

  @PostMapping("api/v1/registration")
  public String register(@RequestBody AppUser appUser) {
    return appUserService.register(appUser);
  }
}
