/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import DAO.DAOProducto;
import HibernateUtil.HibernateUtil;
import Pojos.Producto;
import static java.lang.System.out;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emejia
 */
@ManagedBean
@RequestScoped
public class MbrProducto {
    List<Producto>listaProducto ;
    private Session sesion;
    private Transaction transaccion; 
    /**
     * Creates a new instance of MbrProducto
     */
    public MbrProducto() {
       
    }
    
    public List<Producto> getLista(){
        this.transaccion=null;
        this.sesion=null;
        try{
            DAOProducto daoProducto = new DAOProducto();
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            this.listaProducto= daoProducto.listar(this.sesion);
            this.transaccion.commit();
            return this.listaProducto;
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
//                this.sesion.close();
            }
        }
        
    }
    

    public List<Producto> getListaProducto() {     
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
    
}
