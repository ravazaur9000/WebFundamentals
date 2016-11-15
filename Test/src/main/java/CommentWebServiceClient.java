import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.List;

/**
 * Created by fstancu on 11/9/2016.
 */
public interface CommentWebServiceClient {

    Comment save(Comment comment) throws UnirestException, IOException;
    Comment update(Comment comment) throws IOException, UnirestException;
    Comment findById(Long commentId) throws UnirestException, IOException;
    List<Comment> findAll();
    void delete(Long commentId) throws UnirestException;

}
