package com.bigpumpkin.app.ddng_android.bean;

import java.io.Serializable;

public class ShoppId_Bean implements Serializable {

    String id;

    public ShoppId_Bean(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
