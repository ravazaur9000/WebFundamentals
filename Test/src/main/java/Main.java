import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;

/**
 * Created by fstancu on 11/9/2016.
 */

public class Main {

    public static void main(String[] args) throws UnirestException, IOException {

        Comment commentToBeSaved = new Comment("Adrian Marcu", "marcadi10@gmail.com", "Test comment", new Date());

        CommentWebServiceClient service = new UsingRestTemplate();
        CommentWebServiceClient service2 = new UsingUnirestTemplate();


        /*Unirest save*/
        //service2.save(commentToBeSaved);

        /*Unirest findById*/
        //System.out.println(service2.findById(8l));

        /*Unirest delete*/
        //service2.delete(7l);

        /*Unirest findall*/
        //System.out.println(service2.findAll());


        /*Unirest update*/
        //Comment commentToUpdate2 = service2.findById(9l);
        //commentToUpdate2.setComment("Comment Changed");
        //service2.update(commentToUpdate2);


        /*RestTemplate update*/
        //Comment commentToUpdate1 = service.findById(7l);
        //commentToUpdate1.setComment("Comment has changed with RestTemplate");
        //service.update(commentToUpdate1);

        /*RestTemplate findAll*/
        //System.out.println(service.findAll());

        /*RestTemplate findById*/
        //System.out.println(service.findById(1l).toString());

        /*RestTemplate save*/
        //Comment commentSaved = service.save(commentToBeSaved);

        /*RestTemplate delete*/
        //service.delete((long) 5);



    }



}
