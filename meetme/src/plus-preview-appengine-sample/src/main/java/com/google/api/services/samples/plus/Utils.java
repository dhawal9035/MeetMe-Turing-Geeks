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

import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.plus.PlusScopes;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

class Utils {

  private static GoogleClientSecrets clientSecrets = null;
  private static GoogleClientSecrets clientSecrets_cal = null;
  private static final Set<String> SCOPES = Collections.singleton(PlusScopes.PLUS_ME);
  //prafull
  private static final Set<String> SCOPES_CALENDAR = Collections.singleton(CalendarScopes.CALENDAR);
  public static final UrlFetchTransport HTTP_TRANSPORT = new UrlFetchTransport();
  public static final JacksonFactory JSON_FACTORY = new JacksonFactory();
  public static final UrlFetchTransport HTTP_TRANSPORT_CAL = new UrlFetchTransport();
  public static final AppEngineDataStoreFactory DATA_STORE_FACTORY =
      AppEngineDataStoreFactory.getDefaultInstance();

  public static final AppEngineDataStoreFactory DATA_STORE_FACTORYCAL =
      AppEngineDataStoreFactory.getDefaultInstance();

  public static final JacksonFactory JSON_FACTORY_CAL = new JacksonFactory();
  public static final String MAIN_SERVLET_PATH = "/plussampleservlet";
  public static final String AUTH_CALLBACK_SERVLET_PATH = "/oauth2callback";

  private static GoogleClientSecrets getClientSecrets() throws IOException {
    if (clientSecrets == null) {
      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
          new InputStreamReader(Utils.class.getResourceAsStream("/client_secrets.json")));
      Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
          && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
          "Download client_secrets.json file from https://code.google.com/apis/console/?api=plus "
          + "into plus-preview-appengine-sample/src/main/resources/client_secrets.json");
    }
    return clientSecrets;
  }
  private static GoogleClientSecrets getClientSecrets_cal() throws IOException {
    if (clientSecrets_cal == null) {
      clientSecrets_cal = GoogleClientSecrets.load(JSON_FACTORY_CAL,
          new InputStreamReader(Utils.class.getResourceAsStream("/client_secrets.json")));
      Preconditions.checkArgument(!clientSecrets_cal.getDetails().getClientId().startsWith("Enter ")
          && !clientSecrets_cal.getDetails().getClientSecret().startsWith("Enter "),
          "Download client_secrets.json file from https://code.google.com/apis/console/?api=plus "
          + "into plus-preview-appengine-sample/src/main/resources/client_secrets.json");
    }
    return clientSecrets_cal;
  }
  /*
  static GoogleAuthorizationCodeFlow initializeFlow() throws IOException { return new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, getClientSecrets(), SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("offline").build();}
  */
static GoogleAuthorizationCodeFlow initializeFlowCalendar() throws IOException {
    return new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT_CAL, JSON_FACTORY_CAL, getClientSecrets_cal(),SCOPES_CALENDAR).setDataStoreFactory(DATA_STORE_FACTORYCAL).setAccessType("offline").build();}

  static String getRedirectUri(HttpServletRequest req) {
    GenericUrl requestUrl = new GenericUrl(req.getRequestURL().toString());
    requestUrl.setRawPath(AUTH_CALLBACK_SERVLET_PATH);
    return requestUrl.build();
  }

  static String getUserId(HttpServletRequest req) {
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    return user.getUserId();
  }
}
