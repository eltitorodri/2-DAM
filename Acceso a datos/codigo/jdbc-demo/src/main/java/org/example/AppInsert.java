package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInsert {
    public static void main(String[] args) {

        try (Connection conexion = DatabaseConnection.getConnection()) {
            if (conexion != null) {
                // 1️⃣ Sentencia SQL con parámetros
                String sql = "INSERT INTO alumnos (nombre, edad) VALUES (?, ?)";

                // 2️⃣ PreparedStatement con RETURN_GENERATED_KEYS para obtener ID autoincrementable
                try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    stmt.setString(1, "Rodrigo"); // parámetro 1 → nombre
                    stmt.setInt(2, 19);           // parámetro 2 → edad

                    // 3️⃣ Ejecutar INSERT (no executeQuery)
                    int filasAfectadas = stmt.executeUpdate();
                    System.out.println("Filas insertadas: " + filasAfectadas);

                    // 4️⃣ Obtener ID generado
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idGenerado = rs.getInt(1);
                            System.out.println("ID generado: " + idGenerado);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

