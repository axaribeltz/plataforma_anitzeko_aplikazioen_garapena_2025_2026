package hirugarren_ariketa.Models.db;

import hirugarren_ariketa.Models.ErabiltzaileMugimenduak;

import java.sql.*;
import java.util.ArrayList;

public class Connect {
    private Connection conexion() {
        String url = "jdbc:mysql://localhost:3306/kutxabank";
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

    public ArrayList<ErabiltzaileMugimenduak> getErabiltzaileMugimenduak() throws SQLException {
        Connection con = conexion();
        Statement st = con.createStatement();
        String consulta = "SELECT * FROM erabiltzaile_mugimenduak";
        ResultSet rs = st.executeQuery(consulta);
        ArrayList<ErabiltzaileMugimenduak> lista = new ArrayList<>();
        while (rs.next()) {
            ErabiltzaileMugimenduak t = new ErabiltzaileMugimenduak(
                    rs.getString("izena"),
                    rs.getInt("kantitatea")
            );
            lista.add(t);
        }
        con.close();
        return lista;
    }
}