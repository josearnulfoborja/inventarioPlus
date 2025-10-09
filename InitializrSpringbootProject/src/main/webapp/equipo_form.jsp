<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario Equipo</title>
    <link rel="stylesheet" href="/assets/css/estilos.css">
</head>
<body>
    <h1>Registrar Equipo</h1>
    <form action="/equipos" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required></textarea>

        <label for="estado">Estado:</label>
        <select id="estado" name="estado" required>
            <option value="DISPONIBLE">Disponible</option>
            <option value="EN_PRESTAMO">En Préstamo</option>
            <option value="MANTENIMIENTO">Mantenimiento</option>
        </select>

        <label for="costoDia">Costo por Día:</label>
        <input type="number" id="costoDia" name="costoDia" step="0.01" required>

        <label for="costoHora">Costo por Hora:</label>
        <input type="number" id="costoHora" name="costoHora" step="0.01" required>

        <label for="requiereEspecialista">Requiere Especialista:</label>
        <input type="checkbox" id="requiereEspecialista" name="requiereEspecialista">

        <button type="submit">Registrar</button>
    </form>
</body>
</html>