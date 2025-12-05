/**
 * Este programa de la clase DESARROLLADOR, heredada de la clase USUARIO, sirve para 
 * realizar acciones como registrar tareas, sobre las tareas del sistema que le corresponden.
* @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
 * @version 1.0, 02/12/2025
 * @see DESARROLLADOR
 */
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.time.LocalDate;

public class DESARROLLADOR extends USUARIO {

    public DESARROLLADOR(String ID, String nombreUsuario, String nickname, String correo, String contrasena,
    String tipoUsuario) {
        super(ID, nombreUsuario, nickname, correo, contrasena, tipoUsuario);
    }

    /**
     * Aqui se hara el registro de las tareas del desarrollador
     * @param n tarea del cual se hara un registro
     */
    public void enlistarTarea(TAREAS n) {
        if(ADMINISTRADOR.contadorTareas < ADMINISTRADOR.MAX_USUARIOS) {
            n.setUsuario(this);
            ADMINISTRADOR.listaTareas[ADMINISTRADOR.contadorTareas] = n;
            ADMINISTRADOR.contadorTareas++;
            JOptionPane.showMessageDialog(null, "Tarea creada exitosamente\n");
        } else {
            JOptionPane.showMessageDialog(null, "Lo siento, no se ha podido llevar a cabo tu registro");
        }
    }



    /**
     * Enlista las tareas asignadas a este desarrollador (en caso de haber)
     */ 
    public void desplegarTareasDesa() {
        JTextArea area = new JTextArea(20,40);
        boolean tienesTareas = false;

        

        for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
            TAREAS n = ADMINISTRADOR.listaTareas[i];
            if (n != null && n.getUsuario().getID().equals(this.getID())) {
                area.append(n.toString() + "\n");
                tienesTareas = true;
            }
        }

        if (!tienesTareas) {
            area.setText("No se encontraron tareas registradas");
        }

        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "Tus tareas registradas son : ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * El desarrollador nos ayuda a filtrar sus tareas por estado: pendiente, en curso o completada
     */
    public void filtrarTareasPorEstado() {
        boolean existenciaPendiente = false;
        boolean existenciaEnCurso = false;
        boolean existenciaCompletada = false;
    
        if (ADMINISTRADOR.contadorTareas == 0) {
            JTextArea area = new JTextArea("No se encuntran tareas existentes", 10, 40);
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String estadoF = JOptionPane.showInputDialog("Elige el estado (A/B/C):\nA) PENDIENTES\nB) EN CURSO\nC) COMPLETADAS");
            JTextArea area = new JTextArea("LISTA DE TUS TAREAS\n\n", 10, 40);
    
            switch (estadoF.toUpperCase()) {
                case "A":
                    area.append("PENDIENTES:\n");
                    for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
                        TAREAS n = ADMINISTRADOR.listaTareas[i];
                        if (n != null && n.getUsuario() != null &&
                            n.getUsuario().getID().equals(this.getID()) &&
                            n.getEstado().equalsIgnoreCase("pendiente")) {
    
                            area.append("TAREA:\n" + n + "\n");
                            existenciaPendiente = true;
                        }
                    }
                    if (!existenciaPendiente) {
                        area.append("No tienes tareas pendientes.\n");
                    }
                    break;
    
                case "B":
                    area.append("EN CURSO:\n");
                    for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
                        TAREAS n = ADMINISTRADOR.listaTareas[i];
                        if (n != null && n.getUsuario() != null &&
                            n.getUsuario().getID().equals(this.getID()) &&
                            n.getEstado().equalsIgnoreCase("encurso")) {
    
                            area.append("TAREA:\n" + n + "\n");
                            existenciaEnCurso = true;
                        }
                    }
                    if (!existenciaEnCurso) {
                        area.append("No tienes tareas en curso.\n");
                    }
                    break;
    
                case "C":
                    area.append("COMPLETADAS:\n");
                    for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
                        TAREAS n = ADMINISTRADOR.listaTareas[i];
                        if (n != null && n.getUsuario() != null &&
                            n.getUsuario().getID().equals(this.getID()) &&
                            n.getEstado().equalsIgnoreCase("completada")) {
    
                            area.append("TAREA:\n" + n + "\n");
                            existenciaCompletada = true;
                        }
                    }
                    if (!existenciaCompletada) {
                        area.append("No tienes tareas completadas.\n");
                    }
                    break;
    
                default:
                    area.setText("Opción no válida.");
                    break;
            }
    
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * El desarrollador permite la actualizacion de SUS PROPIAS tareas por  estado, descripción, fechas estimadas y usuario asignado
     */

    public void actualizarTareasDesa() {
        String idTareaDesa = JOptionPane.showInputDialog("Ingresa el ID de la tarea que deseas actualizar:");
        boolean encontrada = false;
    
        for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
            TAREAS n = ADMINISTRADOR.listaTareas[i];
    
            if (n != null &&n.getUsuario() != null &&n.getUsuario().getID().equals(this.getID()) && n.getId().equals(idTareaDesa)) {
                encontrada = true;
    
                if ("completada".equals(n.getEstado())) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos, si la tarea se encuntra en estado completada, ya no se puede modificar");
                    return;
                }
    
                /*
                 * Permite al desarrollador actualizar el estado de la tarea tomando en cuenta 
                 * que el flujo debe ser de la forma : Pendiente → En Curso → Completada
                 */
        String actualizaEstadoDesa = JOptionPane.showInputDialog("¿Quieres modificar el estado? (Si / No)");
        if ("Si".equalsIgnoreCase(actualizaEstadoDesa)) {
            String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado (EN CURSO/COMPLETADA) : ");
            if (nuevoEstado == null) return;
            nuevoEstado = nuevoEstado.toLowerCase().trim();
            
            if (!nuevoEstado.equalsIgnoreCase("EN CURSO") && !nuevoEstado.equalsIgnoreCase("COMPLETADA")) {
                JOptionPane.showMessageDialog(null, "ESTADO INVALIDO");
                return;
            }
            if ("pendiente".equalsIgnoreCase(nuevoEstado)) {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE VOLVER A PENDIENTE");
            } else if ("COMPLETADA".equalsIgnoreCase(nuevoEstado) && "pendiente".equals(n.getEstado())) {
                JOptionPane.showMessageDialog(null, "ERROR \n NO PUEDES PASAR DIRECTAMENTE DE PENDIENTE A COMPLETADA. PRIMERO DEBE ESTAR EN CURSO");
            } else {
                if (nuevoEstado.equalsIgnoreCase("EN CURSO")) {
                    n.setEstado("encurso");
                }else if (nuevoEstado.equalsIgnoreCase("COMPLETADA")) {
                    n.setEstado("completada");
                }
            }
        }
        
        /*
        * Permite al desarrollador cambiar la descripción de la Tarea
        */
        
        String actualizaDescDesa = JOptionPane.showInputDialog("¿Quieres modificar la descripción? (Si/No)");
        if ("Si".equalsIgnoreCase(actualizaDescDesa)) {
            String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:");
            if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
                n.setDescripcion(nuevaDescripcion.trim());
            }
        }
    
        /*
         * Actualiza fechas solo si el desarrollador tiene la tarea con estado pendiente o  en curso
         */
        
         if ("pendiente".equals(n.getEstado()) || "en curso".equals(n.getEstado())) {
            
            String actualizarFechasDesa = JOptionPane.showInputDialog("¿Quieres modificar las fechas estimadas? (Si/No)");
            if ("Si".equalsIgnoreCase(actualizarFechasDesa)) {
                try {
                    String nuevaFechaEI = JOptionPane.showInputDialog("Nueva fecha Estimada de Inicio (yyyy-mm-dd):");
                    n.setFechaEstimadaInicio(LocalDate.parse(nuevaFechaEI));
                    String nuevaFechaEF = JOptionPane.showInputDialog("Nueva fecha estimada de finalización (yyyy-mm-dd):");
                    n.setFechaEstimadaFin(LocalDate.parse(nuevaFechaEF));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Fechas inválidas");
                }
            }
        }
                
                JOptionPane.showMessageDialog(null, "Tarea Actualizada");
                return;
            }
        }
        
        if (!encontrada) {
            JOptionPane.showMessageDialog(null, "No se encontro nunguna tarea con el ID seleccionado");
        }
    }
}   
}


