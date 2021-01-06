package Modelo;


import Controlador.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Profesor {
    private static final AtomicInteger count = new AtomicInteger(0); 
    private long id;
    private String nombre;
    private String correo;
    private int edad;
    private ArrayList<Curso> cursos;

    public Profesor(long id, String nombre, String correo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public Profesor(String nombre, String correo, int edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }
    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
    
    
    //Profesor u = new Profesor(2, "adsad","asda", 545);
    //u.crear()
    public void crear() {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("INSERT INTO profesores (nombre,correo,edad) "
                    + "VALUES ('" + this.nombre + "','" + this.correo + "','" + this.edad + "');");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    //Profesor.consultarRegistros();
    public static ArrayList<Profesor> consultarRegistros() {

        ArrayList<Profesor> profesores = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM profesores");
            while (result.next()) {
                long id = Long.parseLong(result.getString(1));
                String nombre = result.getString(2);
                String correo = result.getString(3);
                int edad = result.getInt("edad");
               
             
                profesores.add(new Profesor(id, nombre, correo, edad));
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesores;
    }
    
   

    //Profesor.leerProfesor(4);
    public static Profesor leerProfesor(long identificador) {

        Profesor profesor = null;

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("select * from profesores where id= " + identificador);
            while (result.next()) {
                long id = result.getInt("id");
                String nombre = result.getString("nombre");
                String correo = result.getString("correo");
                int edad = result.getInt("edad");
               
             
                profesor = new Profesor(id, nombre, correo, edad);
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return profesor;
    }

    //Profesor.actualizar(2, "nombre2222", "correo222", 10);
    public static void actualizar(long id, String nombre, String correo, int edad) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("UPDATE profesores SET nombre='" + nombre +  "',"
                    + "correo='" + correo + "',edad= '" + edad+  "' WHERE id= '" + id + "';");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Profesor.borrar(7);
    public static void borrar(long id) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("delete from profesores where id=" + id + ";");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
        
}