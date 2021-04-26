package cz.educanet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.ArrayList;

@Produces
@Path("animals")
public class AnimalResource {

    @GET
    public Response getAnimals() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/c3zoo", "root", ""
        );

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM zvirata JOIN druhy ON(druhy.id = zvirata.druh)");
        /*String format = "%-20s%s%n";
        System.out.printf(format, "zvire", "druh");*/
        ArrayList<Animal> animals= new ArrayList<>();
        while(result.next()) {
            Animal animal = new Animal();

            String id = result.getString("id");
            animal.setId(id);
            String druh = result.getString("druh");
            animal.setDruh(druh);
            String jmeno = result.getString("jmeno");
            animal.setJmeno(jmeno);
            String vaha = result.getString("vaha");
            animal.setVaha(vaha);
            String narozen = result.getString("narozen");
            animal.setNarozen(narozen);

            animals.add(animal);
            System.out.println("Animal added");

            //System.out.printf(format, jmeno, druh);
        }

        connection.close();
        return Response.ok(animals).build();
    }
}
