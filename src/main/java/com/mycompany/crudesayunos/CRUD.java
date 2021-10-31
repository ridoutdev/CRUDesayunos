package com.mycompany.crudesayunos;
/**/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import models.Pedidos;
import models.Productos;
/**
 ** @author Ridouan Abdellah Tieb
 *
 */
public class CRUD {

    /*
    Creo la conversion de la fecha de java.sql.Date a java.util.Date
     */
    java.util.Date utilDate = new java.util.Date(); //Esto es la fecha actual.
    long lnMilisegundos = utilDate.getTime();
    java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

    /*
    Creo la variable estática "con" del tipo Connection. Será utilizada para 
    crear la conexión.
     */
    private static Connection con;

    /*
    Creo tres variables del tipo String que contendrán los datos necesarios para
    crear la conexión. Además el try solo se va a ejecutar una vez. 
     */
    static {
        String url = "jdbc:mysql://localhost:3306/acceso";
        String user = "root";
        String pass = "";

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
        }

        System.out.println("Se ha establecido la conexión");
        System.out.println("\n");
    }

    /*
    Declaro las constantes estaticas y finales, se suelen escribir
    en mayuscula y separada por guión bajo. En ellas crearé las consultas SQL.  
     */
    static final String CREAR_PEDIDO = "INSERT INTO pedidos (usuario, "
            + "productos_id, fecha, ciclo, estado) VALUES (?,?,?,?,?)";
    static final String ELIMINAR_PEDIDO = "DELETE FROM pedidos WHERE id=?";
    static final String LISTAR_PRODUCTOS = "SELECT * FROM productos";
    static final String LISTAR_PEDIDOS = "SELECT * FROM pedidos";
    static final String ACTUALIZAR_ESTADO_PEDIDO = "UPDATE pedidos SET estado=1 "
            + "WHERE id=?";
    static final String LISTAR_PEDIDOS_HOY = "SELECT * FROM pedidos "
            + "WHERE fecha=CURDATE() AND estado=0";

    /*
    Creo una función crear que será la que permita crear un pedido. Le entrará 
    un parámetro del tipo Pedidos.
     */
    public Integer crear(Pedidos p) {

        try ( PreparedStatement ps = con.prepareStatement(CREAR_PEDIDO,
                RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getUsuario());
            ps.setInt(2, p.getProductos_id());
            ps.setDate(3, sqlDate);
            ps.setString(4, p.getCiclo());
            ps.setInt(5, 0);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();

            if (keys.next()) {
                return keys.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException ex) {
        }
        return 0;
    }

    /*
    Creo una función listar_productos que será la que permita mostrar los 
    productos disponibles por pantalla antes de realizar el pedido. 
     */
    public ArrayList<Productos> listar_productos() {

        var salida = new ArrayList<Productos>();

        try ( Statement st = con.createStatement()) {

            ResultSet resultado = st.executeQuery(LISTAR_PRODUCTOS);

            while (resultado.next()) {
                Productos p = new Productos();
                p.setId(resultado.getInt("id"));
                p.setNombre(resultado.getString("nombre"));
                p.setPrecio(resultado.getFloat("precio"));
                p.setDisponible(resultado.getInt("disponible"));
                p.setTipo(resultado.getString("tipo"));
                salida.add(p);
            }
        } catch (SQLException ex) {
        }
        return salida;

    }

    /*
    Creo una función eliminar que será la encargada de eliminar los pedidos que
    le digamos. 
     */
    public boolean eliminar(Integer id) {

        try ( PreparedStatement ps = con.prepareStatement(ELIMINAR_PEDIDO)) {
            ps.setInt(1, id);
            return (ps.executeUpdate() == 1);
        } catch (SQLException ex) {
        }
        return false;
    }

    /*
    Creo una función listar_pedidos que será la que permita mostrar los pedidos 
    disponibles por pantalla antes de eliminar un pedido. 
     */
    public ArrayList<Pedidos> listar_pedidos() {

        var salida = new ArrayList<Pedidos>();

        try ( Statement st = con.createStatement()) {

            ResultSet resultado = st.executeQuery(LISTAR_PEDIDOS);

            while (resultado.next()) {
                Pedidos p = new Pedidos();
                p.setId(resultado.getInt("id"));
                p.setUsuario(resultado.getString("usuario"));
                p.setFecha(resultado.getDate("fecha"));
                p.setCiclo(resultado.getString("ciclo"));
                p.setEstado(resultado.getInt("estado"));
                salida.add(p);
            }
        } catch (SQLException ex) {
        }
        return salida;

    }

    /*
    Creo una función recoger que será la encargada de marcar si un pedido ha 
    sido recogido o esta en curso. 
     */
    public boolean actualizar(Integer id) {

        try (PreparedStatement ps=con.prepareStatement(ACTUALIZAR_ESTADO_PEDIDO)){
            ps.setInt(1, id);
            return (ps.executeUpdate() == 1);
        } catch (SQLException ex) {
        }
        return false;
    }

    /*
    Creo una función listar_pedidos_hoy que será la que permita mostrar los 
    realizados pedidos en el día actual.  
     */
    public ArrayList<Pedidos> listar_pedidos_hoy() {

        var salida = new ArrayList<Pedidos>();

        try ( Statement st = con.createStatement()) {

            ResultSet resultado = st.executeQuery(LISTAR_PEDIDOS_HOY);

            while (resultado.next()) {
                Pedidos p = new Pedidos();
                p.setId(resultado.getInt("id"));
                p.setUsuario(resultado.getString("usuario"));
                p.setFecha(resultado.getDate("fecha"));
                p.setCiclo(resultado.getString("ciclo"));
                p.setEstado(resultado.getInt("estado"));
                salida.add(p);
            }
        } catch (SQLException ex) {
        }
        return salida;
    }
}
