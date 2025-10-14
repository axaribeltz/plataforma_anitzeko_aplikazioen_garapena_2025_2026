package _2_gaia._2_ariketa;

public class Sinkronizazioa {
	public static void main(String[] args) throws InterruptedException {

		Kontainer kontainerra = new Kontainer();

		// Hari 1: segundoak eguneratzen ditu
		Thread eguneratzaileHaria = new Thread(() -> {
			Thread.currentThread().setName("EGUNERATZAILE_HARIA");
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				kontainerra.bidaliMezua(null); // segundu bat igaro dela adierazi
			}
		});

		// Hari 2: segunduro mezua erakusten du
		Thread segunduHaria = new Thread(() -> {
			Thread.currentThread().setName("SEGUNDU_HARIA");
			while (true) {
				kontainerra.itxaronMezua();
				System.out.println(kontainerra.getSegunduak() + ". segundua");
			}
		});

		// Hari 3: bost segunduro mezua erakusten du
		Thread bostSegunduHaria = new Thread(() -> {
			Thread.currentThread().setName("BOST_SEGUNDU_HARIA");
			while (true) {
				kontainerra.itxaronMezua();
				int s = kontainerra.getSegunduak();
				if (s % 5 == 0) {
					System.out.println(s + " segundu pasatu dira");
				}
			}
		});

		// Hariak abiarazi
		eguneratzaileHaria.start();
		segunduHaria.start();
		bostSegunduHaria.start();
	}
}
