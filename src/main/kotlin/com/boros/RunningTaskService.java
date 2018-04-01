package com.boros;


import com.boros.task.RunningTask;
import com.boros.task.Task;
import org.eclipse.jetty.websocket.api.Session;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: orossbogdan@gmail.com (Bogdan Oros)
 * @date: 25.02.18
 */
// TODO REFACTOR
public class RunningTaskService {

    private final Set<RunningTask> runningTasks = new HashSet<>();

    public RunningTask attachToRunningTasks(Task task, Session session) {
        RunningTask runningTask = runningTasks.stream()
                .filter(r -> r.task.equals(task))
                .findFirst()
                .orElseGet(() -> new RunningTask(task));
        runningTask.sessions.add(session);
        runningTasks.add(runningTask);
        return runningTask;
    }

//    public void attachResultToRunningTask(RunningTaskHandler.ResultRequest task, Session session) {
//        RunningTask runningTask = runningTasks.stream()
//                .filter(r -> r.task.id.equals(task.task))
//                .findFirst().get();
//        runningTask.result.putAll(task.result);

}
