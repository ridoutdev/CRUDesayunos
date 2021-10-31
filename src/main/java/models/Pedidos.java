package models;
/**/
import java.io.Serializable;
import java.sql.Date;
/**/
/**
 ** @author Ridouan Abdellah Tieb
 **/
public class Pedidos implements Serializable {
    /*
    Creo la clase Pedidos que contendrá todos los atributos de esa entidad y que
    serán las columnas de la tabla. Creo el constructor vacío, constructor 
    con parámetros, getters, setters y toString.
    */
    private int id;
    private int productos_id;
    private String usuario;
    private String ciclo;
    private Date fecha;
    private int estado; 
    
    public Pedidos() {}

    public Pedidos(int id, String usuario, int productos_id, Date fecha, 
                   String ciclo, int estado) {
        this.id = id;
        this.productos_id = productos_id;
        this.usuario = usuario;
        this.ciclo = ciclo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductos_id() {
        return productos_id;
    }

    public void setProductos_id(int productos_id) {
        this.productos_id = productos_id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PEDIDOS [" + "ID: " + id + "; USUARIO: " + usuario + "; CICLO: " + 
                ciclo + "; FECHA: " + fecha + "; ESTADO: " + estado + ']';
    }
    
    
}
