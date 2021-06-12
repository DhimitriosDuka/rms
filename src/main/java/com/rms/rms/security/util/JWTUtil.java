package com.rms.rms.security.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtil {

    private String secretKey;
    private Integer jwtExpirationInMs;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.secretKey = secret;
    }

    @Value("${jwt.expirationDateInMs}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
        if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            claims.put("isAdmin", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_OPERATOR"))) {
            claims.put("isOperator", true);
        }
        if (roles.contains(new SimpleGrantedAuthority("ROLE_DELIVERY"))) {
            claims.put("isDelivery", true);
        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public boolean validateToken(String authToken) throws ExpiredJwtException {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtException(ex.getHeader(), ex.getClaims(), "Token has Expired");
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {

        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken).getBody();
        Boolean isAdmin = claims.get("isAdmin", Boolean.class);
        Boolean isOperator = claims.get("isOperator", Boolean.class);
        Boolean isDelivery = claims.get("isDelivery", Boolean.class);

        List<SimpleGrantedAuthority> roles = null;

        if (validateClaim(isAdmin)) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (validateClaim(isOperator)) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_OPERATOR"));
        }
        if (validateClaim(isDelivery)) {
            roles = Collections.singletonList(new SimpleGrantedAuthority("ROLE_DELIVERY"));
        }
        return roles;
    }

    private boolean validateClaim(Boolean isAdmin) {
        return isAdmin != null && isAdmin;
    }


}
