/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class VentaDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int idVenta(){
        int id = 0;
        String sql = "SELECT MAX(id) FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error: "+ e.toString());
        }
        return id;
    }
    
    public int RegistrarVenta(Venta venta){
        String sql = "INSERT INTO ventas (cliente, vendedor, total, fecha) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getCliente());
            ps.setString(2, venta.getVendedor());
            ps.setDouble(3, venta.getTotal());
            ps.setString(4, venta.getDate());
            ps.execute();
        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: "+e.toString());
            }
        }
        return r;
    }
    
    public int RegistrarDetalle(Detalle detalleV){
        String sql = "INSERT INTO detalle (cod_pro, cantidad, precio, id_venta) Values (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, detalleV.getCod_pro());
            ps.setInt(2, detalleV.getCantidad());
            ps.setDouble(3, detalleV.getPrecio());
            ps.setInt(4, detalleV.getId_venta());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Error: "+e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: "+e.toString());
            }
        }
        return r;
    }
    
    public boolean ActualizarStock(int cant, String cod){
        String sql="UPDATE productos SET stock = ? WHERE codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error: "+e.toString());
            return false;
        }
    }
    
        public List ListarVentas() {
        List<Venta> listaVentas = new ArrayList();
        String sql = "SELECT * FROM ventas";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getLong("id"));
                venta.setVendedor(rs.getString("vendedor"));
                venta.setCliente(rs.getString("cliente"));
                venta.setTotal(rs.getDouble("total"));
                listaVentas.add(venta);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaVentas;
    }
    
}
