<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Si ya hay sesión activa, redirigir al dashboard
    if (session.getAttribute("usuario") != null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Sistema de Gestión de Préstamos</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/estilos.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>
<body class="login-page">
    <div class="login-container">
        <div class="row g-0 h-100">
            <!-- Panel izquierdo - Información del sistema -->
            <div class="col-lg-5 login-left">
                <div>
                    <i class="fas fa-tools login-logo"></i>
                    <h2 class="login-title">Sistema de Gestión</h2>
                    <p class="login-subtitle">Préstamos de Equipos</p>
                    
                    <div class="features mt-4">
                        <div class="feature-item">
                            <i class="fas fa-users"></i>
                            <span>Gestión de Clientes</span>
                        </div>
                        <div class="feature-item">
                            <i class="fas fa-cogs"></i>
                            <span>Control de Equipos</span>
                        </div>
                        <div class="feature-item">
                            <i class="fas fa-handshake"></i>
                            <span>Préstamos Seguros</span>
                        </div>
                        <div class="feature-item">
                            <i class="fas fa-chart-line"></i>
                            <span>Reportes Detallados</span>
                        </div>
                        <div class="feature-item">
                            <i class="fas fa-shield-alt"></i>
                            <span>Seguridad Garantizada</span>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- Panel derecho - Formulario de login -->
            <div class="col-lg-7 login-right">
                <div>
                    <h3 class="text-center mb-4">Iniciar Sesión</h3>
                    
                    <!-- Mostrar errores -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger alert-custom" role="alert">
                            <i class="fas fa-exclamation-circle me-2"></i>
                            ${error}
                        </div>
                    </c:if>
                    
                    <!-- Mostrar mensajes informativos -->
                    <c:if test="${not empty mensaje}">
                        <div class="alert alert-info alert-custom" role="alert">
                            <i class="fas fa-info-circle me-2"></i>
                            ${mensaje}
                        </div>
                    </c:if>
                    
                    <!-- Formulario de login -->
                    <form action="${pageContext.request.contextPath}/login" method="post" id="loginForm" novalidate>
                        <div class="form-floating mb-3">
                            <input type="text" 
                                   class="form-control" 
                                   id="username" 
                                   name="username" 
                                   placeholder="Usuario"
                                   required
                                   autocomplete="username">
                            <label for="username">
                                <i class="fas fa-user me-2"></i>Usuario
                            </label>
                            <div class="invalid-feedback">
                                Por favor ingrese su nombre de usuario.
                            </div>
                        </div>
                        
                        <div class="form-floating mb-3">
                            <input type="password" 
                                   class="form-control" 
                                   id="password" 
                                   name="password" 
                                   placeholder="Contraseña"
                                   required
                                   autocomplete="current-password">
                            <label for="password">
                                <i class="fas fa-lock me-2"></i>Contraseña
                            </label>
                            <div class="invalid-feedback">
                                Por favor ingrese su contraseña.
                            </div>
                        </div>
                        
                        <div class="form-check mb-3">
                            <input class="form-check-input" 
                                   type="checkbox" 
                                   id="rememberMe" 
                                   name="rememberMe">
                            <label class="form-check-label" for="rememberMe">
                                Recordar mi sesión
                            </label>
                        </div>
                        
                        <button type="submit" class="btn btn-login btn-primary w-100" id="btnLogin">
                            <i class="fas fa-sign-in-alt me-2"></i>
                            Iniciar Sesión
                        </button>
                    </form>
                    
                    <div class="forgot-password">
                        <a href="#" onclick="showForgotPasswordInfo()">
                            ¿Olvidaste tu contraseña?
                        </a>
                    </div>
                    
                    <!-- Usuarios de prueba -->
                    <div class="test-users">
                        <small class="text-muted">
                            <strong><i class="fas fa-users me-1"></i>Usuarios de prueba:</strong><br>
                            <div class="mt-2">
                                <span class="badge bg-primary">admin/admin123</span>
                                <span class="badge bg-success">evaluador/eval123</span>
                                <span class="badge bg-info">cliente/client123</span>
                                <span class="badge bg-warning text-dark">karen/karen123</span>
                            </div>
                            <div class="mt-2">
                                <small><i class="fas fa-lightbulb me-1"></i>Tip: Doble click en usuario para autocompletar</small>
                            </div>
                        </small>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    
    <!-- Custom JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/js/validaciones.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
</body>
</html>
