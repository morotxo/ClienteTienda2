package Pojos;
// Generated 04/07/2015 11:31:23 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer idCliente;
     private String email;
     private String telefono;
     private String ciudad;
     private String pais;
     private String nombre;
     private String apellido;
     private String password;
     private char estado;
     private Set pedidos = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(String email, String telefono, String ciudad, String pais, String nombre, String apellido, String password, char estado) {
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.pais = pais;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.estado = estado;
    }
    public Cliente(String email, String telefono, String ciudad, String pais, String nombre, String apellido, String password, char estado, Set pedidos) {
       this.email = email;
       this.telefono = telefono;
       this.ciudad = ciudad;
       this.pais = pais;
       this.nombre = nombre;
       this.apellido = apellido;
       this.password = password;
       this.estado = estado;
       this.pedidos = pedidos;
    }
   
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCiudad() {
        return this.ciudad;
    }
    
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPais() {
        return this.pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public char getEstado() {
        return this.estado;
    }
    
    public void setEstado(char estado) {
        this.estado = estado;
    }
    public Set getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set pedidos) {
        this.pedidos = pedidos;
    }




}

