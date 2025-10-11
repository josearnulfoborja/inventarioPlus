<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario Cliente</title>
    <link rel="stylesheet" href="/assets/css/estilos.css">
</head>
<body>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <h1>Registrar Cliente</h1>
    <form action="${pageContext.request.contextPath}${cliente.idCliente != null ? '/mvc/clientes/editar' : '/mvc/clientes'}" method="post">
        <input type="hidden" name="idCliente" value="${cliente.idCliente}" />

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="${cliente.nombre}" required>

        <label for="documento">Documento:</label>
        <input type="text" id="documento" name="documento" value="${cliente.documento}" required>

        <label for="telefono">Teléfono:</label>
        <input type="text" id="telefono" name="telefono" value="${cliente.telefono}">

        <label for="direccion">Dirección:</label>
        <input type="text" id="direccion" name="direccion" value="${cliente.direccion}">

        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" value="${cliente.correo}" required>

        <button type="submit">${cliente.idCliente != null ? 'Actualizar' : 'Registrar'}</button>
    </form>
</body>
</html>