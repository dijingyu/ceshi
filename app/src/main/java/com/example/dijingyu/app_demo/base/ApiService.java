package com.example.dijingyu.app_demo.base;

import com.example.dijingyu.app_demo.bean.AllBean;
import com.example.dijingyu.app_demo.bean.BanMiBean;
import com.example.dijingyu.app_demo.bean.FollowBean;
import com.example.dijingyu.app_demo.bean.HomeBean;
import com.example.dijingyu.app_demo.bean.InfoPathBean;
import com.example.dijingyu.app_demo.bean.LoginInfo;
import com.example.dijingyu.app_demo.bean.MyCollectBean;
import com.example.dijingyu.app_demo.bean.PathCollectBean;
import com.example.dijingyu.app_demo.bean.PathInfoBean;
import com.example.dijingyu.app_demo.bean.PersonageInfoBean;
import com.example.dijingyu.app_demo.bean.UpDataBean;
import com.example.dijingyu.app_demo.bean.VerifyCodeBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：邸某某
 * 时间：2019/5/5
 */

public interface ApiService {
    public static final int SUCCESS_CODE = 0;
    public static final int VERIFY_CODE_ERROR = 200502;
    public static final int WECHAT_HAVE_BINDED = 20171102;
    //token 失效
    public static final int TOKEN_EXPIRE = 20170707;
    //余额不足
    public static final int MONEY_NOT_ENOUGH = 200607;

    String BASE_URL = "http://api.banmi.com/";

    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();

    /**
     * 微信登录
     *
     * @param unionID
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/wechat")
    Observable<LoginInfo> postWechatLogin(@Field("unionID") String unionID);

    /**
     * 微博登录
     *
     * @param oAuthToken 就是三方里面的uid
     * @return
     */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    Observable<LoginInfo> postWeiboLogin(@Field("oAuthToken") String oAuthToken);

    @GET("api/3.0/content/routesbundles")
    Observable<HomeBean> getHomeData(@HeaderMap Map<String,String> headerMap , @Query("page") String page);

    /**
     * 伴米
     * @return
     */
    @GET("api/3.0/banmi")
    Observable<BanMiBean> getBanMiData(@HeaderMap Map<String,String> headerMap, @Query("page") String page);

    /**
     * 修改昵称
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<UpDataBean> upDataName(@HeaderMap Map<String,String> headerMap, @Field("userName") String username);

    /**
     * 修改性别
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<UpDataBean> upDataSex(@HeaderMap Map<String,String> headerMap, @Field("gender") String sex);

    /**
     * 修改个性签名
     * @return
     */
    @POST("api/3.0/account/updateInfo")
    @FormUrlEncoded
    Observable<UpDataBean> upDataQianming(@HeaderMap Map<String,String> headerMap, @Field("description") String description);

    /**
     * 获取用户信息
     * @return
     */
    @GET("api/3.0/account/info")
    Observable<PersonageInfoBean> getPersonageInfo(@HeaderMap Map<String,String> headerMap);

    /**
     * 路线详情
     * @return
     */
    @GET("api/3.0/content/routes/{routeId}")
    Observable<PathInfoBean> getPathInfo(@HeaderMap Map<String,String> headerMap, @Path("routeId") String id);

    /**
     * 路线收藏
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/like")
    Observable<PathCollectBean> collectPath(@HeaderMap Map<String,String> headerMap, @Path("routeId") String id);

    /**
     * 取消收藏
     * @return
     */
    @POST("api/3.0/content/routes/{routeId}/dislike")
    Observable<PathCollectBean> cancelCollect(@HeaderMap Map<String,String> headerMap, @Path("routeId") String id);

    /**
     * 我的收藏
     * @return
     */
    @GET("api/3.0/account/collectedRoutes")
    Observable<MyCollectBean> getCollect(@HeaderMap Map<String,String> headerMap, @Query("page") String page);


    /**
     * 伴米关注
     *
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/follow")
    Observable<FollowBean> follow(@HeaderMap Map<String, String> headerMap,@Path("banmiId") String id);

    /**
     * 我的关注
     * @return
     */
    @GET("api/3.0/account/followedBanmi")
    Observable<BanMiBean> getFollow(@HeaderMap Map<String,String> headerMap, @Query("page") String page);

    /**
     * 伴米取注
     *
     * @return
     */
    @POST("api/3.0/banmi/{banmiId}/unfollow")
    Observable<FollowBean> unFollow(@HeaderMap Map<String, String> headerMap,@Path("banmiId") String id);

    /**
     * 伴米取注
     *
     * @return
     */
    @GET("api/3.0/banmi/{banmiId}/routes")
    Observable<InfoPathBean> getPath(@HeaderMap Map<String, String> headerMap, @Path("banmiId") String id,@Query("page") String page);

    /**
     * 全部评论
     * @param headerMap
     * @param id
     * @param page
     * @return
     */
    @GET("api/3.0/content/routes/{routeId}/reviews")
    Observable<AllBean> getAll(@HeaderMap HashMap<String, String> headerMap, @Path("routeId") String id, @Query("page") String page);
}
