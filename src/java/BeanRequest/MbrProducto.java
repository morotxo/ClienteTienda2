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
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emejia
 */
@ManagedBean
@SessionScoped
public class MbrProducto {
    List<Producto>listaProducto ;
    private Session sesion;
    private String valorbusqueda;
    private Transaction transaccion; 
    private String marca, descripcion;
    /**
     * Creates a new instance of MbrProducto
     */
    public MbrProducto() {
        listaProducto= new ArrayList<Producto>();
       
    }
    
    public String buscar(){
        out.println("Buscar: "+getValorbusqueda());
        this.transaccion=null;
        this.sesion=null;
        try{
            DAOProducto daoProducto = new DAOProducto();
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            this.listaProducto= daoProducto.listar(this.sesion,this.getValorbusqueda());
            this.transaccion.commit();
            return "/Producto/buscar.xhtml?faces-redirect=true";
        }
        catch (Exception ex){
             if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "por favor contacte con su administrador"));
            return "";
        }
        finally{
               if(this.sesion!=null)
            {
                //this.sesion.close();
            }
        }
        
    }
    
//    
//    
//    public String buscar() throws Exception
//    {
//        DAOProducto daoProducto = new DAOProducto();
//        this.listaProducto=daoProducto.buscarProducto(this.getValorbusqueda());
//        return  "/Producto/buscar.xhtml?faces-redirect=true";
//        
//    }

    public List<Producto> getListaProducto() {     
        out.println(listaProducto.size());
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getValorbusqueda() {
        return valorbusqueda;
    }

    public void setValorbusqueda(String valorbusqueda) {
        this.valorbusqueda = valorbusqueda;
    }
    
    
}
