package com.atm.controller;

import com.atm.auth.CurrentUserSession;
import com.atm.enums.UserRole;
import com.atm.model.User;
import com.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    CurrentUserSession currentUserSession;

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> getToken(
            @RequestParam(value = "user_id") String user_id
    ) {
        Map<String, Object> res = new HashMap<>();
        if(!userService.isUserExist(Long.parseLong(user_id))) {
            res.put("message", "user not found");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.put("token", userService.genToken(user_id));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> saveCustomer(
            @RequestBody User user
    ) {
        User newUser = userService.saveUser(user);
        Map<String, Object> res = new HashMap<>();
        res.put("user", newUser);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
