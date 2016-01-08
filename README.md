# Simple JWT example
Based on the excellent example from Erik Gillespie: https://github.com/technical-rex/spring-security-jwt

Check out his blog entry: http://technicalrex.com/2015/02/20/stateless-authentication-with-spring-security-and-jwt/ 

## Instructions 

On startup 3 users are being bootstrapped:

- tom / tomspw
- dick / dickspw
- harry / harriespw

Login as a user:

    curl -H "Content-Type: application/json" -X POST -d '{"username":"tom","password":"tomspw"}' http://localhost:8080/auth/login

Use the returned token:

    curl --header "X-AUTH-TOKEN: [token]" http://localhost:8080/api/greeting
    
For example:

    curl --header "X-AUTH-TOKEN: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaWJlcyJ9.B-YTY1vIxXzrcB4GKjL0jAPSRBBKGcE7m9v9cVNQ72AzfSOb975Nc8SXrXDEzhaG6Xfbvb8JGHwuUMVUKH6Y5w" http://localhost:8080/api/greeting

The generated token expires after 60 minutes, after which the application should login again to obtain a new token. 


## Note

Because both the username and password are sent in clear text, you do **not** want to use this authentication mechanism over an unencrypted connection. Always use HTTPS.
 
## Disclaimer

This example is free software. It comes without any warranty, to the extent permitted by applicable law.
The software is provided “as is," and you use the software at your own risk.
Peruse, fork, and clone as you see fit.
