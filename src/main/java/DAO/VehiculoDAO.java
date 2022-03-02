/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BDConeccion.Conexion;
import Entidad.Vehiculo;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class VehiculoDAO extends Conexion {

    PreparedStatement st;

    /**
     * Agrega registros a la tabla Vehiculo
     *
     * @param vehiculo
     * @return 
     * @throws Exception
     */
    public int registrar(Vehiculo vehiculo) throws Exception {
        //se obtiene la coneccion de la clase Conexion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("{call USP_AGREGAR_VEHICULOS(?,?,?,?,?)}");
        //se registra el parametro de salida
        myCall.registerOutParameter("PIDVEHICULO", java.sql.Types.INTEGER);
        //Se le asigna valor a los parametros 
        myCall.setInt("PIDMODELO", vehiculo.getIdModelo());
        myCall.setInt("PIDCONCESIONARIO", vehiculo.getIdConcesionario());
        myCall.setString("PCOLOR", vehiculo.getColor());
        myCall.setString("PSERIE", vehiculo.getSerie());
        //Se ejecuta la llamada
        myCall.execute();
        //Obtenemos el id del registro insertado
        vehiculo.setId(myCall.getInt("PIDVEHICULO"));
        return vehiculo.getId();
    }

    /**
     * Obtiene una lista de vehiculos
     *
     * @return 
     * @throws Exception
     */
    public List<Vehiculo> listar() throws Exception {
        
        //Se obtiene le coneccion de la clase Conexion
        this.con = this.conectar();

        //se crea una nueva lista de tipo Vehiculo
        List<Vehiculo> lstVehiculos = new ArrayList<>();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("{call USP_SELECCIONAR_VEHICULO__MODELO_CON_NOMBRE()}");
        //se ejecuta la llamada 
        myCall.execute();
        //se le asigna valor al ResultSet
        ResultSet rs = myCall.getResultSet();
        while (rs.next()) {
            //Se crea un nuevo objeto de tipo Vehiculo
            Vehiculo vehiculo = new Vehiculo();
            //Se obtienen las columnas de la tabla Vehiculo
            vehiculo.setId(rs.getInt("IDVEHICULO"));
            vehiculo.setModelo(rs.getString("MODELO"));
            vehiculo.setConcesionario(rs.getString("NOMBRE"));
            vehiculo.setColor(rs.getString("COLOR"));
            vehiculo.setSerie(rs.getString("SERIE"));;

            //se añaden los registros a la lista
            lstVehiculos.add(vehiculo);
        }
        //se retorna la lista
        return lstVehiculos;
    }

    /**
     * Modifica los registros del ID ingresado de la tabla vehiculo
     *
     * @param vehiculo
     * @throws Exception
     */
    public void modificar(Vehiculo vehiculo) throws Exception {

        //Se obtiene la coneccion de la clase Conexion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ACTUALIZAR_VEHICULOS(?,?,?,?,?)");
        //se agrega valor a los parametros 
        myCall.setInt("PIDVEHICULO", vehiculo.getId());
        myCall.setInt("PIDMODELO", vehiculo.getIdModelo());
        myCall.setInt("PIDCONCESIONARIO", vehiculo.getIdConcesionario());
        myCall.setString("PCOLOR", vehiculo.getColor());
        myCall.setString("PSERIE", vehiculo.getSerie());
        //se ejecuta la llamada 
        myCall.executeUpdate();
    }

    /**
     *
     * @param pId 
     * @throws Exception
     */
    public void eliminar(int pId) throws Exception {

        //se obtiene la coneccion de la clase Conexion
        this.con = this.conectar();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ELIMINAR_VEHICULOS(?)");
        //se asigna valor a los parametros
        myCall.setInt("PIDVEHICULO", pId);        //se ejecuta la llamada al SP
        myCall.execute();
    }

}
