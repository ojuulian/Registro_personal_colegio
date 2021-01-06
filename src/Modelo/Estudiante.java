package Modelo;


import Controlador.Conexion;
import Modelo.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Estudiante {

    private long id;
    private String nombre;
    private String celular;
    private int edad;
    private ArrayList<Curso> cursos;

    public Estudiante(long id, String nombre, String celular, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.edad = edad;
    }

    public Estudiante(String nombre, String celular, int edad) {
        this.nombre = nombre;
        this.celular = celular;
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

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
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
    
    
    //Estudiante u = new Estudiante(44, "nomsabre", "897886668", 34);
    //u.crear();
    public void crear() {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("INSERT INTO estudiantes (nombre,celular,edad) "
                    + "VALUES ('" + this.nombre + "','" + this.celular+ "','" + this.edad + "');");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    //Estudiante.consultarRegistros();
    public static ArrayList<Estudiante> consultarRegistros() {

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM estudiantes");
            while (result.next()) {
                long id = Long.parseLong(result.getString(1));
                String nombre = result.getString(2);
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiantes.add(new Estudiante(id, nombre, celular, edad));
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estudiantes;
    }
    
    
    
    //Estudiante.leerEstudiante(4);
    public static Estudiante leerEstudiante(long identificador) {

        Estudiante estudiante = null;

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM estudiantes WHERE id= " + identificador);
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiante = new Estudiante(id, nombre, celular, edad);
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }

    
    //Estudiante.actualizar(2, "nombre2222", "3423243", 10);
    public static void actualizar(long id, String nombre, String celular, int edad) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("UPDATE estudiantes SET nombre='" + nombre +  "',"
                    + "celular='" + celular + "',edad='" + edad+  "' WHERE id='" + id + "';");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
     //Estudiante.borrar(7);
    public static void borrar(long id) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("DELETE FROM estudiantes WHERE id=" + id + ";");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    
    
    
     //

    public static void agregarCurso(int idEstudiante, int idCurso)  {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("INSERT INTO curso_estudiante (id_curso, id_estudiante) "
                    + "VALUES ('" + idCurso + "','" + idEstudiante + "');");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
 
    
    public static ArrayList<Estudiante> extraerEstudianteByProfesor(long identificador) {

        ArrayList<Estudiante> estudiante = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            String query = "SELECT DISTINCT a.id, a.nombre, a.celular, a.edad FROM estudiantes AS a JOIN curso_estudiante AS b ON a.id=b.id_estudiante JOIN cursos AS c ON b.id_curso=c.id WHERE c.profesor='" +identificador+ "';"; 
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                long id = Long.parseLong(result.getString(1));
                String nombre = result.getString(2);
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiante.add(new Estudiante(id, nombre, celular, edad));
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }
      
      
        
  /* 
    public static Estudiante extraerEstudianteByProfesor(long identificador) {

        Estudiante estudiante = null;

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            String query = "SELECT DISTINCT id, nombre, celular, edad FROM estudiantes AS a JOIN curso_estudiante AS b ON a.id=b.id_estudiante JOIN cursos AS c ON b.id_curso=c.id WHERE c.profesor='" +identificador+ "';"; 
            ResultSet result = stmt.executeQuery(query);
            
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiante = new Estudiante(id, nombre, celular, edad);
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }
    
*/    
    
    
    public static ArrayList<Estudiante> extraerEstudianteByCurso(long identificador) {

        ArrayList<Estudiante> estudiante = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            String query = "SELECT DISTINCT a.id, a.nombre, a.celular, a.edad FROM estudiantes AS a JOIN curso_estudiante AS b ON a.id=b.id_estudiante JOIN cursos AS c ON b.id_curso=c.id WHERE c.id='" +identificador+ "';"; 
            ResultSet result = stmt.executeQuery(query);
            
            while (result.next()) {
                long id = Long.parseLong(result.getString(1));
                String nombre = result.getString(2);
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiante.add(new Estudiante(id, nombre, celular, edad));
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }
    
    
    
    
    
    
    
    
   /* 
    
    public static Estudiante extraerEstudianteByCurso(long identificador) {

        Estudiante estudiante = null;

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            String query = "SELECT DISTINCT id, nombre, celular, edad FROM estudiantes AS a JOIN curso_estudiante AS b ON a.id=b.id_estudiante JOIN cursos AS c ON b.id_curso=c.id WHERE c.id='" +identificador+ "';"; 
            ResultSet result = stmt.executeQuery(query);
            
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String celular = result.getString("celular");
                int edad = result.getInt("edad");
               
             
                estudiante = new Estudiante(id, nombre, celular, edad);
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        return estudiante;
    }
    
    */


}