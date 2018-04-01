package com.boros.task;

import java.util.List;

/**
 * @author: orossbogdan@gmail.com (Bogdan Oros)
 * @date: 11.03.18
 */
public class InputData<E> {

    public List<DataNode<E>> data;

    public static <E> DataNode<E> elem(E elem) {
        return new DataNode<>(elem);
    }

    public static class DataNode<T> {

        public DataNode(T data) {
            this.data = data;
        }

        public T data;

        @Override
        public String toString() {
            return "DataNode{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InputData{" +
                "data=" + data +
                '}';
    }
}
