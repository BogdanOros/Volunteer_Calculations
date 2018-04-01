package com.boros;

import com.boros.task.TaskRepository;
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

/**
 * @author: orossbogdan@gmail.com (Bogdan Oros)
 * @date: 25.02.18
 */
public class Runner {

    public static final String WS_CONNECTION = "/execution";

    public static void main(String[] args) {
        staticFileLocation("/");
        webSocket(WS_CONNECTION, RunningTaskHandler.class);
        port(8080);

        exception(Exception.class, (exception, request, response) ->
                exception.printStackTrace());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("attrs", TaskRepository.tasks);
            return render(model, "index");
        });

        get("/admin", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("attrs", TaskRepository.tasks);
            return render(model, "admin_page");
        });
        post("/admin", (req, res) -> {
            Map<String, String> collect = Arrays.stream(req.body().split("&"))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> {
                        try {
                            return URLDecoder.decode(s[1], "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return null;
                        }
                    }));
            TaskRepository.createNewTask(collect);
            res.redirect("/admin");
            return null;
        });
    }

    public static String render(Map<String, Object> model, String view) {
        return new FreeMarkerEngine().render(new ModelAndView(model, view + ".ftl"));
    }
}
