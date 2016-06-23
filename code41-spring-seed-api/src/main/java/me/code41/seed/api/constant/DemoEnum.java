package me.code41.seed.api.constant;

/**
 * Created by code41 on 2016/6/23.
 */
public enum DemoEnum {

    /**
     * DEMO
     */
    DEMO(1, "Demo");


    private int code;
    private String message;

    private DemoEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    private void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

}
