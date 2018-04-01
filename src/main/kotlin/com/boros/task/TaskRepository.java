package com.boros.task;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskRepository {

    public static final List<Task> tasks = new ArrayList<>();

    static {
        Task task1 = new Task();
        task1.id = 1L;
        task1.name = "Length of strings";
        InputData<String> inputData = new InputData<>();
        inputData.data = IntStream.range(0, 100).mapToObj(i -> String.valueOf(i) + LocalDateTime.now().toString())
                .map(InputData::elem).collect(Collectors.toList());
        task1.input = inputData;
        Task.Function function = new Task.Function();
        function.executionFunction = "return str.length;";
        function.paramName = "str";
        task1.function = function;
        tasks.add(task1);

        Task task2 = new Task();
        task2.id = 2L;
        task2.name = "Length of strings in square";
        InputData<String> inputData1 = new InputData<>();
        inputData1.data = Arrays.asList(InputData.elem("Long text1"));
        task2.input = inputData1;
        Task.Function function2 = new Task.Function();
        function2.executionFunction = "return str.length * str.length;";
        function2.paramName = "str";
        task2.function = function2;
        tasks.add(task2);
    }

    public static void createNewTask(Map<String, String> vals) throws UnsupportedEncodingException {
        Task task = new Task();
        task.id = (long) tasks.size() + 1;
        task.name = vals.get("task_name");
        InputData<String> inputData = new InputData<>();
        inputData.data = Arrays.stream(vals.get("inputdata").split(";"))
                .map(InputData::elem).collect(Collectors.toList());
        task.input = inputData;
        Task.Function function = new Task.Function();
        function.executionFunction = vals.get("func");
        function.paramName = "str";
        task.function = function;
        tasks.add(task);
    }


}
