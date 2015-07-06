/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Cliente;
import org.hibernate.Session;

/**
 *
 * @author emejia
 */
public interface InterfaceCliente {
    public boolean registrar(Session sesion, Cliente unCliente) throws Exception;
    public Cliente getByEmail(Session sesion, String correo)throws Exception;
    
}
