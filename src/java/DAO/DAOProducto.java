/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Interface.InterfaceProducto;
import Pojos.Producto;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author emejia
 */
public class DAOProducto implements InterfaceProducto {

    @Override
    public List<Producto> listar(Session sesion) throws Exception {
     String hql= "from Producto";
     Query qt = sesion.createQuery(hql);
     List<Producto> listaProductos = (List)qt.list();
     return listaProductos;
     
    }
    
}
