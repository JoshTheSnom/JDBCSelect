package cz.educanet;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/c3zoo", "root", ""
        );

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT zvirata.jmeno, druhy.nazev FROM zvirata JOIN druhy ON(zvirata.druh = druhy.id) JOIN osetruje ON(osetruje.zvire = zvirata.id) JOIN osetrovatele ON(osetrovatele.id = osetruje.osetrovatel AND osetrovatele.narozen = (SELECT osetrovatele.narozen FROM osetrovatele ORDER BY osetrovatele.narozen ASC LIMIT 1))");
        String format = "%-20s%s%n";
        System.out.printf(format, "zvire", "druh");
        while(result.next()) {
            String jmeno = result.getString("jmeno");
            String druh = result.getString("nazev");

            System.out.printf(format, jmeno, druh);
        }

        connection.close();
    }

}
