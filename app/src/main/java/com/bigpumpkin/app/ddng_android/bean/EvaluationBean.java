package com.bigpumpkin.app.ddng_android.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 发表评价Bean，用于存储不同商品的评价
 */
public class EvaluationBean {

    private String id;//标签id
    private String evaluatinType;//标签id
    private String evaluationContent;//评价内容
    private List<File> evaluationImages;//评价图片集合

    public EvaluationBean() {
        if(evaluationImages==null){
            evaluationImages=new ArrayList<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvaluatinType() {
        return evaluatinType;
    }

    public void setEvaluatinType(String evaluatinType) {
        this.evaluatinType = evaluatinType;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public List<File> getEvaluationImages() {
        return evaluationImages;
    }

    public void setEvaluationImages(List<File> evaluationImages) {
        this.evaluationImages = evaluationImages;
    }

    @Override
    public String toString() {
        return "EvaluationBean{" +
                "evaluatinType=" + evaluatinType +
                ", evaluationContent='" + evaluationContent + '\'' +
                ", evaluationImages=" + evaluationImages +
                '}';
    }

}
