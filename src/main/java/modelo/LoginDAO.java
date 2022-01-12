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

/**
 *
 * @author Usuario
 */
public class LoginDAO {
   Connection con;
   PreparedStatement ps;
   ResultSet rs;
   Conexion cn = new Conexion();
   
   public LoginModel log(String correo, String pass){
       LoginModel login = new LoginModel();
       String sql = "SELECT * FROM usuario WHERE correo = ? AND pass = ?";
       try{
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, correo);
           ps.setString(2, pass);
           rs = ps.executeQuery();
           if (rs.next()) {
               login.setId(rs.getInt("id"));
               login.setNombre(rs.getString("nombre"));
               login.setCorreo(rs.getString("correo"));
               login.setPass(rs.getString("pass"));
           }
       } catch (SQLException e) {
           System.out.println(e.toString());
       }
       return login;
   }
}
