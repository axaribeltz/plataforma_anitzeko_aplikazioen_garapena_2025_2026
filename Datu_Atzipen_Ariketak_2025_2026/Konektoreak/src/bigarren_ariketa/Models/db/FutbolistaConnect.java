package bigarren_ariketa.Models.db;

import bigarren_ariketa.Models.Futbolista;

import java.sql.*;
import java.util.ArrayList;

public class FutbolistaConnect {
    private Connection conexion() {
        String url = "jdbc:mysql://localhost:3306/futbol";
        String username = "root";
        String password = "";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Errorea konektatzen: " + e.getMessage());
        }
        return connection;
    }

    public Futbolista getFutbolistaIDtik(int dni) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM futbolistas WHERE dni='" + dni + "'";
        ResultSet rs = st.executeQuery(consulta);
        Futbolista fut = null;
        if (rs.next()) {
            fut = new Futbolista(
                    rs.getInt("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getDouble("salario"),
                    rs.getInt("idEquipo")
            );
        }
        con.close();
        return fut;
    }

    public void futbolistaSortu(Futbolista fut) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "INSERT INTO futbolistas (dni, nombre, apellido, salario, idEquipo) VALUES ('" +
                fut.getDni() + "', '" + fut.getNombre() + "', '" + fut.getApellido() + "', '" +
                fut.getSalario() + "', '" + fut.getIdTaldea() + "')";
        st.executeUpdate(consulta);
        con.close();
    }

    public void futbolistaEzabatu(int dni) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "DELETE FROM futbolistas WHERE dni='" + dni + "'";
        st.executeUpdate(consulta);
        con.close();
    }

    public ArrayList<Futbolista> getFutbolistak() throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM futbolistas";
        ResultSet rs = st.executeQuery(consulta);
        ArrayList<Futbolista> lista = new ArrayList<>();
        while (rs.next()) {
            Futbolista f = new Futbolista(
                    rs.getInt("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getDouble("salario"),
                    rs.getInt("idEquipo")
            );
            lista.add(f);
        }
        con.close();
        return lista;
    }
}
