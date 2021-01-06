package Controlador;


import Vista.Dashboard;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);
        
        Dashboard d = new Dashboard();
        d.setVisible(true);
        
   
   /*     
        //Ingresando los 3 profesores
        for (int i = 0; i < 3; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            String correo = sc.nextLine();
            int edad = Integer.parseInt(sc.nextLine());
            
            Profesor p = new Profesor(id, nombre, correo, edad);
            //p.crearRegistro();
            p.crear();
        }
        
       
        //Ingresando los 5 estudiantes
        for (int i = 0; i < 5; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            String celular = sc.nextLine();
            int edad = Integer.parseInt(sc.nextLine());
            
            Estudiante e = new Estudiante(id, nombre, celular, edad);
            //p.crearRegistro();
            e.crear();
        }
       
       
   
         //Ingresando 4 cursos
        for (int i = 0; i < 4; i++) {
            long id = Long.parseLong(sc.nextLine());
            String nombre = sc.nextLine();
            int capacidad = Integer.parseInt(sc.nextLine());
            
            Curso c = new Curso(id, nombre, capacidad);
            //p.crearRegistro();
            c.crear();
        }
        

 

   
        //16 operaciones
        
        // 1--> Asigna profesor a curso
        //2 --> Asigna estudiante a curso
        for (int i = 0; i < 16; i++) {
            int operation = Integer.parseInt(sc.nextLine());
            String op = sc.nextLine();
            String[] ids = op.split(" - ");
            
            if (operation ==1){
                int idProfesor = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);
                
                Curso.agregarCurso(idCurso, idProfesor);
            }else{
                int idEstudiante = Integer.parseInt(ids[0]);
                int idCurso = Integer.parseInt(ids[1]);
                
                Estudiante.agregarCurso(idEstudiante, idCurso);
            }
            
        }
      


    
        //Rta de los cursos dado un idprofesor
        long idProfesor = Long.parseLong(sc.nextLine());
        ArrayList<Curso> cursos = Curso.extraerCursoByProfesor(idProfesor);
        
        for(Curso curso : cursos){
            System.out.println("Curso: "+curso.getId()+" - "+ curso.getNombre()+ " - " +curso.getCapacidad());    
        }


     
   
        //Rta de los estudiantes dado el idprofesor
        long idProfesor2 = Long.parseLong(sc.nextLine());
        
        ArrayList<Estudiante> estudiantess = Estudiante.extraerEstudianteByProfesor(idProfesor2);
        
        for(Estudiante estudiante : estudiantess){
            System.out.println("Estudiante: "+estudiante.getId()+" - "+ estudiante.getNombre() +" - "+ estudiante.getEdad());    
        }
          
     
       
    //Estudiante.extraerEstudianteByCurso(2);
                //rta de la lista de estudiantes dado un idcurso
        long  idCurso = Long.parseLong(sc.nextLine());
        ArrayList<Estudiante> estudiantes = Estudiante.extraerEstudianteByCurso(idCurso);
        
        for(Estudiante estudiante : estudiantes){
            System.out.println("Estudiante: "+estudiante.getId()+" - "+ estudiante.getNombre() +" - "+ estudiante.getEdad());    
        }

       
 */
   
   
    }
  
}