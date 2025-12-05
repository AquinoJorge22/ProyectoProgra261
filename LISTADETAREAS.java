/**
 * Programa que permite al usuario realizar ciertas acciones en las tareas y/o usuarios
 * del sistema, dependiendo de sus privilegios por tipo de usuario.
 * @author Gabriel Reyes
 * @author Paula López
 * @author Gael Dúran
 * @author Ana Gutiérrez
 * @version 1.0, 23/05/2025
 * @see LISTADETAREAS
 */
import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;
public class LISTADETAREAS{

public static void main(String[] LISTADETAREAS ){ 
    String NicknameUsuario="";
    String ContrasenaUsuario="";
    String menuAdministrador,menuDesarrollador,menuInvitado;
    String otraVez=""; 
    String opc="";
    String rep="";
    final int MAX_USUARIOS=1000;//cantidad maxima de usuarios
    USUARIO usuarios[]= new USUARIO[MAX_USUARIOS];
    final int MAX_TAREAS= 1000000;//cantidad maxima de tareas
    TAREAS [] tareas= new TAREAS[MAX_TAREAS];
    usuarios[0] = new ADMINISTRADOR("10", "Araceli", "Mercado", "araceli@gmail.com", "1234", "Administrador");
    usuarios[1] = new DESARROLLADOR("11", "Diego", "Alberto", "diego@gmail.com", "5678", "Desarrollador");
    usuarios[2] = new INVITADO("12", "Diana", "Rojas", "diana@gmail.com", "9876", "Invitado");
    opc= solicitarInput("Bienvenido, seleccione una opción: \n 1. Iniciar sesión \n 2. Salir");
        //INICIA SESIÓN
        try{
        if ("1".equals(opc)) {
        //PIDE DATOS DEL USUARIO
            NicknameUsuario= solicitarInput("Ingresa tu nickname o correo ");
            ContrasenaUsuario= solicitarInput("Ingresa tu contraseña");
            //REVISA SI EXISTE EL USUARIO
            for (int i=0;i<MAX_USUARIOS; i++) {
                if ( usuarios[i]!= null && NicknameUsuario.equals(usuarios[i].nickname) && ContrasenaUsuario.equals(usuarios[i].contrasena) || NicknameUsuario.equals(usuarios[i].correo) && ContrasenaUsuario.equals(usuarios[i].contrasena)) {
                    //MANTIENE AL USUARIO EN EL PROGRAMA HASTA QUE DECIDA LO CONTRARIO
                    do{
                         //SI EL USUARIO ES ADMINISTRADOR
                        if (usuarios[i].tipoUsuario.equals("Administrador")) {
                            //EL USUARIO QUE ENTRA ES U2
                            ADMINISTRADOR u2= (ADMINISTRADOR) usuarios[i];
                            //MUESTRA MENÚ
                            menuAdministrador=solicitarInput("MENÚ ADMINISTRADOR\n 1. Agregar usuario\n 2.Crear tareas\n 3.Ver tareas\n 4.Filtrar tareas\n 5.Actualizar tareas\n 6.Eliminar tareas");
                           //SEGÚN LA ACCIÓN QUE QUIERA REALIZAR EL ADMINISTRADOR
                            switch (menuAdministrador) {

                                case "1": //AGREGA UN USUARIO
                                String tipoUsuario1;
                                do{
                                String nombreusuario1 = solicitarInput("Nombre del nuevo usuario ");
                                String nickname1= solicitarInput("Nickname del nuevo usuario ");
                                String correo1 = solicitarInput("Correo electrónico del nuevo usuario ");
                                String contrasena1 = solicitarInput("Contraseña del nuevo usuario ");
                                boolean IDcorrecto;
                                String id1;
                                USUARIO u1 = null;
                                
                                tipoUsuario1 = solicitarInput("Tipo de usuario\n Elige el número de acuerdo al tipo de usuario\n 1.ADMINISTRADOR\n 2.DESARROLLADOR\n 3.INVITADO ");
                                if (!tipoUsuario1.equals("1") && !tipoUsuario1.equals("2") && !tipoUsuario1.equals("3")) {
                                    JOptionPane.showMessageDialog(null, "El tipo de usuario ingresado no es válido", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                do {
                                     IDcorrecto=false;
                                    Random aleatorio= new Random();
                                    int id= aleatorio.nextInt(1000);//Números entre 0 Y 999 para ID
                                    id1 = Integer.toString(id);
                                    for (int j = 0; j < MAX_USUARIOS; j++) {
                                        if (usuarios[j]!=null && usuarios[j].ID.equals(id1)) {
                                            IDcorrecto = true;
                                            break;
                                        }
                                    }
                                } while (IDcorrecto==true);

                                switch (tipoUsuario1) {
                                    case "1":
                                        u1 = new ADMINISTRADOR(id1,nombreusuario1,nickname1,correo1,contrasena1, tipoUsuario1);
                                        break;
                                    case "2":
                                        u1 = new DESARROLLADOR(id1,nombreusuario1,nickname1,correo1,contrasena1, tipoUsuario1);
                                        break;
                                    case "3":
                                        u1 = new INVITADO(id1,nombreusuario1,nickname1,correo1,contrasena1, tipoUsuario1);
                                        break;
                                }
                                u2.agregarUsuario(u1);
                                rep=solicitarInput("Deseas agregar otro usuario?(S/N)");
                                } while ("S".equalsIgnoreCase(rep)) ;
                                break;

                                case "2": //CREAR TAREA
                                boolean iDRep;
                                USUARIO u3=null;
                                String id2,fechaEI,fechaEF,descripcion,idUsuarioTarea;
                                LocalDate fechaEstimadaIn=null;
                                LocalDate fechaEstimadaFin=null;
                                LocalDate fechaInicio,fechaFinal;
                                String estado= "pendiente";
                                do{
                                descripcion= solicitarInput("Ingresa la descripción de la tarea a asignar");
                                idUsuarioTarea=solicitarInput("Ingresa ID del usuario al que se le asignará la tarea");

                                for (int j = 0; j < MAX_USUARIOS; j++) {
                                    if (usuarios[j]!=null && usuarios[j].ID.equals(idUsuarioTarea)) {
                                        u3=usuarios[j]; 
                                        break;
                                    }
                                }
                                try {
                                    fechaEI = solicitarInput("Fecha estimada de inicio (aaaa-mm-dd):");
                                    fechaEF = solicitarInput("Fecha estimada de finalización (aaaa-mm-dd):");
                                    fechaEstimadaIn = LocalDate.parse(fechaEI);
                                    fechaEstimadaFin = LocalDate.parse(fechaEF);
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, "USTED NO ESCRIBIÓ LA FECHA EN EL FORMATO INDICADO", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    break;
                                }

                                fechaInicio =LocalDate.of(2000, 01, 01) ; // solamente le damos a un valor a la fecha inicial
                                fechaFinal =LocalDate.of(2000, 01, 01) ;//solamente le damos un valor a la fecha final

                                        do {
                                            iDRep=false;
                                            Random aleatorio= new Random();
                                            int id= aleatorio.nextInt(1000);//Números entre 0 y 999 para ID
                                            id2 = Integer.toString(id);
                                            for (int j = 0; j < MAX_TAREAS; j++) {
                                                if (tareas[j]!=null && tareas[j].id.equals(id2)) {
                                                iDRep = true;
                                                break;
                                                }
                                            }
                                        } while (iDRep==true);

                                TAREAS t2= new TAREAS(id2, estado, u3, descripcion,fechaEstimadaIn, fechaInicio, fechaEstimadaFin, fechaFinal);
                                u2.crearTareaAdmin(t2);
                                rep=solicitarInput("Deseas crear otra tarea? (S/N)");
                                }while("S".equalsIgnoreCase(rep));
                                break;    

                                case "3"://VER TAREAS
                                u2.desplegarTareasAdmin();
                                break;

                                case "4"://FILTRA TAREAS
                                Boolean usuarioExistente=false;
                                do{
                                    String filtrar=solicitarInput("Desea filtrar las tareas por(A/B):\n A) Estado\n B) Usuario");
                                    USUARIO u4=null;
                                    String estadoF;
                                    try {
                                        if ("A".equals(filtrar)) {
                                            u2.filtrarTareasPorEstado();
                                        }else if ("B".equals(filtrar)) {
                                            estadoF=solicitarInput("Ingresa el ID del usuario\n");
                                            for (int j = 0; j < MAX_USUARIOS; j++) {
                                                if (usuarios[j]!=null && usuarios[j].ID.equals(estadoF) ){
                                                    usuarioExistente=true;
                                                    u4=usuarios[j];
                                                    u2.filtrarTareasPorUsuario(u4);
                                                    break;
                                                }
                                            }
                                            if (usuarioExistente==false) {
                                                JOptionPane.showMessageDialog(null, "USTED NO INGRESO UN USUARIO EXISTENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                                            }
                                        }
                                    } catch (NullPointerException e) {
                                        if (filtrar.equals("A")==false && filtrar.equals("B")==false) {
                                            JOptionPane.showMessageDialog(null, "USTED NO ELIGIÓ NINGUNA DE LAS OPCIONES EXISTENTES", "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } 
                                    rep=solicitarInput("Deseas elegir otro filtro? (S/N)");
                                }while("S".equalsIgnoreCase(rep))  ;    
                                break;

                                case "5"://ACTUALIZAR TAREA
                                do {
                                    String idTareas=solicitarInput("Ingresa el ID de la tarea que desea actualizar");
                                    u2.actualizarTareaPorId(idTareas);
                                    rep=solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                } while ("S".equalsIgnoreCase(rep));
                                break;

                                case "6"://ELIMINAR TAREA
                                do {
                                    String idTareaEliminar= solicitarInput("Ingresa el ID de la tarea que deseas eliminar");
                                    u2.eliminarTareaPorId(idTareaEliminar);
                                    rep=solicitarInput("Deseas eliminar otra tarea? (S/N)");
                                } while ("S".equalsIgnoreCase(rep));
                                break;

                                default:
                                break;

                            }
                        }else if (usuarios[i].tipoUsuario=="Desarrollador") {
                            // SI EL USUARIO ES DESARROLLADOR
                            DESARROLLADOR desarrollador = (DESARROLLADOR) usuarios[i];
                            menuDesarrollador=solicitarInput("MENÚ DESARROLLADOR\n 1.Crear tareas\n 2. Desplegar tareas\n 3.Filtrar tareas\n 4. Actualizar tareas\n");
                            
                            switch (menuDesarrollador) {
                                //Crear la tarea 
                                case "1":
                                do{
                                boolean idRepetido;
                                String idTarea;
                                do {
                                    idRepetido = false;
                                    Random rand = new Random();
                                    idTarea = Integer.toString(rand.nextInt(1000));
                                    for (int j = 0; j < ADMINISTRADOR.MAX_TAREAS; j++) {
                                        if (ADMINISTRADOR.listaTareas[j] != null && ADMINISTRADOR.listaTareas[j].getId().equals(idTarea)) {
                                            idRepetido = true;
                                            break;
                                        }
                                    }
                                } while (idRepetido);

                                String estado = "pendiente";
                                String descripcion = solicitarInput("Descripción de la tarea:");
                                String fechaEI = solicitarInput("Fecha estimada de inicio (aaaa-mm-dd):");
                                String fechaEF = solicitarInput("Fecha estimada de finalización (aaaa-mm-dd):");
                                LocalDate fechaEstimadaInicio = LocalDate.parse(fechaEI);
                                LocalDate fechaEstimadaFin = LocalDate.parse(fechaEF);
                                LocalDate fechaInicio = null;
                                LocalDate fechaFinal = null;

                                if (estado.equals("en curso")) {
                                    fechaInicio = LocalDate.now();
                                }
                                
                                if (estado.equals("completada")) {
                                    fechaInicio = LocalDate.now();
                                    fechaFinal = LocalDate.now();
                                }

                                TAREAS nueva = new TAREAS(idTarea, estado, desarrollador, descripcion, fechaEstimadaInicio, fechaInicio, fechaEstimadaFin, fechaFinal);
                                desarrollador.enlistarTarea(nueva);
                                rep=solicitarInput("Deseas crear otra tarea?(S/N)");
                                }while("S".equalsIgnoreCase(rep));
                                break;

                                //Desplegar Tareas 
                                case "2":
                                desarrollador.desplegarTareasDesa();
                                break;

                                //Filtrar tareas
                                case "3":
                                do {
                                    desarrollador.filtrarTareasPorEstado();
                                    rep=solicitarInput("Deseas elegir otro filtro? (S/N)");
                                } while ("S".equalsIgnoreCase(rep));
                                break;
                                
                                //Actualizar tareas
                                case "4":
                                 do {
                                    desarrollador.actualizarTareasDesa();
                                    rep=solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                } while ("S".equalsIgnoreCase(rep));
                                break;
                                default:
                                   break;
                            } 

                        } else if (usuarios[i].tipoUsuario=="Invitado") {
                            INVITADO invitado = (INVITADO) usuarios[i];
                            menuInvitado=solicitarInput("MENÚ INVITADO\n 1.Ver tareas\n 2.Filtrar");
                            switch (menuInvitado) {
                                //Desplegar tareas
                                case "1":
                                invitado.desplegarTareasInv();
                                    break;
                                
                                //Filtrar tareas
                                case "2":
                                do {
                                    invitado.filtrarTareasEstadoInv();
                                    rep=solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                } while ("S".equalsIgnoreCase(rep));
                                break;
                                default:
                                break;
                            }
                        }
                        //PREGUNTA AL USUARIO SI DESEA SEGUIR EN EL SISTEMA O NO
                        otraVez= solicitarInput("Desea regresar al menú y seguir en el programa?(S/N)");
                    }while (otraVez.equalsIgnoreCase("S")); //HACE QUE SIGA EN EL PROGRAMA O SALGA

                }
            }

        }else if(opc.equals("2")){
            JOptionPane.showMessageDialog(null, "USTED HA DECIDIDO SALIR");
        } else {
            JOptionPane.showMessageDialog(null, "UPS... USTED NO DECIDIÓ ENTRAR NI SALIR", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        }catch(NullPointerException todo){
             if ("".equals(ContrasenaUsuario)|| "".equals(NicknameUsuario)) {
               JOptionPane.showMessageDialog(null, "UPS... USTED NO INTRODUJO UN VALOR", "ERROR", JOptionPane.ERROR_MESSAGE);
               return;
            } else if ("N".equalsIgnoreCase(otraVez) || "".equals(otraVez)) {
                JOptionPane.showMessageDialog(null, "USTED HA DECIDIDO SALIR");
                return;
            }else{
                JOptionPane.showMessageDialog(null, "UPS... USTED NO INTRODUJO UN VALOR VÁLIDO", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        
}
           
public static String solicitarInput(String mensaje ) {
            String elementos = JOptionPane.showInputDialog(mensaje);
            return elementos;
    }

}
