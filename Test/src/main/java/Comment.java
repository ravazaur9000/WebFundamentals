import java.util.Date;

/**
 * Created by fstancu on 11/9/2016.
 */
public class Comment {

    private long id;
    private String name;
    private String email;
    private String comment;
    private Date date;
    private Object _links;






    public Comment() {
    }

    public Comment(String name, String email, String comment, Date date) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", date=" + date + '\'' +
                ", _links=" + _links +
                '}';
    }

    public Object get_links() {
        return _links;
    }

    public void set_links(Object _links) {
        this._links = _links;
    }
}
