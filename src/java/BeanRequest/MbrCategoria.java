/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import DAO.DAOCategoria;
import HibernateUtil.HibernateUtil;
import Pojos.Categoria;
import static java.lang.System.out;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author david13mo
 */
@ManagedBean
@SessionScoped
public class MbrCategoria {
    private List<Categoria> listacategorias;
    private Transaction transaccion;
    private Session sesion;
    public void MbrCategoria(){
        updateLista();
    }
    public void updateLista(){
        this.transaccion=null;
        this.sesion=null;
        try{
            DAOCategoria daoCategoria = new DAOCategoria();
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            this.listacategorias= daoCategoria.getAll(this.sesion);
            this.transaccion.commit();
        }
        catch (Exception ex){
             if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
        }
        finally{
               if(this.sesion!=null)
            {
                //this.sesion.close();
            }
        }
    }
    public List<Categoria> getListacategorias() {
        return listacategorias;
    }

    public void setListacategorias(List<Categoria> listacategorias) {
        this.listacategorias = listacategorias;
    }

  
    

   
}
