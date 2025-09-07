package com.app.JuberEats.primitives;

import lombok.Getter;

@Getter
public class Result {
    private boolean isSuccess;
    private boolean isFailure;
    private AppError error;
    public static Result Success(){
        return new Result();
    }
    public static Result Failure(AppError error){
        return new Result(error);
    }

    public Result(){
        isSuccess = true;
        isFailure = false;
        error = null;
    }

    public Result(AppError error){
        this.error = error;
        isSuccess = false;
        isFailure = true;
    }
}
