/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BDConeccion.Conexion;
import Entidad.Concesionario;
import Entidad.Marca;
import Entidad.Modelo;
import Entidad.Vehiculo;
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
public class ConcesionarioDAO extends Conexion {

    public PreparedStatement st;

    //Modificador de acceso-Nombre del metodo
    /**
     * Registra un concesionario en la tabla Concesionario
     *
     * @param concesionario
     * @throws Exception
     */
    public int registrar(Concesionario concesionario) throws Exception {
        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Coneccion
        this.con = this.conectar();
        //se prepara la llamada con el procedimiento 
        CallableStatement myCall = con.prepareCall("{call USP_AGREGAR_CONCESIONARIO(?,?,?)}");
        //se registra el parametro de salida
        myCall.registerOutParameter("PIDCONCESIONARIO", java.sql.Types.INTEGER);
        //se asigna un valor a los parametros
        myCall.setString("PNOMBRE", concesionario.getNombre());
        myCall.setString("PENTIDADFEDERATIVA", concesionario.getEntidadFederativa());
        //se ejecuta la llamada 
        myCall.execute();
        //obtenemos el id del resgistro insertado
        concesionario.setIdConcesionario(myCall.getInt("PIDCONCESIONARIO"));
        //se retorna el id 
        return concesionario.getIdConcesionario();
    }

    /**
     * Obtiene una lista de tipo Concesionario
     *
     * @return
     * @throws Exception
     */
    public List<Concesionario> listar() throws Exception {
        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Coneccion
        this.con = this.conectar();
        //se isntancia una lista
        List<Concesionario> listarConce = new ArrayList<>();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("{call USP_SELECCIONAR_CONCESIONARIO()}");
        //se ejecuta la llamada
        myCall.execute();
        //se le asigna un valor a ResultSet
        ResultSet rs = myCall.getResultSet();

        while (rs.next()) {
            //Se instancia Concesionario 
            Concesionario conce = new Concesionario();
            //se obtienen las columnas de la tabla
            conce.setIdConcesionario(rs.getInt("IDCONCESIONARIO"));
            conce.setNombre(rs.getString("NOMBRE"));
            conce.setEntidadFederativa(rs.getString("ENTIDADFEDERATIVA"));
            //se añaden los registros
            listarConce.add(conce);
        }
        return listarConce;
    }

    /**
     * Modifica los registros de la tabla Concesionario
     *
     * @param concesionario
     * @throws Exception
     */
    public void modificar(Concesionario concesionario) throws Exception {

        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Coneccion
        this.con = this.conectar();
        //Se prepara la llamada al procedimiento almacenado
        CallableStatement myCall = con.prepareCall("call USP_ACTUALIZAR_CONCESIONARIO(?,?,?)");
        //Se le asigna un valor a los parametros 
        myCall.setInt("PIDCONCESIONARIO", concesionario.getIdConcesionario());
        myCall.setString("PNOMBRE", concesionario.getNombre());
        myCall.setString("PENTIDADFEDERATIVA", concesionario.getEntidadFederativa());
        //Se ejecuta la llamada 
        myCall.executeUpdate();
    }

    /**
     * Elimina el registro del ID ingresado
     *
     * @param pIdConcesionario
     * @throws Exception
     */
    public void eliminar(int pIdConcesionario) throws Exception {

        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Coneccion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_ELIMINAR_CONCESIONARIO(?)");
        //se asigna valor al parametro
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        //Se ejecuta la llamada
        myCall.execute();
    }

    /**
     * Relaciona una marca con el concesionario
     *
     * @param pIdConcesionario
     * @param pIdMarca
     * @throws SQLException
     */
    public void agregarConcesionarioMarca(int pIdConcesionario, int pIdMarca) throws SQLException {

        //Se obtiene la coneccion del metodo conectar que se hereda de la clase Coneccion
        this.con = this.conectar();
        //Se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_AGREGAR_CONCESIONARIO_MARCA(?,?)");
        //Se le asigna valores a los parametros
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        myCall.setInt("PIDMARCA", pIdMarca);
        //Se ejecuta la llamada
        myCall.execute();
    }

    /**
     * Regresa el numero de veces que una Marca está relacionada a un
     * Concesionario
     *
     * @param pIdConcesionario
     * @param pIdMarca
     * @return
     * @throws SQLException
     */
    public int getExisteMarcaConce(int pIdConcesionario, int pIdMarca) throws SQLException {

        this.con = this.conectar();

        int nMarca = 0;

        CallableStatement myCall = con.prepareCall("call USP_EXISTE_MARCA_CONCESIONARIO(?,?)");
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        myCall.setInt("PIDMARCA", pIdMarca);
        myCall.execute();

        ResultSet rs = myCall.getResultSet();

        if (rs.next()) {
            nMarca = rs.getInt("NMARCA");
        }

        return nMarca;

    }

    /**
     * Obtiene una lista de Marcas de un Concesionario
     *
     * @param pIdConcesionario
     * @return
     * @throws SQLException
     */
    public List<Marca> listarMarcas(int pIdConcesionario) throws SQLException {

        //Se obtiene la coneccion a la base de datos
        this.con = this.conectar();
        //se crea una nueva lista de tipo Marca
        List<Marca> listarMarcas = new ArrayList<>();
        //Prepara la llamada sl SP
        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_CONCESIONARIOS_MARCAS_NOMBRE(?)");
        //se le asigna un valor al parametro
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        //se ejecuta la llamada
        myCall.execute();
        //Se le asigna un valor al ResultSet
        ResultSet rs = myCall.getResultSet();
        //se recorre el conjunto de resultados de la consulta
        while (rs.next()) {
            //Se crea un nuevo objeto de tipo Marca
            Marca marca = new Marca();
            //se obtienen los valores de los registros
            marca.setIdMarca(rs.getInt("IDMARCA"));
            marca.setNombre(rs.getString("NOMBRE"));
            //Se agregan los valores a la lista
            listarMarcas.add(marca);
        }
        //se retorna la lista
        return listarMarcas;
    }

    public void eliminarMarcaConcesionario(int pIdConcesionario, int pIdMarca) throws SQLException {

        this.con = this.conectar();

        CallableStatement myCall = con.prepareCall("call USP_ELIMINAR_CONCESIONARIO_MARCA(?,?)");
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        myCall.setInt("PIDMARCA", pIdMarca);
        myCall.execute();
    }

    /**
     * Obtiene una lista de los vehiculos relacionados al Concesionario
     *
     * @param pIdConcesionario
     * @return
     * @throws SQLException
     */
    public List<Vehiculo> listarVehiculos(int pIdConcesionario) throws SQLException {
        //se obtiene la coneccion a la base de datos
        this.con = this.conectar();
        //se crea una nueva lista de tipo vehiculo
        List<Vehiculo> listarVehiculos = new ArrayList<>();
        //se prepara la llamada al SP
        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_VEHICULO_MODELO_CONCESIONARIO_NOMBRE(?)");
        //se asigna valor al parametro
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        //se ejecuta la llamada
        myCall.execute();
        //se le asigna valor al ResultSet
        ResultSet rs = myCall.getResultSet();
        //se recorre el conjunto de resultados de la consulta
        while (rs.next()) {
            //se crea un nuevo objeto de tipo vehiculo
            Vehiculo vehiculo = new Vehiculo();
            //se obtienen los valores de los registros
            vehiculo.setIdConcesionario(rs.getInt("IDCONCESIONARIO"));
            vehiculo.setId(rs.getInt("IDVEHICULO"));
            vehiculo.setIdModelo(rs.getInt("IDMODELO"));
            vehiculo.setModelo(rs.getString("MODELO"));
            vehiculo.setColor(rs.getString("COLOR"));
            vehiculo.setSerie(rs.getString("SERIE"));
            vehiculo.setEstado(rs.getString("ESTADO"));
            //se agregan los valores a la lista
            listarVehiculos.add(vehiculo);
        }
        //se retorna la lista de vehiculos
        return listarVehiculos;
    }

    public List<Modelo> listarMarcaModelos(int pIdConcesionario) throws SQLException {

        this.con = this.conectar();

        List<Modelo> listarModelos = new ArrayList<>();

        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_CONCESIONARIO_MODELO(?)");
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        myCall.execute();

        ResultSet rs = myCall.getResultSet();

        while (rs.next()) {

            Modelo modelo = new Modelo();

            modelo.setIdConcesionario(rs.getInt("IDCONCESIONARIO"));
            modelo.setNombre(rs.getString("NOMBRE"));
            modelo.setModelo(rs.getString("MODELO"));

            listarModelos.add(modelo);
        }
        return listarModelos;
    }

    public List<Vehiculo> listarVehiculosConce(int pIdConcesionario) throws SQLException {

        this.con = this.conectar();

        List<Vehiculo> listarVehiculos = new ArrayList<>();

        CallableStatement myCall = con.prepareCall("call USP_SELECCIONAR_CONCESIONARIO_VEHICULOS(?)");
        myCall.setInt("PIDCONCESIONARIO", pIdConcesionario);
        myCall.execute();

        ResultSet rs = myCall.getResultSet();

        while (rs.next()) {

            Vehiculo vehiculo = new Vehiculo();

            vehiculo.setIdConcesionario(rs.getInt("IDCONCESIONARIO"));
            vehiculo.setId(rs.getInt("IDVEHICULO"));
            vehiculo.setModelo(rs.getString("MODELO"));
            vehiculo.setColor(rs.getString("COLOR"));
            vehiculo.setSerie(rs.getString("SERIE"));

            listarVehiculos.add(vehiculo);
        }
        return listarVehiculos;

    }

    public Vector<Concesionario> mostrarConcesionarios() throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion cn = new Conexion();
        Connection con = cn.conectar();

        Vector<Concesionario> datos = new Vector<Concesionario>();

        String sql = "call USP_SELECCIONAR_CONCESIONARIO";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Concesionario conce = new Concesionario();
                conce.setIdConcesionario(rs.getInt("IDCONCESIONARIO"));
                conce.setNombre(rs.getString("NOMBRE"));
                conce.setEntidadFederativa(rs.getString("ENTIDADFEDERATIVA"));
                datos.add(conce);
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return datos;
    }
}
