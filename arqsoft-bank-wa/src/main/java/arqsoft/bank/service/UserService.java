package arqsoft.bank.service;

import arqsoft.bank.model.User;
import arqsoft.bank.route.AuthenticationRoute;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.entity.StringEntity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.Gson;
import javax.ws.rs.core.Response;
import java.net.MalformedURLException;

/**
 * Created by javergarav on 01/03/2017.
 */
public class UserService {

    private String usersEndpoint = AuthenticationRoute.USERS_ENDPOINT;

    public User[] getAllUsers() throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        StringBuffer stringBuffer = new StringBuffer();

        try {

            HttpGet getRequest = new HttpGet(usersEndpoint);
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

        ArrayList<User> usersList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(stringBuffer.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj =  jsonArray.getJSONObject(i);
            usersList.add(new User(obj.getLong("id"), obj.getString("name"),
                    obj.getString("email"), obj.getString("password")));
        }

        User[] users = new User[usersList.size()];
        usersList.toArray(users);
        return users;
    }

    public User getUserById(Long id) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        StringBuffer stringBuffer = new StringBuffer();

        try {

            HttpGet getRequest = new HttpGet(usersEndpoint + "id/" + id);
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
        User user = new User(obj.getLong("id"), obj.getString("name"),
                obj.getString("email"), obj.getString("password"));
        return user;
    }

    public User getUserByEmail(String email) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();
        StringBuffer stringBuffer = new StringBuffer();

        try {

            HttpGet getRequest = new HttpGet(usersEndpoint + "email/" + email);
            System.out.println("PATH: " + usersEndpoint + "byEmail/" + email);
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
        User user = new User(obj.getLong("id"), obj.getString("name"),
                obj.getString("email"), obj.getString("password"));
        return user;
    }

    public String createUser(User userToCreate) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        ServiceInterface serviceInterface = new ServiceInterface();

        serviceInterface.createService(httpClient, usersEndpoint, userToCreate);

        return "The user has been successfully created!";
    }

    public String updateUser(Long id, User userToUpdate) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            HttpPut putRequest = new HttpPut(usersEndpoint + id);

            Gson gson = new Gson();
            StringEntity input = new StringEntity(gson.toJson(userToUpdate));
            input.setContentType("application/json");
            putRequest.setEntity(input);

            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != Response.Status.NO_CONTENT.getStatusCode()) {
                throw new RuntimeException("FAILED: HTTP error code " + response.getStatusLine().getStatusCode());
            }

            System.out.println("SUCCESS: HTTP code " + response.getStatusLine().getStatusCode());
            httpClient.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "The user has been successfully updated!";
    }

    public String deleteUser(Long id) throws Exception {

        DefaultHttpClient httpClient = new DefaultHttpClient();

        try {

            HttpDelete deleteRequest = new HttpDelete(usersEndpoint + id);
            HttpResponse response = httpClient.execute(deleteRequest);

            if (response.getStatusLine().getStatusCode() != Response.Status.OK.getStatusCode()) {
                throw new RuntimeException("FAILED: HTTP error code " + response.getStatusLine().getStatusCode());
            }

            System.out.println("SUCCESS: HTTP code " + response.getStatusLine().getStatusCode());
            httpClient.getConnectionManager().shutdown();

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "The user has been successfully deleted!";
    }
}