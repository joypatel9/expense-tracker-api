package com.joypatel.expensetrackerapi.services;

import com.joypatel.expensetrackerapi.domain.User;
import com.joypatel.expensetrackerapi.exceptions.EtAuthException;
import com.joypatel.expensetrackerapi.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) {
        if (email != null) email = email.toLowerCase();

        try {
            User user = userRepository.findByEmail(email);
            if (!BCrypt.checkpw(password, user.getPassword()))
                throw new EtAuthException("Invalid email/password");
            return user;
        } catch (EmptyResultDataAccessException e) {
            throw new EtAuthException("Invalid email/password");
        }
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if (email != null)
            email = email.toLowerCase();

        if (!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");

        long count = userRepository.countByEmail(email);
        if (count > 0)
            throw new EtAuthException("Email already in use");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}
