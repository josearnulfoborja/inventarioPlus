package com.example.InventarioPlus.dto;

/**
 * Clase wrapper para estandarizar las respuestas de la API.
 * 
 * Esta clase envuelve las respuestas del backend con un formato consistente
 * que incluye un indicador de éxito, los datos, y opcionalmente un mensaje.
 * 
 * Formato de respuesta:
 * {
 * "success": true,
 * "data": { ... },
 * "message": "Operación exitosa"
 * }
 * 
 * @param <T> El tipo de datos que se envuelven en la respuesta
 * @author User
 */
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String message;

    /**
     * Constructor para respuestas exitosas sin mensaje
     */
    public ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    /**
     * Constructor para respuestas con mensaje
     */
    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
