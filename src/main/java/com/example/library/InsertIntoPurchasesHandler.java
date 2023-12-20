package com.example.library;

public class InsertIntoPurchasesHandler extends PurchaseHandler{
    public InsertIntoPurchasesHandler(PurchaseHandler nextHandler){
        super(nextHandler);
    }
    public String handleRequest(PurchaseRequest request) throws Exception{
        request.getLibraryItem().buy(request.getClient(),request.getConnection());
        return "Purchase Successfully";
    }
}

