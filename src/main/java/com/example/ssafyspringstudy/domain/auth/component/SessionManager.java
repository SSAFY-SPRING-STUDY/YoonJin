package com.example.ssafyspringstudy.domain.auth.component;

import org.springframework.stereotype.Component;


import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {
    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long id){
        String token = UUID.randomUUID().toString();
        sessionStore.put(token,id);
        return token;
    }

    public void removeSession(String accesstoken) {
        sessionStore.remove(accesstoken);
    }

    public <T> Optional<Long> getMemberId(String accessToken) {
        return Optional.ofNullable(sessionStore.get(accessToken));
    }
}
