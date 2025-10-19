/**
 * ================================================
 * LOGIN PAGE JAVASCRIPT
 * Funcionalidades específicas para la página de login
 * ================================================ */

// Esperar a que el DOM esté cargado
document.addEventListener('DOMContentLoaded', function() {
    initializeLogin();
});

/**
 * Inicializar funcionalidades del login
 */
function initializeLogin() {
    initializeFormValidation();
    initializeInputHandlers();
    initializeAnimations();
    initializeShortcuts();
}

/**
 * Inicializar validación del formulario
 */
function initializeFormValidation() {
    const loginForm = document.getElementById('loginForm');
    if (!loginForm) return;
    
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        validateAndSubmitForm();
    });
}

/**
 * Validar y enviar formulario
 */
function validateAndSubmitForm() {
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const btnLogin = document.getElementById('btnLogin');
    
    // Limpiar estados previos
    clearValidationStates();
    
    let isValid = true;
    
    // Validar username
    if (!username.value.trim()) {
        showFieldError(username, 'Por favor ingrese su nombre de usuario.');
        isValid = false;
    } else if (username.value.trim().length < 3) {
        showFieldError(username, 'El usuario debe tener al menos 3 caracteres.');
        isValid = false;
    }
    
    // Validar password
    if (!password.value.trim()) {
        showFieldError(password, 'Por favor ingrese su contraseña.');
        isValid = false;
    } else if (password.value.length < 4) {
        showFieldError(password, 'La contraseña debe tener al menos 4 caracteres.');
        isValid = false;
    }
    
    if (isValid) {
        submitForm(btnLogin);
    } else {
        // Enfocar el primer campo con error
        const firstError = document.querySelector('.is-invalid');
        if (firstError) {
            firstError.focus();
        }
    }
}

/**
 * Mostrar error en un campo
 */
function showFieldError(field, message) {
    field.classList.add('is-invalid');
    
    // Actualizar mensaje de error
    const feedback = field.nextElementSibling?.nextElementSibling;
    if (feedback && feedback.classList.contains('invalid-feedback')) {
        feedback.textContent = message;
    }
}

/**
 * Limpiar estados de validación
 */
function clearValidationStates() {
    const fields = document.querySelectorAll('.form-control');
    fields.forEach(field => {
        field.classList.remove('is-invalid', 'is-valid');
    });
}

/**
 * Enviar formulario con estado de carga
 */
function submitForm(btnLogin) {
    // Mostrar estado de carga
    setButtonLoading(btnLogin, true);
    
    // Simular validación (en un entorno real esto sería instantáneo)
    setTimeout(() => {
        document.getElementById('loginForm').submit();
    }, 500);
}

/**
 * Establecer estado de carga en botón
 */
function setButtonLoading(button, loading) {
    if (loading) {
        button.dataset.originalText = button.innerHTML;
        button.innerHTML = '<i class="fas fa-spinner fa-spin me-2"></i>Validando...';
        button.disabled = true;
        button.classList.add('loading');
    } else {
        button.innerHTML = button.dataset.originalText || 'Iniciar Sesión';
        button.disabled = false;
        button.classList.remove('loading');
    }
}

/**
 * Inicializar manejadores de inputs
 */
function initializeInputHandlers() {
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    
    if (username) {
        username.addEventListener('input', function() {
            clearFieldError(this);
        });
        
        // Auto-completar con doble click
        username.addEventListener('dblclick', function() {
            fillTestCredentials();
        });
    }
    
    if (password) {
        password.addEventListener('input', function() {
            clearFieldError(this);
        });
        
        // Permitir envío con Enter
        password.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                validateAndSubmitForm();
            }
        });
    }
}

/**
 * Limpiar error de un campo
 */
function clearFieldError(field) {
    field.classList.remove('is-invalid');
    field.classList.add('is-valid');
}

/**
 * Llenar credenciales de prueba
 */
function fillTestCredentials() {
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    
    if (username && password) {
        username.value = 'admin';
        password.value = 'admin123';
        
        // Añadir efectos visuales
        username.classList.add('is-valid');
        password.classList.add('is-valid');
        
        // Mostrar tip
        showTooltip('Credenciales de prueba cargadas. Presiona Enter para continuar.');
    }
}

/**
 * Inicializar animaciones
 */
function initializeAnimations() {
    const container = document.querySelector('.login-container');
    if (container) {
        // Aplicar animación de entrada
        container.classList.add('fade-in');
        
        // Efecto de hover en features
        const featureItems = document.querySelectorAll('.feature-item');
        featureItems.forEach(item => {
            item.addEventListener('mouseenter', function() {
                this.style.transform = 'translateX(10px)';
                this.style.transition = 'transform 0.3s ease';
            });
            
            item.addEventListener('mouseleave', function() {
                this.style.transform = 'translateX(0)';
            });
        });
    }
}

/**
 * Inicializar atajos de teclado
 */
function initializeShortcuts() {
    document.addEventListener('keydown', function(e) {
        // Ctrl + Enter para enviar formulario
        if (e.ctrlKey && e.key === 'Enter') {
            e.preventDefault();
            validateAndSubmitForm();
        }
        
        // Escape para limpiar formulario
        if (e.key === 'Escape') {
            clearForm();
        }
    });
}

/**
 * Limpiar formulario
 */
function clearForm() {
    const form = document.getElementById('loginForm');
    if (form) {
        form.reset();
        clearValidationStates();
    }
}

/**
 * Mostrar información de contraseña olvidada
 */
function showForgotPasswordInfo() {
    const message = `
Para recuperar su contraseña, contacte al administrador del sistema.

📋 USUARIOS DE PRUEBA:

👨‍💼 admin / admin123 (Administrador)
   • Acceso completo al sistema
   • Gestión de usuarios y reportes

👨‍🔧 evaluador / eval123 (Evaluador)
   • Evaluación de préstamos
   • Gestión de clientes y equipos

👤 cliente / client123 (Cliente)
   • Visualización de préstamos
   • Acceso limitado

👩‍💻 karen / karen123 (Administrador)
   • Usuario de desarrollo
   • Acceso completo

💡 TIP: Haz doble click en el campo usuario para cargar credenciales de prueba automáticamente.`;
    
    alert(message);
}

/**
 * Mostrar tooltip temporal
 */
function showTooltip(message, duration = 3000) {
    // Crear tooltip
    const tooltip = document.createElement('div');
    tooltip.className = 'alert alert-info alert-dismissible fade show position-fixed';
    tooltip.style.cssText = `
        top: 20px;
        right: 20px;
        z-index: 9999;
        max-width: 300px;
        animation: slideInRight 0.3s ease;
    `;
    
    tooltip.innerHTML = `
        <i class="fas fa-info-circle me-2"></i>
        ${message}
        <button type="button" class="btn-close" onclick="this.parentElement.remove()"></button>
    `;
    
    document.body.appendChild(tooltip);
    
    // Auto-remover después del tiempo especificado
    setTimeout(() => {
        if (tooltip.parentNode) {
            tooltip.remove();
        }
    }, duration);
}

/**
 * Funciones globales para usar desde HTML
 */
window.LoginManager = {
    showForgotPasswordInfo,
    fillTestCredentials,
    validateAndSubmitForm,
    clearForm
};

// Alias para compatibilidad
window.showForgotPasswordInfo = showForgotPasswordInfo;

// CSS adicional para animaciones
const additionalCSS = `
    @keyframes slideInRight {
        from {
            transform: translateX(100%);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
`;

// Inyectar CSS adicional
const style = document.createElement('style');
style.textContent = additionalCSS;
document.head.appendChild(style);