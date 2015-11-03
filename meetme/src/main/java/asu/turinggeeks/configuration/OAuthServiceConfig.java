package asu.turinggeeks.configuration;

import org.scribe.builder.api.Api;

public class OAuthServiceConfig {
	
	private String apiKey;
	private String apiSecret;
	private String callback;
	private Class<? extends Api> apiClass;
	
	public OAuthServiceConfig() {
	}
	
	public OAuthServiceConfig(String apiKey, String apiSecret, String callback,
            Class<? extends Api> apiClass) {
	    super();
	    this.apiKey = apiKey;
	    this.apiSecret = apiSecret;
	    this.callback = callback;
	    this.apiClass = apiClass;
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public Class<? extends Api> getApiClass() {
		return apiClass;
	}

	public void setApiClass(Class<? extends Api> apiClass) {
		this.apiClass = apiClass;
	}
	
	
	@Override
    public String toString() {
	    return "OAuthServiceConfig [apiKey=" + apiKey + ", apiSecret="
	            + apiSecret + ", callback=" + callback + ", apiClass="
	            + apiClass + "]";
    }
}