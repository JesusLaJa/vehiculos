/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDConeccion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author pc
 */
public class Conexion {

    public Connection con;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            this.con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

            System.out.println("Coneccion exitosa");

        } catch (Exception e) {

            System.err.println("No se a podido establecer conección con la base de datos" + e); 

        }
        return this.con;
    }
}
