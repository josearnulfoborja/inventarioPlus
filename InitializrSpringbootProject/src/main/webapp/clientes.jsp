<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="/assets/css/estilos.css">
</head>
<body>
    <h1>Clientes Registrados</h1>
    <a href="${pageContext.request.contextPath}/mvc/clientes/nuevo">Registrar Nuevo Cliente</a>
    <c:if test="${not empty success}">
        <div class="alert alert-success">${success}</div>
    </c:if>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Documento</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Correo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cliente" items="${clientes}">
                <tr>
                    <td>${cliente.idCliente}</td>
                    <td>${cliente.nombre}</td>
                    <td>${cliente.documento}</td>
                    <td>${cliente.telefono}</td>
                    <td>${cliente.direccion}</td>
                    <td>${cliente.correo}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/mvc/clientes/editar/${cliente.idCliente}">Editar</a>
                        <form action="${pageContext.request.contextPath}/mvc/clientes/eliminar/${cliente.idCliente}" method="post" style="display:inline" onsubmit="return confirm('¿Eliminar cliente?');">
                            <button type="submit">Eliminar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>