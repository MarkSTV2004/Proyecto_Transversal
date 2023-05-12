package Modelo;

import java.time.LocalDate;

public class Alumno {
    private int id_alumno = -1;
    private int dni;
    private String apellido;
    private String nombre;
    private LocalDate fecha_nacimiento;
    private boolean estado;

    //Constructores
        public Alumno(int id_alumno, int dni, String nombre, String apellido, LocalDate fecha_nacimiento, boolean estado) {
            this.id_alumno = id_alumno;
            this.dni = dni;
            this.apellido = apellido;
            this.nombre = nombre;
            this.fecha_nacimiento = fecha_nacimiento;
            this.estado = estado;
        }
        
        public Alumno(int dni, String nombre, String apellido, LocalDate fecha_nacimiento, boolean estado) {
            this.dni = dni;
            this.apellido = apellido;
            this.nombre = nombre;
            this.fecha_nacimiento = fecha_nacimiento;
            this.estado = estado;
        }

        public Alumno() {
        }

    //Getters
        public int getId_alumno() {
            return id_alumno;
        }
        
        public int getDni() {
            return dni;
        }
        
        public String getApellido() {
            return apellido;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public LocalDate getFecha_nacimiento() {
            return fecha_nacimiento;
        }
        
        public boolean getEstado() {
            return estado;
        }
    
    //Setters
        public void setId_alumno(int id_alumno) {
            this.id_alumno = id_alumno;
        }

        public void setDni(int dni) {
            this.dni = dni;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
            this.fecha_nacimiento = fecha_nacimiento;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }

    //Metodos Extras
        @Override
        public String toString() {
            return "Alumno{" + "id_alumno=" + id_alumno + ", dni=" + dni + ", apellido=" + apellido + ", nombre=" + nombre + ", fecha_nacimiento=" + fecha_nacimiento + ", estado=" + estado + '}';
        }

}
