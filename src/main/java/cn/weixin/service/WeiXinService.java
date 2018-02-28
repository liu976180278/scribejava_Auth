/**
 * @Description: TODO
 * @date 2017年10月30日 下午4:42:52 	
 */
package cn.weixin.service;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.github.scribejava.apis.SinaWeiboApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * @author lzc
 *
 */
public class WeiXinService {

	// 获取用户uid 的 URL
    private static final String U_ID_URL = "https://api.weibo.com/2/account/get_uid.json";
    // 获取用户信息的 URL
    private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json?uid=%s";
    // 下面的属性可以通过配置读取
    //注意 切记callbackUrl参数是你在微博开发平台下填写的回掉页面，此地址一定要放在外网，用个穿透工具就行。下载地址    http://download.csdn.net/download/liu976180578/10139479
    private String callbackUrl = "http://lzclzc.tunnel.qydev.com/scribejava_Auth/oauth/weibo/callback.action"; // WeiBo 在登陆成功后回调的 URL，这个 URL 必须在 WeiBo 互联里填写过
    private String apiKey      = "3703387386";                                      // WeiBo 互联应用管理中心的 APP ID
    private String apiSecret   = "f525745eaa5fbaee169b23dae552049f";               // WeiBo 互联应用管理中心的 APP Key
    private String scope       = "all";                                           // WeiBo 互联的 API 接口，访问用户资料
    private OAuth20Service oauthService; // 访问 WeiBo 服务的 service
    public WeiXinService() {
        // 创建访问 WeiBo 服务的 service
        oauthService = new ServiceBuilder().apiKey(apiKey).apiSecret(apiSecret)
                .scope(scope).callback(callbackUrl).build(SinaWeiboApi20.instance());
    }
    /**
     * 取得 WeiBo 登陆页面的 URL，例如
     * https://graph.WeiBo.com/oauth2.0/authorize?response_type=code&client_id=101292272&
     * redirect_uri=http://open.qtdebug.com:8080/oauth/WeiBo/callback&scope=get_user_info
     *
     * @return WeiBo 登陆页面的 URL
     */
    public String getLoginUrl() {
        return oauthService.getAuthorizationUrl();
    }
    /**
     * 使用 code 换取 access token
     *
     * @param code 成功登陆后 WeiBo Server 返回给回调 URL 的中间 code，用于换取 access token
     * @return 用于访问 WeiBo 服务的 token
     * @throws IOException
     */
    public String getAccessToken(String code) throws IOException {
        OAuth2AccessToken token = oauthService.getAccessToken(code); // 使用 code 换取 accessToken
        String accessToken = token.getAccessToken(); // 5943BF2461ED97237B878BECE78A8744
        return accessToken;
    }
    
    /**
     * 获取用户的uid，WeiBo 的昵称，WeiBo 空间的头像等，一般这 2 个属性用的最多
     *
     * @param accessToken 登陆时从 WeiBo 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @throws IOException
     */
    public String getUserid(String accessToken) throws IOException {
        String url = String.format(U_ID_URL);
        Response oauthResponse = request(oauthService, accessToken, url);
        String responseJson = oauthResponse.getBody();
        String uid=JSON.parseObject(responseJson).getString("uid");
        return uid;
    }
    
    /**
     * 获取用户的信息，WeiBo 的昵称，WeiBo 空间的头像等，一般这 2 个属性用的最多
     *
     * @param accessToken 登陆时从 WeiBo 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @param Uid 用户的uid
     * @return JSONObject 对象
     * @throws IOException
     */
    public String getUserInfo(String accessToken,String uid) throws IOException {
        String url = String.format(USER_INFO_URL,uid);
        Response oauthResponse = request(oauthService, accessToken, url);
        String responseJson = oauthResponse.getBody();
        return responseJson;
    }
    /**
     * 使用 OAuth 2.0 的方式从服务器获取 URL 指定的信息
     *
     * @param accessToken 登陆时从 WeiBo 系统得到的 access token，作为访问的凭证，相当于用户名密码的作用
     * @param url 访问 OAuth Server 服务的 URL
     * @return
     */
    public Response request(OAuth20Service service, String accessToken, String url) {
        OAuth2AccessToken token = new OAuth2AccessToken(accessToken);
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, url, service);
        service.signRequest(token, oauthRequest); // 会把 accessToken 添加到请求中，GET 请求即添加到 URL 上
        Response oauthResponse = oauthRequest.send();
        return oauthResponse;
    }

}
