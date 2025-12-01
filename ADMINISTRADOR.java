/**
 * Programa que hace uso de la clase ADMINISTRADOR, heredada de la clase USUARIO, para 
 * realizar diversas acciones sobre todas las tareas y usuarios del sistema.
 * @author Gael Dúran
 * @author Ana Gutiérrez
 * @version 1.0, 23/05/2025
 * @see ADMINISTRADOR
 */
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ADMINISTRADOR extends USUARIO{

     public ADMINISTRADOR(String ID, String nombreUsuario, String nickname, String correo, String contrasena, String tipoUsuario) {
        super(ID, nombreUsuario, nickname, correo, contrasena, tipoUsuario);
    } 

    public static final int CANTIDAD = 1000;
    public static USUARIO[] usuarios = new USUARIO[CANTIDAD];
    public static int cantidadUsuarios;

    static {
        usuarios[0] = new ADMINISTRADOR("10", "Araceli", "Mercado", "araceli@gmail.com", "1234", "Administrador");
        usuarios[1] = new DESARROLLADOR("11", "Diego", "Alberto", "diego@gmail.com", "5678", "Desarrollador");
        usuarios[2] = new INVITADO("12", "Diana", "Rojas", "diana@gmail.com", "9876", "Invitado");
        cantidadUsuarios = 3;
    }

    /**
     * Se agrega el nuevo usuario que el administrador, ya existente, desea agregar al arreglo de los usuarios.
    * @param u es el usuario que será agregado por el usuario administrador.
    */
    public static void agregarUsuario(USUARIO u){
            for (int i = 0; i < CANTIDAD; i++) {
            //SI EL NICKNAME O CORREO Y CONTRASEÑA SON IGUALES AL DE UN USUARIO EXISTENTE (AUNQUE EL ID SEA DIFERENTE) MANDA MENSAJE SOBRE LA CREACION ANTERIOR DEL USUARIO
            if (usuarios[i]!= null &&u.equals(usuarios[i])) {
                JOptionPane.showMessageDialog(null, "EL USUARIO YA HA SIDO CREADO ANTERIORMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
                }
            }
            //SI LA CANTIDAD DE USUARIOS ES MENOR A LA CANTIDAD MAXIMA DE USUARIOS
            if(cantidadUsuarios<CANTIDAD){
                    usuarios[cantidadUsuarios]= u;
                    cantidadUsuarios=cantidadUsuarios+1;
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente el usuario\n");
            }else {
            // SI LA CANTIDAD MAXIMA DE USUARIOS FUE SUPERADA
                JOptionPane.showMessageDialog(null, "NO ES POSIBLE AGREGAR OTRO USUARIO");
            }
        
    }

    public static  int cantidad= 1000000;
    public static TAREAS [] tareas= new TAREAS[cantidad];
    public static int cantidadTareas = 1;
    static{
          LocalDate fechaEstimadaIn1=LocalDate.parse("2025-05-12");
    LocalDate fechaIn1=LocalDate.parse("2025-05-13");
    LocalDate fechaEstimadaFin1=LocalDate.parse("2025-05-25");
    LocalDate fechaFin1=LocalDate.parse("2025-05-23");
    tareas[0]=new TAREAS("12","completada", usuarios[2], "La tarea de progra es sobre usuarios", fechaEstimadaIn1,fechaIn1 , fechaEstimadaFin1,fechaFin1);
    }

    /**
     * Se crea la tarea indicada por el administrador al arreglo de todas las tareas.
     * @param t1 Es la tarea que se creará por el administrador.
     */
    public static void crearTareasAdmin(TAREAS t1){
                //SI LA DESCRIPCION Y EL USUARIO ES EL MISMO MANDA MENSAJE SOBRE LA CREACION ANTERIOR DE LA TAREA
                for (int i = 0; i < cantidad; i++) {
                    if (tareas[i]!=null && t1.equals(tareas[i])) {
                    JOptionPane.showMessageDialog(null, "LA TAREA YA HA SIDO CREADO ANTERIORMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                    }
                }
                //SI LA CANTIDAD DE TAREAS ACTUALES ES MENOR A LA CANTIDAD PERMITIDA DE TAREAS
                if(cantidadTareas<cantidad){
                    tareas[cantidadTareas]= t1;
                    JOptionPane.showMessageDialog(null, "Se ha creado correctamente la tarea\n");
                    cantidadTareas=cantidadTareas+1;
                }else {
                //SI SUPERA LA CANTIDAD MAXIMA DE TAREAS
                    JOptionPane.showMessageDialog(null, "NO ES POSIBLE AGREGAR OTRA TAREA", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

    /**
     * El administrador podrá desplegar todas las tareas existentes y si es el caso de no haber ninguna, se mostrará un mensaje.
     */
    public static void desplegarTareasAdmin(){
        if (cantidadTareas==0) {
            JOptionPane.showMessageDialog(null, "Todavia no existen tareas");
        }else{
            JTextArea area = new JTextArea("LISTA DE TAREAS:\n", 10, 40);
            for (int i = 0; i <cantidadTareas; i++) {
                     area.append("TAREA "+(i+1)+":\n"+tareas[i]+"\n");
            }
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * El administrador podrá desplegar todas las tareas existentes según sea su estado, y si es el caso de no haber ninguna se mostrará un mensaje.
     */
    public static void filtrarTareasEstadoAdmin() {
        boolean existenciaP=false;
        boolean existenciaEnCurso=false;
        boolean existenciaC=false;
        //SI NO EXISTEN TAREAS AUN LA CANTIDAD DE TAREAS SE MANTIENE EN CERO
         if (cantidadTareas==0) {
            JOptionPane.showMessageDialog(null, "Todavia no existen tareas");
        }else{
        //SI EXISTEN TAREAS PREGUNTA EL ESTADO A FILTRAR
        String estadoF = solicitaElementos("Elige el estado(A/B/C): A)PENDIENTES\n B)EN CURSO\n C)COMPLETADO\n");
             JTextArea area = new JTextArea("LISTA DE TAREAS ", 10, 40);
            switch (estadoF) {
                case "A":
                    area.append("PENDIENTES\n");
                    for (int i = 0; i <cantidad; i++) {
                        if (tareas[i]!=null && tareas[i].estado.equals("pendiente")) {
                        area.append("TAREA:\n"+tareas[i]+"\n");
                        existenciaP= true;
                        }
                    }
                    if (existenciaP==false) {
                        area.append("No existen tareas pendientes");
                    }
                    break;
                case "B":
                    area.append("EN CURSO:\n");
                    for (int i = 0; i <cantidad; i++) {
                        if (tareas[i]!=null && tareas[i].estado.equals("enCurso")) {
                        area.append("TAREA\n"+tareas[i]+"\n");
                        existenciaEnCurso=true;
                         }
                    }
                    if (existenciaEnCurso==false) {
                        area.append("No existen tareas en curso");
                    }
                    break;
                case "C":
                        area.append("COMPLETADA\n");
                        for (int i = 0; i <cantidad; i++) {
                            if (tareas[i]!=null &&tareas[i].estado.equals("completada")) {
                            area.append("TAREA:\n"+tareas[i]+"\n");
                            existenciaC=true;
                            }
                        }
                        if (existenciaC==false) {
                            area.append("No existen tareas completadas");
                         }
                default:
                    break;
            }
            JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    /**
     * El administrador podrá desplegar todas las tareas existentes según  y si es el caso de no haber ninguna, se mostrará un mensaje.
     * @param u Es el usuario al que pertenecen las tareas que se filtran.
     */
    public static void filtrarTareasUsuarioAdmin(USUARIO u) {
        boolean tareaUsuario=false;
         if (cantidadTareas==0) {
             JOptionPane.showMessageDialog(null, "Todavia no existen tareas");
        } else{
            JTextArea area = new JTextArea("LISTA DE TAREAS PARA "+u.nickname+"\n", 10, 40);
            for (int j = 0; j < tareas.length; j++) {
                if (tareas[j]!=null && tareas[j].usuario.equals(u)) {
                    area.append("TAREA:\n"+tareas[j]+"\n");
                    tareaUsuario=true;
                }         
            }
             if (tareaUsuario==false) {
                 area.append("Todavia no existen tareas para "+u.nickname);
            } 
             JScrollPane scroll = new JScrollPane(area);
            JOptionPane.showMessageDialog(null, scroll, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
        }

            
    }

    /**
     * Se actualizan los datos de la tarea que el usuario decida actualizar, y que el estado de la tarea permita.
     * @param t Es el id de la tarea que el administrador desea actualizar.
     */
    public static void actualizarTareasAdmin(String t) {
        Boolean existeTarea=false;
        String estadoAnterior = "";
        String nuevousuario,nuevaDescrip;
        if (cantidadTareas==0) {
            JOptionPane.showMessageDialog(null, "Todavia no existen tareas");
        }else{
            for (int i = 0; i < cantidad;i++) {
                if (tareas[i]!=null && tareas[i].id.equals(t)) {
                    existeTarea=true;
    
                    String modificarEstado=solicitaElementos("Quieres modificar el estado de la tarea?(S/N)");
                    if (modificarEstado.equals("S")) { 
                        if ("completada".equals(tareas[i].estado)) {
                             JOptionPane.showMessageDialog(null, "La tarea  ya ha sido completada, no es posible realizar la modificación\n"); 
                        }else{ 
                            if ("pendiente".equals(tareas[i].estado)) {
                                estadoAnterior=tareas[i].estado;
                                tareas[i].fechaInicio=LocalDate.now();
                                tareas[i].setEstado("enCurso");
                            }else if ("enCurso".equals(tareas[i].estado)) {
                                estadoAnterior=tareas[i].estado;
                                tareas[i].fechaFin=LocalDate.now();
                                tareas[i].setEstado("completada");
                            }
                            JOptionPane.showMessageDialog(null, "Se realizó la siguiente modificación al estado de la tarea:\n"+ estadoAnterior +"-->"+ tareas[i].estado); 
                        }
                    }
         
                    String modificarUsuario=solicitaElementos("Quieres modificar el usuario al que pertenece la tarea?(S/N)\n");
                        if (modificarUsuario.equals("S")) {
                            boolean usuarioExiste=false;
                            nuevousuario=solicitaElementos("Ingresa el ID del nuevo usuario al que le pertenecera la tarea");
                            for (int j = 0; j < CANTIDAD; j++) {
                                if (usuarios[j]!=null && nuevousuario.equals(usuarios[j].ID)) {
                                    usuarioExiste=true;
                                    if (usuarioExiste==false) {
                                        JOptionPane.showMessageDialog(null, "UPS... EL ID INGRESADO NO CORRESPONDE A ALGÚN USUARIO EXISTENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    }
                                    tareas[i].usuario=usuarios[j];
                                     JOptionPane.showMessageDialog(null, "Se realizó la siguiente modificación al usuario de la tarea:\n"+"El nuevo usuario es: "+tareas[i].usuario.nickname); 
                                    break;
                                }
                            }
                        }

                    String modificarDescrip=solicitaElementos("Quieres modificar la Descripción de la tarea?(S/N)");
                        if (modificarDescrip.equals("S")) {
                            nuevaDescrip=solicitaElementos("Ingresa la nueva descripción de la tarea");
                            tareas[i].descripcion=nuevaDescrip;
                             JOptionPane.showMessageDialog(null, "Se realizó la siguiente modificación a la descripción de la tarea:\n"+"El nuevo descripción: "+tareas[i].descripcion); 
                        }

                    if ("pendiente".equals(tareas[i].estado)) {
                        String modificarFechaEsIn=solicitaElementos("Quieres modificar la fecha estimada de inicio ?(S/N)");
                        if (modificarFechaEsIn.equals("S")) {
                            String fechaEI=solicitaElementos("Nueva fecha estimada de inicio (aaaa-mm-dd):");
                            tareas[i].fechaEstimadaInicio=LocalDate.parse(fechaEI);
                            JOptionPane.showMessageDialog(null, "La nueva fecha de inicio de la tarea es:\n"+tareas[i].fechaEstimadaInicio); 
                        }

                        String modificarFechaEsFin=solicitaElementos("Quieres modificar la fecha estimada de fin ?(S/N)");
                        if (modificarFechaEsFin.equals("S")) {
                            String fechaEF=solicitaElementos("Nueva fecha estimada de fin (aaaa-mm-dd):");
                            tareas[i].fechaEstimadaFin=LocalDate.parse(fechaEF);
                            JOptionPane.showMessageDialog(null, "La nueva fecha de fin de la tarea es:\n"+tareas[i].fechaEstimadaFin); 
                        }
                    }

                    break;
                }
            }
            if (!existeTarea) {
                 JOptionPane.showMessageDialog(null, "LA TAREA INGRESADA NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
       

    /**
     * Metodo que elimina una tarea del arreglo de tareas.
     * @param x Es el id de la tarea que se eliminara.
     */
    public static void eliminarTareas(String x){
        boolean encontrada=false;
        if (cantidadTareas==0) {
            JOptionPane.showMessageDialog(null, "Todavia no existen tareas");
             return;
        } else{
                for (int j = 0; j <cantidad; j++) {
                    if(tareas[j]!=null && x.equals(tareas[j].id)){
                        encontrada=true;
                        for (int i = j; i < cantidad-1; i++) {
                             tareas[i]=tareas[i+1];
                        }
                        tareas[cantidad - 1] = null;
                        cantidadTareas=cantidadTareas-1;
                        JOptionPane.showMessageDialog(null, "La tarea se ha eliminado satisfactoriamente");

                    }
                }
                 if (encontrada==false) {
                         JOptionPane.showMessageDialog(null, "LA TAREA INGRESADA NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
        
            }

    }

    public static String solicitaElementos(String mensaje ) {
            String elementos = JOptionPane.showInputDialog(mensaje);
            return elementos;
    }



}




