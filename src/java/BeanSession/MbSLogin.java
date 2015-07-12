/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanSession;

import Clases.Encriptado;
import DAO.DAOCliente;
import HibernateUtil.HibernateUtil;
import Pojos.Cliente;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Leito
 */
@ManagedBean
@SessionScoped
public class MbSLogin implements Serializable{

    /**
     * Creates a new instance of MbSLogin
     */
    
    private String correo;
    private String contrasena;
    
    private Session session;
    private Transaction transaccion;
    
    public MbSLogin() {
    }

    public String login() throws Exception{
        this.session=null;
        this.transaccion=null;
        
        try{
            
            DAOCliente daoCliente = new DAOCliente();      
            
            this.session=HibernateUtil.getSessionFactory().openSession();
            this.transaccion = this.session.beginTransaction();
        
            Cliente cliente = daoCliente.getByEmail(session, this.correo);
        
            if(cliente!=null){
                if(cliente.getPassword().equals(Encriptado.sha512(this.contrasena))){
                    HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                    httpSession.setAttribute(correo, this.correo);
                    
//                    return "/index";
                }                
            }
            
            this.transaccion.commit();
            this.contrasena=null;
            this.correo=null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de Acceso: "," Usuario o Contraseña Incorrecta"));
            
            return "/Cliente/Loguin";
            
        }catch(Exception ex){
            if(this.transaccion!=null){
                this.transaccion.rollback();
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal: ", "Por facor contacte a su Administrador "+ex.getMessage()));
            
            return null;
        }
        
        finally{
            if(this.session!=null){
                this.session.close();
            }
        }
    }
    
    public String cerrarSesion(){
        this.contrasena=null;
        this.correo=null;
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        httpSession.invalidate();        
        return "";
    }
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
    
}
