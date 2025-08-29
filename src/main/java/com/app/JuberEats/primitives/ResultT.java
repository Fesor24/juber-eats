package com.app.JuberEats.primitives;

import lombok.Getter;

@Getter
public class ResultT<TBody> extends Result {
    private TBody body;

    public ResultT(TBody body){
        super();
        this.body = body;
    }
}
