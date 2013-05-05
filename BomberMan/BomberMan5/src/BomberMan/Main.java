package BomberMan;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main 
{
    public static void main(String args[])
    {
		
		JFrame cerceve=new JFrame("BomberMan");
		cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/* Oyun başlamadan önce kontrol tuşlarını JOptionPane cercevesinde gösterir.*/
		JOptionPane.showMessageDialog( null, "1.OYUNCU İÇİN  (Sol Üst)  \n\n " +
                                                     "ATEŞ                  : ctrl\n " +
                    				     "YÖN TUŞLARI  : oklar\n\n"+
				   		     "2.OYUNCU İÇİN  (Sağ Alt)  \n\n " +
		   			             "ATEŞ                  : space\n " +
						     "YÖN TUŞLARI   : w,a,s,d\n\n",
						     "Oyun Kontrol Tuşları", JOptionPane.INFORMATION_MESSAGE);
		
		Oyun yeniOyun=new Oyun();			// Yeni bir oyun sınıfı oluşturur
		yeniOyun.setBackground(Color.white);            // Arkaplan rengini beyaza boyar
		cerceve.add(yeniOyun);				// Oyun sınıfımızı cerceveye ekliyoruz
		cerceve.setSize(605,631);			// cercevenin boyutlarını giriyoruz
		cerceve.setVisible(true);			// cercevenin ekranda kalması için true 
                cerceve.setLocationRelativeTo(null);            // cercevenin açılırken ekranın ortasında olmasını saglar
		cerceve.setResizable(false);                    // cerçevemizin boyutunun dışarıdan değiştirilmemesini istiyoruz
    }
}
