package com.example.library;

public class QuantityCheckHandler extends PurchaseHandler{

    public QuantityCheckHandler(PurchaseHandler nextHandler) {
        super(nextHandler);
    }

    public String handleRequest(PurchaseRequest request) throws Exception{
        if(request.getLibraryItem().getQuantity()){
            return super.handleRequest(request);
        }
        else {
            System.out.println("The Book has not left");
            return "The Book has not left";
        }
    }
}

