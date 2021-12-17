package com.fourthgroup.projectmanageman.model;

public enum Status {
     PENDING("Pending"), STARTED("Started"), CLOSED("Closed");

    private final String status;
    private Status(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

    public static Status fromInteger(int x) {
        switch(x) {
            case 0:
                return PENDING;
            case 1:
                return STARTED;
            case 2:
                return CLOSED;
        }
        return null;
    }
}
