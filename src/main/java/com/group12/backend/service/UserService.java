package com.group12.backend.service;

import com.group12.backend.domain.AppUser;
import com.group12.backend.domain.Role;

import java.util.List;

/**
 * Interface for user service
 */
public interface UserService {
    /**
     * save a user function
     * @param user
     * @return
     */
    AppUser saveUser(AppUser user);

    /**
     * save a role function
     * @param role
     * @return
     */
    Role saveRole(Role role);

    /**
     * Add a role to a user function
     * @param username
     * @param roleName
     */
    void addRoleToUser(String username, String roleName);

    /**
     * Get a single user
     * @param username
     * @return
     */
    AppUser getUser(String username);

    /**
     * Get all the users
     * @return
     */
    List<AppUser> getUsers();
}
