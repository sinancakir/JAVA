package BomberMan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

public class Bomba extends Thread 
{
	public Oyun oyun=null;                                                  // Bombaya patlama efekti vermek için
				
	public Image bombaResim[];                                              // Bomba resmini animasyom olarak gösterebilmek için (2 resim kullanıyoruz)
	public Image b1,b2;                                                     // İki bomba resmi (Küçük ve büyük olmak üzere)

	public int kullanilanBombaResmi=0;                                      // Resmi değiştirebilmek için
	public int geriSayim=3000;                                              // Bombanın patlama süresi 3000 ms (3 saniye)
	public int x,y;                                                         // Oyuncunun aynı zamanda bombanın kordinatları
	public int oyuncu;                                                      // Hangi oyuncunun bombası olduğunu bilebilmemiz için

	public Bomba(Oyun oyun,int x,int y ,int oyuncu)
        {
		this.x=x;
                this.y=y;
		this.oyun=oyun;
		this.oyuncu=oyuncu;

		Toolkit tk = Toolkit.getDefaultToolkit();

                b1 = tk.getImage(new File("b1.gif").getAbsolutePath());         // bomba resimlerini yükle
                b2 = tk.getImage(new File("b2.gif").getAbsolutePath());         // bomba resimlerini yükle

                bombaResim=new Image[2];

                bombaResim[0]=b1;
                bombaResim[1]=b2;
        
                start();                                                        // bomba efektini başlatmak için (run metodunu çağır)
	}

        @Override
	public  void run()
        {
		 while (true)
                 {
	            kullanilanBombaResmi = (kullanilanBombaResmi + 1) % 2;	// bomba resmini değiştir
	            try 
                    {
	            	oyun.repaint();						// haritayı yenile
	            	sleep(125);						// bomba animasyonunda 125 ms aralıklarla göster
	            } catch (Exception e) {}
	            geriSayim -= 125;
	            if (geriSayim <= 0) break;                                  // süresi gelen bombayı patlat
	        }
	}

	public  void paint(Graphics g)
        {
		if(geriSayim==0)
                {                                                               // bomba patladığında haritada bomba resmi kalmaması için beyaza boya
			g.setColor(Color.white);
			g.fillRect(x*40, y*40, 39, 39);
		}
		else
			g.drawImage(bombaResim[kullanilanBombaResmi], x*40,y*40 ,39,39, null);	// bombayı ekranda göster	
	}
}
