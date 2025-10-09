package com.example.InventarioPlus.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/prestamos_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password"; // Cambia esto por tu contraseña real

    private static Connection connection;

    static {
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}