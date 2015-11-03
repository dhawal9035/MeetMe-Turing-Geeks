package asu.turinggeeks.controller;

import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.web.context.request.RequestAttributes.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import asu.turinggeeks.configuration.OAuthServiceProvider;
import static asu.turinggeeks.configuration.SessionAttributes.*;

@Controller
public class FacebookController {
	
	@Autowired
	private OAuthServiceProvider facebookServiceProvider;
	
	private static final Token EMPTY_TOKEN = null;
	
	@RequestMapping(value={"/facebook"}, method = RequestMethod.GET)
	public String login(WebRequest webRequest) {
		
		Token accessToken = (Token) webRequest.getAttribute(ATTR_OAUTH_ACCESS_TOKEN, SCOPE_SESSION);
		if(accessToken == null) {
			OAuthService service = facebookServiceProvider.getService();
			webRequest.setAttribute(ATTR_OAUTH_REQUEST_TOKEN, EMPTY_TOKEN, SCOPE_SESSION);
			return "redirect:" + service.getAuthorizationUrl(EMPTY_TOKEN);
		}
		return "success";
	}
	
	@RequestMapping(value={"/facebook-callback"}, method = RequestMethod.GET)
	public ModelAndView callback(@RequestParam(value="code", required=false) String oauthVerifier, WebRequest webRequest) {
		
		OAuthService service = facebookServiceProvider.getService();
		Token requestToken = (Token) webRequest.getAttribute(ATTR_OAUTH_REQUEST_TOKEN, SCOPE_SESSION);
		
		Verifier verifier = new Verifier(oauthVerifier);
		Token accessToken = service.getAccessToken(requestToken, verifier);
		
		webRequest.setAttribute(ATTR_OAUTH_ACCESS_TOKEN, accessToken, SCOPE_SESSION);
		
		OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me");
		service.signRequest(accessToken, oauthRequest);
		Response oauthResponse = oauthRequest.send();
		System.out.println(oauthResponse.getBody());

		ModelAndView mav = new ModelAndView("redirect:login");
		return mav;
	}
}