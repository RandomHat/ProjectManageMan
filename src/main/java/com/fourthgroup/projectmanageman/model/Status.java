package com.fourthgroup.projectmanageman.model;

public enum Status {
    STARTED("Started"), PENDING("Pending"), CLOSED("Closed");

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
                return STARTED;
            case 1:
                return PENDING;
            case 2:
                return CLOSED;
        }
        return null;
    }
}
