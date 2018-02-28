/**
 * @Description: TODO
 * @date 2017年10月30日 下午4:38:32 	
 */
package cn.weixin.api;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class WeiXinApi extends DefaultApi20 {


    protected WeiXinApi() {
    }

    private static class InstanceHolder {
        private static final WeiXinApi INSTANCE = new WeiXinApi();
    }

    public static WeiXinApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token";
    }


    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }
    
    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new OsChinaOAuthServiceImpl(this, config);
    }

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub

		return "https://open.weixin.qq.com/connect/qrconnect";
	}
}
