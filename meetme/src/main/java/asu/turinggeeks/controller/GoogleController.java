package asu.turinggeeks.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

import asu.turinggeeks.model.Calendar;
import asu.turinggeeks.service.CalendarService;

@Controller
public class GoogleController {
	
	
	@Autowired
	CalendarService calendarService;
	private static int counter = 0;
	private static final String VIEW_INDEX = "index";
	//private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GoogleController.class);
	
	private static final java.io.File DATA_STORE_DIR =
		      new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");
	
	private static final String CLIENT_ID = "972686849948-mfbvtg9uhl6ecag9234qkc4l4gp8b50p.apps.googleusercontent.com";
	private static final String CLIENT_SECRET = "aLj8gLOSzMCPoUfmDS9FfA_h";	
	 private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.calendar.Calendar client;

	
	@RequestMapping(value = "/googleSuccess", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		model.addAttribute("message", "Welcome");
		model.addAttribute("counter", ++counter);
		//logger.debug("[welcome] counter : {}", counter);

		// Spring uses InternalResourceViewResolver and return back index.jsp
		return "success";
 
	}

@RequestMapping("/redirect")
public String CallSampleServlet_sub( 
        HttpServletRequest request, HttpServletResponse response, @ModelAttribute("calendar") Calendar calendar) throws IOException{
	final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo";
	HttpTransport httpTransport=new NetHttpTransport();
	  JsonFactory jsonFactory=new JacksonFactory();
	String code= request.getParameter("code");
	System.out.println("code "+ code);
	
	final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email;https://www.googleapis.com/auth/calendar".split(";"));
	
	GoogleAuthorizationCodeFlow flow=new GoogleAuthorizationCodeFlow.Builder(httpTransport,jsonFactory,CLIENT_ID,CLIENT_SECRET,Collections.singleton(CalendarScopes.CALENDAR)).setAccessType("online").setApprovalPrompt("auto").build();
	//GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport,JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE).build();
	  GoogleTokenResponse resp 
	  =flow.newTokenRequest(code).setRedirectUri("http://localhost:8080/meetme/redirect").execute();
	  System.out.println((resp.getAccessToken()));
	  //GoogleCredential credential=new GoogleCredential().setFromTokenResponse(resp);
	  GoogleTokenResponse resp2 = new GoogleTokenResponse();
	  resp2.setAccessToken(resp.getAccessToken());
			  
	  final Credential credential = flow.createAndStoreCredential((TokenResponse) resp2, null);
	  System.out.println("credential created: "+ credential);
	  /*com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
		        Utils.HTTP_TRANSPORT_CAL, Utils.JSON_FACTORY_CAL, credential)
		        .setApplicationName("API Demo")
		        .build();*/
      // final Credential cred = flow.createAndStoreCredential(response, null);
	 	
	  
	  
	    //Credential credential_calendar = authFlow.loadCredential(Utils.getUserId(req));
	    com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
	            httpTransport, jsonFactory, credential)
	            .setApplicationName("Spring API Demo")
	            .build();	
	    
	    System.out.println("<h1>HELLO WORLD Prafull</h1>");
	    DateTime now = new DateTime(System.currentTimeMillis());
	    System.out.println("<h2>" + service.getApplicationName() + "</h2>");
	    CalendarList feed = service.calendarList().list().execute();
	    Events even = service.events().list("primary").setMaxResults(10).setTimeMin(now).setOrderBy("startTime").setSingleEvents(true).execute();
	    com.google.api.services.calendar.model.Events events = service.events().list("primary")
	            .setMaxResults(10)
	            .setTimeMin(now)
	            .setOrderBy("startTime")
	            .setSingleEvents(true)
	            .execute();
	    System.out.println("No upcoming events first found.");
	    List<Event> items = events.getItems();
	    List<Calendar> cal = new ArrayList<Calendar>();
	    DateTime start,end;
	    String st_str,end_str;
	    if (items.size() == 0) {
	        System.out.println("No upcoming events found.");
	    } else {
	    	
	    	System.out.println("Upcoming events");
	        for (Event event : items) {
	            start = event.getStart().getDateTime();
	            
	            end = event.getEnd().getDateTime();
	   
	        
	            if (start == null) {
	                start = event.getStart().getDateTime();
	                end = event.getEnd().getDateTime(); 
	            }
	            if (start !=null){
	            Calendar tempCal = new Calendar();
	            	end_str = end.toString().substring(0, 19);
	            	st_str = start.toString().substring(0, 19);	
	            	end_str = end_str.replace('T', ' ' );
	            	st_str = st_str.replace('T', ' ' );
	            	tempCal.setEndTime(end_str);
	            	tempCal.setStartTime(st_str);
	            	tempCal.setEventDescription(event.getSummary());
	            	tempCal.setEventName(event.getSummary());
	            	cal.add(tempCal);
	            	System.out.printf("%s (%s) end - (%s)\n", event.getSummary(), st_str,end);
	            }
	            
	        }
	    } 
	  final HttpRequestFactory requestFactory = httpTransport.createRequestFactory(credential);
		// Make an authenticated request
		final GenericUrl url = new GenericUrl(USER_INFO_URL);
		final HttpRequest req = requestFactory.buildGetRequest(url);
		req.getHeaders().setContentType("application/json");
		final String jsonIdentity = req.execute().parseAsString();
		//final String jsonIdentit1y = req.execute().parseAsString();

		System.out.println(jsonIdentity);
		
		Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
		          "Oauth2").build();
		 Userinfoplus userinfo = oauth2.userinfo().get().execute();
		 System.out.println(userinfo.toPrettyString());
		
		 com.google.gson.Gson usergson = new com.google.gson.Gson();
		 Userinfoplus user = usergson.fromJson(jsonIdentity, Userinfoplus.class);
		 System.out.println(user.getFamilyName()+ "  "+ user.getGivenName()+ " "+ user.getEmail());
		System.out.println(jsonIdentity);
		HttpSession session = request.getSession();
		session.setAttribute("email", user.getEmail());
		
		boolean check = calendarService.insertForGoogleCalendar(cal , user.getEmail());
		if( check == true)
			System.out.println("done and signed");
		return "success";
		
		
	// Exchange authorization code for user credentials.
	/*GoogleTokenResponse tokenResponse = authFlow.newTokenRequest(request.getParameter("code"))
	    .setRedirectUri(Utils.getRedirectUri(request)).execute();
	// Save the credentials for this user so we can access them from the main servlet.
	authFlow.createAndStoreCredential(tokenResponse, Utils.getUserId(request));
	response.sendRedirect(Utils.MAIN_SERVLET_PATH);
*/
}
	
	@RequestMapping("/google")
	public @ResponseBody void simplemethod( 
	        HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException{
	String REDIRECT_URI = "http://localhost:8080/meetme/redirect";
	HttpTransport httpTransport=new NetHttpTransport();
	  JsonFactory jsonFactory=new JacksonFactory();
	  final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email;https://www.googleapis.com/auth/calendar".split(";"));
	  GoogleAuthorizationCodeFlow flow=new GoogleAuthorizationCodeFlow.Builder(httpTransport,jsonFactory,CLIENT_ID,CLIENT_SECRET,SCOPE).setAccessType("online").setApprovalPrompt("auto").build();
	  //GoogleAuthorizationCodeFlow flow=new GoogleAuthorizationCodeFlow.Builder(httpTransport,jsonFactory,CLIENT_ID,CLIENT_SECRET,Collections.singleton(CalendarScopes.CALENDAR)).setAccessType("online").setApprovalPrompt("auto").build();
	  String url=flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	  System.out.println("Please open the following URL in your browser then type the authorization code:");
	  System.out.println("  " + url);	
	  response.sendRedirect(
			  flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build());
	  return;
	  /*String code= request.getParameter("code");
	  System.out.println("  " + code);	
	  GoogleTokenResponse resp=flow.newTokenRequest(code).setRedirectUri("http://localhost:8080/counterwebapp/redirect").execute();
	  GoogleCredential credential=new GoogleCredential().setFromTokenResponse(resp);
	  return VIEW_INDEX;*/
	  //googleDriveClient=new Drive.Builder(httpTransport,jsonFactory,credential).build();
	}
	
}
