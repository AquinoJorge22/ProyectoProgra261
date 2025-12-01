/**
 *Programa que hace uso de la clase INVITADO, heredada de la clase USUARIO, para 
 * visualizar las tareas que le corresponden.
 * @author Gabriel Reyes
 * @author Paula López
 * @version 1.0, 23/05/2025
 * @see INVITADO
 */
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class INVITADO extends USUARIO{

    public INVITADO(String ID, String nombreUsuario, String nickname, String correo, String contrasena,
            String tipoUsuario) {
        super(ID, nombreUsuario, nickname, correo, contrasena, tipoUsuario);
    }
    
    /**
     * Despliega las tareas asignadas al invitado y si no hay avisa al usuario
     */
        public void desplegarTareasInv() {
            JTextArea area = new JTextArea(20,40);
            boolean tienesTareas = false;
    
            
    
            for (int i = 0; i < ADMINISTRADOR.cantidadTareas; i++) {
                TAREAS t = ADMINISTRADOR.tareas[i];
                if (t != null && t.getUsuario().getID().equals(this.getID())) {
                    area.append(t.toString() + "\n");
                    tienesTareas = true;
                }
                JScrollPane scroll = new JScrollPane(area);
                JOptionPane.showMessageDialog(null, scroll, "TUS TAREAS : ", JOptionPane.INFORMATION_MESSAGE);
            }
    
            if (!tienesTareas) {
                JOptionPane.showMessageDialog(null, "NO EXISTEN TAREAS EN EL SISTEMA");
            }
    
        }
        
        /**
         * Fltra las tareas del invitado por el estado : pendiente, completada o en curso
         */
        public void filtrarTareasEstadoInv() {
            boolean existenciaP = false;
            boolean existenciaEnCurso = false;
            boolean existenciaC = false;
        
            if (ADMINISTRADOR.cantidadTareas == 0) {
                 JOptionPane.showMessageDialog(null, "NO EXISTEN TAREAS EN EL SISTEMA");
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



}