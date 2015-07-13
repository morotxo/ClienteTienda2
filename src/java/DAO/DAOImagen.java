/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Pojos.Imagen;
import org.hibernate.Session;

/**
 *
 * @author emejia
 */
public class DAOImagen {
   public void guardar(Session sesion, Imagen unaImagen)
   {
       sesion.save(unaImagen);
   }
    
    
}
