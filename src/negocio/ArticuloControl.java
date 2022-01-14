/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Esta clase tiene toda lalogica de negocio
package negocio;

import datos.ArticuloDAO;
import datos.CategoriaDAO;
import entidades.Articulo;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author angel
 */
public class ArticuloControl {

    private final ArticuloDAO DATOS;
    private Articulo obj;
    private final CategoriaDAO DATOSCAT;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ArticuloControl() {
        this.DATOS = new ArticuloDAO();
        this.DATOSCAT=new CategoriaDAO();//Con este objeto podemos hacer referencia a los objetos de nuestraclase CategoriaDAO
        this.obj = new Articulo();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));
        //LLenamos la tabla con los siguientes datos, que corresponderan a cada una de nuestras columnas 
        String[] titulos = {"Id", "Categori ID", "Categoria", " Codigo", "Nombre", "Precio Venta", "Stock", "Descripcion", "Imagen", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[10];

        this.registrosMostrados = 0;
        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getId());
            registro[1] = Integer.toString(item.getCategoriaID());
            registro[2] = item.getCategoriaNombre();
            registro[3] = item.getCodigo();
            registro[4] = item.getNombre();
            registro[5] = Double.toString(item.getPrecioVenta());
            registro[6] = Integer.toString(item.getStock());
            registro[7] = item.getDescripcion();
            registro[8] = item.getImagen();
            registro[9] = estado;
            this.modeloTabla.addRow(registro);
            this.registrosMostrados = this.registrosMostrados + 1;
        }
        return this.modeloTabla;
    }
    public DefaultComboBoxModel seleccionar(){
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Categoria> lista = new ArrayList();
        lista=DATOSCAT.seleccionar();
        for(Categoria item:lista){
            items.addElement(new Categoria(item.getId(),item.getNombre()));
        }
        return items;
    }

    public String insertar(int categoriId, String Codigo, String nombre, double precioVenta, int stock, String descripcion, String imagen) {
        if (DATOS.existe(nombre)) {
            return "El registro ya existe";
        } else {
            obj.setCategoriaID(categoriId);
            obj.setCodigo(Codigo);
            obj.setNombre(nombre);
            obj.setPrecioVenta(precioVenta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";
            }
        }
    }

    public String actualizar(int id, int categoriaId, String codigo, String nombre, String nombreAnt, double precioVenta, int stock, String descripcion, String imagen) {
        if (nombre.equals(nombreAnt)) {
            obj.setId(id);
            obj.setCategoriaID(categoriaId);
            obj.setCodigo(codigo);
            obj.setNombre(nombre);
            obj.setPrecioVenta(precioVenta);
            obj.setStock(stock);
            obj.setDescripcion(descripcion);
            obj.setImagen(imagen);
            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualizacion";
            }
        } else {
            if (DATOS.existe(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setId(id);
                obj.setCategoriaID(categoriaId);
                obj.setCodigo(codigo);
                obj.setNombre(nombre);
                obj.setPrecioVenta(precioVenta);
                obj.setStock(stock);
                obj.setDescripcion(descripcion);
                obj.setImagen(imagen);
                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Eror en la Actualizaion";
                }
            }
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el registro";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}
