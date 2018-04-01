package com.boros.task;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Result<T> {

    @Id
    public Long id;

    @ManyToOne
    public Task task;

    public InputData.DataNode<T> inputData;
    public T result;
}

