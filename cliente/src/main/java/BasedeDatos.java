package cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import datos.Persona;

public class BasedeDatos {


String url = "Volver al Futuro.db";
Connection connect;

public void connect(){ //para abrir la bd
	 try {
	     connect = DriverManager.getConnection("jdbc:sqlite:"+url);
	     if (connect!=null) {
	         System.out.println("Conectado");
	     }
	 }catch (SQLException ex) {
	     System.err.println("No se ha podido conectar a la base de datos\n"+ex.getMessage());
	 }
	}

public void close(){ //para cerrar la bd
	        try {
	            connect.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(BasedeDatos.class.getName()).log(Level.SEVERE, null, ex);
	        }
	 }
public void saveAlumno(Persona p){ //GUARDA JUGADOR
    try {
        PreparedStatement st = connect.prepareStatement("insert into DatosUsuario(Usuario, Pass, Nickname, Pregunta, Respuesta) values (?,?,?,?,?)");
        st.setString(1, p.getNombre());
        st.setString(2, p.getPass());
        st.setString(3, p.getNick());
        st.setString(4, p.getPregunta());
        st.setString(5, p.getRespuesta());
        st.execute();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

}
public int autenticarUser(String u, String p)
	{    try {
        PreparedStatement st = connect.prepareStatement("SELECT * FROM DatosUsuario WHERE Usuario = ? and Pass = ?");
        st.setString(1,u);
        st.setString(2,p);
        ResultSet valor=st.executeQuery();
        //System.out.println(valor);
        if(valor.next())
        	return (1);
        else
        	return (0);
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
	return 0;}
public void mostrarDatosJugador(Persona j,String u,String p)// Busca datos
{
    ResultSet result = null;
    try {
        PreparedStatement st = connect.prepareStatement("SELECT * FROM DatosUsuario WHERE Usuario = ? and Pass = ?");
        st.setString(1,u);
        st.setString(2,p);
        result = st.executeQuery();
       
            j.setNombre(result.getString("Usuario"));
            j.setPass(result.getString("Pass"));
            j.setNick(result.getString("NickName"));
            j.setPregunta(result.getString("Pregunta"));
            j.setRespuesta(result.getString("Respuesta"));

        
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
public int cambiarContraseña(Persona p,String u, String s, String t)
{    
try {
    PreparedStatement st = connect.prepareStatement("UPDATE DatosUsuario SET Pass= ? WHERE Usuario = ? and Pass = ? and Respuesta = ? ");
	st.setString(1, s);
	st.setString(2,p.getNombre());
    st.setString(3,u);
    st.setString(4,t);
 
    int valor=st.executeUpdate();
    //System.out.println(valor);
    if(valor!=0)
    	return (1);
    else
    	return (0);
      
} catch (SQLException ex) {
	
    System.err.println(ex.getMessage());
}
return 0;}
public int cambiarNick(Persona p, String u)
{try {
    PreparedStatement st = connect.prepareStatement("UPDATE DatosUsuario SET Nickname= ? WHERE Usuario = ?");
	st.setString(1, u);
	st.setString(2,p.getNombre());
    int valor=st.executeUpdate();
    //System.out.println(valor);
    if(valor!=0)
    	return (1);
    else
    	return (0);
      
} catch (SQLException ex) {
	System.out.println("excep");
    System.err.println(ex.getMessage());
}
return 0;}
public int validauser(String s)
{ try {
    PreparedStatement st = connect.prepareStatement("SELECT * FROM DatosUsuario WHERE Usuario = ?");
    st.setString(1,s);
    //st.setString(2,p);
    ResultSet valor=st.executeQuery();
    //System.out.println(valor);
    if(valor.next())
    	return (1);
    else
    	return (0);
} catch (SQLException ex) {
    System.err.println(ex.getMessage());
}
return 0;}
}


