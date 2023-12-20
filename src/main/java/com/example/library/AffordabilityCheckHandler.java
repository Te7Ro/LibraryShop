package com.example.library;

public class AffordabilityCheckHandler extends PurchaseHandler{

    public AffordabilityCheckHandler(PurchaseHandler nextHandler) {
        super(nextHandler);
    }

    public String handleRequest(PurchaseRequest request) throws Exception{
        if(request.getClient().getMoney() >= request.getLibraryItem().getPrice()){
            return super.handleRequest(request);
        }
        else{
            System.out.println("You have not enough money");
            return "You have not enough money";
        }
    }
}

