/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeanRequest;

import BeanSession.MbSLogin;
import Clases.objetoResultado;
import DAO.DAOCategoria;
import DAO.DAOProducto;
import HibernateUtil.HibernateUtil;
import Pojos.Categoria;
import Pojos.Imagen;
import Pojos.Producto;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author emejia
 */
@ManagedBean
@SessionScoped
public class MbrProducto {
    List<objetoResultado>listaProducto ;
    private Session sesion;
    private List<String> selectedCategorias;
    private List<objetoResultado> oldlistaProducto;
    private List<Categoria> listacategorias;
    private String valorbusqueda;
    private Transaction transaccion; 
    private String marca, descripcion;
    private List<Producto> carro;
    private objetoResultado seleccionado;
    private Producto selectCarrito;
    private Double totalPagar=0.0;
    private MbSLogin verificacionLogin;
    /**
     * Creates a new instance of MbrProducto
     */
    public MbrProducto() {
        listaProducto= new ArrayList<>();
        carro= new ArrayList<>();
        verificacionLogin= new MbSLogin();
    }
    
    public String buscar(){
        if (listacategorias==null){
            updateLista();
        }
        out.println("Buscar: "+getValorbusqueda());
        this.transaccion=null;
        this.sesion=null;
        try{
            DAOProducto daoProducto = new DAOProducto();
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            this.listaProducto= daoProducto.listarImagenPortada(this.sesion,this.getValorbusqueda());
            this.oldlistaProducto=listaProducto;
            this.transaccion.commit();       
            return "/Producto/buscar.xhtml?faces-redirect=true";
        }
        catch (Exception ex){
             if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error Fatal:", "por favor contacte con su administrador"));
            return "";
        }
        finally{
               if(this.sesion!=null)
            {
                //this.sesion.close();
            }
        }
        
    }
    public void mostrar(){
        if (selectedCategorias.isEmpty()){
            listaProducto=oldlistaProducto;
        }else{
            listaProducto = new ArrayList<>();
            for (objetoResultado p : oldlistaProducto) {
                for (String sC : selectedCategorias) {
                    String idC = String.valueOf(p.getP().getCategoria().getIdCategoria());
                    if (idC.equals(sC)) {
                        listaProducto.add(p);
                        break;
                    }
                }
            }
            for (String sC : selectedCategorias) {
                out.println(sC);
            }
        }
        
        for (objetoResultado lP : listaProducto) {
            out.println(lP.getP().getDescripcion());
        }
    }
    public List<String> getSelectedCategorias() {
        return selectedCategorias;
    }

    public void setSelectedCategorias(List<String> selectedCategorias) {
        this.selectedCategorias = selectedCategorias;
    }
    public void updateLista(){
        this.transaccion=null;
        this.sesion=null;
        try{
            DAOCategoria daoCategoria = new DAOCategoria();
            this.sesion=HibernateUtil.getSessionFactory().openSession();
            this.transaccion= this.sesion.beginTransaction();
            this.listacategorias= daoCategoria.getAll(this.sesion);
            this.transaccion.commit();
        }
        catch (Exception ex){
             if(this.transaccion!=null)
            {
                this.transaccion.rollback();
            }
        }
        finally{
               if(this.sesion!=null)
            {
                //this.sesion.close();
            }
        }
    }
    
  
    public String AgregarCarrito(){      
        if (seleccionado==null){
            FacesMessage mensaje = new FacesMessage("producto");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);   
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error de Acceso: "," Usuario o Contraseña Incorrecta"));
            return "/Producto/buscar.xhtml?faces-redirect=true";
        }else{
            carro.add(seleccionado.getP());    
            totalPagar=totalPagar+seleccionado.getP().getPrecio();
            out.print(totalPagar);
            FacesMessage mensaje = new FacesMessage("producto");
            FacesContext.getCurrentInstance().addMessage(null, mensaje); 
            out.print(carro.size());
            return "/Carrito/Cantidad.xhtml?faces-redirect=true";
        }            
    }  
    
    
    public void EliminarCarrito(){
        out.print(selectCarrito.getMarca());
        
        if(selectCarrito==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
        }else{
            carro.remove(selectCarrito);
            totalPagar=totalPagar-selectCarrito.getPrecio();
        }    
    }
    
    
    public String Compra(){   
        if(verificacionLogin.isEstado()==false){
            return "/Cliente/Loguin.xhtml?faces-redirect=true";
            
        } else {   
            return "/Producto/Comprar.xhtml?faces-redirect=true";
        }
        
        
    }
    
    
    
//    
//    
//    public String buscar() throws Exception
//    {
//        DAOProducto daoProducto = new DAOProducto();
//        this.listaProducto=daoProducto.buscarProducto(this.getValorbusqueda());
//        return  "/Producto/buscar.xhtml?faces-redirect=true";
//        
//    }

    public List<objetoResultado> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<objetoResultado> listaProducto) {
        this.listaProducto = listaProducto;
    }
    public List<Categoria> getListacategorias() {
        return listacategorias;
    }

    public void setListacategorias(List<Categoria> listacategorias) {
        this.listacategorias = listacategorias;
    }
  

    public String getValorbusqueda() {
        return valorbusqueda;
    }

    public void setValorbusqueda(String valorbusqueda) {
        this.valorbusqueda = valorbusqueda;
    }

    public List<Producto> getCarro() {
        return carro;
    }

    public void setCarro(List<Producto> carro) {
        this.carro = carro;
    }

    public objetoResultado getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(objetoResultado seleccionado) {
        this.seleccionado = seleccionado;
    }

    public Double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public Producto getSelectCarrito() {
        return selectCarrito;
    }

    public void setSelectCarrito(Producto selectCarrito) {
        this.selectCarrito = selectCarrito;
    }
    
    
}
