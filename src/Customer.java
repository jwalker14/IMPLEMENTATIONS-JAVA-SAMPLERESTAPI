import org.json.JSONArray;

import javax.ws.rs.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jasonwalker on 11/23/16.
 */
@Path("/customers")
@Produces("text/plain")
public class Customer {
    private ConnectionManager db = new ConnectionManager();

    @GET
    public String getAllCustomers(){
        String sql = "SELECT * FROM customers";
        ResultSet rs = db.runQuery(sql);
        try{
            JSONArray json = Convertor.convertToJSON(rs);
            return json.toString();
        }
        catch(Exception e) {
            return e.toString();
        }
    }

    @POST
    public boolean postNewCustomer(){
        return false;
    }

    @PUT
    public boolean putCustomer(){
        return false;
    }

    @Path("{id}")
    @GET
    public String getCustomer(@PathParam("id")final String id){
        String sql = "SELECT * FROM customers WHERE customerNumber = " + id;
        ResultSet rs = db.runQuery(sql);

        try{
            JSONArray json = Convertor.convertToJSON(rs);
            return json.toString();
        }
        catch(Exception e){
            return e.toString();
        }
    }

    @Path("{id}")
    @DELETE
    public String deleteCustomer(@PathParam("id")final String id){
        // FOREIGN KEYS IN ORDERS TABLE
        // customerNumber deletes orders deletes orderdetails
        String sql = "SELECT * FROM orders WHERE customerNumber = "+ id;
        ResultSet rs = db.runQuery(sql);
        try{

            while(rs.next()){
                String orderNumber = rs.getNString("orderNumber");
                System.out.println(orderNumber);
                sql = "DELETE FROM orderdetails WHERE orderNumber = " + orderNumber;
                ResultSet rs2 = db.runQuery(sql);
                sql = "DELETE FROM orders WHERE orderNumber = " + orderNumber;
                ResultSet rs3 = db.runQuery(sql);
            }

            sql = "DELETE FROM customers WHERE customerNumber = " + id;
            ResultSet rs4 = db.runQuery(sql);
            return "TRUE";
        }
        catch(SQLException e){
            return e.toString();
        }
    }
}
