/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import database.Conexion;
import datos.Interfeaces.CrudPaginadoInterface;
import entidades.Persona;
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
public class PersonaDAO implements CrudPaginadoInterface<Persona> {

    private final Conexion CON;
    private PreparedStatement ps;//Compila la sentencia sql y enviarlo a la base de datos 
    private ResultSet rs;//Almacena el resultado de ejecutar un selet 
    private boolean resp;

    public PersonaDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Persona> listar(String texto, int totalPorPagina, int numPagina) {
        List<Persona> registros = new ArrayList();
        try {
            //si nos damos cuenta en todo esta sentencia solo le enviamos 3 parametros que se asignan con el simbolo: ?
            ps = CON.conectar().prepareStatement("SELECT p.id, p.tipo_persona, p.nombre, p.tipo_documento,p.num_documento,\n"
                    + "p.direccion, p.telefono, p.email, p.activo\n"
                    + " FROM persona p \n"
                    + " WHERE p.nombre LIKE ? ORDER BY p.id ASC LIMIT ?,?");
            //Enviamos el primer parametro que esta en LIKE
            ps.setString(1, "%" + texto + "%");
            //
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Estamos enviando los parametros a nuestra clase articulo, para crear el elemento de nuestra lista "Registros"
                registros.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getBoolean(9)));
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
     public List<Persona> listarTipo(String texto, int totalPorPagina, int numPagina,String tipoPersona) {
        List<Persona> registros = new ArrayList();
        try {
            //si nos damos cuenta en todo esta sentencia solo le enviamos 3 parametros que se asignan con el simbolo: ?
            ps = CON.conectar().prepareStatement("SELECT p.id, p.tipo_persona, p.nombre, p.tipo_documento,p.num_documento,\n"
                    + "p.direccion, p.telefono, p.email, p.activo\n"
                    + "FROM persona p \n"
                    + "WHERE p.nombre LIKE ? AND tipo_persona=? ORDER BY p.id ASC LIMIT ?,?");
            //Enviamos el primer parametro que esta en LIKE
            ps.setString(1, "%" + texto + "%");
            ps.setString(2,tipoPersona);
            ps.setInt(3, (numPagina - 1) * totalPorPagina);
            ps.setInt(4, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Estamos enviando los parametros a nuestra clase articulo, para crear el elemento de nuestra lista "Registros"
                registros.add(new Persona(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getBoolean(9)));
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
    public boolean insertar(Persona obj) {
        resp = false;
        try {
            //Enviaremos valores al Prepare Statement
            ps = CON.conectar().prepareStatement("INSERT INTO persona (tipo_persona,nombre,tipo_documento,num_documento,direccion,telefono,email,activo) VALUES(?,?,?,?,?,?,?,1)");
            ps.setString(1, obj.getTipoPersona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
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
    public boolean actualizar(Persona obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET tipo_persona=?, nombre=?, tipo_documento=?, num_documento=?, direccion=?, telefono=?, email=? WHERE id=?");
            ps.setString(1, obj.getTipoPersona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipoDocumento());
            ps.setString(4, obj.getNumDocumento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setInt(8, obj.getId());
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
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET activo=0 WHERE id=?");
            ps.setInt(1, id);
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
    public boolean activar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET activo=1 WHERE id=?");
            ps.setInt(1, id);
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
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM persona");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id)");
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
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("SELECT nombre FROM persona WHERE nombre=?");
            ps.setString(1, texto);
            rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() > 0) {
                resp = true;
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
        return resp;
    }

}
