package com.bigpumpkin.app.ddng_android.model;

import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.bean.Address_Bean;
import com.bigpumpkin.app.ddng_android.bean.Address_Success_Bean;
import com.bigpumpkin.app.ddng_android.bean.All_OrdersBean;
import com.bigpumpkin.app.ddng_android.bean.Bazaar_Bean;
import com.bigpumpkin.app.ddng_android.bean.Cancel_Colledctions_Bean;
import com.bigpumpkin.app.ddng_android.bean.Change_Bean;
import com.bigpumpkin.app.ddng_android.bean.Collections_Bean;
import com.bigpumpkin.app.ddng_android.bean.Coupons_Bean;
import com.bigpumpkin.app.ddng_android.bean.Del_Address_Bean;
import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;
import com.bigpumpkin.app.ddng_android.bean.Footprint_Bean;
import com.bigpumpkin.app.ddng_android.bean.For_GoodsBean;
import com.bigpumpkin.app.ddng_android.bean.GoodsBean;
import com.bigpumpkin.app.ddng_android.bean.Grow_Bean;
import com.bigpumpkin.app.ddng_android.bean.Log_Bean;
import com.bigpumpkin.app.ddng_android.bean.Message_Bean;
import com.bigpumpkin.app.ddng_android.bean.Order_Bean;
import com.bigpumpkin.app.ddng_android.bean.Registration_Bean;
import com.bigpumpkin.app.ddng_android.bean.Settlement_Bean;
import com.bigpumpkin.app.ddng_android.bean.Shopping_Bean;
import com.bigpumpkin.app.ddng_android.bean.User_Bean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Alipay_Bean;
import com.bigpumpkin.app.ddng_android.bean.Zfb_Bean;
import com.bigpumpkin.app.ddng_android.callback.MyCallBack;
import com.bigpumpkin.app.ddng_android.net.Retrofits;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class MyModelImpl implements MyModel {

    @Override
    public void setpost(String url, Map<String, Object> headmap, Map<String, Object> map, final Class clas, final MyCallBack callBack) {
        if (clas == Focus_Bean.class) {
            //我的关关注
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Log_Bean.class) {
            //密码登录
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    ToastUtil.showShort(App.appContext, "手机号或密码不对");
                    callBack.error(error);
                }
            });
        } else if (clas == Registration_Bean.class) {
            //新用户注册
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == User_Bean.class) {
            //密码登录
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Message_Bean.class) {
            //留言
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Collections_Bean.class) {
            //我的收藏
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Cancel_Colledctions_Bean.class) {
            //取消收藏
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Footprint_Bean.class) {
            //我的足迹
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Coupons_Bean.class) {
            //我的优惠券
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Grow_Bean.class) {
            //生长中
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Zfb_Bean.class) {
            //支付宝登录
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == Order_Bean.class) {
            //我发起的订单Singe_Bean
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == Zfb_Alipay_Bean.class) {
            //支付宝支付
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == All_OrdersBean.class) {
            //全部订单
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == For_GoodsBean.class) {
            //代付款
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == Address_Bean.class) {
            //收货地址列表
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Address_Success_Bean.class) {
            //收货地址添加
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Change_Bean.class) {
            //修改信息
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == Del_Address_Bean.class) {
            //删除收货地址
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });

        } else if (clas == Shopping_Bean.class) {
            //购物车展示
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Settlement_Bean.class) {
            //結算
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        }
    }

    @Override
    public void setget(String url, Map<String, Object> headmap, Map<String, Object> map, final Class clas, final MyCallBack callBack) {
        if (clas == GoodsBean.class) {
            //首页
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        } else if (clas == Bazaar_Bean.class) {
            //集市
            Retrofits.getInstance().post(url, headmap, map).getonclcked(new Retrofits.onclick() {
                @Override
                public void success(String strjson) {
                    Gson gson = new Gson();
                    Object o = gson.fromJson(strjson, clas);
                    callBack.success(o);
                }

                @Override
                public void error(String error) {
                    callBack.error(error);
                }
            });
        }
    }

    /**
     * post请求
     *
     * @param url
     * @param headmap
     * @param map
     * @param clas
     * @param callBack
     */


    @Override
    public void img(String url, Map<String, Object> headmap, Map<String, Object> map, List<Object> list, final Class clas, final MyCallBack callBack) {

    }
}