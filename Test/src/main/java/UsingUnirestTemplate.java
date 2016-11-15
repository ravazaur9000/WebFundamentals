import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admarcu on 11/10/2016.
 */
public class UsingUnirestTemplate implements CommentWebServiceClient {


    private static String url = "http://localhost:8080/api/comment/";

    public Comment save(Comment comment) throws UnirestException, IOException {
        ObjectMapper obj2 = new ObjectMapper();

        HttpResponse<JsonNode> postResponse = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(obj2.writeValueAsString(comment))
                .asJson();
        return null;

    }

    public Comment update(Comment comment) throws IOException, UnirestException {
        ObjectMapper obj2 = new ObjectMapper();
        String[] uriParsed = comment.get_links().toString().split("/");
        String commentId = uriParsed[uriParsed.length - 1].replace("}", "");
        HttpResponse<JsonNode> resp = Unirest.put(url + commentId)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(obj2.writeValueAsString(comment)).asJson();
        Comment c = obj2.readValue(resp.getBody().toString(), Comment.class);
        System.out.println(c);
        return c;
    }

    public Comment findById(Long commentId) throws UnirestException, IOException {
        HttpResponse<JsonNode> response = Unirest.get(url + commentId).asJson();
        ObjectMapper mapper = new ObjectMapper();
        Comment comment = mapper.readValue(response.getBody().toString(), Comment.class);
        return comment;

    }

    public List<Comment> findAll() {
        try {
            ObjectMapper obj2 = new ObjectMapper();
            HttpResponse<JsonNode> resp = Unirest.get(url).asJson();
            return Arrays.asList(obj2.readValue(resp.getBody().toString(), Comments.class).get_embedded().getComment());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Long commentId) throws UnirestException {
        Unirest.delete(url + commentId).asJson();
    }
}
