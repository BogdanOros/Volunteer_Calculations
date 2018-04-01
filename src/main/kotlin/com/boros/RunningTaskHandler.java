package com.boros;

import com.boros.task.InputData;
import com.boros.task.RunningTask;
import com.boros.task.Task;
import com.boros.task.TaskRepository;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebSocket
public class RunningTaskHandler {

    private final Logger logger = LoggerFactory.getLogger(RunningTaskHandler.class);

    private final Gson gson = new Gson();

    private final IdleSessionService idleSessionService = new IdleSessionService();
    private final RunningTaskService runningTaskService = new RunningTaskService();

    @OnWebSocketConnect
    public void onConnection(Session session) throws IOException {
        idleSessionService.addSession(session);
        logger.info("New session connected. Session attached to idle sessions: {}", session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        logger.info("Session [{}] disconnected. Reason [{}]", session, reason);
    }


    @OnWebSocketError
    public void onError(Session session, Throwable throwable) {
    }


    @OnWebSocketMessage
    public void onMessage(Session session, String rawRequest) throws IOException {
        // TODO 1) Create running task if not exists;
        // TODO 2) Attach session to running task;
        // TODO 3) Divide task input between users;
        // TODO 4) Send task input data part to user;
        Request request = gson.fromJson(rawRequest, Request.class);
        if (Action.ActionType.ATTACH.is(request.action)) {
            Task task = TaskRepository.tasks.stream()
                    .filter(t -> t.id.equals(request.payload))
                    .findFirst().get();
            RunningTask runningTask = runningTaskService.attachToRunningTasks(task, session);
            logger.info("Attached session to running task {}", runningTask);
            session.getRemote().sendString(gson.toJson(new AttachResponse<>(task, task.input)));
        }


    }

    public static class Action {

        public String action;

        public enum ActionType {
            ATTACH("attach"),
            RESULT("result");

            final String type;

            ActionType(String type) {
                this.type = type;
               }

            public boolean is(String action) {
                return this.type.equals(action);
            }

        }

    }

    public class Request {

        public String action;
        public Long payload;

    }

    public static class AttachResponse<T> {

        private final String action = Action.ActionType.ATTACH.type;
        private final Task task;
        private final InputData<T> inputData;

        AttachResponse(Task task, InputData<T> inputData) {
            this.task = task;
            this.inputData = inputData;
        }

    }

}
