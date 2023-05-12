package Modelo;

public class Materia {
    private int id_materia;
    private String nombre;
    private int anio;
    private boolean estado;

    //Constructores
        public Materia(int id_materia, String nombre, int anio, boolean estado) {
            this.id_materia = id_materia;
            this.nombre = nombre;
            this.anio = anio;
            this.estado = estado;
        }

        public Materia(String nombre, int anio, boolean estado) {
            this.nombre = nombre;
            this.anio = anio;
            this.estado = estado;
        }

        public Materia() {
        }
        
    //Getters
        public int getId_materia() {
            return id_materia;
        }
        
        public String getNombre() {
            return nombre;
        }
        
        public int getAnio() {
            return anio;
        }
        
        public boolean getEstado() {
            return estado;
        }
        
    //Setters
        public void setId_materia(int id_materia) {
            this.id_materia = id_materia;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setAnio(int anio) {
            this.anio = anio;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }
        
    //Metodos Extras

        @Override
        public String toString() {
            return "Materia{" + "id_materia=" + id_materia + ", nombre=" + nombre + ", anio=" + anio + ", estado=" + estado + '}';
        }
        
}
