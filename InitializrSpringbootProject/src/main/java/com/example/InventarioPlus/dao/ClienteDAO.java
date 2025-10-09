package com.example.InventarioPlus.dao;

import com.example.InventarioPlus.model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO Cliente (nombre, documento, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getCorreo());
            stmt.executeUpdate();
        }
    }

    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    public Cliente getClienteById(int id) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setDocumento(rs.getString("documento"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setCorreo(rs.getString("correo"));
                    return cliente;
                }
            }
        }
        return null;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE Cliente SET nombre = ?, documento = ?, telefono = ?, direccion = ?, correo = ? WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDocumento());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.setString(5, cliente.getCorreo());
            stmt.setInt(6, cliente.getIdCliente());
            stmt.executeUpdate();
        }
    }

    public void deleteCliente(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE idCliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}