/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import HibernateUtil.HibernateUtil;
import Interface.InterfaceCliente;
import Pojos.Cliente;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emejia
 */
public class DAOCliente implements InterfaceCliente {
    //private Session sesion ;
/*
    @Override
    public boolean registrar(Cliente unCliente) throws Exception {
        //sesion=HibernateUtil.getSessionFactory().openSession();
        //Transaction transaccion= sesion.beginTransaction();
        
        //transaccion.commit();
        //sesion.close();
        return true;
    }
*/
    @Override
    public boolean registrar(Session sesion, Cliente unCliente) throws Exception {
       sesion.save(unCliente);
       return true;
    }

    @Override
    public Cliente getByEmail(Session sesion, String email) throws Exception {
        String hql="from Cliente where email=:email";
        Query qt= sesion.createQuery(hql);
        qt.setParameter("email", email);
        Cliente unCliente = (Cliente) qt.uniqueResult();
     return unCliente;   
    }
    
}
