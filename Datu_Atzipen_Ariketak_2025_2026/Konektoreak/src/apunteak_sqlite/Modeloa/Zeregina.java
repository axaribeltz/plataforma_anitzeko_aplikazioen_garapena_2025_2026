package apunteak_sqlite.Modeloa;

public class Zeregina {
	private int id;
	private String deskribapena;
	private boolean burutua;

	public Zeregina(int id, String deskribapena, boolean burutua) {
		this.id = id;
		this.deskribapena = deskribapena;
		this.burutua = burutua;
	}

	public int getId() {
		return id;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public boolean isBurutua() {
		return burutua;
	}

	@Override
	public String toString() {
		return "Zeregina [id=" + id + ", deskribapena=" + deskribapena + ", burutua=" + burutua + "]";
	}
	
	
}