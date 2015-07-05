/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import Clases.Encriptado;
import DAO.DAOCliente;
import Pojos.Cliente;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author emejia
 */
@ManagedBean
@RequestScoped
public class MbrCliente {
    private Cliente unCliente;
    private String rePass;
    

    /**
     * Creates a new instance of MbrCliente
     */
    public MbrCliente() {
        unCliente=new Cliente();
        
    }
    public String registrar() throws Exception{
        if(!(this.unCliente.getPassword().equalsIgnoreCase(this.rePass)))
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Las contraseñas no coinciden"));
            return "/Cliente/Registrar";
        }
        this.unCliente.setPassword(Encriptado.sha512(this.unCliente.getPassword()) );
        DAOCliente daoCliente = new DAOCliente();
        daoCliente.registrar(this.unCliente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "El registro se realizo satisfactoriamente"));
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
    
}
