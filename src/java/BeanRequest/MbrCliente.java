/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import Clases.Encriptado;
import DAO.DAOCliente;
import HibernateUtil.HibernateUtil;
import Pojos.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author emejia
 */
@ManagedBean
@RequestScoped
public class MbrCliente {
    private Cliente unCliente;
    private String rePass;
    private Session sesion;
    private Transaction transaccion; 

    /**
     * Creates a new instance of MbrCliente
     */
    public MbrCliente() {
        unCliente=new Cliente();
        
    }
    public String registrar() throws Exception{
        
        this.transaccion=null;
        this.sesion=null;
        
        try {
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= sesion.beginTransaction();
            
            if(!(this.unCliente.getPassword().equalsIgnoreCase(this.rePass)))
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Las contraseñas no coinciden"));
                return "/Cliente/Registrar";
            }
             
            DAOCliente daoCliente = new DAOCliente();
            
            if(daoCliente.getByEmail(this.sesion, this.unCliente.getEmail())!=null)
            {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "El ususario ya existe en el sistema"));
              return "/Cliente/Registrar";      
            }
        
            this.unCliente.setPassword(Encriptado.sha512(this.unCliente.getPassword()) );
            daoCliente.registrar(this.sesion,this.unCliente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro se realizo satisfactoriamente"));
            this.transaccion.commit();
            RequestContext.getCurrentInstance().execute("limpiarFormulario('RegistrarCliente')");
            
            return "/Cliente/Registrar";
            
        }
        catch(Exception ex)
        {
            if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "por favor contacte con su administrador"));
        }
        finally 
        {
            if(this.sesion!=null)
            {
                this.sesion.close();
            }
        }
        
        
        return "/Cliente/Registrar";
    }

    public Cliente getUnCliente() {
        return unCliente;
    }

    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    public String getRePass() {
        return rePass;
    }

    public void setRePass(String rePass) {
        this.rePass = rePass;
    }
    
    
    public String loguin(){
        return null;
        
    }
    
}
