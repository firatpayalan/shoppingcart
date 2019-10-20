package com.firat.shoppingcart;

/**
 *  contains operation result of the specific method.
 */
public class Result {
    int status;
    String message;

    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public Result(){
        this.status = 0;
        this.message = "Success";
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
