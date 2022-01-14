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
public interface CrudSimpleInterfase<T>{
    //Una vez que implemente esta interfas tambien implementara todos estos metodos:
    public List<T> listar(String texto);
    public boolean insertar(T obj);
    public boolean actualizar (T obj);
    public boolean desactivar(int id);
    public boolean activar (int id);
    public int total();
    public boolean existe(String texto);
    
}
