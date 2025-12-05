/**
 * Programa que representa la estructura que cumplen las tareas del sistema.
* @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
 * @version 1.0, 
 * @see TAREAS
 */
import java.time.LocalDate;

public class TAREAS {
   
    // Atriburtos principales
    public String id; //id unico de la tarea
    public String estado; // "pendiente", "enCurso","completada"
    public USUARIO usuario; // Usuario, propietario de la tarea
    public String descripcion; //Texto con la descripcion de la tarea 
   
    // Fechas relevantes
    public LocalDate fechaEstimadaInicio;
    public LocalDate fechaInicio;
    public LocalDate fechaEstimadaFin;
    public LocalDate fechaFin;



    /**
     * Construtor de la clase TAREAS.
     *
     * Se incluye @throws para indicar que el constructor puede generar
     * una excepción si se recibe información inválida (por ejemplo, fechas en orden incorrecto).
     *
     * @param id Identificador único de la tarea.
     * @param descripcion Breve dictamen de lo que se debe realizar.
     * @param usuario Usuario al que se le asigno a la tarea (puede ser null si no hay aún uno asignado).
     * @param fechaEstimadaInicio Fecha estimada de inicio. No puede ser previa a la fecha actual.
     * @param fechaEstimadaFin Fecha estimada en la que debería finalizar la tarea.
     *
     * @throws IllegalArgumentException si las fechas estimadas no cumplen reglas lógicas.
     */

    public TAREAS(String id, String descripcion, USUARIO usuario, 
        LocalDate fechaEstimadaInicio, LocalDate fechaEstimadaFin) {
        

        if (fechaEstimadaInicio == null || fechaEstimadaFin == null){
            throw new IllegalArgumentException("Las fechas estimadas no pueden ser nulas :).");
        }    
         if (fechaEstimadaInicio.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Las fechas estimada de inicio no puede ser anterior a la fecha actual. ");
        }   
         if (fechaEstimadaFin.isBefore(LocalDate.now())){
             throw new IllegalArgumentException("La fecha estimada final no puede ser antes de la fecha estimada de inicio.");
        }   


        this.id = id;
        this.descripcion = descripcion;
        this.usuario = usuario;
        this.estado = "pendiente";
       
        this.fechaEstimadaInicio = fechaEstimadaInicio;
        this.fechaEstimadaFin = fechaEstimadaFin;

        this.fechaInicio = null;
        this.fechaFin = null;
    }

    /**
     * Muestra la informacion resumida de la tarea.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id).append("\n");
        sb.append("Descripción: ").append(descripcion).append("\n");
        sb.append("Estado: ").append(estado).append("\n");
        sb.append("Asignado a : ").append(usuario != null ? usuario.getNickname() : "Sin asignacion").append("\n");
        sb.append("Fecha estimada de inicio: ").append(fechaEstimadaInicio).append("\n");
        sb.append("Fecha estimada final: ").append(fechaEstimadaFin).append("\n");
    
    
      if (fechaInicio != null) sb.append("Fecha real de inicio: ").append(fechaInicio).append("\n");
      if (fechaFin != null) sb.append("Fecha final real: ").append(fechaFin).append("\n");
    
    
      return sb.toString();
    
    } 


    /**
     * Cambio del estado de la tarea
     * Se esta respetando el que la tarea pase por:
     * pendiente - enCurso - completada.
     */

    public void setEstado(String nuevoEstado){
        if (nuevoEstado == null){
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }

        String estadoNormalizado = nuevoEstado.toLowerCase().replace(" ","");

        switch (estadoNormalizado){
            case "pendiente":
                this.estado = "pendiente";
                break;
            case "encurso": 
              if (!estado.equalsIgnoreCase("pendiente")){
                throw new IllegalArgumentException("Solo se puede pasar a 'En curso' desde 'Pendiente'");
              }

              this.estado = "enCurso";
              this.fechaInicio = LocalDate.now();
              break;

            case "completada":
                if (!estado.equalsIgnoreCase("encurso")){
                 throw new  IllegalArgumentException("Solo se puede completar una tarea que ya estaba 'En curso'");
                }  
                this.estado ="completada";
                this.fechaFin = LocalDate.now();
                break;

            default:
                throw new IllegalArgumentException("Estado no reconocido: " + nuevoEstado);

        }
    }

    // --------- GETTERS -------
    public String getId(){
        return id;
    }
    public String getEstado(){ 
        return estado; 
    }
    public String getDescripcion(){ 
        return descripcion; 
    }
    public USUARIO getUsuario(){
         return usuario; 
        }
    public LocalDate getFechaEstimadaInicio() {
         return fechaEstimadaInicio;
        }
    public LocalDate getFechaInicio(){
        return  fechaInicio;
    }
    public LocalDate getFechaEstimadaFin(){
        return fechaEstimadaFin;
    }
    public LocalDate getFechaFin(){
        return fechafin;
    }

    // ------SETTERS -------
    public void setUsuario (USUARIO usuario){
        this.usuario = usuario;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    


}





