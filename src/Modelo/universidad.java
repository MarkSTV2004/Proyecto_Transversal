
package Modelo;
import Controlador.*;
import java.time.LocalDate;


public class universidad {
    public static void main(String[] args) {

    // <--- Create --->
        /*
        Alumno alumno1 = new Alumno(32412345, "Godoy", "Bernardo", LocalDate.of(1998, 05, 23), true);
        AlumnoData alumno1_Data = new AlumnoData();
        alumno1_Data.guardarAlumno(alumno1);
        */
        
    // <--- Read --->
        /*
        AlumnoData alumno1_Data = new AlumnoData();
        alumno1_Data.buscarAlumno(2);
       */
        
    // <--- Update --->
        /*
        Alumno alumno1 = new Alumno(1, 32412345, "Godoy", "Bernardo", LocalDate.of(1998, 05, 23), true);
        AlumnoData alumno1_Data = new AlumnoData();
        alumno1_Data.actualizarAlumno(alumno1);
        */
        
    // <--- Delete --->
        /*
        Alumno alumno1 = new Alumno ();
        AlumnoData alumno1_Data = new AlumnoData();
        alumno1_Data.eliminarAlumno(2);
        */
        
        
    // <--- Habilitar --->
        /**/
        Alumno alumno1 = new Alumno ();
        AlumnoData alumno1_Data = new AlumnoData();
        alumno1_Data.habilitarAlumno(2);
        /**/

        
    }
}
