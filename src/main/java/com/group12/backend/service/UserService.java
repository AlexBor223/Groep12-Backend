package com.group12.backend.service;

import com.group12.backend.domain.AppUser;
import com.group12.backend.domain.Role;

import java.util.List;

public interface UserService {

    AppUser saveUser(AppUser user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();
}
