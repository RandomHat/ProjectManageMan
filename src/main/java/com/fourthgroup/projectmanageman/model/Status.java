package com.fourthgroup.projectmanageman.model;

public enum Status {
    STARTED, PENDING, CLOSED;

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
