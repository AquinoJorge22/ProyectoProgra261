/**
 * Programa que representa la estructura que cumplen los usuarios del sistema.
* @author Aquino Sumuano Jorge Carlos
 * @author Blancas Mejía Laura Mariana
 * @author Campos Sierra Diane Yriatzi
 * @author Eugenio López Maritza Marlem 
 * @version 1.0, 23/05/2025
 * @see USUARIO
 */
public class USUARIO {


    //------- ATRIBUTOS -------
    private String ID;
    private String nombreUsuario;
    private String nickname;
    private String correo;
    private String contrasena;
    private String tipoUsuario;

    /**
     // ----- CONSTRUCTOR DEL USUARIO ----- 
     * @param ID Identificador único que se le asigna
     * @param nombreUsuario Nombre del usuario
     * @param nickname Alias que se le asignara al usuario dentro del sistema
     * @param correo Correo del usuario
     * @param contrasena Contraseña del usuario
     * @param tipoUsuario 
     */
    public USUARIO(String ID, String nombreUsuario, String nickname, String correo, String contrasena, String tipoUsuario){
        this.ID= ID;
        this.nombreUsuario=nombreUsuario;
        this.nickname= nickname;
        this.correo= correo;
        this.contrasena= contrasena;
        this.tipoUsuario=tipoUsuario;
    }

       
    // ______ Representación legible ______
    /**
     * Devuleve una representación textual legible del usuario (no se incluye contraseña)
     * Util para mostrar listas o imprimir informacion por consola.
     */
    
    @Override
    public String toString() {
        return "ID: " + ID +"\n" + 
               "Nombre de usuario:"+ nombreUsuario + "\n" + 
               "Nickname: "+nickname+ "\n" + 
               "Correo: "+correo+ "\n" + 
               "Tipo: "+ getTipoUsuario() + "\n" ;
               //Obs: No se muestra la contraseña por seguridad.
    } 


    // _________equals seguro para comparar credenciales ______
    /**
     * Compara dos usuarios: son iguales si coinciden (nickname o correo) y la contraseña.
     * Realiazamos comprobaciones null para evitar NullPointerException.
     * @param obj El objeto a comparar
     * @return True si representan al mismo usuario por las coincidencias, False en otro caso. 
     */


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  //se esta cmparando si es el mismo objeto
        if (!(obj instanceof USUARIO)) return false;
        /*instanceof lo estamos utilizando para comprobar si el objeto es de la clase USUARIO, 
        si en dado caso el objeto NO lo es entonces nos regresara false
        */
       
        USUARIO otro =(USUARIO) obj;
        // Si alguna verificacion es nula, no se considera igual.
        if (this.contrasena == null || otro.contrasena == null) return false;


        boolean sameNickname = this.nickname != null && otro.nickname != null
                && this.nickname.equalsIgnoreCase(otro.nickname);

         boolean sameCorreo = this.correo != null && otro.correo != null
                && this.correo.equalsIgnoreCase(otro.correo);
        
         boolean sameClave = this.contrasena.equals(otro.contrasena);

         return ( (sameNickname || sameCorreo) && sameClave );
    }

    // --------- GETTERS ----------

    /**
     * Obtener el ID.
     * @return El ID único del usuario.
     */

    public String getID() {
        return ID;
    }

    
    /**
     * Obtener el nombre del usuario.
     * @return El nombre de usuario. 
     */
     
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Obtiene el nickname
     * @return El nickname del usuario.
     */
    public String getNickname() {
        return nickname;
    }


    /**
     * Obtiene el correo de usuario
     * @return El correo que del usuario.
     */
    public String getCorreo() {
        return correo;
    }


    /**
     * Obtiene la contraseña.
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }


     /**
     * Arroja el tipo de usuario en formato legible.
     * @return El tipo de usuario.
     */
    public String getTipoUsuario() {
      switch (tipoUsuario){
        case "administrador":
        case "1": return "Administrador";

        case "desarrollador":   
        case "2": return "Desarrollador";

        case "invitado":
        case "3": return "Invitado";
        
        default: return "Desconocido";
      }
      
    }

    
    // ---------- SETTERS -----------
    /**
     * Asigna un nuevo ID (Si es que asi se requiere)
     * @param ID nuevo idetificador único del usuario.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    
    /**
     * Modificacion del nombre del usuario.
     * @param nombreUsuario Nuevo nombre real del usuario.
     */
    public void setNombreUSUARIO(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

     /**
     * Modificacion del nickname del usuario
     * @param nickname Nuevo nickname del usuario.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Moficiacion del correo del usuario.
     * @param correo Actualizacion del correo registardo.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

     /**
     * Actualizacion de contraseña del usuario.
     * @param contrasena Nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Cambia el tipo de usuario según su rol.
     * @param tipoUsuario rol que se le asigna
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}

