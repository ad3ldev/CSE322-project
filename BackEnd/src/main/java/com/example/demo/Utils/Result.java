package com.example.demo.Utils;


import com.example.demo.Models.Type;

public class Result {
    public Result(State state, Long id, Type type) {
        this.state = state;
        this.id = id;
        this.type = type;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    //Notice state=0 failure
    //state = 1 success
    State state;

    Long id;
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    Type type;




}
