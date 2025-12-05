/**
* @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
*/
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * Clase ADMINISTRADOR
 *
 * <p>
 * Implementa la lógica administrativa sobre usuarios y tareas y además
 * provee una interfaz gráfica mínima (ventana con botones) para visualizar
 * y ejecutar las operaciones principales (listar tareas, filtrar, crear,
 * actualizar y eliminar).
 * </p>
 */
public class ADMINISTRADOR extends USUARIO {

    /* =========================
       CONSTANTES Y ESTADO GLOBAL (NOMBRES EN ESPAÑOL)
       ========================= */

    /** Máximo de usuarios permitidos en el arreglo interno. */
    public static final int MAX_USUARIOS = 1000;

    /** Arreglo que contiene los usuarios del sistema. */
    public static USUARIO[] listaUsuarios = new USUARIO[MAX_USUARIOS];

    /** Número actual de usuarios registrados en {@link #listaUsuarios}. */
    public static int contadorUsuarios = 0;

    /** Máximo de tareas permitidas en el arreglo interno. */
    public static final int MAX_TAREAS = 1_000_000;

    /** Arreglo que contiene las tareas del sistema. */
    public static TAREAS[] listaTareas = new TAREAS[MAX_TAREAS];
    
    public static int contadorTareas = 0;

    /* ======
       BLOQUE DE INICIALIZACIÓN ESTÁTICO
       ====== */

    static {
        // Usuarios de ejemplo
        listaUsuarios[0] = new ADMINISTRADOR("10", "Araceli", "Mercado", "araceli@gmail.com", "1234", "Administrador");
        listaUsuarios[1] = new DESARROLLADOR("11", "Laura", "Blancas", "laura@gmail.com", "2102", "Desarrollador");
        listaUsuarios[2] = new INVITADO("12", "Mariana", "Mejia", "mariana@gmail.com", "5615", "Invitado");
        contadorUsuarios = 3;

        // Tarea de ejemplo (se asume constructor de TAREAS como en tu versión original)
        LocalDate fechaEstimadaIn1 = LocalDate.now().plus(3);
        LocalDate fechaEstimadaFin1 = LocalDate.now().plusDays(15);
        listaTareas[0] = new TAREAS("12", "La tarea de progra es sobre usuarios", listaUsuarios[2], 
            fechaEstimadaIn1, fechaEstimadaFin1);
    }

    /* ======================
       CONSTRUCTOR
       ====================== */

    /**
     * Constructor del administrador (delegado a {@code USUARIO}).
     *
     * @param ID            identificador del usuario
     * @param nombreUsuario nombre 
     * @param nickname      apellido
     * @param correo        correo electrónico
     * @param contrasena    contraseña
     * @param tipoUsuario   tipo de usuario (p. ej. Administrador)
     */
    public ADMINISTRADOR(String ID, String nombreUsuario, String nickname, String correo, String contrasena, String tipoUsuario) {
        super(ID, nombreUsuario, nickname, correo, contrasena, tipoUsuario);
    }

    /* ===========================
       MÉTODOS PÚBLICOS (LÓGICA) - NOMBRES EN ESPAÑOL
       =========================== */

    /**
     * Agrega un nuevo usuario a {@link #listaUsuarios} si no existe duplicado.
     *
     * @param nuevoUsuario usuario a agregar
     */
    public static void agregarUsuario(USUARIO nuevoUsuario) {
        for (int i = 0; i < MAX_USUARIOS; i++) {
            if (listaUsuarios[i] != null && nuevoUsuario.equals(listaUsuarios[i])) {
                JOptionPane.showMessageDialog(null, "EL USUARIO YA HA SIDO CREADO ANTERIORMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (contadorUsuarios < MAX_USUARIOS) {
            listaUsuarios[contadorUsuarios] = nuevoUsuario;
            contadorUsuarios++;
            JOptionPane.showMessageDialog(null, "Se ha creado correctamente el usuario\n");
        } else {
            JOptionPane.showMessageDialog(null, "NO ES POSIBLE AGREGAR OTRO USUARIO");
        }
    }

    /**
     * Crea una nueva tarea y la añade a {@link #listaTareas} si no existe duplicada.
     *
     * @param nuevaTarea tarea a crear
     */
    public static void crearTareaAdmin(TAREAS nuevaTarea) {
        for (int i = 0; i < MAX_TAREAS; i++) {
            if (listaTareas[i] != null && nuevaTarea.equals(listaTareas[i])) {
                JOptionPane.showMessageDialog(null, "LA TAREA YA HA SIDO CREADA ANTERIORMENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        if (contadorTareas < MAX_TAREAS) {
            listaTareas[contadorTareas] = nuevaTarea;
            JOptionPane.showMessageDialog(null, "Se ha creado correctamente la tarea\n");
            contadorTareas++;
        } else {
            JOptionPane.showMessageDialog(null, "NO ES POSIBLE AGREGAR OTRA TAREA", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra todas las tareas en un JTextArea dentro de un JOptionPane.
     * Si no hay tareas, muestra un mensaje informativo.
     */
    public static void desplegarTareasAdmin() {
        if (contadorTareas == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no existen tareas");
            return;
        }
        JTextArea area = new JTextArea("LISTA DE TAREAS:\n", 15, 50);
        for (int i = 0; i < contadorTareas; i++) {
            area.append("TAREA " + (i + 1) + ":\n" + listaTareas[i] + "\n");
        }
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Filtra las tareas por estado (pendiente / enCurso / completada) y las muestra.
     * La selección se hace mediante un input (A/B/C).
     */
    public static void filtrarTareasPorEstado() {
        if (contadorTareas == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no existen tareas");
            return;
        }
        String opcion = solicitarEntrada("Elige el estado (A/B/C):\nA) PENDIENTES\nB) EN CURSO\nC) COMPLETADO\n");
        if (opcion == null) return;
        JTextArea area = new JTextArea("LISTA DE TAREAS\n\n", 15, 50);
        boolean encontrado = false;
        switch (opcion.toUpperCase()) {
            case "A":
                area.append("PENDIENTES\n");
                for (int i = 0; i < MAX_TAREAS; i++) {
                    if (listaTareas[i] != null && "pendiente".equalsIgnoreCase(listaTareas[i].estado)) {
                        area.append("TAREA:\n" + listaTareas[i] + "\n");
                        encontrado = true;
                    }
                }
                if (!encontrado) area.append("No existen tareas pendientes");
                break;
            case "B":
                area.append("EN CURSO\n");
                for (int i = 0; i < MAX_TAREAS; i++) {
                    if (listaTareas[i] != null && ("enCurso".equalsIgnoreCase(listaTareas[i].estado) || "encurso".equalsIgnoreCase(listaTareas[i].estado))) {
                        area.append("TAREA:\n" + listaTareas[i] + "\n");
                        encontrado = true;
                    }
                }
                if (!encontrado) area.append("No existen tareas en curso");
                break;
            case "C":
                area.append("COMPLETADAS\n");
                for (int i = 0; i < MAX_TAREAS; i++) {
                    if (listaTareas[i] != null && "completada".equalsIgnoreCase(listaTareas[i].estado)) {
                        area.append("TAREA:\n" + listaTareas[i] + "\n");
                        encontrado = true;
                    }
                }
                if (!encontrado) area.append("No existen tareas completadas");
                break;
            default:
                area.append("Opción inválida");
        }
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra las tareas pertenecientes a un usuario indicado.
     *
     * @param usuario usuario para el cual se filtran las tareas
     */
    public static void filtrarTareasPorUsuario(USUARIO usuario) {
        if (contadorTareas == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no existen tareas");
            return;
        }
        JTextArea area = new JTextArea("LISTA DE TAREAS PARA " + usuario.getNickname() + "\n", 15, 50);
        boolean tareaUsuario = false;
        for (int i = 0; i < listaTareas.length; i++) {
            if (listaTareas[i] != null && listaTareas[i].usuario.equals(usuario)) {
                area.append("TAREA:\n" + listaTareas[i] + "\n");
                tareaUsuario = true;
            }
        }
        if (!tareaUsuario) area.append("Todavía no existen tareas para " + usuario.getNickname());
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        JOptionPane.showMessageDialog(null, scroll, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Actualiza una tarea por su ID; permite cambiar estado, usuario asignado,
     * descripción y (si aplica) fechas estimadas.
     *
     * @param idTarea identificador de la tarea a actualizar
     */
    public static void actualizarTareaPorId(String idTarea) {
        if (contadorTareas == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no existen tareas");
            return;
        }
        boolean existe = false;
        for (int i = 0; i < listaTareas.length; i++) {
            if (listaTareas[i] != null && listaTareas[i].id.equals(idTarea)) {
                existe = true;

                // Modificar estado
                String modificarEstado = solicitarEntrada("¿Quieres modificar el estado de la tarea? (S/N)");
                if ("S".equalsIgnoreCase(modificarEstado)) {
                    if ("completada".equalsIgnoreCase(listaTareas[i].estado)) {
                        JOptionPane.showMessageDialog(null, "La tarea ya ha sido completada, no es posible realizar la modificación");
                    } else {
                        String anterior = listaTareas[i].estado;
                        if ("pendiente".equalsIgnoreCase(listaTareas[i].estado)) {
                            listaTareas[i].fechaInicio = LocalDate.now();
                            listaTareas[i].setEstado("enCurso");
                        } else if ("enCurso".equalsIgnoreCase(listaTareas[i].estado) || "encurso".equalsIgnoreCase(listaTareas[i].estado)) {
                            listaTareas[i].fechaFin = LocalDate.now();
                            listaTareas[i].setEstado("completada");
                        }
                        JOptionPane.showMessageDialog(null, "Se realizó la siguiente modificación al estado de la tarea:\n" + anterior + " --> " + listaTareas[i].estado);
                    }
                }

                // Modificar usuario asignado
                String modificarUsuario = solicitarEntrada("¿Quieres modificar el usuario al que pertenece la tarea? (S/N)");
                if ("S".equalsIgnoreCase(modificarUsuario)) {
                    String nuevoIdUsuario = solicitarEntrada("Ingresa el ID del nuevo usuario al que le pertenecerá la tarea");
                    if (nuevoIdUsuario != null) {
                        boolean usuarioEncontrado = false;
                        for (int j = 0; j < MAX_USUARIOS; j++) {
                            if (listaUsuarios[j] != null && nuevoIdUsuario.equals(listaUsuarios[j].getID())) {
                                usuarioEncontrado = true;
                                listaTareas[i].usuario = listaUsuarios[j];
                                JOptionPane.showMessageDialog(null, "Se realizó la modificación al usuario de la tarea:\nEl nuevo usuario es: " + listaTareas[i].usuario.getNickname());
                                break;
                            }
                        }
                        if (!usuarioEncontrado) {
                            JOptionPane.showMessageDialog(null, "UPS... EL ID INGRESADO NO CORRESPONDE A ALGÚN USUARIO EXISTENTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                // Modificar descripción
                String modificarDescripcion = solicitarEntrada("¿Quieres modificar la descripción de la tarea? (S/N)");
                if ("S".equalsIgnoreCase(modificarDescripcion)) {
                    String nuevaDescripcion = solicitarEntrada("Ingresa la nueva descripción de la tarea");
                    if (nuevaDescripcion != null) {
                        listaTareas[i].descripcion = nuevaDescripcion;
                        JOptionPane.showMessageDialog(null, "Se realizó la modificación a la descripción de la tarea.");
                    }
                }

                // Modificar fechas (solo si sigue siendo pendiente)
                if ("pendiente".equalsIgnoreCase(listaTareas[i].estado)) {
                    String modificarEstIn = solicitarEntrada("¿Quieres modificar la fecha estimada de inicio? (S/N)");
                    if ("S".equalsIgnoreCase(modificarEstIn)) {
                        String fechaEI = solicitarEntrada("Nueva fecha estimada de inicio (aaaa-mm-dd):");
                        if (fechaEI != null && !fechaEI.trim().isEmpty()) {
                            listaTareas[i].fechaEstimadaInicio = LocalDate.parse(fechaEI);
                            JOptionPane.showMessageDialog(null, "La nueva fecha de inicio de la tarea es: " + listaTareas[i].fechaEstimadaInicio);
                        }
                    }
                    String modificarEstFin = solicitarEntrada("¿Quieres modificar la fecha estimada de fin? (S/N)");
                    if ("S".equalsIgnoreCase(modificarEstFin)) {
                        String fechaEF = solicitarEntrada("Nueva fecha estimada de fin (aaaa-mm-dd):");
                        if (fechaEF != null && !fechaEF.trim().isEmpty()) {
                            listaTareas[i].fechaEstimadaFin = LocalDate.parse(fechaEF);
                            JOptionPane.showMessageDialog(null, "La nueva fecha de fin de la tarea es: " + listaTareas[i].fechaEstimadaFin);
                        }
                    }
                }

                break;
            }
        }
        if (!existe) {
            JOptionPane.showMessageDialog(null, "LA TAREA INGRESADA NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Elimina una tarea por su identificador.
     *
     * @param idTarea identificador de la tarea a eliminar
     */
    public static void eliminarTareaPorId(String idTarea) {
        if (contadorTareas == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no existen tareas");
            return;
        }
        boolean encontrada = false;
        for (int j = 0; j < MAX_TAREAS; j++) {
            if (listaTareas[j] != null && idTarea.equals(listaTareas[j].id)) {
                encontrada = true;
                for (int i = j; i < MAX_TAREAS - 1; i++) {
                    listaTareas[i] = listaTareas[i + 1];
                }
                listaTareas[MAX_TAREAS - 1] = null;
                contadorTareas--;
                JOptionPane.showMessageDialog(null, "La tarea se ha eliminado satisfactoriamente");
                break;
            }
        }
        if (!encontrada) {
            JOptionPane.showMessageDialog(null, "LA TAREA INGRESADA NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Función auxiliar que pide una cadena al usuario con {@link JOptionPane#showInputDialog}.
     *
     * @param mensaje texto a mostrar en la entrada
     * @return cadena ingresada por el usuario, o {@code null} si canceló
     */
    public static String solicitarEntrada(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    /* ===========================
       INTERFAZ GRÁFICA (VENTANA) - NOMBRES EN ESPAÑOL
       =========================== */

    /**
     * Lanza una ventana Swing con botones que permiten invocar
     * las operaciones administrativas principales para visualizar la interfaz.
     *
     * Esta ventana NO reemplaza la lógica interna: únicamente ofrece
     * botones que invocan los métodos (los diálogos concretos se siguen mostrando con {@link JOptionPane}).
     */
    public static void lanzarInterfazAdmin() {
        EventQueue.invokeLater(() -> {
            JFrame ventana = new JFrame("Panel Administrativo - Visualización");
            ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventana.setSize(700, 420);
            ventana.setLocationRelativeTo(null);

            JPanel panelBotones = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;

            JButton btnMostrarTodas = new JButton("Desplegar todas las tareas");
            btnMostrarTodas.addActionListener(e -> desplegarTareasAdmin());
            panelBotones.add(btnMostrarTodas, gbc);

            gbc.gridy++;
            JButton btnFiltrarEstado = new JButton("Filtrar tareas por estado");
            btnFiltrarEstado.addActionListener(e -> filtrarTareasPorEstado());
            panelBotones.add(btnFiltrarEstado, gbc);

            gbc.gridy++;
            JButton btnFiltrarUsuario = new JButton("Filtrar tareas por usuario (ID)");
            btnFiltrarUsuario.addActionListener(e -> {
                String id = solicitarEntrada("Ingresa el ID del usuario:");
                if (id == null || id.trim().isEmpty()) return;
                USUARIO u = null;
                for (int i = 0; i < MAX_USUARIOS; i++) {
                    if (listaUsuarios[i] != null && listaUsuarios[i].getID().equals(id.trim())) {
                        u = listaUsuarios[i];
                        break;
                    }
                }
                if (u != null) filtrarTareasPorUsuario(u);
                else JOptionPane.showMessageDialog(ventana, "Usuario no encontrado.", "ERROR", JOptionPane.ERROR_MESSAGE);
            });
            panelBotones.add(btnFiltrarUsuario, gbc);

            gbc.gridy++;
            JButton btnCrearUsuario = new JButton("Crear usuario (simulado)");
            btnCrearUsuario.addActionListener(e -> {
                String id = solicitarEntrada("ID:");
                if (id == null || id.trim().isEmpty()) return;
                String nombre = solicitarEntrada("Nombre del usuario:");
                if (nombre == null) return;
                String nick = solicitarEntrada("Nickname:");
                if (nick == null) return;
                String correo = solicitarEntrada("Correo:");
                if (correo == null) return;
                String pass = solicitarEntrada("Contraseña:");
                if (pass == null) return;
                String tipo = solicitarEntrada("Tipo de usuario (Administrador/Desarrollador/Invitado):");
                if (tipo == null) return;

                USUARIO nuevo = new ADMINISTRADOR(id.trim(), nombre.trim(), nick.trim(), correo.trim(), pass.trim(), tipo.trim());
                agregarUsuario(nuevo);
            });
            panelBotones.add(btnCrearUsuario, gbc);

            gbc.gridy++;
            JButton btnCrearTarea = new JButton("Crear tarea (simulada)");
            btnCrearTarea.addActionListener(e -> {
                String id = solicitarEntrada("ID tarea:");
                if (id == null || id.trim().isEmpty()) return;
                String estado = solicitarEntrada("Estado (pendiente/enCurso/completada):");
                if (estado == null) return;
                String idUsuario = solicitarEntrada("ID del usuario asignado:");
                if (idUsuario == null) return;
                USUARIO usuarioAsignado = null;
                for (int i = 0; i < MAX_USUARIOS; i++) {
                    if (listaUsuarios[i] != null && listaUsuarios[i].getID().equals(idUsuario.trim())) {
                        usuarioAsignado = listaUsuarios[i];
                        break;
                    }
                }
                if (usuarioAsignado == null) {
                    JOptionPane.showMessageDialog(ventana, "Usuario no encontrado.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String desc = solicitarEntrada("Descripción:");
                if (desc == null) return;
                String fechaEstIn = solicitarEntrada("Fecha estimada inicio (aaaa-mm-dd) - opcional:");
                LocalDate fechaEI = (fechaEstIn == null || fechaEstIn.trim().isEmpty()) ? null : LocalDate.parse(fechaEstIn.trim());
                LocalDate fechaEF = (fechaEstFin == null || fechaEstFin.trim().isEmpty()) ? null : LocalDate.parse(fechaEstFin.trim());
                 
                TAREAS nueva = new TAREAS(id.trim(), desc.trim(), usuarioAsignado, 
                                           fechaEI, fechaEF);
                crearTareaAdmin(nueva);
            });
            panelBotones.add(btnCrearTarea, gbc);

            gbc.gridy++;
            JButton btnActualizarTarea = new JButton("Actualizar tarea (por ID)");
            btnActualizarTarea.addActionListener(e -> {
                String id = solicitarEntrada("Ingresa el ID de la tarea a actualizar:");
                if (id == null || id.trim().isEmpty()) return;
                actualizarTareaPorId(id.trim());
            });
            panelBotones.add(btnActualizarTarea, gbc);

            gbc.gridy++;
            JButton btnEliminarTarea = new JButton("Eliminar tarea (por ID)");
            btnEliminarTarea.addActionListener(e -> {
                String id = solicitarEntrada("Ingresa el ID de la tarea a eliminar:");
                if (id == null || id.trim().isEmpty()) return;
                eliminarTareaPorId(id.trim());
            });
            panelBotones.add(btnEliminarTarea, gbc);

            // Pie informativo
            JPanel pie = new JPanel(new BorderLayout());
            JLabel info = new JLabel("<html><center>Interfaz en español — los botones llaman a los diálogos existentes.<br>Variables y métodos renombrados y todo documentado.</center></html>", SwingConstants.CENTER);
            pie.add(info, BorderLayout.CENTER);

            ventana.getContentPane().add(panelBotones, BorderLayout.CENTER);
            ventana.getContentPane().add(pie, BorderLayout.SOUTH);

            ventana.setVisible(true);
        });
    }

    /* ===========================
       MÉTODO main DE PRUEBA (OPCIONAL)
       =========================== */

    /**
     * Método main de prueba para lanzar la interfaz gráfica del administrador.
     * Puedes comentar o eliminar este método si no deseas que esta clase tenga
     * un punto de entrada propio.
     *
     * @param args argumentos de línea de comando (no usados)
     */
    public static void main(String[] args) {
        lanzarInterfazAdmin();
    }
}



