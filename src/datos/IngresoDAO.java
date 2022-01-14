/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import database.Conexion;
import datos.Interfeaces.CrudIngresoInterface;
import entidades.Articulo;
import entidades.DetalleIngreso;
import entidades.Ingreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author angel
 */
public class IngresoDAO implements CrudIngresoInterface<Ingreso, DetalleIngreso> {
     
    private final Conexion CON;
    private PreparedStatement ps;//Compila la sentencia sql y enviarlo a la base de datos 
    private ResultSet rs;//Almacena el resultado de ejecutar un selet 
    private boolean resp;
    
    public IngresoDAO(){
        CON=Conexion.getInstancia();
    }


    @Override
    public List<Ingreso> listar(String texto, int totalPorPagina, int numPagina) {
        List<Ingreso> registros = new ArrayList();
        try {
            //si nos damos cuenta en todo esta sentencia solo le enviamos 3 parametros que se asignan con el simbolo: ?
            ps = CON.conectar().prepareStatement("SELECT i.id, i.usuario_id, u.nombre as usuario_nombre,i.persona_id, p.nombre"
                    + "as persona_nombre, i.tipo_comprobante, i.serie_comprobante, i.num_comprobante, i.fecha, i.impuesto, i.total"
                    + "i.estado FROM ingreso i INNER JOIN persona p ON i.persona_id=p.id"
                    + "INNER JOIN usuario u ON i.usuario_id=u.id WHERE i.num_comprobante Like ? ORDER BY i.id ASC LIMIT ?,?");
            //Enviamos el primer parametro que esta en LIKE
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Estamos enviando los parametros a nuestra clase articulo, para crear el elemento de nuestra lista "Registros"
                registros.add(new Ingreso(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getDouble (10), rs.getDouble(11),rs.getString(12)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public List<DetalleIngreso> listarDetalle(int id) {
        
    }

    @Override
    public boolean insertar(Ingreso obj) {
        resp = false;
        Connection conn= null;
        try {
            conn=CON.conectar();
            conn.setAutoCommit(false);//Para iniciar una transaccion debemos de deshabilitar e autocomit para tener control de cuando y como se realiza
            //Enviaremos valores al Prepare Statement
            ps = CON.conectar().prepareStatement("INSERT INTO articulo (categoria_id,codigo,nombre,precio_venta,stock,descripcion,imagen,activo) VALUES(?,?,?,?,?,?,?,1)");
            ps.setInt(1, obj.getCategoriaID());
            ps.setString(2, obj.getCodigo());
            ps.setString(3, obj.getNombre());
            ps.setDouble(4, obj.getPrecioVenta());
            ps.setInt(5, obj.getStock());
            ps.setString(6, obj.getDescripcion());
            ps.setString(7, obj.getImagen());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean anular(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
