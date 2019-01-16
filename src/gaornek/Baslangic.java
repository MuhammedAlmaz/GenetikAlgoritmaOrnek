package gaornek;

import java.util.Arrays;

public class Baslangic {

	static int hassasiyet=27;
	public static int genAdedi=hassasiyet*9;
	public static void main(String[] args) {


        Populasyon populasyon = new Populasyon(100, true);
        
        for(int i=0;i<100;i++)
        {

    		byte[] bireyinGeni=populasyon.enIyiBirey().genleriGetir();
    		String virguldenSonra="";
    		double aDegeri=3.5;
    		
    		for(int j=0;j<Baslangic.hassasiyet;j++)
    		{
    			virguldenSonra+=FitnessHesapla.sayiyiGetir(
    					Arrays.toString(
    							Arrays.copyOfRange(bireyinGeni, j*9, (j*9)+10)
    							)
    					);
    		}
    		aDegeri=3.5+Double.parseDouble("0."+virguldenSonra);
        	System.out.println("Generation: " + i 
        			+ " fitness: " + populasyon.enIyiBirey().fitnessGetir()
        			+ " aDegeri: " + populasyon.enIyiBirey().aDegeriniGetir()
        			+ " sayilarin Sayisi: " + Arrays.toString(populasyon.enIyiBirey().sayilarinSayisiniGetir())
        			+" Birlerin Sayisi :"+populasyon.enIyiBirey().birlerinSayisiGetir());
        	populasyon = GenetikAlgoritma.evrimGecirt(populasyon);
        }
        
        

	}

}
