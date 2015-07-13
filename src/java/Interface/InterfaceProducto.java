/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Pojos.Producto;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author emejia
 */
public interface InterfaceProducto {
    
    public List<Producto> listar(Session sesion, String valorbusqueda) throws Exception;
    
   
    
}
