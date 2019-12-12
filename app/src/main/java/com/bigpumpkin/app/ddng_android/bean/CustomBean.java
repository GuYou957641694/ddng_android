package com.bigpumpkin.app.ddng_android.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomBean {

    private String farmscore;//产品评分
    private String servicescore;//服务评分
    private String logisticsscore;//物流评分



    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;//商品id
        private String title;//评价内容
        private List<File> pic;//上传图片的地址
        private String evaluation_dimension;//标签id


        public DataBean() {
            if (pic == null) {
                pic = new ArrayList<>();
            }

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<File> getPic() {
            return pic;
        }

        public void setPic(List<File> pic) {
            this.pic = pic;
        }

        public String getEvaluation_dimension() {
            return evaluation_dimension;
        }

        public void setEvaluation_dimension(String evaluation_dimension) {
            this.evaluation_dimension = evaluation_dimension;
        }
    }
}
