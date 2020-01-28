package org.zhivko.todoListApi.configs.jwt;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    public String createToken(Authentication authentication);

    public boolean validateToken(String authToken);

    public Authentication getAuthentication(String authToken);

}
