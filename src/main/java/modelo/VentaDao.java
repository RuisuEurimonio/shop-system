/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class VentaDao {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    int r;
    
    public int RegistrarVenta(Venta venta){
        String sql = "INSERT INTO ventas (cliente, vendedor, total) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, venta.getCliente());
            ps.setString(2, venta.getVendedor());
            ps.setDouble(3, venta.getTotal());
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
    
}
