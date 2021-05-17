package com.joypatel.expensetrackerapi.services;

import com.joypatel.expensetrackerapi.domain.User;

public interface UserService {

    User validateUser(String email, String password);

    User registerUser(String firstName, String lastName, String email, String password);
}
