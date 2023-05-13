package Controlador;

import Conexion.Conexion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MateriaData {
    
    private final Connection con;
    
    public MateriaData (){
        con = Conexion.getConexion();
    }
    
    public void guardarMateria( Materia materia ){
        
        String query = "INSERT INTO materia (nombre, anio, estado) VALUES (?, ?, ?)";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
            // numeracion corresponde a ? ? ?
            //Recibe parametros del objeto creado en Universidad.java
            stmt.setString( 1, materia.getNombre() );
            stmt.setInt( 2 , materia.getAnio() );
            stmt.setBoolean( 3 , materia.getEstado() );
            
            //Ejecuta la consuta
            stmt.executeUpdate();
            ResultSet resultado = stmt.getGeneratedKeys();
            
            //Resultado.next() - obtiene el id siguiente
            if( resultado.next() ) {
                // 1 = posicion en db
                materia.setId_materia( resultado.getInt( 1 ) ); 
            }

            JOptionPane.showMessageDialog(null, "Materia guardada con exito.");
            stmt.close();
        }
        
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al guardar el Alumno: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.guardarMateria()
    
    
    public void actualizarMateria ( Materia materia ){
        
        String query = "UPDATE materia SET nombre = ?, anio = ? , estado = ? WHERE id_materia = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setString( 1, materia.getNombre() );
            stmt.setInt( 2, materia.getAnio() );
            stmt.setBoolean( 3, materia.getEstado() );
            stmt.setInt( 4, materia.getId_materia() );
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro actualizado.");
            stmt.close();
        }
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al actualizar registro: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.actualizarMateria()
    
    
    public Materia buscarMateria ( int id_materia ){
        Materia materiaN = null;
        
        String query = "SELECT nombre, anio, estado FROM materia WHERE id_materia = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, id_materia );
            
            ResultSet resultado = stmt.executeQuery();
            
            if( resultado.next() ){
                materiaN = new Materia();
                materiaN.setId_materia ( id_materia );
                materiaN.setNombre(resultado.getString("nombre"));
                materiaN.setAnio(resultado.getInt("anio"));
                materiaN.setEstado(resultado.getBoolean("estado"));
                
                String estado = (resultado.getBoolean("estado") != false) ? "Habilitado" : "Deshabilitado";
                
                JOptionPane.showMessageDialog(null, 
                        "Datos de la materia \n" + 
                        "ID: " + id_materia + 
                        "\nNOMBRE: " + resultado.getString("nombre") + 
                        "\nAÑO: " + resultado.getInt("anio") +
                        "\nESTADO: " + estado
                );
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro la materia solicitada.");
                System.out.println("No se encontro la materia solicitada.");
            }
            
            stmt.close();
        }
        catch ( SQLException ex ){
            JOptionPane.showMessageDialog(null, "ERROR al buscar la materia: " + ex);
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
        return materiaN;
    }//.buscarMateria()
    
    public void eliminarMateria ( int id_materia ){
        String query = "SELECT id_materia FROM materia WHERE id_materia = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, id_materia);
            ResultSet resultado = stmt.executeQuery();
            
            if ( resultado.next() ) {
                String query_delete = "UPDATE materia SET estado = false WHERE id_materia = ?";
                PreparedStatement stmt_delete = con.prepareStatement( query_delete );
                stmt_delete.setInt( 1, id_materia);
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
    }//.eliminarMateria()
    
    
    public void habilitarMateria ( int id_materia ){
        String query = "SELECT id_materia FROM materia WHERE id_materia = ?";
        
        try{
            PreparedStatement stmt = con.prepareStatement( query );
            stmt.setInt( 1, id_materia);
            ResultSet resultado = stmt.executeQuery();
            
            if ( resultado.next() ) {
                String query_delete = "UPDATE materia SET estado = true WHERE id_materia = ?";
                PreparedStatement stmt_delete = con.prepareStatement( query_delete );
                stmt_delete.setInt( 1, id_materia);
                stmt_delete.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Materia habilitada.");
            }else{
                JOptionPane.showMessageDialog(null, "ID ingresado invalido");
            }
            stmt.close();            
        }
        catch ( SQLException ex ){
            Logger.getLogger( AlumnoData.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }//.eliminarMateria()
    
}
