/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HibernateUtil.HibernateUtil;
import Pojos.Categoria;
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
public class DAOCategoria  {
     private Transaction transaccion;
     
    public List<Categoria> getAll(Session session) throws Exception {
        String hql = "from Categoria";
        Query qt = session.createQuery(hql);
        List<Categoria> listaProductos = (List<Categoria>) qt.list();
        out.println("Numero categorias: "+listaProductos.size());
        return listaProductos;
    }
    
    
       
    
}
