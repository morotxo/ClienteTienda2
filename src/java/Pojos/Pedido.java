package Pojos;
// Generated 04/07/2015 11:31:23 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Pedido generated by hbm2java
 */
public class Pedido  implements java.io.Serializable {


     private Integer idPedido;
     private Cliente cliente;
     private Factura factura;
     private Date fecha;
     private String formaPago;
     private char estado;
     private Set detalles = new HashSet(0);

    public Pedido() {
    }

	
    public Pedido(char estado) {
        this.estado = estado;
    }
    public Pedido(Cliente cliente, Factura factura, Date fecha, String formaPago, char estado, Set detalles) {
       this.cliente = cliente;
       this.factura = factura;
       this.fecha = fecha;
       this.formaPago = formaPago;
       this.estado = estado;
       this.detalles = detalles;
    }
   
    public Integer getIdPedido() {
        return this.idPedido;
    }
    
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Factura getFactura() {
        return this.factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getFormaPago() {
        return this.formaPago;
    }
    
    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getDetalles() {
        return this.detalles;
    }
    
    public void setDetalles(Set detalles) {
        this.detalles = detalles;
    }




}


