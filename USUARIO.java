/**
 * Programa que representa la estructura que cumplen los usuarios del sistema.
 * @author Gael Dúran
 * @author Ana Gutiérrez
 * @version 1.0, 23/05/2025
 * @see USUARIO
 */
public class USUARIO {
    public String ID, nombreUsuario, nickname, correo, contrasena, tipoUsuario;

    /**
     * 
     * @param ID
     * @param nombreUsuario
     * @param nickname
     * @param correo
     * @param contrasena
     */
    public USUARIO(String ID, String nombreUsuario,String nickname,String correo,String contrasena, String tipoUsuario){
        this.ID= ID;
        this.nombreUsuario=nombreUsuario;
        this.nickname= nickname;
        this.correo= correo;
        this.contrasena= contrasena;
        this.tipoUsuario=tipoUsuario;
    }

       
    /**
     * Muestra la forma del usuario.
     */
    @Override
    public String toString() {
        return "ID: " + ID +"\n" + 
               "Nombre de usuario:"+ nombreUsuario + "\n" + 
               "Nickname: "+nickname+ "\n" + 
               "Correo: "+correo+ "\n" + 
               "Contraseña: "+ contrasena + "\n" 
        ;
    } 

       /**
     * Metodo que compara si 2 usuarios de la Clase USUARIO son iguales.
     * @param obj El segundo usuario a comparar.
     * @return True si el parametro obj es un objeto de la Clase USUARIO,
     * y si son usuarios iguales, False en otro caso.
     */
    @Override
    public boolean equals(Object obj) {  
        USUARIO o=(USUARIO)obj;
        try {
            if (this.nickname==o.nickname && this.contrasena==o.contrasena || this.correo==o.correo && this.contrasena==o.contrasena) {
            return true;
        }
        } catch (NullPointerException e) {
            System.out.println();
        }
        return false;
    
    }

     //Getters

    /**
     * Obtener el ID.
     * @return El ID único del usuario.
     */
    public String getID() {
        return ID;
    }

    
    /**
     * Obtiene el nombre del usuario.
     * @return El nombre de usuario que le corresponde a cada usuario.
     */
    public String getnombreUsuario() {
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
     * @return El correo que le corresponde al usuario.
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
     * Obtiene el tipo de usuario.
     * @return El tipo de usuario.
     */
    public String getTipoUsuario() {
       String x= tipoUsuario;
        switch (x) {
            case "1":
                tipoUsuario= "Administrador";
            case "2":
            tipoUsuario ="Desarrollador";
            case "3":
            tipoUsuario="Invitado";
        }
        if (x.equals("1")==false|| x.equals("2")==false|| x.equals("3")==false) {
            return null;
        }
        return tipoUsuario;
      
    }

    //Setters

    /**
     * Asigna el ID al usuario.
     * @param iD ID único del usuario.
     */
    public void setID(String iD) {
        ID = iD;
    }

    
    /**
     * Asigna el nombre al usuario.
     * @param nombreUsuario Es el nombre de usuario que le corresponde a cada usuario.
     */
    public void setNombreUSUARIO(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

     /**
     * Asigna el nickname al usuario
     * @param nickname Es el nickname del usuario.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

        /**
     * Asigna el correo al usuario.
     * @param correo Es el correo que le corresponde al usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

     /**
     * Asigna la contraseña al usuario.
     * @param contrasena Es la contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Asigna el tipo de usuario al usuario.
     * @param tipoUsuario Es el tipo de usuario que es asignado a cada usuario, y que le otorga ciertaos privilegios y acciones.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
