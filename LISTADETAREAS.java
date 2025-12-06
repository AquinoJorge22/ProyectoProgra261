
/**
 * Programa que permite al usuario realizar acciones sobre tareas y/o usuarios,
 * dependiendo de su tipo de usuario. Básicamente: entras, te identificas,
 * y si sí eres quien dices ser, pues ya puedes hacer lo tuyo.
 *  
 * @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
 * @version 1.1, 05/12/2025
 * @see LISTADETAREAS
 */


import javax.swing.JOptionPane;

public class LISTADETAREAS {



    // 🔴 Atributos de clase
    public static final int MAX_USUARIOS = 1000;
    public static final int MAX_TAREAS = 1000000;
    public static USUARIO[] listaUsuarios = new USUARIO[MAX_USUARIOS];
    public static TAREAS[] listaTareas = new TAREAS[MAX_TAREAS];

    // 🔴 Función auxiliar para pedir datos al usuario
    public static String solicitarInput(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    // 🔴 Inicializa usuarios base

    // 🔴 Método que inicia el login y permite regresar al menú principal
    public static void mostrarLogin() {

            String opc = solicitarInput(
                    "Bienvenido, seleccione una opción: \n1. Iniciar sesión \n2. Salir");
            if (opc == null) return;

            if ("1".equals(opc)) {
                boolean encontrado = false;
                while(!encontrado){
                String NicknameUsuario = solicitarInput("Ingresa tu nickname o correo");
                String ContrasenaUsuario = solicitarInput("Ingresa tu contraseña");

                if (NicknameUsuario == null || ContrasenaUsuario == null || NicknameUsuario.trim().isEmpty() || ContrasenaUsuario.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "UPS... USTED NO INTRODUJO UN VALOR", "ERROR", JOptionPane.ERROR_MESSAGE);

                }

                for (int i = 0; i < MAX_USUARIOS; i++) {
                    if (listaUsuarios[i] != null &&
                        (NicknameUsuario.equals(listaUsuarios[i].nickname) && ContrasenaUsuario.equals(listaUsuarios[i].contrasena)
                        || NicknameUsuario.equals(listaUsuarios[i].correo) && ContrasenaUsuario.equals(listaUsuarios[i].contrasena))) {

                        encontrado = true;

                        // 🔴 ADMINISTRADOR
                        if ("Administrador".equals(listaUsuarios[i].tipoUsuario)) {
                            ADMINISTRADOR admin = (ADMINISTRADOR) listaUsuarios[i];
                            admin.lanzarInterfazAdmin(); // interfaz gráfica admin
                        }

                        // 🔴 DESARROLLADOR
                        else if ("Desarrollador".equals(listaUsuarios[i].tipoUsuario)) {
                            DESARROLLADOR des = (DESARROLLADOR) listaUsuarios[i];
                            des.lanzarInterfazDesarrollador(); // interfaz gráfica desarrollador
                        }

                        // 🔴 INVITADO
                        else if ("Invitado".equals(listaUsuarios[i].tipoUsuario)) {
                            INVITADO inv = (INVITADO) listaUsuarios[i];
                            inv.lanzarInterfazInvitado(); // interfaz gráfica invitado
                        }

                        break; // Usuario encontrado, salir del loop
                    }
                }

                if (!encontrado) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            } else if ("2".equals(opc)) {
                JOptionPane.showMessageDialog(null, "USTED HA DECIDIDO SALIR");
                return;
            } else {
                JOptionPane.showMessageDialog(null, "UPS... USTED NO DECIDIÓ ENTRAR NI SALIR", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
    }

    public static void main(String[] args) {
    ARCHIVO archivo = new ARCHIVO();

    // Leer usuarios y tareas desde los archivos
    archivo.leerUsuarios();
    archivo.leerTareas();

    if (LISTADETAREAS.listaUsuarios[0] == null ) {
    listaUsuarios[0] = new ADMINISTRADOR("10", "Araceli", "Mercado", "araceli@gmail.com", "1234", "Administrador");
    listaUsuarios[1] = new DESARROLLADOR("11", "Diego", "Alberto", "diego@gmail.com", "5678", "Desarrollador");
    listaUsuarios[2] = new INVITADO("12", "Diana", "Rojas", "diana@gmail.com", "9876", "Invitado");
}
    // Mostrar login
    mostrarLogin();

    // Al final, guardar cambios antes de cerrar
    archivo.guardarUsuarios(listaUsuarios);
    archivo.guardarTareas(listaTareas);
}
}






