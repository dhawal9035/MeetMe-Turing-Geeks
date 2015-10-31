/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.api.services.samples.plus;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PlusSampleServlet extends HttpServlet {

  private static final long serialVersionUID = 1;
 
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    // Check if we have stored credentials using the Authorization Flow.
    // Note that we only check if there are stored credentials, but not if they are still valid.
    // The user may have revoked authorization, in which case we would need to go through the
    // authorization flow again, which this implementation does not handle.
    //----GoogleAuthorizationCodeFlow authFlow = Utils.initializeFlow();
    //Prafull
    GoogleAuthorizationCodeFlow authFlowCalendar = Utils.initializeFlowCalendar();
    
  //----Credential credential = authFlow.loadCredential(Utils.getUserId(req));
    //Prafull
    Credential credential_calendar = authFlowCalendar.loadCredential(Utils.getUserId(req));
    /* ----
    if (credential == null) {
      // If we don't have a token in store, redirect to authorization screen.
      resp.sendRedirect(
          authFlow.newAuthorizationUrl().setRedirectUri(Utils.getRedirectUri(req)).build());
      return;
    }
    ----*/
    //Prafull
    if (credential_calendar == null) {
      // If we don't have a token in store, redirect to authorization screen.
      resp.sendRedirect(
          authFlowCalendar.newAuthorizationUrl().setRedirectUri(Utils.getRedirectUri(req)).build());
      return;
    }
     
    

    // If we do have stored credentials, build the Plus object using them.
   
    
    //--- Plus plus = new Plus.Builder(
          //=---Utils.HTTP_TRANSPORT, Utils.JSON_FACTORY, credential).setApplicationName("API Demo").build();
    
    com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
        Utils.HTTP_TRANSPORT_CAL, Utils.JSON_FACTORY_CAL, credential_calendar)
        .setApplicationName("API Demo")
        .build();
    
    // Make the API call
    

    
    //Person profile = plus.people().get("me").execute();
    // Send the results as the response

    // create a response writer to print on the JSP page
    PrintWriter respWriter = resp.getWriter();
    resp.setStatus(200);
    resp.setContentType("text/html");
    respWriter.println("<h1>HELLO WORLD </h1>");
    
    
    //respWriter.println("<img src='" + profile.getImage().getUrl() + "'>");
    //respWriter.println("<a href='" + profile.getUrl() + "'>" + profile.getDisplayName() + "</a>");
    
    
    DateTime now = new DateTime(System.currentTimeMillis());
    respWriter.println("<h2>" + service.getApplicationName() + "</h2>");
  
    CalendarList feed = service.calendarList().list().execute();
    Events even = service.events().list("primary").setMaxResults(10).setTimeMin(now).setOrderBy("startTime").setSingleEvents(true).execute();
    
    com.google.api.services.calendar.model.Events events = service.events().list("primary")
        .setMaxResults(10)
        .setTimeMin(now)
        .setOrderBy("startTime")
        .setSingleEvents(true)
        .execute();
        
    List<Event> items = events.getItems();
    if (items.size() == 0) {
        respWriter.println("No upcoming events found.");
    } else {
        respWriter.println("Upcoming events");
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
            }
            respWriter.printf("%s (%s)\n", event.getSummary(), start);
        }
    }
    
    //respWriter.println("<a href='" + profile.getUrl() + "'>" + profile.getDisplayName() + "</a>");
 
  
  }
}
