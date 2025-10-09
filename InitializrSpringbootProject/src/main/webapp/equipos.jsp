<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Equipos</title>
    <link rel="stylesheet" href="/assets/css/estilos.css">
</head>
<body>
    <h1>Equipos Registrados</h1>
    <a href="/equipo_form.jsp">Registrar Nuevo Equipo</a>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Estado</th>
                <th>Costo por Día</th>
                <th>Costo por Hora</th>
                <th>Requiere Especialista</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="equipo" items="${equipos}">
                <tr>
                    <td>${equipo.idEquipo}</td>
                    <td>${equipo.nombre}</td>
                    <td>${equipo.descripcion}</td>
                    <td>${equipo.estado}</td>
                    <td>${equipo.costoDia}</td>
                    <td>${equipo.costoHora}</td>
                    <td>${equipo.requiereEspecialista ? "Sí" : "No"}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>