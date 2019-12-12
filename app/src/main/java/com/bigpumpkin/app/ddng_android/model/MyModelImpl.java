package com.bigpumpkin.app.ddng_android.model;

import com.bigpumpkin.app.ddng_android.activity.SpellGrowBean;
import com.bigpumpkin.app.ddng_android.app.App;
import com.bigpumpkin.app.ddng_android.bean.*;
import com.bigpumpkin.app.ddng_android.callback.MyCallBack;
import com.bigpumpkin.app.ddng_android.net.Retrofits;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;

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

        } else if (clas == Del_Shooppoing_Bean.class) {
            //删除购物车
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
        } else if (clas == Ngz_Bean.class) {
            //查询南瓜籽
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
        } else if (clas == GoodBean.class) {
            //商品详情
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
        } else if (clas == MyFarmBean.class) {
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
        } else if (clas == Buy_NowBean.class) {
            //立即购买
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
        } else if (clas == AddShoppingBean.class) {
            //立即购买
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

        } else if (clas == PendingPay_Bean.class) {
            //待付款
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
        } else if (clas == DetailassGoodBean.class) {
            //待付款
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

        } else if (clas == EvaluateBean.class) {
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

        } else if (clas == CommentsBean.class) {
            //品类植物认养
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
        } else if (clas == Cancel_collectionsBean.class) {
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

        } else if (clas == GenerateOrdersBean.class) {
            //生成订单
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

        } else if (clas == WxPayBean.class) {
            //微信支付
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
        } else if (clas == OrderlistsBean.class) {
            //生长中详情
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
        } else if (clas == SingleBean.class) {
            //生长中详情
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

        } else if (clas == Orders_Bean.class) {
            //生长中详情
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

        } else if (clas == OrderDetailsBean.class) {
            //订单详情
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
        } else if (clas == Logs_Bean.class) {
            //订单详情
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
        } else if (clas == Farm_AttentionsBean.class) {
            //订单详情
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
        } else if (clas == FarmHeadsBean.class) {
            //订单详情
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
        } else if (clas == GrowersBean.class) {
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
        } else if (clas == DetailsBean.class) {
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
        } else if (clas == SpellGrowBean.class) {
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
        } else if (clas == SpellBuyBean.class) {
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
        } else if (clas == CollagesBean.class) {
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
        } else if (clas == ParticipationCollagesBean.class) {
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
        } else if (clas == ParticipateDetailsBean.class) {
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
        } else if (clas == VideoBean.class) {
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
        } else if (clas == NewBuyNowBean.class) {
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
        } else if (clas == NewBuyNowOrdersBean.class) {
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
        } else if (clas == ShopBuyBean.class) {
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
        } else if (clas == BuySubmitOrders.class) {
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
        } else if (clas == ChangeBean.class) {
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
        } else if (clas == PendingReceiptDetailssBean.class) {
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
        }else if (clas == GeneralLogisticsBean.class) {
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
        }else if (clas == EvaluationDetailsBean.class) {
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
        }else if (clas == Ordinary.class) {
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
        }else if (clas == CusomerBean.class) {
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
        }else if (clas == IntegralBean.class) {
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
        } else if (clas == VideoBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == FarmTypeBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == MilkMoreBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == AdoptBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Commodity_Recommended_Bean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Rarm_RencommendedBean.class) {
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
        } else if (clas == Fragment_Bean.class) {
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
        } else if (clas == Recommended_Bean.class) {
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
        } else if (clas == FragmentFor_Bean.class) {
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

        } else if (clas == Adopt_Bean.class) {
            //定制认养
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

        } else if (clas == Save_More_Bean.class) {
            //救助农民列表页
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
        } else if (clas == FragmentThere_Bean.class) {
            //幸运集市
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
        } else if (clas == Plant_Bean.class) {
            //推荐认养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Fruit_Bean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == GoodBean.class) {
            //商品详情
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == MyFarmBean.class) {
            //首页
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == FrmaPeopleBean.class) {
            //首页
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == NearBean.class) {
            //附近植物认养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == Category_Bean.class) {
            //品类植物认养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == Plants_AdoptBean.class) {
            //植物认养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == HundredBean.class) {
            //百年果树
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Fruit_Bean.class) {
            //百年果树
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == RareBean.class) {
            //百年果树
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == ProcessingBean.class) {
            //农场深加工
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == WisdomBean.class) {
            //智慧农场
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == PoultryBean.class) {
            //家禽领养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == RecommendedBean.class) {
            //家禽领养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == MeatBean.class) {
            //家禽领养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == ProductsBean.class) {
            //家禽领养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == FreshBean.class) {
            //家禽领养
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Wisdoms_AreasBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == FarmHeadsBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Farm_CouponsBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Farm_Recommend_Bean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == Show_Bean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == Farm_index_productBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == SearchBean.class) {
            //商品搜索
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == Store_SearchBean.class) {
            //店铺搜索
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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

        } else if (clas == Custom_ClassificationBean.class) {
            //植物认养纬度
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == GrowersBean.class) {
            //植物认养果农说
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == OriginBean.class) {
            //植物认养果农说
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == SpellGrowBean.class) {
            //植物认养果农说
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == DetailsBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == ProcessingShopBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == HotSearchsBean.class) {
            Retrofits.getInstance().get(url, headmap, map).getonclcked(new Retrofits.onclick() {
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
    public void img(String url, Map<String, RequestBody> headmap, Class clas, MyCallBack callBack) {
        if (clas == ChangeBean.class) {
            //头像
            Retrofits.getInstance().image(url, headmap).getonclcked(new Retrofits.onclick() {
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
        } else if (clas == CeSshi.class) {
            Retrofits.getInstance().image(url, headmap).getonclcked(new Retrofits.onclick() {
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


}