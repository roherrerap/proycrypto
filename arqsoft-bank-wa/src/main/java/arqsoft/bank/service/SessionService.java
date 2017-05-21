package arqsoft.bank.service;

import arqsoft.bank.model.Session;
import arqsoft.bank.route.AuthenticationRoute;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by javergarav on 01/03/2017.
 */
public class SessionService {

    private String sessionsEndpoint = AuthenticationRoute.SESSIONS_ENDPOINT;

    public Session getSessionByUserId(long userId) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        StringBuffer stringBuffer = new StringBuffer();

        try {

            HttpGet getRequest = new HttpGet(sessionsEndpoint + userId);
            System.out.println("PATH: " + sessionsEndpoint + userId);
            getRequest.addHeader("accept", "application/json");
            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("FAILED: HTTP error code " + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            System.out.println("SUCCESS: HTTP code " + response.getStatusLine().getStatusCode());
            String output;

            while ((output = br.readLine()) != null) {
                stringBuffer.append(output);
            }

            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject obj = new JSONObject(stringBuffer.toString());
        Session session = new Session(obj.getLong("userId"), obj.getString("token"));
        System.out.println("SESSION: " +  session.getUserId());
        return session;
    }

    public String createSession(Session sessionToCreate) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        ServiceInterface serviceInterface = new ServiceInterface();

        serviceInterface.createService(httpClient, sessionsEndpoint, sessionToCreate);

        return "The session has been successfully created!";
    }


}