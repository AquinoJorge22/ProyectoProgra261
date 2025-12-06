/**
 * Programa que permite al usuario realizar acciones sobre tareas y/o usuarios,
 * dependiendo de su tipo de usuario. Básicamente: entras, te identificas,
 * y si sí eres quien dices ser, pues ya puedes hacer lo tuyo.
 *  
 * @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 

 * @version 1.0, Noviembre 2025
 * @see LISTADETAREAS
 */

import java.time.LocalDate;
import java.util.Random;
import javax.swing.JOptionPane;

public class LISTADETAREAS {

    // Función auxiliar para pedir datos al usuario (la dejamos hasta arriba para que se vea)
    public static String solicitarInput(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    public static void main(String[] LISTADETAREAS) {

        // Vars iniciales de registro/login
        String NicknameUsuario = "";
        String ContrasenaUsuario = "";
        String menuAdministrador, menuDesarrollador, menuInvitado;
        String otraVez = "";
        String opc = "";
        String rep = "";

        // Máximos del sistema (se dejan igual, pero se comenta qué onda)
        final int MAX_USUARIOS = 1000;   // Límite de usuarios que se pueden guardar
        final int MAX_TAREAS = 1000000;  // Límite de tareas disponibles

        // Arreglos principales del sistema
        USUARIO usuarios[] = new USUARIO[MAX_USUARIOS];
        TAREAS tareas[] = new TAREAS[MAX_TAREAS];

        // Se cargan usuarios base del sistema (como preset)
        usuarios[0] = new ADMINISTRADOR("10", "Araceli", "Mercado", "araceli@gmail.com", "1234", "Administrador");
        usuarios[1] = new DESARROLLADOR("11", "Diego", "Alberto", "diego@gmail.com", "5678", "Desarrollador");
        usuarios[2] = new INVITADO("12", "Diana", "Rojas", "diana@gmail.com", "9876", "Invitado");

        // Menú inicial: entrar o salir
        opc = solicitarInput("Bienvenido, seleccione una opción: \n 1. Iniciar sesión \n 2. Salir");

        try {

            if ("1".equals(opc)) {

                // *** Proceso de login ***
                NicknameUsuario = solicitarInput("Ingresa tu nickname o correo ");
                ContrasenaUsuario = solicitarInput("Ingresa tu contraseña");

                // Se busca al usuario en el arreglo general
                for (int i = 0; i < MAX_USUARIOS; i++) {

                    // Revisa coincidencia por nickname o correo, más contraseña
                    if (usuarios[i] != null &&
                        (NicknameUsuario.equals(usuarios[i].nickname) && ContrasenaUsuario.equals(usuarios[i].contrasena)
                        || NicknameUsuario.equals(usuarios[i].correo) && ContrasenaUsuario.equals(usuarios[i].contrasena))) {

                        // Una vez dentro, se queda en un loop hasta que el usuario decida salirse
                        do {

                            // -------------------------
                            //      ADMINISTRADOR
                            // -------------------------
                            if (usuarios[i].tipoUsuario.equals("Administrador")) {

                                ADMINISTRADOR u2 = (ADMINISTRADOR) usuarios[i];

                                // Menú principal del admin
                                menuAdministrador = solicitarInput(
                                        "MENÚ ADMINISTRADOR\n 1. Agregar usuario\n 2. Crear tareas\n 3. Ver tareas\n 4. Filtrar tareas\n 5. Actualizar tareas\n 6. Eliminar tareas");

                                switch (menuAdministrador) {

                                    case "1": // Agregar usuario nuevo
                                        do {
                                            String nombreusuario1 = solicitarInput("Nombre del nuevo usuario ");
                                            String nickname1 = solicitarInput("Nickname del nuevo usuario ");
                                            String correo1 = solicitarInput("Correo electrónico del nuevo usuario ");
                                            String contrasena1 = solicitarInput("Contraseña del nuevo usuario ");

                                            boolean IDcorrecto;
                                            String id1;
                                            String tipoUsuario1;
                                            USUARIO u1 = null;

                                            // Se elige qué tipo de usuario se creará
                                            tipoUsuario1 = solicitarInput(
                                                    "Tipo de usuario\n 1.ADMINISTRADOR\n 2.DESARROLLADOR\n 3.INVITADO ");

                                            if (!tipoUsuario1.equals("1") && !tipoUsuario1.equals("2") && !tipoUsuario1.equals("3")) {
                                                JOptionPane.showMessageDialog(null, "El tipo de usuario ingresado no es válido",
                                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }

                                            // Se genera un ID aleatorio que no se repita
                                            do {
                                                IDcorrecto = false;
                                                Random aleatorio = new Random();
                                                int id = aleatorio.nextInt(1000);
                                                id1 = Integer.toString(id);

                                                for (int j = 0; j < MAX_USUARIOS; j++) {
                                                    if (usuarios[j] != null && usuarios[j].ID.equals(id1)) {
                                                        IDcorrecto = true;
                                                        break;
                                                    }
                                                }
                                            } while (IDcorrecto);

                                            // Se crea el usuario según su tipo
                                            switch (tipoUsuario1) {
                                                case "1":
                                                    u1 = new ADMINISTRADOR(id1, nombreusuario1, nickname1, correo1, contrasena1,
                                                            tipoUsuario1);
                                                    break;
                                                case "2":
                                                    u1 = new DESARROLLADOR(id1, nombreusuario1, nickname1, correo1, contrasena1,
                                                            tipoUsuario1);
                                                    break;
                                                case "3":
                                                    u1 = new INVITADO(id1, nombreusuario1, nickname1, correo1, contrasena1,
                                                            tipoUsuario1);
                                                    break;
                                            }

                                            // Se agrega al arreglo del admin
                                            u2.agregarUsuario(u1);

                                            rep = solicitarInput("Deseas agregar otro usuario?(S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    case "2": // Crear tarea
                                        boolean iDRep;
                                        USUARIO u3 = null;
                                        String id2, fechaEI, fechaEF, descripcion, idUsuarioTarea;
                                        LocalDate fechaEstimadaIn = null, fechaEstimadaFin = null;
                                        LocalDate fechaInicio, fechaFinal;
                                        String estado = "pendiente";

                                        do {
                                            descripcion = solicitarInput("Ingresa la descripción de la tarea a asignar");
                                            idUsuarioTarea = solicitarInput("Ingresa ID del usuario al que se le asignará la tarea");

                                            // Se verifica que el usuario asignado exista
                                            for (int j = 0; j < MAX_USUARIOS; j++) {
                                                if (usuarios[j] != null && usuarios[j].ID.equals(idUsuarioTarea)) {
                                                    u3 = usuarios[j];
                                                    break;
                                                }
                                            }

                                            // Fechas estimadas
                                            try {
                                                fechaEI = solicitarInput("Fecha estimada de inicio (aaaa-mm-dd):");
                                                fechaEF = solicitarInput("Fecha estimada de finalización (aaaa-mm-dd):");
                                                fechaEstimadaIn = LocalDate.parse(fechaEI);
                                                fechaEstimadaFin = LocalDate.parse(fechaEF);
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(null,
                                                        "USTED NO ESCRIBIÓ LA FECHA EN EL FORMATO INDICADO", "ERROR",
                                                        JOptionPane.ERROR_MESSAGE);
                                                break;
                                            }

                                            // Fechas reales iniciales (se ponen vacías por default)
                                            fechaInicio = LocalDate.of(2000, 01, 01);
                                            fechaFinal = LocalDate.of(2000, 01, 01);

                                            // Crear ID de la tarea sin repetir
                                            do {
                                                iDRep = false;
                                                Random aleatorio = new Random();
                                                int id = aleatorio.nextInt(1000);
                                                id2 = Integer.toString(id);

                                                for (int j = 0; j < MAX_TAREAS; j++) {
                                                    if (tareas[j] != null && tareas[j].id.equals(id2)) {
                                                        iDRep = true;
                                                        break;
                                                    }
                                                }
                                            } while (iDRep);

                                            // Se arma la tarea
                                            TAREAS t2 = new TAREAS(id2, descripcion, u3, fechaEstimadaIn,
                                                    fechaEstimadaFin);

                                            // Y el admin la agrega a la lista global
                                            u2.crearTareaAdmin(t2);

                                            rep = solicitarInput("Deseas crear otra tarea? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));

                                        break;

                                    case "3": // Ver todas las tareas
                                        u2.desplegarTareasAdmin();
                                        break;

                                    case "4": // Filtrar tareas
                                        Boolean usuarioExistente = false;

                                        do {
                                            String filtrar = solicitarInput(
                                                    "Desea filtrar las tareas por(A/B):\n A) Estado\n B) Usuario");
                                            USUARIO u4 = null;
                                            String estadoF;

                                            try {

                                                if ("A".equals(filtrar)) {
                                                    u2.filtrarTareasPorEstado();

                                                } else if ("B".equals(filtrar)) {
                                                    estadoF = solicitarInput("Ingresa el ID del usuario\n");

                                                    for (int j = 0; j < MAX_USUARIOS; j++) {
                                                        if (usuarios[j] != null && usuarios[j].ID.equals(estadoF)) {
                                                            usuarioExistente = true;
                                                            u4 = usuarios[j];
                                                            u2.filtrarTareasPorUsuario(u4);
                                                            break;
                                                        }
                                                    }

                                                    if (!usuarioExistente) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "USTED NO INGRESO UN USUARIO EXISTENTE", "ERROR",
                                                                JOptionPane.ERROR_MESSAGE);
                                                    }
                                                }

                                            } catch (NullPointerException e) {
                                                if (!filtrar.equals("A") && !filtrar.equals("B")) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "USTED NO ELIGIÓ NINGUNA DE LAS OPCIONES EXISTENTES", "ERROR",
                                                            JOptionPane.ERROR_MESSAGE);
                                                }
                                            }

                                            rep = solicitarInput("Deseas elegir otro filtro? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));

                                        break;

                                    case "5": // Actualizar tarea
                                        do {
                                            String idTareas = solicitarInput("Ingresa el ID de la tarea que desea actualizar");
                                            u2.actualizarTareaPorId(idTareas);

                                            rep = solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    case "6": // Eliminar tarea
                                        do {
                                            String idTareaEliminar = solicitarInput(
                                                    "Ingresa el ID de la tarea que deseas eliminar");
                                            u2.eliminarTareaPorId(idTareaEliminar);

                                            rep = solicitarInput("Deseas eliminar otra tarea? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    default:
                                        break;
                                }

                            // -------------------------
                            //      DESARROLLADOR
                            // -------------------------
                            } else if (usuarios[i].tipoUsuario == "Desarrollador") {

                                DESARROLLADOR desarrollador = (DESARROLLADOR) usuarios[i];

                                menuDesarrollador = solicitarInput(
                                        "MENÚ DESARROLLADOR\n 1.Crear tareas\n 2. Desplegar tareas\n 3.Filtrar tareas\n 4. Actualizar tareas\n");

                                switch (menuDesarrollador) {

                                    case "1": // Crear tarea
                                        do {
                                            boolean idRepetido;
                                            String idTarea;

                                            // Generar ID sin repetir
                                            do {
                                                idRepetido = false;
                                                Random rand = new Random();
                                                idTarea = Integer.toString(rand.nextInt(1000));

                                                for (int j = 0; j < ADMINISTRADOR.MAX_TAREAS; j++) {
                                                    if (ADMINISTRADOR.listaTareas[j] != null
                                                            && ADMINISTRADOR.listaTareas[j].getId().equals(idTarea)) {
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

                                            // Ajuste de fechas reales según estado
                                            if (estado.equals("en curso")) {
                                                fechaInicio = LocalDate.now();
                                            }
                                            if (estado.equals("completada")) {
                                                fechaInicio = LocalDate.now();
                                                fechaFinal = LocalDate.now();
                                            }

                                            TAREAS nueva = new TAREAS(idTarea, estado, desarrollador, descripcion,
                                                    fechaEstimadaInicio, fechaInicio, fechaEstimadaFin, fechaFinal);

                                            desarrollador.enlistarTarea(nueva);

                                            rep = solicitarInput("Deseas crear otra tarea?(S/N)");

                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    case "2": // Ver tareas
                                        desarrollador.desplegarTareasDesa();
                                        break;

                                    case "3": // Filtrar tareas
                                        do {
                                            desarrollador.filtrarTareasPorEstado();
                                            rep = solicitarInput("Deseas elegir otro filtro? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    case "4": // Actualizar tareas
                                        do {
                                            desarrollador.actualizarTareasDesa();
                                            rep = solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    default:
                                        break;
                                }

                            // -------------------------
                            //         INVITADO
                            // -------------------------
                            } else if (usuarios[i].tipoUsuario == "Invitado") {

                                INVITADO invitado = (INVITADO) usuarios[i];

                                menuInvitado = solicitarInput("MENÚ INVITADO\n 1.Ver tareas\n 2.Filtrar");

                                switch (menuInvitado) {

                                    case "1": // Ver tareas
                                        invitado.desplegarTareasInv();
                                        break;

                                    case "2": // Filtrar
                                        do {
                                            invitado.filtrarTareasEstadoInv();
                                            rep = solicitarInput("Deseas actualizar otra tarea? (S/N)");
                                        } while ("S".equalsIgnoreCase(rep));
                                        break;

                                    default:
                                        break;
                                }
                            }

                            // Pregunta final para ver si el usuario sigue dentro o ya se va
                            otraVez = solicitarInput("Desea regresar al menú y seguir en el programa?(S/N)");

                        } while (otraVez.equalsIgnoreCase("S"));

                    }
                }

            } else if (opc.equals("2")) { // si eligió salir desde el inicio
                JOptionPane.showMessageDialog(null, "USTED HA DECIDIDO SALIR");
            } else {
                JOptionPane.showMessageDialog(null, "UPS... USTED NO DECIDIÓ ENTRAR NI SALIR", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (NullPointerException todo) {

            // Manejo de errores cuando el usuario deja inputs vacíos
            if ("".equals(ContrasenaUsuario) || "".equals(NicknameUsuario)) {
                JOptionPane.showMessageDialog(null, "UPS... USTED NO INTRODUJO UN VALOR", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                return;

            } else if ("N".equalsIgnoreCase(otraVez) || "".equals(otraVez)) {
                JOptionPane.showMessageDialog(null, "USTED HA DECIDIDO SALIR");
                return;

            } else {
                JOptionPane.showMessageDialog(null, "UPS... USTED NO INTRODUJO UN VALOR VÁLIDO", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



