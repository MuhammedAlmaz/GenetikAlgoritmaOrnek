package gaornek;

public class Populasyon {
	
	private Birey[] bireyler;
	private int bireySayisi=0;
	public Populasyon(int bireySayisi,boolean yeniMiOlusturulacak) {
		this.bireySayisi=bireySayisi;
		bireyler=new Birey[bireySayisi];
		if(yeniMiOlusturulacak)
		{
			// Kaç Bireyden Oluþacak
            for (int i = 0; i < bireySayisi; i++) {
                Birey yeniBirey = new Birey();
                yeniBirey.bireyOlustur();
                bireyEkle(i, yeniBirey);
                //Bireyler Oluþuyor
            }
		}
	}
	
	public Birey bireyiGetir(int indis) {
		return bireyler[indis];
	}
	
	 public Birey enIyiBirey() {
	        Birey tempBirey = bireyler[0];
	        // Tüm bireylerin fitness deðerlerini karþýlaþtýr
	        for (int i = 0; i < this.bireySayisi; i++) {
	            if (tempBirey.fitnessGetir() <= bireyiGetir(i).fitnessGetir()) {
	            	tempBirey = bireyiGetir(i);
	            }
	        }
	        return tempBirey;
	    }
	 
	 
	// bireyi Ekleme
	    public void bireyEkle(int indis, Birey birey) {
	        bireyler[indis] = birey;
	    }
	    
	    public int bireySayisi() {
	    	return this.bireySayisi;
	    }
	    
	
	

}
