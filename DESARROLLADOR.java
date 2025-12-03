/**
 * Programa que hace uso de la clase DESARROLLADOR, heredada de la clase USUARIO, para 
 * realizar diversas acciones sobre las tareas del sistema que le corresponden.
 * @author Gabriel Reyes
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
     * Registra las tareas del desarrollador
     * @param t tarea que se va a registrar
     */
    public void registrarTarea(TAREAS t) {
        if(ADMINISTRADOR.cantidadTareas < ADMINISTRADOR.CANTIDAD) {
            t.setUsuario(this);
            ADMINISTRADOR.tareas[ADMINISTRADOR.cantidadTareas] = t;
            ADMINISTRADOR.cantidadTareas++;
            JOptionPane.showMessageDialog(null, "Se ha creado correctamente la tarea\n");
        } else {
            JOptionPane.showMessageDialog(null, "LO SIENTO , NO SE PUEDEN AÑADIR TAREAS ");
        }
    }



    /**
     * Despliega las tareas asignadas a este desarrollador (en caso de haber)
     */ 
    public void desplegarTareasDesa() {
        JTextArea area = new JTextArea(20,40);
        boolean tienesTareas = false;

        

        for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
            TAREAS t = ADMINISTRADOR.tareas[i];
            if (t != null && t.getUsuario().getID().equals(this.getID())) {
                area.append(t.toString() + "\n");
                tienesTareas = true;
            }
        }

        if (!tienesTareas) {
            area.setText("NO TIENES TAREAS");
        }

        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "TUS TAREAS : ", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * El desarrollador puede filtrar sus tareas por estado: pendiente, en curso o completada
     */
    public void filtrarTareasEstadoDesa() {
        boolean existenciaP = false;
        boolean existenciaEnCurso = false;
        boolean existenciaC = false;
    
        if (ADMINISTRADOR.cantidadTareas == 0) {
            JTextArea area = new JTextArea("NO EXISTEN TAREAS EN EL SISTEMA", 10, 40);
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String estadoF = JOptionPane.showInputDialog("Elige el estado (A/B/C):\nA) PENDIENTES\nB) EN CURSO\nC) COMPLETADAS");
            JTextArea area = new JTextArea("LISTA DE TUS TAREAS\n\n", 10, 40);
    
            switch (estadoF.toUpperCase()) {
                case "A":
                    area.append("PENDIENTES:\n");
                    for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
                        TAREAS t = ADMINISTRADOR.tareas[i];
                        if (t != null && t.getUsuario() != null &&
                            t.getUsuario().getID().equals(this.getID()) &&
                            t.getEstado().equalsIgnoreCase("pendiente")) {
    
                            area.append("TAREA:\n" + t + "\n");
                            existenciaP = true;
                        }
                    }
                    if (!existenciaP) {
                        area.append("No tienes tareas pendientes.\n");
                    }
                    break;
    
                case "B":
                    area.append("EN CURSO:\n");
                    for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
                        TAREAS t = ADMINISTRADOR.tareas[i];
                        if (t != null && t.getUsuario() != null &&
                            t.getUsuario().getID().equals(this.getID()) &&
                            t.getEstado().equalsIgnoreCase("en curso")) {
    
                            area.append("TAREA:\n" + t + "\n");
                            existenciaEnCurso = true;
                        }
                    }
                    if (!existenciaEnCurso) {
                        area.append("No tienes tareas en curso.\n");
                    }
                    break;
    
                case "C":
                    area.append("COMPLETADAS:\n");
                    for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
                        TAREAS t = ADMINISTRADOR.tareas[i];
                        if (t != null && t.getUsuario() != null &&
                            t.getUsuario().getID().equals(this.getID()) &&
                            t.getEstado().equalsIgnoreCase("completada")) {
    
                            area.append("TAREA:\n" + t + "\n");
                            existenciaC = true;
                        }
                    }
                    if (!existenciaC) {
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
     * El desarrollador  puede actualizar SUS PROPIAS tareas por  estado, descripción, fechas estimadas y usuario asignado
     */

    public void actualizarTareasDesa() {
        String idTareaDesa = JOptionPane.showInputDialog("Ingresa el ID de la tarea que deseas actualizar:");
        boolean encontrada = false;
    
        for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
            TAREAS t = ADMINISTRADOR.tareas[i];
    
            if (t != null &&t.getUsuario() != null &&t.getUsuario().getID().equals(this.getID()) && t.getId().equals(idTareaDesa)) {
                encontrada = true;
    
                if ("completada".equals(t.getEstado())) {
                    JOptionPane.showMessageDialog(null, "NO SE PUEDE MODIFICAR UNA TAREA COMPLETADA");
                    return;
                }
    
                /*
                 * Permite al desarrollador actualizar el estado de la tarea tomando en cuenta 
                 * que el flujo debe ser de la forma : Pendiente → En Curso → Completada
                 */
        String actualizaEstadoDesa = JOptionPane.showInputDialog("¿Quieres modificar el estado? (S / N)");
        if ("S".equalsIgnoreCase(actualizaEstadoDesa)) {
            String nuevoEstado = JOptionPane.showInputDialog("Nuevo estado (EN CURSO/COMPLETADA) : ");
            if (nuevoEstado == null) return;
            nuevoEstado = nuevoEstado.toLowerCase().trim();
            
            if (!nuevoEstado.equalsIgnoreCase("EN CURSO") && !nuevoEstado.equalsIgnoreCase("COMPLETADA")) {
                JOptionPane.showMessageDialog(null, "ESTADO INVALIDO");
                return;
            }
            if ("pendiente".equalsIgnoreCase(nuevoEstado)) {
                JOptionPane.showMessageDialog(null, "NO SE PUEDE VOLVER A PENDIENTE");
            } else if ("COMPLETADA".equalsIgnoreCase(nuevoEstado) && "pendiente".equals(t.getEstado())) {
                JOptionPane.showMessageDialog(null, "ERROR \n NO PUEDES PASAR DIRECTAMENTE DE PENDIENTE A COMPLETADA. PRIMERO DEBE ESTAR EN CURSO");
            } else {
                if (nuevoEstado.equalsIgnoreCase("EN CURSO")) {
                    t.setEstado("enCurso");
                }else if (nuevoEstado.equalsIgnoreCase("COMPLETADA")) {
                    t.setEstado("completada");
                }
            }
        }
        
        /*
        * Permite al desarrollador cambiar la descripción de la Tarea
        */
        
        String actualizaDescDesa = JOptionPane.showInputDialog("¿Quieres modificar la descripción? (S/N)");
        if ("S".equalsIgnoreCase(actualizaDescDesa)) {
            String nuevaDescripcion = JOptionPane.showInputDialog("Nueva descripción:");
            if (nuevaDescripcion != null && !nuevaDescripcion.trim().isEmpty()) {
                t.setDescripcion(nuevaDescripcion.trim());
            }
        }
    
        /*
         * Actualiza fechas solo si el desarrollador tiene la tarea con estado pendiente o  en curso
         */
        
         if ("pendiente".equals(t.getEstado()) || "en curso".equals(t.getEstado())) {
            
            String actualizarFechasDesa = JOptionPane.showInputDialog("¿Quieres modificar las fechas estimadas? (S/N)");
            if ("S".equalsIgnoreCase(actualizarFechasDesa)) {
                try {
                    String nuevaFechaEI = JOptionPane.showInputDialog("Nueva fecha Estimada de Inicio (yyyy-mm-dd):");
                    t.setFechaEstimadaInicio(LocalDate.parse(nuevaFechaEI));
                    String nuevaFechaEF = JOptionPane.showInputDialog("Nueva fecha estimada de finalización (yyyy-mm-dd):");
                    t.setFechaEstimadaFin(LocalDate.parse(nuevaFechaEF));
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
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRO NINGUNA TAREA CON EL ID SELECCIONADO");
        }
    }


    


}


