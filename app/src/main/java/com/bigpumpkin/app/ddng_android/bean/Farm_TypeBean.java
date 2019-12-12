package com.bigpumpkin.app.ddng_android.bean;

public class Farm_TypeBean {

    private String name;
    private boolean rype;

    public Farm_TypeBean(String name, boolean rype) {
        this.name = name;
        this.rype = rype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRype() {
        return rype;
    }

    public void setRype(boolean rype) {
        this.rype = rype;
    }
}
