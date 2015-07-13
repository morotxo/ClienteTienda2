/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Pojos.Detalle;
import Pojos.Pedido;
import ClienteRest.restDetalle;
import ClienteRest.restPedido;
import ClienteRest.restProducto;
import DAO.DAOProducto;
import HibernateUtil.HibernateUtil;
import Pojos.Producto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author david13mo
 */
public class main {

   
    
    public static void main(String[] args) {
        
      recuperarProducto();
        
    }
    public static void recuperarProducto()
    {
        Session sesion=null;
        Transaction transaccion=null; 
        restProducto rProducto = new restProducto();
        DAOProducto daoProducto= new DAOProducto();
       
        try{
            sesion= HibernateUtil.getSessionFactory().openSession();
            transaccion=sesion.beginTransaction();
            List<Producto> productos= rProducto.findAll_JSON();
            for (Producto pro : productos)
            {
                  daoProducto.guardar(sesion, pro);
            }
            transaccion.commit();            
        }
        catch(Exception ex)
        {
             if(transaccion!=null)
            {
               transaccion.rollback();
            }
        }
        finally
        {
            sesion.close();
        }
        
    }
}
