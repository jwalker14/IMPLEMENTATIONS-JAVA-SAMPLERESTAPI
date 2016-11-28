import org.json.JSONArray;
import javax.ws.rs.*;
import java.sql.ResultSet;

/**
 * Created by jasonwalker on 11/23/16.
 */
@Path("/customers")
@Produces("text/plain")
public class Customer {
    private ConnectionManager db = new ConnectionManager();


    /**
     *
     * @return json string or exception string
     * @description this function returns all the customers from the database.
     */
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

    /**
     *
     * @param customerName - usually company name
     * @param contactLastName
     * @param contactFirstName
     * @param phone
     * @param addressLine1
     * @param addressLine2
     * @param city
     * @param state
     * @param postalCode
     * @param country
     * @param salesRepEmployeeNumber - foreign key to employee database
     * @param creditLimit
     * @return integer
     * @description returns 1 if added to database 0 is otherwise
     */
    @POST
    public int postNewCustomer(@FormParam("customerName") String customerName,
                                   @FormParam("contactLastName") String contactLastName,
                                   @FormParam("contactFirstName") String contactFirstName,
                                   @FormParam("phone") String phone,
                                   @FormParam("addressLine1") String addressLine1,
                                   @FormParam("addressLine2") String addressLine2,
                                   @FormParam("city") String city,
                                   @FormParam("state") String state,
                                   @FormParam("postalCode") String postalCode,
                                   @FormParam("country") String country,
                                   @FormParam("salesRepEmployeeNumber") int salesRepEmployeeNumber,
                                   @FormParam("creditLimit") float creditLimit){

        String sql =    "INSERT INTO customers " +
                        "(customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber,creditLimit) " +
                        "VALUES (" +
                            "'" + customerName + "'," +
                            "'" + contactLastName + "'," +
                            "'" + contactFirstName + "'," +
                            "'" + phone + "'," +
                            "'" + addressLine1 + "'," +
                            "'" + addressLine2 + "'," +
                            "'" + city + "'," +
                            "'" + state + "'," +
                            "'" + postalCode + "'," +
                            "'" + country + "'," +
                            "'" + salesRepEmployeeNumber + "'," +
                            "'" + creditLimit + "'" +
                        ")";
        System.out.println(sql);
        int updated = db.executeUpdate(sql);

        return updated;
    }

    /**
     *
     * @param customerNumber
     * @param customerName
     * @param contactLastName
     * @param contactFirstName
     * @param phone
     * @param addressLine1
     * @param addressLine2
     * @param city
     * @param state
     * @param postalCode
     * @param country
     * @param salesRepEmployeeNumber
     * @param creditLimit
     * @return int
     * @description 1 if sucessfully updated 0 otherwise
     *
     * @notes this should post a new entry if one doesn't exist. (Currently doesn't)
     */
    @Path("{id}")
    @PUT
    public int putCustomer( @PathParam("id") int customerNumber,
                            @FormParam("customerName") String customerName,
                            @FormParam("contactLastName") String contactLastName,
                            @FormParam("contactFirstName") String contactFirstName,
                            @FormParam("phone") String phone,
                            @FormParam("addressLine1") String addressLine1,
                            @FormParam("addressLine2") String addressLine2,
                            @FormParam("city") String city,
                            @FormParam("state") String state,
                            @FormParam("postalCode") String postalCode,
                            @FormParam("country") String country,
                            @FormParam("salesRepEmployeeNumber") int salesRepEmployeeNumber,
                            @FormParam("creditLimit") float creditLimit){

        String sql = "UPDATE customers " +
                "SET " +
                "customerName='" + customerName + "', " +
                "contactLastName='" + contactLastName + "', " +
                "contactFirstName='" + contactFirstName + "', " +
                "phone='" + phone + "', " +
                "addressLine1='" + addressLine1 + "', " +
                "addressLine2='" + addressLine2 + "', " +
                "city='" + city + "', " +
                "state='" + state + "', " +
                "postalCode='" + postalCode + "', " +
                "country='" + country + "', " +
                "salesRepEmployeeNumber='" + salesRepEmployeeNumber + "', " +
                "creditLimit='" + creditLimit + "' " +
                "WHERE customerNumber='" + customerNumber + "'";
        System.out.println(sql);
        int updated = db.executeUpdate(sql);

        return updated;
    }

    /**
     *
     * @param id
     * @return string
     * @description returns a json object of a customer entry by an ID
     */
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

//    NOT WORKING?
    @DELETE
    @Path("{id}")
    public String deleteCustomer(@PathParam("id")final int id){
        System.out.println(id);
        return "DELETE";
    }
}
