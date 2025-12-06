/**
 *Programa que hace uso de la clase INVITADO, heredada de la clase USUARIO.
 *Los invitados solo pueden visualizar y filtrar sus propias tareas, 
 *sin poder modificarlas ni crear nuevas.
 * Se hace uso de JOptionPane en este caso nos ayuda para: pedir al usuario que ingrese información 
 * (como el estado de las tareas o el ID) y mostrar resultados o mensajes informativos (como la lista de tareas o alertas de error).
 * y JScrollPane en este caso permite que la lista de tareas se vea completa aunque haya muchas, sin que la ventana se corte.
 * Hacer la visualización más amigable y navegable para el usuario
 * de manera amigable.
 * 
 * 
 * @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
 * @version 1.0 Noviembre 2025
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
    
            
            //Se recorren todas las tareas existentes en el sistema
            for (int i = 0; i < ADMINISTRADOR.contadorTareas; i++) {
                TAREAS n = ADMINISTRADOR.listaTareas[i];
                if (n != null && n.getUsuario().getID().equals(this.getID())) {
                    area.append(n.toString() + "\n");
                    tienesTareas = true;
                }
                JScrollPane scroll = new JScrollPane(area);
                JOptionPane.showMessageDialog(null, scroll, "TUS TAREAS : ", JOptionPane.INFORMATION_MESSAGE);
            }
            // Solo se muestran las tareas que pertenecena este invitado
            if (!tienesTareas) {
                JOptionPane.showMessageDialog(null, "No se encontraron tareas en el sistema");
            }
    
        }
        
        /**
         * Fltra las tareas del invitado por el estado : pendiente, completada o en curso
         */
        public void filtrarTareasEstadoInv() {
            boolean existenciaPendiente = false;
            boolean existenciaEnCurso = false;
            boolean existenciaCompletada = false;
        
            if (ADMINISTRADOR.contadorTareas == 0) {
                 JOptionPane.showMessageDialog(null, "No se encontraron tareas en el sistema");
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
                //Se meustra la lista final en un JScrollPane
                JScrollPane scroll = new JScrollPane(area);
                JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
            }
        }




}


