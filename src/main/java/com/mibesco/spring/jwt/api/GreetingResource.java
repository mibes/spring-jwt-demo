package com.mibesco.spring.jwt.api;

import com.mibesco.spring.jwt.auth.UserAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingResource {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public ResponseEntity<Greeting> getJars(UserAuthentication authentication) {

        String currentUser = authentication.getName();

        return ResponseEntity.ok(new Greeting("Hello: " + currentUser));
    }
}
