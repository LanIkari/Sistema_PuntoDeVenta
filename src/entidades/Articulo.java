/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author angel
 */
public class Articulo {

    private int id;
    private int categoriaID;
    private String categoriaNombre;
    private String codigo;
    private String nombre;
    private double precioVenta;
    private int stock;
    private String descripcion;
    private String imagen;
    private boolean activo;

    public Articulo() {

    }

    public Articulo(int id, int categoriaID, String categoriaNombre, String codigo, String nombre, double precioVenta, int stock, String descripcion, String imagen, boolean acrivo) {
        this.id = id;
        this.categoriaID = categoriaID;
        this.categoriaNombre = categoriaNombre;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = acrivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setAcrivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Articulo{" + "id=" + id + ", categoriaID=" + categoriaID + ", categoriaNombre=" + categoriaNombre + ", codigo=" + codigo + ", nombre=" + nombre + ", precioVenta=" + precioVenta + ", stock=" + stock + ", descripcion=" + descripcion + ", imagen=" + imagen + ", acrivo=" + activo + '}';
    }

}
