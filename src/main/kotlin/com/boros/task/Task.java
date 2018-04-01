package com.boros.task;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    @Id
    public Long id;

    public String name;

    public Function function;

    public InputData<String> input;

    public static class Function {

        public String paramName;
        public String executionFunction;

        public String getExecutionFunction() {
            return executionFunction;
        }


    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Function getFunction() {
        return function;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

