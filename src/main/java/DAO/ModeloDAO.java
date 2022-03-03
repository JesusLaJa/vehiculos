/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BDConeccion.Conexion;
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
public class ModeloDAO extends Conexion {

    PreparedStatement st;

    /**
     * Agrega registros a la tabla modelo
     *
     * @param modelo
     * @throws Exception
     */
    public int registrar(Modelo modelo) throws Exception {

        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Conexion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_AGREGAR_MODELOS(?,?,?,?,?)");
        //se registra el parametro de salida
        myCall.registerOutParameter("PIDMODELO", java.sql.Types.INTEGER);
        //Se asigna valor a los parametros
        myCall.setInt("PIDMARCA", modelo.getIdMarca());
        myCall.setString("PMODELO", modelo.getModelo());
        myCall.setInt("PANIO", modelo.getAnio());
        myCall.setInt("PPRECIO", modelo.getPrecio());
        //Se ejecuta la llamada 
        myCall.execute();
        //se obtiene el id del registro insertado
        modelo.setIdModelo(myCall.getInt("PIDMODELO"));
        //se retorna el id
        return modelo.getIdModelo();
    }

    /**
     * Obtiene una lista de la tabla Modelo
     *
     * @return
     * @throws Exception
     */
    public List<Modelo> listar() throws Exception {

        //Se obtiene la coneccion de la clase Conexion 
        this.con = this.conectar();
        //Se crea una nueva lista de tipo Modelo
        List<Modelo> listarModelo = new ArrayList<>();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_MODELOS_MARCA_NOMBRE()");
        //se ejecuta la llamada
        myCall.execute();
        //se le asigna un valor al ResultSet
        ResultSet rs = myCall.getResultSet();

        //se crea un while para obtener las columnas de la tabla
        while (rs.next()) {
            //se instancia modelo
            Modelo modelo = new Modelo();
            //se obtienen las columnas de la tabla MODELO
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

    /**
     * Modifica el registro del id ingresado de tabla Modelo
     *
     * @param modelo
     * @throws Exception
     */
    public void modificar(Modelo modelo) throws Exception {

        //Se obtiene la coneccion de la clase Conexion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ACTUALIZAR_MODELOS(?,?,?,?,?)");
        //Se asigna valor a los parametros
        myCall.setInt("PIDMODELO", modelo.getIdModelo());
        myCall.setInt("PIDMARCA", modelo.getIdMarca());
        myCall.setString("PMODELO", modelo.getModelo());
        myCall.setInt("PANIO", modelo.getAnio());
        myCall.setInt("PPRECIO", modelo.getPrecio());
        //Se ejecuta la llamada 
        myCall.executeUpdate();

    }

    public int getExisteModeloMarca(int pIdMarca, String pModelo,int pAnio) throws SQLException {

        this.con = this.conectar();

        int nModelo = 0;

        CallableStatement myCall = con.prepareCall("call USP_EXISTE_MODELO(?,?,?)");
        myCall.setInt("PIDMARCA", pIdMarca);
        myCall.setString("PMODELO", pModelo);
        myCall.setInt("PANIO", pAnio);
        myCall.execute();

        ResultSet rs = myCall.getResultSet();

        if (rs.next()) {
            nModelo = rs.getInt("NMODELO");
        }

        return nModelo;
    }

    public void eliminar(int pIdModelo) throws Exception {

        //Se obtiene la coneciion de la clase Conexion
        this.con = this.conectar();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ELIMINAR_MODELOS(?)");
        //se asigna valor al parametro
        myCall.setInt("PIDMODELO", pIdModelo);
        //se ejecuta la llamada
        myCall.execute();
    }

    public Vector<Modelo> mostrarModelos() throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion cn = new Conexion();
        Connection con = cn.conectar();

        Vector<Modelo> datos = new Vector<Modelo>();
        Modelo modelo = null;

        String sql = "call USP_SELECCIONAR_MODELOS";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                modelo = new Modelo();
                modelo.setIdModelo(rs.getInt("IDMODELO"));
                modelo.setModelo(rs.getString("MODELO"));
                datos.add(modelo);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return datos;
    }

}
