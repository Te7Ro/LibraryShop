package com.example.library;

public abstract class PurchaseHandler {
    private PurchaseHandler nextHandler;
    public PurchaseHandler(PurchaseHandler nextHandler){
        this.nextHandler = nextHandler;
    }
    String handleRequest(PurchaseRequest request) throws Exception{
        if(nextHandler != null){
            return nextHandler.handleRequest(request);
        }
        else return "";
    }
}
