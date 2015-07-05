/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HibernateUtil.HibernateUtil;
import Interface.InterfaceCliente;
import Pojos.Cliente;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emejia
 */
public class DAOCliente implements InterfaceCliente {
    private Session sesion ;

    @Override
    public boolean registrar(Cliente unCliente) throws Exception {
        sesion=HibernateUtil.getSessionFactory().openSession();
        Transaction transaccion= sesion.beginTransaction();
        sesion.save(unCliente);
        transaccion.commit();
        sesion.close();
        return true;
    }
    
}
