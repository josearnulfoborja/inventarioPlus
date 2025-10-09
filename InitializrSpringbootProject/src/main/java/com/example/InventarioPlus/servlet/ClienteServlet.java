package com.example.InventarioPlus.model.servlet;

import com.example.InventarioPlus.dao.ClienteDAO;
import com.example.InventarioPlus.model.Cliente;
import com.example.InventarioPlus.model.util.Conexion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/clientes")
public class ClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
    Connection connection = Conexion.getConnection();
        clienteDAO = new ClienteDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Cliente> clientes = clienteDAO.getAllClientes();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("clientes.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al obtener clientes", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String documento = request.getParameter("documento");
            String telefono = request.getParameter("telefono");
            String direccion = request.getParameter("direccion");
            String correo = request.getParameter("correo");

            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setDocumento(documento);
            cliente.setTelefono(telefono);
            cliente.setDireccion(direccion);
            cliente.setCorreo(correo);

            clienteDAO.addCliente(cliente);
            response.sendRedirect("/clientes");
        } catch (Exception e) {
            throw new ServletException("Error al agregar cliente", e);
        }
    }
}