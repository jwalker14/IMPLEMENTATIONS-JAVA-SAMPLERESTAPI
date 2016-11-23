import org.json.JSONArray;

import javax.ws.rs.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("/helloworld")
@Produces("text/plain")
public class HelloWorld {
    private ConnectionManager db = new ConnectionManager();

    @GET
    public String getClichedMessage(){
        ResultSet rs;
        try{
            PreparedStatement ps = db.getConnection().prepareStatement("SELECT * FROM customers");
            rs = ps.executeQuery();
            JSONArray json = Convertor.convertToJSON(rs);

            return json.toString();
        }
        catch(Exception e){
            return e.toString();
        }

    }

    @POST
    public String postClichedMessage(){
        return "Post Message";
    }

    @PUT
    public String putClichedMessage(){
        return "PUT MESSAGE";
    }

    @DELETE
    public String delete(){
        return "DELETE MESSAGE";
    }
}