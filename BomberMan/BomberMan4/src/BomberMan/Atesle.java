package BomberMan;

import java.awt.Color;
import java.awt.Graphics;

public class Atesle extends Thread
{

	public Oyun oyun=null;
	public Bomba bomb=null;

	public int x,y;
	public int oyuncu;
	public int renk=0;			// Kırmızı renkteki patlama efektlerini vermek için
	public int sure=125;			// 125 ms aralıklarla patlama efektini değiştir

	public Atesle(Oyun oyun,Bomba bomb,int oyuncu)
        {

		this.oyun=oyun;
		this.bomb=bomb;
		this.oyuncu=oyuncu;
		x=bomb.x;
		y=bomb.y;

		start();   		// ates efektini başlatmak için (run metodunu çağır)
	}

        @Override
	public  void run()
        {
		while (true) 
                {
			try 
                        {
                            oyun.repaint();                                     // haritayı yenile
                            sleep(sure);                                        // resimler arası ekranda bekle
                        }       
			catch (Exception e){}
			renk +=1;                                               // patlama efekti renk dolgusunu değiştir
			if(oyun.oyunbitti == true)
				sure=750;                                       // oyun bitti ise oyuncunun öldüğü (daha iyi görülebilmesi için) 750 ms aralıklarla göster
			if (renk > 6) break;
		}

		oyun.harita[x][y]=0;

		if(oyun.harita[x][y-1]!=1)
                {                                                               // bomba kordinatlarının 2 birim üstünü kontrol et ve patlat
                    if(y!=1 && oyun.harita[x][y-1]!=2)
                        if(y!=1 && oyun.harita[x][y-2]==2)
                            oyun.harita[x][y-2]=0;                              /* !eğer duvar patladı ise 0' a yani zemine dönüştür! */
                        oyun.harita[x][y-1]=0;
		}
		if(oyun.harita[x][y+1]!=1)
                {                                                               // bomba kordinatlarının 2 birim altını kontrol et ve patlat
                    if(y!=13 && oyun.harita[x][y+1]!=2)
                        if(y!=13 && oyun.harita[x][y+2]==2)
                            oyun.harita[x][y+2]=0;
			oyun.harita[x][y+1]=0;
		}
		if(oyun.harita[x-1][y]!=1)
                {                                                               // bomba kordinatlarının 2 birim solunu kontrol et ve patlat
                    if(x!=1 && oyun.harita[x-1][y]!=2 )
                        if(x!=1 && oyun.harita[x-2][y]==2 )
                            oyun.harita[x-2][y]=0;
			oyun.harita[x-1][y]=0;
		}
		if(oyun.harita[x+1][y]!=1)
                {                                                               // bomba kordinatlarının 2 birim sağını kontrol et ve patlat
                    if(x!=13 && oyun.harita[x+1][y]!=2)
                        if(x!=13 && oyun.harita[x+2][y]==2)
                            oyun.harita[x+2][y]=0;
			oyun.harita[x+1][y]=0;
		}
		/* Hangi oyuncu bombası ise bıraktığı bomba sayısını 1 azalt
		  		(Ates efektini sonrası bomba patlamıştır)
		*/
		if(oyun.bBirak[oyuncu-1] > 0)
                {
			oyun.bBirak[oyuncu-1]--;
		}
	}

	public void paint(Graphics g)
        {
            /* Patlama efekti kırmızı renk tonları açılan->kapanan->açılan şeklinde */
            if(renk == 1)
                g.setColor(new Color(100,0,0));
            else if(renk == 2)
                g.setColor(new Color(200,0,0));
            else if(renk == 3)
                g.setColor(new Color(255,0,0));
            else if(renk == 4)
		g.setColor(new Color(200,0,0));
            else if(renk == 5)
		g.setColor(new Color(100,0,0));
            else if(renk == 6)
		g.setColor(Color.white);
            

            if(renk<=6)
            {
                g.fillRect((x)*40, (y)*40, 39, 39);
                if((oyun.p1x == x && oyun.p1y == y ) || (oyun.p2x == x && oyun.p2y == y))   // ölme durumunu kontrol et
                { 
                    oyun.oyunbitti=true;                                        // oyunun bittiğini belirt
                    if(oyun.p1x == x && oyun.p1y == y)	// ! Eğer oyuncunun kordnt. bombanın kordnt. eşit ise oyunu kaybettiğini belirt
                        oyun.oyunuKaybeden[0]=1;
                    else if(oyun.p2x == x && oyun.p2y == y) // ! Eğer oyuncunun kordnt. bombanın kordnt. eşit ise oyunu kaybettiğini belirt
			oyun.oyunuKaybeden[1]=1;
		}
            /*  !!! Bomba en fazla kendinden 2 birim uzaklıktaki duvarı patlatabilir !!!
                !!! Yan yana iki patlayabilen duvardan sadece bombaya en yakın olanı patlar !!!
             	Genel olarak bombanın komşu 4 karesine bakar,sabit duvar değilse duvarları patlatır.
            */
            if(oyun.harita[x][y-1]!=1)
            {
                if(y!=1 && oyun.harita[x][y-1]!=2)
                {
                    if(y!=1 && (oyun.harita[x][y-2]==2 || oyun.harita[x][y-2]==0))
                    {
                        g.fillRect((x)*40, (y-2)*40, 39, 39);
			if((oyun.p1x == x && oyun.p1y == y-2 ) || (oyun.p2x == x && oyun.p2y == y-2))
                        {
                            oyun.oyunbitti=true;
                            if(oyun.p1x == x && oyun.p1y == y-2)
                                oyun.oyunuKaybeden[0]=1;
                            else if(oyun.p2x == x && oyun.p2y == y-2)
                                oyun.oyunuKaybeden[1]=1;                           
			}
                    }
		}
                g.fillRect((x)*40, (y-1)*40, 39, 39);
                if((oyun.p1x == x && oyun.p1y == y-1) || (oyun.p2x == x && oyun.p2y == y-1))
                {
                    oyun.oyunbitti=true;
                    if(oyun.p1x == x && oyun.p1y == y-1)
                        oyun.oyunuKaybeden[0]=1;
                    else if(oyun.p2x == x && oyun.p2y == y-1)
                        oyun.oyunuKaybeden[1]=1;            
		}
            }
            if(oyun.harita[x][y+1]!=1)
            {
                if(y!=13 && oyun.harita[x][y+1]!=2)
                    if(y!=13 && (oyun.harita[x][y+2]==2||oyun.harita[x][y+2]==0))
                    {
                        g.fillRect((x)*40, (y+2)*40, 39, 39);
                        if((oyun.p1x == x && oyun.p1y == y+2) || (oyun.p2x == x && oyun.p2y == y+2))
                        {                          
                            oyun.oyunbitti=true;                           
                            if(oyun.p1x == x && oyun.p1y == y+2)
                                oyun.oyunuKaybeden[0]=1;
                            else if(oyun.p2x == x && oyun.p2y == y+2)
                                oyun.oyunuKaybeden[1]=1;
			}
                    }
                g.fillRect((x)*40, (y+1)*40, 39, 39);
                if((oyun.p1x == x && oyun.p1y == y+1) || (oyun.p2x == x && oyun.p2y == y+1))
                {                  
                    oyun.oyunbitti=true;
                    if(oyun.p1x == x && oyun.p1y == y+1)
                        oyun.oyunuKaybeden[0]=1;
                    else if(oyun.p2x == x && oyun.p2y == y+1)
                        oyun.oyunuKaybeden[1]=1;
		}
            }
            if(oyun.harita[x-1][y]!=1)
            {
                if(x!=1 && oyun.harita[x-1][y]!=2 )
                    if(x!=1 && (oyun.harita[x-2][y]==2 || oyun.harita[x-2][y]==0))
                    {
                        g.fillRect((x-2)*40, (y)*40, 39, 39);
                        if((oyun.p1x == x-2 && oyun.p1y == y) || (oyun.p2x == x-2 && oyun.p2y == y))
                        {
                            oyun.oyunbitti=true;
                            if(oyun.p1x == x-2 && oyun.p1y == y)
                                oyun.oyunuKaybeden[0]=1;
                            else if(oyun.p2x == x-2 && oyun.p2y == y)
                                oyun.oyunuKaybeden[1]=1;
			}
                    }
                g.fillRect((x-1)*40, (y)*40, 39, 39);
                if((oyun.p1x == x-1 && oyun.p1y == y) || (oyun.p2x == x-1 && oyun.p2y == y))
                {
                    oyun.oyunbitti=true;
                    if(oyun.p1x == x-1 && oyun.p1y == y)
                        oyun.oyunuKaybeden[0]=1;
                    else if(oyun.p2x == x-1 && oyun.p2y == y)
                        oyun.oyunuKaybeden[1]=1;                  
		}
            }
            if(oyun.harita[x+1][y]!=1)
            {
                if(x!=13 && oyun.harita[x+1][y]!=2)
                    if(x!=13 && (oyun.harita[x+2][y]==2 || oyun.harita[x+2][y]==0))
                    {
                        g.fillRect((x+2)*40, (y)*40, 39, 39);
                        if((oyun.p1x == x+2 && oyun.p1y == y) || (oyun.p2x == x+2 && oyun.p2y == y))
                        {                           
                            oyun.oyunbitti=true;
                            if(oyun.p1x == x+2 && oyun.p1y == y)
                                oyun.oyunuKaybeden[0]=1;
                            else if(oyun.p2x == x+2 && oyun.p2y == y)
                                oyun.oyunuKaybeden[1]=1;
			}
                    }
                g.fillRect((x+1)*40, (y)*40, 39, 39);
                if((oyun.p1x == x+1 && oyun.p1y == y) || (oyun.p2x == x+1 && oyun.p2y == y))
                {
                    oyun.oyunbitti=true;
                    if(oyun.p1x == x+1 && oyun.p1y == y)
                        oyun.oyunuKaybeden[0]=1;
                    else if(oyun.p2x == x+1 && oyun.p2y == y)
                        oyun.oyunuKaybeden[1]=1;
		}
            }
            }
	}
}