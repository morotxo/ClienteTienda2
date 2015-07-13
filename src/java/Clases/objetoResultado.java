/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Pojos.Imagen;
import Pojos.Producto;

/**
 *
 * @author emejia
 */
public class objetoResultado {
     private Imagen im;
     private Producto p;
     public objetoResultado(Imagen i, Producto p){
         this.im=i;
         this.p=p;
     }
    public Imagen getIm() {
        return im;
    }

    public void setIm(Imagen im) {
        this.im = im;
    }

    public Producto getP() {
        return p;
    }

    public void setP(Producto p) {
        this.p = p;
    }
     
}
