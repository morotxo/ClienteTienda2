/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import DAO.DAOCliente;
import Pojos.Cliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
        DAOCliente daoCliente = new DAOCliente();
        daoCliente.registrar(this.unCliente);
        return "Cliente/Registrar";
        
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
