/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BDConeccion.Conexion;
import Entidad.Marca;
import Entidad.Modelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author pc
 */
public class MarcaDAO extends Conexion {

    public PreparedStatement st;

    public int registrar(Marca marca) throws Exception {

        //Se inicializa la conceccion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_AGREGAR_MARCA(?,?)");
        //se registra el parametro de salida 
        myCall.registerOutParameter("PIDMARCA", java.sql.Types.INTEGER);
        //Se asigna los valores a los parametros
        myCall.setString("PNOMBRE", marca.getNombre());
        //Se ejecuta la llamada
        myCall.execute();
        //se obtiene el id de registtro insertado
        marca.setIdMarca(myCall.getInt("PIDMARCA"));
        //se retorna el id
        return marca.getIdMarca();
    }

    public List<Marca> listar() throws Exception {
        //Se inicializa la coneccion
        this.con = this.conectar();

        //Se crea un nuevo objeto de tipo ArrayList
        List<Marca> listarMarca = new ArrayList<>();

        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_MARCA()");
        //Se ejecuta la llamada 
        myCall.execute();
        //Se le asigna un valor al ResultSet
        ResultSet rs = myCall.getResultSet();

        //Se crea un while para obtener las columnas y añadir los registros
        while (rs.next()) {
            //Se instancia Marca
            Marca mar = new Marca();

            //Se obtienen las columnas
            mar.setIdMarca(rs.getInt("IDMARCA"));
            mar.setNombre(rs.getString("NOMBRE"));
            //se añaden los registros a la tabla
            listarMarca.add(mar);

        }

        //se retorna la lista
        return listarMarca;
    }

    public void modificar(Marca marca) throws Exception {

        //Se instancia la conceccion
        this.con = this.conectar();
        //Se instancia marca
        Marca mar = marca;
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ACTUALIZAR_MARCA(?,?)");
        //Se le asigna los valores a los parametros
        myCall.setInt("PIDMARCA", mar.getIdMarca());
        myCall.setString("PNOMBRE", mar.getNombre());
        //Se ejecuta la llamada
        myCall.executeUpdate();
    }

    public void eliminar(int pIdMarca) throws Exception {

        //Se instancia la conceccion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ELIMINAR_MARCA(?)");
        //Se asigana valor a los parametros
        myCall.setInt("PIDMARCA", pIdMarca);
        //Se ejecuta la llamada 
        myCall.execute();

    }

    public List<Modelo> listarModelos(int pIdMarca) throws Exception {

        //Se obtiene la coneccion de la clase Conexion 
        this.con = this.conectar();
        //Se crea una nueva lista de tipo Modelo
        List<Modelo> listarModelo = new ArrayList<>();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_MODELOS_MARCA(?)");
        myCall.setInt("PIDMARCA", pIdMarca);
        //se ejecuta la llamada
        myCall.execute();
        //se le asigna un valor al ResultSet
        ResultSet rs = myCall.getResultSet();

        //se crea un while para obtener las columnas de la tabla
        while (rs.next()) {
            //se instancia modelo
            Modelo modelo = new Modelo();
            modelo.setIdModelo(rs.getInt("IDMODELO"));
            modelo.setIdMarca(rs.getInt("IDMARCA"));
            modelo.setNombre(rs.getString("NOMBRE"));
            modelo.setModelo(rs.getString("MODELO"));
            modelo.setAnio(rs.getInt("ANIO"));
            modelo.setPrecio(rs.getInt("PRECIO"));
            //se añaden los registros a la lista
            listarModelo.add(modelo);
        }
        //retornamos la lista
        return listarModelo;
    }

    public Vector<Marca> mostrarMarcas() throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion cn = new Conexion();
        Connection con = cn.conectar();

        Vector<Marca> datos = new Vector<Marca>();

        String sql = "call USP_SELECCIONAR_MARCA";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setIdMarca(rs.getInt("IDMARCA"));
                marca.setNombre(rs.getString("NOMBRE"));
                datos.add(marca);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return datos;
    }
}
