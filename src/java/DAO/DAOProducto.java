/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HibernateUtil.HibernateUtil;
import Interface.InterfaceProducto;
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

    @Override
    public List<Producto> buscarProducto(String busqueda) throws Exception {
        this.transaccion=null;
        this.sesion=null;
        try{
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            String hql = "from Producto where marca=:busqueda";
            Query qt = sesion.createQuery(hql);
            qt.setParameter("valorbusqueda", busqueda);
            List <Producto>resultado=(List<Producto>)qt.list();
            this.transaccion.commit();
            return resultado;
//            return "/Producto/buscar.xhtml?faces-redirect=true";
        }
        catch (Exception ex){
             if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "por favor contacte con su administrador"));
            return null;
        }
        finally{
               if(this.sesion!=null)
            {
                this.sesion.close();
            }
        }
    }
    
    
       
    
}
