package com.example.todo.views.uimodel;

public class Result<T> {

    T result;
    Throwable error;

    public void setError(Throwable error) {
        this.error = error;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }


    public Throwable getError() {
        return error;
    }



    public boolean isSuccess() {
        return result!=null;
    }



}
