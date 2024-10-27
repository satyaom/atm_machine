package com.atm.service;

import com.atm.exception.AuthException;
import com.atm.model.User;
import com.atm.respository.UserRepository;
import com.atm.util.TokenGenerator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RedisService redisService;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public Optional<User> getUserById(Long userId) { return userRepository.findById(userId);}

    public String genToken(String userId) {
        String token =  TokenGenerator.generateDigestToken(userId);
        redisService.addAuthToken(token, "token-"+userId);
        return "Basic " + Base64.encodeBase64String((userId + ":" + token).getBytes());
    }
    public User getUserByToken(String token, String userId) {
        // get token from redis
        String tokenFromRedis = redisService.getAuthToken("token-" + userId);
        if (!tokenFromRedis.isEmpty() && tokenFromRedis.endsWith(token)) {
            Optional<User> user = getUserById(Long.parseLong(userId));
            if (user.isPresent()) {
                redisService.updateExpiryForAuthToken(userId);
                return user.get();
            }
        }
        throw new AuthException();
    }

    public boolean isUserExist(Long userId) {
        return userRepository.userExist(userId);
    }
}
