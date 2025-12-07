-- Trigger que crea una evaluación técnica cuando se inserta un préstamo con especialista

DROP TRIGGER IF EXISTS crear_evaluacion_tecnica;

DELIMITER $$

CREATE TRIGGER crear_evaluacion_tecnica
AFTER INSERT ON prestamos
FOR EACH ROW
BEGIN
    IF NEW.id_especialista IS NOT NULL THEN
        INSERT INTO evaluaciones_tecnicas (id_equipo, id_especialista)
        VALUES (NEW.id_equipo, NEW.id_especialista);
    END IF;
END$$

DELIMITER ;

-- Para verificar que el trigger se creó correctamente:
-- SHOW TRIGGERS FROM inventarioPlus;

-- Para eliminar el trigger si es necesario:
-- DROP TRIGGER IF EXISTS crear_evaluacion_tecnica;
