package com.bigpumpkin.app.ddng_android.utils;

import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 验证用户名
     *
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkUserName(String idCard) {
        String regex = "/^[A-Za-z0-9_\\x{4e00}-\\x{9fa5}]{1,10}$/u";
        return Pattern.matches(regex, idCard);
    }

}
