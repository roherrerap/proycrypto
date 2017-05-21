package arqsoft.bank.service;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by javergarav on 30/03/2017.
 */
public class ServiceInterface {

    public void createService(DefaultHttpClient http, String endpoint, Object serviceToCreate) {

        try {

            HttpPost postRequest = new HttpPost(endpoint);

            Gson gson = new Gson();
            StringEntity input = new StringEntity(gson.toJson(serviceToCreate));
            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = http.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != Response.Status.CREATED.getStatusCode()) {
                throw new RuntimeException("FAILED: HTTP error code " + response.getStatusLine().getStatusCode());
            }

            System.out.println("SUCCESS: HTTP code " + response.getStatusLine().getStatusCode());
            http.getConnectionManager().shutdown();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
