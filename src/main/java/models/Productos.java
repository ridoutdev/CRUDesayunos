package models;
/**/
import java.io.Serializable;
/**
 ** @author Ridouan Abdellah Tieb
 **/
public class Productos implements Serializable {
    /*
    Creo la clase Productos que contendrá todos los atributos de esa entidad y 
    que serán las columnas de la tabla. Creo el constructor vacío, constructor
    con parámetros, getters, setters y toString.
    */
    private int id;
    private float precio;
    private String nombre;
    private String tipo;
    private int disponible;

    public Productos() {}

    public Productos(int id, float precio, String nombre, String tipo, 
                 int disponible) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "PRODUCTOS [" + "ID: " + id + "; NOMBRE: " + nombre + "; PRECIO: " 
              + precio + "€; TIPO: " + tipo + "; DISPONIBLE: " + disponible + ']';
    }
    
}
