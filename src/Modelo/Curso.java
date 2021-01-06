package Modelo;


import Controlador.Conexion;
import Modelo.Profesor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Curso {

     
    private long id;
    private String nombre;
    private int capacidad;
    private Profesor profesor;
    private int profesor2;
    private ArrayList<Estudiante> estudiantes;

    public Curso(long id, String nombre, int capacidad, Profesor profesor) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }
    
    public Curso(long id, String nombre, int capacidad, int profesor2) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor2 = profesor2;
    }
    
    public Curso(long id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }

    public Curso(String nombre, int capacidad, Profesor profesor) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.profesor = profesor;
    }

    public Curso(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
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

    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Profesor getProfesor() {
        return profesor;
    }
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
    
      
        
    
     //Curso u = new Curso(1, "curso1", 1);
        //u.crear();
    
     public void crear() {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("INSERT INTO cursos (nombre,capacidad,profesor)"
                    + "VALUES ('" + this.nombre + "','" + this.capacidad + "','');");
                                                        //   + "','" + this.profesor.getId()
            stmt.close();     
            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
     
     //Curso.consultarRegistros();
    public static ArrayList<Curso> consultarRegistros() {

        ArrayList<Curso> cursos = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM cursos");
            while (result.next()) {
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                int capacidad = result.getInt("capacidad");
               
             
                cursos.add(new Curso(id, nombre, capacidad));
            }
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }
  
     
     
     
     
    
 //Curso.leerCurso(4);
    public static Curso leerCurso(long identificador) {

        Curso curso = null;

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM cursos WHERE id= " + identificador);
            
                long id = result.getLong("id");
                String nombre = result.getString("nombre");
                int capacidad = result.getInt("capacidad");
                      
             
                curso = new Curso(id, nombre, capacidad);
           
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return curso;
    }
     
   
    
    //Curso.actualizar(2, "nombre2222", 454, 1);
    public static void actualizar(long id, String nombre, int capacidad, int profesor) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("UPDATE cursos SET nombre='" + nombre +  "',"
                    + "capacidad=" + capacidad + ",profesor=" + profesor +  " where id=" + id + ";");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     //Curso.borrar(1);
    public static void borrar(long id) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("DELETE FROM cursos WHERE id=" + id + ";");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
       
         //Curso.actualizar(2, "nombre2222", 454, 1);
    public static void agregarCurso(int id, int idprofesor) {
        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            stmt.execute("UPDATE cursos SET profesor='" +idprofesor+ "' WHERE id ='" +id+ "';");
            //ResultSet result = stmt.executeQuery("SELECT * FROM db_test.usuarios");
            stmt.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   public static ArrayList<Curso> extraerCursoByProfesor(long identificador) {

        ArrayList<Curso> curso = new ArrayList<>();

        try {
            Conexion conexion = new Conexion("colegio.db");
            conexion.conectar();

            Statement stmt = conexion.getConexion().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM cursos WHERE profesor= '" + identificador+ "';");
            while (result.next()) {
                long id = Long.parseLong(result.getString(1));
                String nombre = result.getString(2);
                int capacidad = result.getInt("capacidad");
                //int edad = result.getInt("edad");
             
                curso.add(new Curso(id, nombre, capacidad));
            }    
            result.close();

            conexion.cerrarConexion();
        } catch (SQLException ex) {
            Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return curso;
    } 
    
  
   
   
}