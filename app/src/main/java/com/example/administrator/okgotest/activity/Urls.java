/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.administrator.okgotest.activity;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/4/6
 * 描    述：我的Github地址  https://github.com/jeasonlzy
 * 修订历史：
 * ================================================
 */
public class Urls {
    public static final String SERVER = "https://www.medstandard.com.cn/medstandard/api/v1/";
    public static final String SERVERPAY = "https://www.medstandard.com.cn/medstandard/api/pay/";
    //    public static final String SERVER = "http://192.168.1.121:8080/OkHttpUtils/";
    public static final String URL_HOSPITAL = SERVER + "gethospitalinfo";
    public static final String URL_USERLOGIN = SERVER + "userlogin/";
    public static final String URL_GETPLANINFO = SERVERPAY + "getplaninfo";
    public static final String URL_GETUSERDATA = SERVER + "getuserdata";
    public static final String URL_GETORBIT = SERVER + "getorbit";
    public static final String URL_GETFAMILYINFOBYMOBILE = SERVER + "getfamilyinfobymobile/";
    public static final String URL_ADDFAMILTINFO = SERVER + "addfamilyinfo";
    public static final String URL_ALLORDER = SERVERPAY + "alipay/allorder";
    public static final String URL_REDIRECT = SERVER + "redirect";
    public static final String URL_GANK_BASE = "http://gank.io/api/data/";
    //修改用户密码
    public static final String updatepassword = SERVER + "updatepassword";
}
