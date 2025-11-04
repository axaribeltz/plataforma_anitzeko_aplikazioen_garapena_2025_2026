package apunteak_access;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import apunteak_access.Kontaktua;

public class DBKontaktua {

	private Connection Konexioa() throws SQLException, ClassNotFoundException {
		Connection kon = DriverManager.getConnection("jdbc:ucanaccess://src\\Kontaktuak.mdb");
		return kon;
	}

	public ArrayList<Kontaktua> kontaktuGuztiakLortu() throws ClassNotFoundException {

		ArrayList<Kontaktua> kontaktuak = new ArrayList<Kontaktua>();
		// KONTUZ: Eguneratu taularen izena eta zutabeak!
		String sql = "SELECT ID, Izena, Adina FROM Kontaktuak";

		// try-with-resources erabiltzen da baliabideak automatikoki ixteko (Connection,
		// Statement, ResultSet)
		try (Connection konexioa = Konexioa();
				Statement sententzia = konexioa.createStatement();
				ResultSet emaitza = sententzia.executeQuery(sql)) {

			while (emaitza.next()) {
				// 'Kontaktua' objektu bat sortu irakurritako errenkada bakoitzeko
				int id = emaitza.getInt("Id");
				String izena = emaitza.getString("Izena");
				int adina = emaitza.getInt("Adina");

				Kontaktua kontaktua = new Kontaktua(id, izena, adina);
				kontaktuak.add(kontaktua);
			}

		} catch (SQLException e) {
			System.err.println("Errorea kontaktuak lortzean: " + e.getMessage());
			e.printStackTrace();
		}

		return kontaktuak;
	}
}
