/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.Interfeaces;

import java.util.List;

/**
 *
 * @author angel
 */
public interface CrudPaginadoInterface<T> {
    public List<T> listar(String texto,int totalPgina,int numPagina);
    public boolean insertar(T obj);
    public boolean actualizar (T obj);
    public boolean desactivar(int id);
    public boolean activar (int id);
    public int total();
    public boolean existe(String texto);
    
}
