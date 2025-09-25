package org.example;

import javax.xml.crypto.Data;
import java.sql.*;

public class AppMayor20 {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                String sql = "select * from alumnos where edad > 20";
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    System.out.println("ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Edad: " + edad);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
