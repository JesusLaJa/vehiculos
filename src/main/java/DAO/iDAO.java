/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *¿Para que sirve una interfaz?
 * @author pc
 */
public interface iDAO {

    public void registrar(Object entidad) throws Exception;

    /**
     *
     * @param entidad
     * @return
     * @throws Exception
     */
    public List<Object> listar() throws Exception;

    public void modificar(Object entidad) throws Exception;

    public void eliminar(Object entidad) throws Exception;
}