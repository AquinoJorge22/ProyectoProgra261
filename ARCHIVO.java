import java.io.*;
import java.time.LocalDate;

public class ARCHIVO {

    // 🔴 Separador y directorio
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String USER_DIR = System.getProperty("user.dir");

    // 🔴 Rutas de archivos
    private static final String RUTA_USUARIOS = USER_DIR + FILE_SEP + "usuarios.txt";
    private static final String RUTA_TAREAS = USER_DIR + FILE_SEP + "tareas.txt";

    // 🔴 Constructor que crea archivos si no existen
    public ARCHIVO() {
        crearArchivoSiNoExiste(RUTA_USUARIOS);
        crearArchivoSiNoExiste(RUTA_TAREAS);
    }

    // 🔴 Método que crea archivo si no existe
    private void crearArchivoSiNoExiste(String ruta) {
        File archivo = new File(ruta);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + ruta);
            e.printStackTrace();
        }
    }

    // 🔴 Guardar lista de usuarios en archivo
    public void guardarUsuarios(USUARIO[] listaUsuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_USUARIOS))) {
            for (USUARIO u : listaUsuarios) {
                if (u != null) {
                    String linea = u.getID() + "|" + u.getnombreUsuario() + "|" + u.getNickname() + "|" +
                                   u.getCorreo() + "|" + u.getContrasena() + "|" + u.tipoUsuario;
                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios.");
            e.printStackTrace();
        }
    }

    // 🔴 Leer lista de usuarios desde archivo
    public void leerUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_USUARIOS))) {
            String linea;
            int indice = 0;
            while ((linea = reader.readLine()) != null && indice < LISTADETAREAS.MAX_USUARIOS) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 6) {
                    USUARIO u = new USUARIO(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                    LISTADETAREAS.listaUsuarios[indice++] = u;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios.");
            e.printStackTrace();
        }
    }

    // 🔴 Guardar lista de tareas en archivo
    public void guardarTareas(TAREAS[] listaTareas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_TAREAS))) {
            for (TAREAS t : listaTareas) {
                if (t != null) {
                    String linea = t.getId() + "|" + t.getEstado() + "|" + 
                                   (t.getUsuario() != null ? t.getUsuario().getNickname() : "null") + "|" +
                                   t.getDescripcion() + "|" +
                                   (t.getFechaEstimadaInicio() != null ? t.getFechaEstimadaInicio() : "null") + "|" +
                                   (t.getFechaInicio() != null ? t.getFechaInicio() : "null") + "|" +
                                   (t.getFechaEstimadaFin() != null ? t.getFechaEstimadaFin() : "null") + "|" +
                                   (t.getFechaFin() != null ? t.getFechaFin() : "null");
                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tareas.");
            e.printStackTrace();
        }
    }

    // 🔴 Leer lista de tareas desde archivo
    public void leerTareas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTA_TAREAS))) {
            String linea;
            int indice = 0;
            while ((linea = reader.readLine()) != null && indice < LISTADETAREAS.MAX_TAREAS) {
                String[] datos = linea.split("\\|");
                if (datos.length >= 8) {
                    // Buscar usuario por nickname
                    USUARIO usuario = null;
                    for (USUARIO u : LISTADETAREAS.listaUsuarios) {
                        if (u != null && u.getNickname().equals(datos[2])) {
                            usuario = u;
                            break;
                        }
                    }
                    LocalDate fechaEstIni = !"null".equals(datos[4]) ? LocalDate.parse(datos[4]) : null;
                    LocalDate fechaIni = !"null".equals(datos[5]) ? LocalDate.parse(datos[5]) : null;
                    LocalDate fechaEstFin = !"null".equals(datos[6]) ? LocalDate.parse(datos[6]) : null;
                    LocalDate fechaFin = !"null".equals(datos[7]) ? LocalDate.parse(datos[7]) : null;

                    TAREAS t = new TAREAS(datos[0], datos[1], usuario, datos[3],
                            fechaEstIni, fechaIni, fechaEstFin, fechaFin);
                    LISTADETAREAS.listaTareas[indice++] = t;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer tareas.");
            e.printStackTrace();
        }
    }

}
