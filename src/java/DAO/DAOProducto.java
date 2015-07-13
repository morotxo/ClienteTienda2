/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HibernateUtil.HibernateUtil;
import Interface.InterfaceProducto;
import Pojos.Imagen;
import Pojos.Producto;
import static java.lang.System.out;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author emejia
 */
public class DAOProducto implements InterfaceProducto {
     private Session sesion;
     private Transaction transaccion; 
    @Override
    public List<Producto> listar(Session sesion, String valorbusqueda) throws Exception {
        out.println("valor a buscar "+valorbusqueda);
        //String hql = "from Producto where marca=:valorbusqueda";
        String hql = "from Producto where marca like '%"+valorbusqueda+"%' or descripcion like '%"+valorbusqueda+"%'";
        Query qt = sesion.createQuery(hql);
        //qt.setParameter("valorbusqueda", valorbusqueda);
        List<Producto> listaProductos = (List<Producto>) qt.list();
        out.println("Numero producots: "+listaProductos.size());
        return listaProductos;
    }
    
    public List<Imagen> listarImagenPortada( Session sesion, String valorBusqueda)
    {
        String hql = "from Imagen, Producto where Imagen.id_producto=Producto.idProducto and imagen.descripcion like 'busqueda'";
        Query qt = sesion.createQuery(hql);
        List<Imagen> resultado = (List<Imagen>)qt.list();
        return resultado;
    }
}
