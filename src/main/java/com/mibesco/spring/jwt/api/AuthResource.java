package com.mibesco.spring.jwt.api;

import com.mibesco.spring.jwt.auth.TokenAuthenticationService;
import com.mibesco.spring.jwt.auth.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger log = LoggerFactory.getLogger(AuthResource.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<RestResult> login(@RequestBody RestUser restUser) {

        User user = userService.loadUserByUsername(restUser.getUsername());

        try {
            if (passwordEncoder.matches(restUser.getPassword(), user.getPassword())) {
                String token = tokenAuthenticationService.createToken(user);
                return ResponseEntity.ok(new RestResult(true, token));
            }
        }
        catch (AuthenticationException e) {
            log.error("Authentication error: ", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new RestResult(false, "Forbidden"));
    }

}
