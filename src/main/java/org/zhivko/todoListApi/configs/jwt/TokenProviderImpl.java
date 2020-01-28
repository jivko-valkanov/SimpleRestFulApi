package org.zhivko.todoListApi.configs.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProviderImpl implements TokenProvider {

    private final String AUTHORITIES_KEY = "auth";
    private final String TYPE = "typ";
    private final String TYPE_JWT = "JWT";

    @Value("${jwt.secret}")
    private String secretKey = null;

    @Value("${jwt.token-ttl}")
    private long tokenValidityInMilliseconds = 0L;

    public TokenProviderImpl() {}

    @Override
    public String createToken(Authentication authentication) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date validity = new Date(new Date().getTime() + tokenValidityInMilliseconds);

        return Jwts.builder()
                .setHeaderParam(TYPE, TYPE_JWT)
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(validity)
                .compact();
    }

    @Override
    public boolean validateToken(String authToken) {

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException se) {
            se.printStackTrace();
        }
        catch (ExpiredJwtException eje) {
            eje.printStackTrace();
        }
        catch (UnsupportedJwtException uje) {
            uje.printStackTrace();
        }
        catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }


        return false;
    }

    @Override
    public Authentication getAuthentication(String authToken) {

        Claims claims = Jwts.parser()
                .setSigningKey(this.secretKey)
                .parseClaimsJws(authToken)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
