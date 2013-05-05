package bomberman;

import java.awt.Color;
import javax.swing.JFrame;

public class Main 
{
    public static void main(String args[])
    {		
        JFrame cerceve=new JFrame("BomberMan");
	cerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Oyun yeniOyun=new Oyun();			// Yeni bir oyun sınıfı oluşturur
	yeniOyun.setBackground(Color.white);            // Arkaplan rengini beyaza boyar
	cerceve.add(yeniOyun);				// Oyun sınıfımızı cerceveye ekliyoruz
	cerceve.setSize(605,631);			// cercevenin boyutlarını giriyoruz
	cerceve.setVisible(true);			// cercevenin ekranda kalması için true 
        cerceve.setLocationRelativeTo(null);            // cercevenin açılırken ekranın ortasında olmasını saglar
	cerceve.setResizable(false);                    // cerçevemizin boyutunun dışarıdan değiştirilmemesini istiyoruz
    }
}
