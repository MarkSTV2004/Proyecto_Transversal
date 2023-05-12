package Controlador;

import Conexion.Conexion;
import Modelo.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class AlumnoData {

    private final Connection con;
    
    public AlumnoData(){
        con = Conexion.getConexion();
    }

    public void guardarAlumno( Alumno alumno ){
        
        String query = "INSERT INTO alumno (dni, apellido, nombre, fecha_nacimiento, estado) VALUES (?, ?, ?, ?, ?)";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
            // numeracion corresponde a ? ? ?
            stmt.setInt( 1, alumno.getDni() );
            stmt.setString( 2, alumno.getApellido() );
            stmt.setString( 3, alumno.getNombre() );
            stmt.setDate( 4, Date.valueOf( alumno.getFecha_nacimiento() ) );
            stmt.setBoolean( 5, alumno.getEstado() );
            
            stmt.executeUpdate();
            
            ResultSet resultado = stmt.getGeneratedKeys();
            
            if( resultado.next() ) {
                // 1 = posicion en db
                alumno.setId_alumno(resultado.getInt( 1 )); 
            }

            JOptionPane.showMessageDialog(null, "Alumno guardado con exito");
            stmt.close();
        }
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al guardar el Alumno: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.guardarAlumno()
    
    
    public void actualizarAlumno ( Alumno alumno ){
        
        String query = "UPDATE alumno SET dni = ?, apellido = ? , nombre = ? , fecha_nacimiento = ?  WHERE id_alumno = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, alumno.getDni() );
            stmt.setString( 2, alumno.getApellido() );
            stmt.setString( 3, alumno.getNombre() );
            stmt.setDate( 4, Date.valueOf( alumno.getFecha_nacimiento() ) );
            stmt.setInt( 5, alumno.getId_alumno() );
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro actualizado.");
            stmt.close();
        }
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al actualizar registro: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.actualizarAlumno()
    
    
    public Alumno buscarAlumno ( int id_alumno ){
        Alumno alumnoN = null;
        
        String query = "SELECT dni, apellido, nombre, fecha_nacimiento, estado FROM alumno WHERE id_alumno = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, id_alumno );
            
            ResultSet resultado = stmt.executeQuery();
            
            if( resultado.next() ){
                alumnoN = new Alumno();
                alumnoN.setId_alumno( id_alumno );
                alumnoN.setDni(resultado.getInt("dni"));
                alumnoN.setApellido(resultado.getString("apellido"));
                alumnoN.setNombre(resultado.getString("nombre"));
                alumnoN.setFecha_nacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                alumnoN.setEstado(resultado.getBoolean("estado"));
                
                String estado = (resultado.getBoolean("estado") != false) ? "Habilitado" : "Deshabilitado";
                
                JOptionPane.showMessageDialog(null, 
                        "Datos del alumno \n" + 
                        "ID: " + id_alumno + 
                        "\nDNI: " + resultado.getInt("dni") + 
                        "\nAPELLIDO: " + resultado.getString("apellido") +
                        "\nNOMBRE: " + resultado.getString("nombre") + 
                        "\nFECHA DE NACIMIENTO: " + resultado.getDate("fecha_nacimiento").toLocalDate() +
                        "\nESTADO: " + estado
                );
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el alumno solicitado.");
                System.out.println("No se encontro el alumno solicitado.");
            }
            
            stmt.close();
        }
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al buscar alumno: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        return alumnoN;
    }//.buscarAlumno()
    
    
    public void eliminarAlumno ( int id_alumno ){
        String query = "SELECT id_alumno FROM alumno WHERE id_alumno = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, id_alumno);
            ResultSet resultado = stmt.executeQuery();
            
            if ( resultado.next() ){
                String query_delete = "UPDATE alumno SET estado = false WHERE id_alumno = ?";
                PreparedStatement stmt_delete = con.prepareStatement( query_delete );
                stmt_delete.setInt( 1, id_alumno);
                stmt_delete.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Registro eliminado");
            }else{
                JOptionPane.showMessageDialog(null, "ID ingresado invalido");
            }
            
            

            
            
            stmt.close();
            
        }
        catch ( SQLException ex ){
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.eliminarAlumno()
    
}
