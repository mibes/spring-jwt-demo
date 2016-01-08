package com.mibesco.spring.jwt.auth;

import com.mibesco.spring.jwt.validation.StringConditions;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.springframework.security.core.userdetails.User;

import java.util.Calendar;

public class TokenHandler {
    private final String secret;
    private final UserService userService;

    private final int expires = 60; //minutes

    public TokenHandler(String secret, UserService userService) {
        this.secret = StringConditions.checkNotBlank(secret);
        this.userService = Preconditions.checkNotNull(userService);
    }

    public User parseUserFromToken(String token) {
        try {
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return userService.loadUserByUsername(username);
        }
        catch (SignatureException e) {
            return null;
        }
        catch (ExpiredJwtException e) {
            return null;
        }
    }

    public String createTokenForUser(User user) {
        Calendar calendar = Calendar.getInstance();

        // expire quickly
        calendar.add(Calendar.MINUTE, expires);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
