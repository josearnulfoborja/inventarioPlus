package com.example.InventarioPlus.dao;

import com.example.InventarioPlus.model.Equipo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {

    private Connection connection;

    public EquipoDAO(Connection connection) {
        this.connection = connection;
    }

    public void addEquipo(Equipo equipo) throws SQLException {
        String sql = "INSERT INTO Equipo (nombre, descripcion, estado, costoDia, costoHora, requiereEspecialista) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getDescripcion());
            stmt.setString(3, equipo.getEstado());
            stmt.setDouble(4, equipo.getCostoDia());
            stmt.setDouble(5, equipo.getCostoHora());
            stmt.setBoolean(6, equipo.isRequiereEspecialista());
            stmt.executeUpdate();
        }
    }

    public List<Equipo> getAllEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String sql = "SELECT * FROM Equipo";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setDescripcion(rs.getString("descripcion"));
                equipo.setEstado(rs.getString("estado"));
                equipo.setCostoDia(rs.getDouble("costoDia"));
                equipo.setCostoHora(rs.getDouble("costoHora"));
                equipo.setRequiereEspecialista(rs.getBoolean("requiereEspecialista"));
                equipos.add(equipo);
            }
        }
        return equipos;
    }

    public Equipo getEquipoById(int id) throws SQLException {
        String sql = "SELECT * FROM Equipo WHERE idEquipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Equipo equipo = new Equipo();
                    equipo.setIdEquipo(rs.getInt("idEquipo"));
                    equipo.setNombre(rs.getString("nombre"));
                    equipo.setDescripcion(rs.getString("descripcion"));
                    equipo.setEstado(rs.getString("estado"));
                    equipo.setCostoDia(rs.getDouble("costoDia"));
                    equipo.setCostoHora(rs.getDouble("costoHora"));
                    equipo.setRequiereEspecialista(rs.getBoolean("requiereEspecialista"));
                    return equipo;
                }
            }
        }
        return null;
    }

    public void updateEquipo(Equipo equipo) throws SQLException {
        String sql = "UPDATE Equipo SET nombre = ?, descripcion = ?, estado = ?, costoDia = ?, costoHora = ?, requiereEspecialista = ? WHERE idEquipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipo.getNombre());
            stmt.setString(2, equipo.getDescripcion());
            stmt.setString(3, equipo.getEstado());
            stmt.setDouble(4, equipo.getCostoDia());
            stmt.setDouble(5, equipo.getCostoHora());
            stmt.setBoolean(6, equipo.isRequiereEspecialista());
            stmt.setInt(7, equipo.getIdEquipo());
            stmt.executeUpdate();
        }
    }

    public void deleteEquipo(int id) throws SQLException {
        String sql = "DELETE FROM Equipo WHERE idEquipo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}