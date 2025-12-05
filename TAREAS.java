/**
 * Programa que representa la estructura que cumplen las tareas del sistema.
 * @author Gabriel Reyes
 * @author Paula López
 * @version 1.0, 23/05/2025
 * @see TAREAS
 */
import java.time.LocalDate;

public class TAREAS {

    public String id;
    public String estado;
    public USUARIO usuario;
    public String descripcion;
    public LocalDate fechaEstimadaInicio;
    public LocalDate fechaInicio;
    public LocalDate fechaEstimadaFin;
    public LocalDate fechaFin;

    public TAREAS(String id, String estado, USUARIO usuario, String descripcion, LocalDate fechaEstimadaInicio,
            LocalDate fechaInicio, LocalDate fechaEstimadaFin, LocalDate fechaFin) {
        this.id = id;
        this.estado = estado;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.fechaEstimadaInicio = fechaEstimadaInicio;
        this.fechaInicio = fechaInicio;
        this.fechaEstimadaFin = fechaEstimadaFin;
        this.fechaFin = fechaFin;
    }

    /**
     * Muestra la forma del usuario.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Estado: ").append(estado).append("\n");

    if ("pendiente".equals(estado) || "en curso".equals(estado) || "completada".equals(estado)) {
        sb.append("Fecha estimada de inicio: ").append(fechaEstimadaInicio).append("\n");
        sb.append("Fecha estimada de finalización: ").append(fechaEstimadaFin).append("\n");
    }

    if ("en curso".equals(estado) || "completada".equals(estado)) {
        sb.append("Fecha de inicio: ").append(fechaInicio).append("\n");
    }

    if ("completada".equals(estado)) {
        sb.append("Fecha de finalización: ").append(fechaFin).append("\n");
    }

    sb.append("Propietario: ").append(usuario != null ? usuario.getNickname() : "No hay Información");

    return sb.toString();
    
    } 

    //Getters
    public USUARIO getUsuario(){
        return usuario;
    }
    public String getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaEstimadaInicio() {
        return fechaEstimadaInicio;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaEstimadaFin() {
        return fechaEstimadaFin;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    //Setters
    /**
     * Asigna un ID a cada tarea
     * @param id ID único
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Asigna una descripción a la tarea
     * @param descripcion en texto de cada tarea
     */

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //verifica el estado
    /**
     * Asigna la fecha estimada de inicio en caso de que el estado sea "pendiente"
     * @param fecha estimada de inicio
     */
    public void setFechaEstimadaInicio(LocalDate fechaEstimadaInicio) {
        if ("pendiente".equals(this.estado)) {
            this.fechaEstimadaInicio = fechaEstimadaInicio;
        }
    }

    /**
     * Asigna la fecha estimada de finalización  si el estado es "pendiente"
     * @param fechaEstimadaFin Fecha estimada de finalización
     */
     public void setFechaEstimadaFin(LocalDate fechaEstimadaFin) {
        if ("pendiente".equals(this.estado)) {
            this.fechaEstimadaFin = fechaEstimadaFin;
        }
    }


    //setter de estados para fecha de inicio y final
    /**
     * Cambia el estado de la tarea
     * @param  Nuevo estado de la tarea
     */
    public void setEstado(String nuevoEstado) {
        if (nuevoEstado == null) return;

        if (nuevoEstado.equals("pendiente")) {
            if (this.estado == null) {
                this.estado = "pendiente";
            }

        } else if (nuevoEstado.equalsIgnoreCase("enCurso")) {
            if ("pendiente".equals(this.estado)) {
                this.estado = "enCurso";
                if (this.fechaInicio == null) {
                    this.fechaInicio = LocalDate.now();
                }
            }

        } else if (nuevoEstado.equalsIgnoreCase("completada")) {
            if ("enCurso".equals(this.estado)) {
                this.estado = "completada";
                if (this.fechaFin == null) {
                    this.fechaFin = LocalDate.now();
                }
            }

        } else {
            throw new IllegalArgumentException("Estado inválido");
        }
    }


public void setUsuario(USUARIO asignado) {
    this.usuario = usuario;
}
}



//EJEMPLO LOCAL DATE
/*
LocalDate cumple = LocalDate.of(2000, 5, 4); // 4 de mayo del 2000
 */


