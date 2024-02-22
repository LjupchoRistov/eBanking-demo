package com.example.racepulse.data;

import com.example.racepulse.models.Role;
import com.example.racepulse.models.UserEntity;
import com.example.racepulse.repository.RoleRepository;
import com.example.racepulse.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
public class DataInitializer {

    private final NewsArticleRepository newsArticleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public DataInitializer(NewsArticleRepository newsArticleRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.newsArticleRepository = newsArticleRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initData() {
        if (roleRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()){
            Role adminRole = new Role((long) 1, "ADMIN", new ArrayList<>());
            Role editorRole = new Role((long) 2, "EDITOR", new ArrayList<>());
            Role userRole = new Role((long) 3, "USER", new ArrayList<>());

            this.roleRepository.save(adminRole);
            this.roleRepository.save(editorRole);
            this.roleRepository.save(userRole);

            String username = "bubsi";
            String email = "bubsi@gmail.com";
            String salt = createSalt();
            String hashedPassword = (hashPassword(combinePasswordAndSalt("Bubsi123!", salt.getBytes())));
            Role role = roleRepository.findByName("USER");
            List<Role> roles = (Arrays.asList(role));
            UserEntity user = new UserEntity((long) 999, username, email, hashedPassword, salt, roles);

            this.userRepository.save(user);
        }
    }

    //todo: implement SALT creation
    private String createSalt() {
        byte[] salt = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // Convert the byte array to a Base64-encoded string
        return Base64.getEncoder().encodeToString(salt);
    }

    //todo: combine pass and salt
    private static String combinePasswordAndSalt(String password, byte[] salt) {
        return password + Base64.getEncoder().encodeToString(salt);
    }

    //todo: hash password
    private static String hashPassword(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

