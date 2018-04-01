package com.boros;


import org.eclipse.jetty.websocket.api.Session;

import java.util.HashSet;
import java.util.Set;

public class IdleSessionService {

    private final Set<Session> idleSessions = new HashSet<>();

    public boolean addSession(Session session) {
        return idleSessions.add(session);
    }

    public boolean removeSession(Session session) {
        return idleSessions.remove(session);
    }

}
