package gaornek;

import java.awt.List;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
public class FitnessHesapla {

	public static double fitnessGetir(Birey birey) {
		// sayiAdetleriDizi indis deðeri bizim için 0-15 arasýndaki sayýlarýn adedini temsil edecektir.

		byte[] bireyinGeni=birey.genleriGetir();
		String virguldenSonra="";
		double aDegeri=3.5;
		
		for(int i=0;i<Baslangic.hassasiyet;i++)
		{
			virguldenSonra+=sayiyiGetir(
					Arrays.toString(
							Arrays.copyOfRange(bireyinGeni, i*9, (i*9)+10)
							)
					);
		}
		aDegeri=Double.parseDouble("3."+virguldenSonra);
		if(aDegeri<3.5)
		{
			aDegeri+=0.5;
		}
		

		//Bitleri Oluþturma
		String bitDizilimleri="";
		int[] sayiAdetleri=new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		double sonXDegeri=0.5;
		int birlerinSayisi=0;
		String binnaryDizilimi="";
		for(int i=0;i<1000000;i++)
		{
			if(i%4==0&&i!=0)
			{
				sayiAdetleri[Integer.parseInt(binnaryDizilimi, 2)]++;
				binnaryDizilimi="";
			}
			sonXDegeri=aDegeri*sonXDegeri*(1-sonXDegeri);
			
			if(sonXDegeri>0.5)
			{
				//Dizideki 1 lerin toplamý bize o anki sayýyý verecek .
				//Örneðin gen dizilimide 5 adet 1 , 4 adet 0 var ise o anki bit deðerimiz
				//5 olacaðýndan x deðerinin 0.5 büyük olma durumunda sayi deðerini arttýracaðýz.
				birlerinSayisi++;
				binnaryDizilimi+="1";
			}else {

				binnaryDizilimi+="0";
			}
			
			
		}
		
		//Aralarýndaki Farký Minimize Edeceðiz O yüzden En büyük fark deðeri bizim fitness deðrimiz olacak
		//En küçük deðeri aradýðýmýz için 1/Deðer olmak zorunda. Fitness deðeri ters mantýk çalýþýr.
		//En büyük fitness deðeri bizim için en iyi birey olacaktýr.
		//Bu sebepten dolayý eðer 1/deðer þeklinde yapmaz isek bize en kötü sonucu verecektir.
		// dizideki max deðer sayýsý - min deðer sayýsý bize aradaki farký verecek.
		

        IntSummaryStatistics istatistik = Arrays.stream(sayiAdetleri).summaryStatistics();

        birey.sayilarinSayisiniDegistir(sayiAdetleri);
		birey.birlerinSayisiDegistir(birlerinSayisi);
		birey.aDegeriniDegistir(aDegeri);
		return 1.0/(istatistik.getMax()-istatistik.getMin());
	}
	
	static int sayiyiGetir(String deger)
	{
		int adet=0;
		for(int i=0;i<deger.length();i++)
		{
			if(deger.charAt(i)=='1')
			{
				adet++;
			}
		}
		return adet;
	}
}
