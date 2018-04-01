package com.boros.task;

import org.eclipse.jetty.websocket.api.Session;

import java.util.*;

public class RunningTask {

    public Task task;
    public Set<Session> sessions = new HashSet<>();
    public Map<String, Object> result = new HashMap<>();
    public boolean finished = false;

    public boolean equals(Object o) {
        return o instanceof RunningTask && Objects.equals(((RunningTask) o).task.id, this.task.id);
    }

    public RunningTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "RunningTask{" +
                "task=" + task +
                '}';
    }
}
