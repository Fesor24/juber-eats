package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.Result;
import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.response.PaginatedList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    public static <TBody> ResponseEntity<ResultT<TBody>> OK(TBody body){
        return new ResponseEntity<>(new ResultT<>(body), HttpStatus.OK);
    }

    public static ResponseEntity<Result> OK(){
        return new ResponseEntity<>(Result.Success(), HttpStatus.OK);
    }

    public static <TBody> ResponseEntity<ResultT<PaginatedList<TBody>>> OK(PaginatedList<TBody> body){
        return new ResponseEntity<>(new ResultT<>(body), HttpStatus.OK);
    }
}
