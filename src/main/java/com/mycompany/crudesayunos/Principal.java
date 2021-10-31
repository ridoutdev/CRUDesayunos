package com.mycompany.crudesayunos;
/**/
import java.util.Scanner;
import models.Pedidos;
/**
 ** @author Ridouan Abdellah Tieb
 *
 */
public class Principal {

    public static void main(String[] args) {

        /*
        Creo la conversion de la fecha de java.sql.Date a java.util.Date
         */
        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

        /*
        Creo la clase Scanner para introducir datos por teclado. Creo el menú 
        del programa con un while y un switch donde ocurriran los eventos. 
         */
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        System.out.println("BIENVENIDO A COMANDAS 1.0");

        System.out.println("\n¿QUE DESEA HACER?");

        while (opcion != 5) {

            System.out.println("\n1. CREAR UN NUEVO PEDIDO");
            System.out.println("\n2. ELIMINAR UN PEDIDO EXISTENTE");
            System.out.println("\n3. MARCAR UN PEDIDO COMO RECOGIDO");
            System.out.println("\n4. LISTAR LAS COMANDAS PENDIENTES DE HOY");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    /*
                    Primero listo la carta de productos y después creo un pedido
                    con los datos introducidos por teclado. 
                    */
                    var comanda = new CRUD();
                    var productos = comanda.listar_productos();
                    System.out.println("LA DISPONIBILIDAD DEL PRODUCTO VIENE "
                      + "REFLEJADA DE LA SIGUIENTE MANERA: 0 = NO DISPONIBLE Y "
                      + "1 = DISPONIBLE\n\n");
                    productos.forEach((pro) -> System.out.println(pro));
                    
                    System.out.println("\nINTRODUZCA EL NOMBRE DEL ALUMNO");
                    String nombre = sc.next();
                    
                    System.out.println("\nINTRODUZCA EL MÓDULO DEL ALUMNO");
                    String modulo = sc.next();
                    
                    System.out.println("\nINTRODUZCA EL ID DEL PRODUCTO");
                    int producto = sc.nextInt();
                    
                    Pedidos p = new Pedidos(1, nombre, producto, sqlDate, 
                                            modulo, 0);
                    comanda.crear(p);
                    break;
                case 2:
                    /*
                    Primero listo la lista de pedidos totales y luego elimino
                    el pedido seleccionado mediante el ID, usando el método
                    eliminar de la clase CRUD
                    */
                    comanda = new CRUD();
                    var pedidos = comanda.listar_pedidos();
                    System.out.println("EL ESTADO DEL PEDIDO VIENE REFLEJADO DE "
                                   + "LA SIGUIENTE MANERA: 0 = PENDIENTE Y "
                                   + "1 = RECOGIDO\n\n");
                    pedidos.forEach((pro) -> System.out.println(pro));
                    
                    System.out.println("INDIQUE EL ID DEL PEDIDO A ELIMINAR");
                    int elimino = sc.nextInt();
                    comanda.eliminar(elimino);
                    break;
                case 3:
                    /*
                    Primero listo la lista de pedidos totales y luego le cambio
                    el estado seleccionado mediante el ID, usando método 
                    actualizar de la clase CRUD.
                    */
                    comanda = new CRUD();
                    pedidos = comanda.listar_pedidos();
                    System.out.println("EL ESTADO DEL PEDIDO VIENE REFLEJADO DE "
                                        + "LA SIGUIENTE MANERA: 0 = PENDIENTE Y "
                                        + "1 = RECOGIDO\n\n");
                    pedidos.forEach((ped) -> System.out.println(ped));
                    System.out.println("INDIQUE EL ID DEL PEDIDO QUE QUIERE "
                                        + "MARCAR COMO RECOGIDO");
                    int recojo = sc.nextInt();
                    comanda.actualizar(recojo);
                    break;
                case 4:
                    /*
                    Usando el método listar_pedidos_hoy, consigo listar los 
                    pedidos pendientes del día actual, utilizo una consulta 
                    SELECT y las condiciones de la fecha y el estado, con el 
                    método Curdate() y el valor=1; 
                    */
                    comanda = new CRUD();
                    pedidos = comanda.listar_pedidos_hoy();
                    System.out.println("EL ESTADO DEL PEDIDO VIENE REFLEJADO DE "
                                        + "LA SIGUIENTE MANERA: 0 = EN CURSO Y "
                                        + "1 = RECOGIDO\n\n");
                    pedidos.forEach((ped) -> System.out.println(ped));
                    break;
            }
        }

    }

}
