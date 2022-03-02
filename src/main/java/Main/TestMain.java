/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import DAO.ConcesionarioDAO;
import DAO.MarcaDAO;
import DAO.ModeloDAO;
import DAO.VehiculoDAO;
import Entidad.Concesionario;
import Entidad.Marca;
import Entidad.Modelo;
import Entidad.Vehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pc
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        /**
         * ******************************
         * CONCESIONARIO ******************************
         */
        //creo un nuevo objeto de tipo Concesionario
        Concesionario conce = new Concesionario();
        //creo un nuevo objeto de tipo ConcesionarioDAO
        ConcesionarioDAO conceDAO = new ConcesionarioDAO();

        /**
         * AISGNO LA LISTA OBTENIDA*
         */
//        List<Concesionario> listConce = conceDAO.listar();
//
//        listConce.forEach(c -> {
//            System.out.print(c.getIdConcesionario());
//            System.out.print(" " + c.getNombre());
//            System.out.println(" " + c.getEntidadFederativa());
//        });
//
//        /**
//         * ***********************************************
//         */
//        /**
//         * AGREGAR CONCESIONARIO*
//         */
//        //Llamo a los prametros
//        conce.setNombre("Concesionario Centro");
//        conce.setEntidadFederativa("Puebla");
//
//        //llamo al metodo
//        conceDAO.registrar(conce);
//
//        //Abro un if para que verifique si la inserción a sido exitosa 
//        if (conce.getIdConcesionario() > 0) {
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Agregado con exito");
//        }
//
//        /**
//         * **********************
//         */
//        /**
//         * ACTUALIZAR CONCESIONARIO*
//         */
//        //Llamo a los prametros
//        conce.setIdConcesionario(12);
//        conce.setNombre("Concesionario Centro");
//        conce.setEntidadFederativa("Guerrero");
//
//        //Abro un tryCatch para verificar que se haya actualizado correctamente el registro
//        try {
//            //llamo al metodo
//            conceDAO.modificar(conce);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Actualizado con exito");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("No se a podido actualizar" + e.getMessage());
//        }
//        /**
//         * *************************
//         */
//
//        /**
//         * ELIMINAR CONCESIONARIO*
//         */
//        //Abro un tryCatch para verificar que se haya eliminado correctamente el registro
//        try {
//            //llamo al metodo
//            conceDAO.eliminar(9);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Eliminado con exito");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al eliminar" + e.getMessage());
//        }
        /**
         * ***********************
         */
        /**
         * SABER SI HAY EL ID YA EXISTE*
         */
        int nMarca = conceDAO.getExisteMarcaConce(2, 1);

        System.out.println("Número de veces que se encontró: " + nMarca);
        
        if(nMarca < 1) {
            System.out.println("No existe la relación.");
            System.out.println("inserto el registro de marca con concesionario");
        }else {
            System.out.println("Relación Existente");
        }
        /**
         * *****************************
         */

        /**
         * ******************************
         * MARCA ******************************
         */
        //creo un nuevo objeto de tipo Marca
//        Marca marca = new Marca();
//        //creo un nuevo objeto de tipo MarcaDAO
//        MarcaDAO marcaDAO = new MarcaDAO();
//
//        /**
//         * ****************************
//         */
//        /**
//         * AGREGAR MARCA*
//         */
//        //Llamo a los prametros
//        marca.setNombre("Chevrolet");
//
//        //llamo al metodo
//        marcaDAO.registrar(marca);
//
//        //Abro un if para que verifique si la inserción a sido exitosa 
//        if (marca.getIdMarca() > 0) {
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Agregado Correctamente");
//        }
//        /**
//         * **************
//         */
//
//        /**
//         * ACTUALIZAR MARCA*
//         */
//        //Llamo a los prametros
//        marca.setIdMarca(9);
//        marca.setNombre("Volkswagen");
//
//        //Abro un tryCatch para verificar que se haya actualizado correctamente el registro
//        try {
//            //llamo al metodo
//            marcaDAO.modificar(marca);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Actualizado con exito");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al actualizar" + e.getMessage());
//        }
//        /**
//         * *****************
//         */
//
//        /**
//         * ELIMINAR MARCA*
//         */
//        //Abro un tryCatch para verificar que se haya eliminado correctamente el registro
//        try {
//            //llamo al metodo
//            marcaDAO.eliminar(9);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Eliminado correctamente");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al eliminar" + e.getMessage());
//        }
//        /**
//         * ***************
//         */
//
//        /**
//         * ******************************
//         * MODELO
//        *******************************
//         */
//        //creo un nuevo objeto de tipo Modelo
//        Modelo modelo = new Modelo();
//        //creo un nuevo objeto de tipo ModeloDAO
//        ModeloDAO modeloDAO = new ModeloDAO();
//
//        /**
//         * ***************************
//         */
//        /**
//         * AGREGAR MODELO*
//         */
//        //Llamo a los prametros
//        modelo.setIdMarca(7);
//        modelo.setModelo("Mini Cooper");
//        modelo.setAnio(2003);
//        modelo.setPrecio(12000);
//
//        //llamo al metodo
//        modeloDAO.registrar(modelo);
//
//        //Abro un if para que verifique si la inserción a sido exitosa 
//        if (modelo.getIdMarca() > 0) {
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Añadido con exito");
//        }
//        /**
//         * ***************
//         */
//
//        /**
//         * ACTUALIZAR MODELO*
//         */
//        //Llamo a los prametros
//        modelo.setIdModelo(6);
//        modelo.setIdMarca(4);
//        modelo.setModelo("Mini");
//        modelo.setAnio(2017);
//        modelo.setPrecio(12000);
//
//        try {
//            //llamo al metodo
//            modeloDAO.modificar(modelo);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Actualizado con exito");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al eliminar" + e.getMessage());
//        }
//        /**
//         * ******************
//         */
//
//        /**
//         * ELIMINAR MODELO*
//         */
//        try {
//            //llamo al metodo
//            modeloDAO.eliminar(6);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Eliminado Correctamente");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al eliminar" + e.getMessage());
//        }
//        /**
//         * ****************
//         */
//
//        /**
//         * ******************************
//         * VEHICULO
//        *******************************
//         */
//        //creo un nuevo objeto de tipo Vehiculo
//        Vehiculo vehiculo = new Vehiculo();
//        //creo un nuevo objeto de tipo VehiculoDAO
//        VehiculoDAO vehiculoDAO = new VehiculoDAO();
//
//        /**
//         * ****************************
//         */
//        /**
//         * AGREGAR VEHICULO*
//         */
//        //Llamo a los parametros
//        vehiculo.setIdModelo(0);
//        vehiculo.setIdConcesionario(0);
//        vehiculo.setColor("");
//        vehiculo.setSerie("");
//
//        //llamo al metodo
//        vehiculoDAO.registrar(vehiculo);
//
//        //Abro un if para que verifique si la inserción a sido exitosa 
//        if (vehiculo.getId() > 0) {
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Insertado con exito");
//        }
//        /**
//         * ******************
//         */
//
//        /**
//         * ACTUALIZAR VEHICULO*
//         */
//        //Llamo a los prametros
//        vehiculo.setId(0);
//        vehiculo.setIdModelo(0);
//        vehiculo.setIdConcesionario(0);
//        vehiculo.setColor("");
//        vehiculo.setSerie("");
//
//        //Abro un tryCatch para verificar que se haya actualizado correctamente el registro
//        try {
//            //Llamo al metodo
//            vehiculoDAO.modificar(vehiculo);
//            //muestro el mensaje en caso de que se actualice correcto
//            System.out.println("Se a actualizado correctamente");
//        } catch (Exception e) {
//            //muestro el mensaje en caso de que no se actualice correcto
//            System.out.println("Error al actualizar" + e.getMessage());
//        }
//        /**
//         * ********************
//         */
//
//        /**
//         * ELIMINAR VEHICULO*
//         */
//        //Abro un tryCatch para verificar que se haya actualizado correctamente el registro
//        try {
//            //LLamo al metodo
//            vehiculoDAO.eliminar(0);
//            //Muestro el mensaje en caso de que se realice correctamente la ejecucion
//            System.out.println("Eliminado correctamente");
//        } catch (Exception e) {
//            //Muestro el mensaje en caso de que no se realice correctamente la ejecucion
//            System.out.println("Error al eliminar" + e.getMessage());
//        }
        /**
         * ******************
         */
    }

}
