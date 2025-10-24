package bigarren_ariketa.Models.db;

import bigarren_ariketa.Models.Taldea;

import java.sql.*;
import java.util.ArrayList;

public class TaldeaConnect {
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

    public ArrayList<Taldea> getTaldeak() throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM equipos";
        ResultSet rs = st.executeQuery(consulta);
        ArrayList<Taldea> lista = new ArrayList<>();
        while (rs.next()) {
            Taldea t = new Taldea(
                    rs.getInt("idEquipo"),
                    rs.getString("nombre"),
                    rs.getString("ciudad")
            );
            lista.add(t);
        }
        con.close();
        return lista;
    }

    public Taldea getTaldeaIDtik(int idEquipo) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM equipos WHERE idEquipo='" + idEquipo + "'";
        ResultSet rs = st.executeQuery(consulta);
        Taldea taldea = null;
        if (rs.next()) {
            taldea = new Taldea(
                    rs.getInt("idEquipo"),
                    rs.getString("nombre"),
                    rs.getString("ciudad")
            );
        }
        con.close();
        return taldea;
    }

    public void taldeaSortu(Taldea taldea) throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "INSERT INTO equipos (idEquipo, nombre, ciudad) VALUES ('" +
                taldea.getIdEquipo() + "', '" + taldea.getNombre() + "', '" + taldea.getCiudad() + "')";
        st.executeUpdate(consulta);
        con.close();
    }
}
