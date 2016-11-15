import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
;
/**
 * Created by fstancu on 11/9/2016.
 */

public class UsingRestTemplate implements CommentWebServiceClient {
    private static String url="http://localhost:8080/api/comment/";
    /**
     * http://localhost:8080/api/comment
     * <p>
     * Content-Type: application/json
     * <p>
     * {
     * "name": "Florin2",
     * "email": "stancu.florin23@gmail.com",
     * "comment": "test",
     * "date": "2016-01-02"
     * }
     *
     * @param comment
     * @return
     */
    public Comment save(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        return restTemplate.postForObject(url, comment, Comment.class);
    }

    public Comment update(Comment comment) {
        RestTemplate restTemplate = new RestTemplate();

        System.out.println(comment);
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);

        String[] uriParsed = comment.get_links().toString().split("/");
        String commentId = uriParsed[uriParsed.length-1].replace("}","");
        System.out.println(commentId);
        restTemplate.put(url + commentId, comment);
        return  null;
    }

    /**
     * http://localhost:8080/api/comment/4
     *
     * @param commentId
     * @return
     */

    public Comment findById(Long commentId) {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonHttpMessageConverter);
        return restTemplate.getForObject(url + commentId, Comment.class);
    }

    public List<Comment> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        Comments comments = restTemplate.getForObject(url, Comments.class);
        return Arrays.asList(comments.get_embedded().getComment());
    }

    public void delete(Long commentId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url + commentId);
    }

}
