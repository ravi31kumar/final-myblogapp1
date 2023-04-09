package com.myblog1.myblog1.security;

import com.myblog1.myblog1.exception.BlogApiException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currrentDate = new Date();
        Date expireDate = new Date(currrentDate.getTime() + jwtExpirationMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }
        public String getUsernameFromJWT(String token){
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();

        }
        public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;


            } catch (SignatureException ex) {
                throw new BlogApiException("Invalid signature", HttpStatus.BAD_REQUEST);
            } catch (MalformedJwtException ex) {
                throw new BlogApiException("Invalid Jwt Token", HttpStatus.BAD_REQUEST);
            } catch (ExpiredJwtException ex) {
                throw new BlogApiException("Expired Jwt Token", HttpStatus.BAD_REQUEST);
            } catch (UnsupportedJwtException ex) {
                throw new BlogApiException("Unsupported Jwt Token", HttpStatus.BAD_REQUEST);
            } catch (IllegalArgumentException ex) {
                throw new BlogApiException("Jwt Claim String is empty", HttpStatus.BAD_REQUEST);
            }
        }

    }





