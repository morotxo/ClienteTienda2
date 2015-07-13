/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.objetoResultado;
import HibernateUtil.HibernateUtil;
import Interface.InterfaceProducto;
import Pojos.Imagen;
import Pojos.Producto;
import static java.lang.System.out;
import java.util.ArrayList;
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
    
    public List<objetoResultado> listarImagenPortada( Session sesion, String valorBusqueda)
    {
        List<objetoResultado> or=new ArrayList<>();
        String hql = "from Imagen as imagen, Producto as producto where imagen.producto=producto.idProducto and imagen.descripcion like 'busqueda' and (producto.marca like '%"+valorBusqueda+"%' or producto.descripcion like '%"+valorBusqueda+"%')";
        Query qt = sesion.createQuery(hql);
        List<Object[]> resultado = (List<Object[]>)qt.list();
        for (Object[] r : resultado) {
            Imagen im=(Imagen)r[0];
            Producto pr=(Producto)r[1];
            or.add(new objetoResultado(im, pr));
        }
        return or;
        
         
    }
    public void guardar(Session sesion, Producto unProducto){
        
        sesion.save(unProducto);        
    }    
}
