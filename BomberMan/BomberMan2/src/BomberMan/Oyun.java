package BomberMan;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;
import javax.swing.JPanel;

    public class Oyun extends JPanel implements KeyListener
    {
         public int p1x,p1y;		// 1.Oyuncunun kordinat bilgileri
	 public int p2x,p2y;		// 2.Oyuncunun kordinat bilgileri
        
         Random randomSayi = new Random();  
	 
	 public int randomKaya;	   // Haritadaki patlayabilen duvarları rastgele oluşturmak için
         
         public Image p1,p2;      // Oyuncu resimlerini almak için
         
         int harita[][]={       {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},  // 1 'ler sabit duvarları belirtir
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},  // 0 'lar paylayabilen duvarları
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1},
			 	{1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			 	{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}   
                        };
         public Oyun()
         {
             p1x=1; p1y=1;
             p2x=13; p2y=13;
             
             this.setFocusable(true);
             this.addKeyListener(this);
             
             Toolkit tk = Toolkit.getDefaultToolkit();
             
             p1 = tk.getImage(new File("p1.gif").getAbsolutePath());
             p2 = tk.getImage(new File("p2.gif").getAbsolutePath());
             
             for(int i=0 ; i<15 ; i++)
             {
                 for(int j=0 ; j<15 ; j++)
                 {
                     if(harita[i][j]==0)
                     {  // Oyuncuların oyun başlarken hareket edebilmesi için bölgelerindeki 3 kareyi boş bırakır
                         if((i==1&&j==1)||(i==1&&j==2)||(i==2&&j==1)||(i==13&&j==13)||(i==13&&j==12)||(i==12&&j==13))
                            continue;

                            randomKaya = 1 + randomSayi.nextInt( 10 );
					
                            if(randomKaya<=6)
                            {		
                                harita[i][j]=2;          // Haritayı %60 dolulukla patlayabilen duvar yap [ Değeri : 2 ]
                            }
                     }
                }
	    }
         }
          
         @Override
         public void keyPressed(KeyEvent e)
         {
		 switch (e.getKeyCode()) 
                 {
		 /*	Oyuncunun kordinatlarını basılan tuşa göre, eğer hareket edebiliyorsa 1 birim değiştir.
		  		(Oyuncu sadece zeminde -beyaz alanda- dolaşabilir)
		 */
         	case KeyEvent.VK_LEFT :
         		if(harita[p1x-1][p1y]==0 )
         			p1x-=1;
         		break;
         	case KeyEvent.VK_RIGHT :
         		if(harita[p1x+1][p1y]==0 )
         			p1x+=1;
         		break;
         	case KeyEvent.VK_UP:
         		if(harita[p1x][p1y-1]==0 )
         			p1y-=1;
         		break;
         	case KeyEvent.VK_DOWN :
         		if(harita[p1x][p1y+1]==0 )
         			p1y+=1;
         		break;
         	
         	case KeyEvent.VK_W :
         		if(harita[p2x][p2y-1]==0 )
         			p2y-=1;
         		break;
         	case KeyEvent.VK_A :
         		if(harita[p2x-1][p2y]==0 )
         			p2x-=1;
         		break;
         	case KeyEvent.VK_S :
         		if(harita[p2x][p2y+1]==0 )
         			p2y+=1;
         		break;
         	case KeyEvent.VK_D :
         		if(harita[p2x+1][p2y]==0 )
         			p2x+=1;
         		break;
		 }
		 repaint();	// her tuş hareketinde haritayı yenile
	 }
         
         public void keyReleased(KeyEvent e){}
	 public void keyTyped(KeyEvent e){}
         
         @Override
         public void paint(Graphics g)
         {
		super.paint(g);
		
		/* Haritadaki sabit alanları (koyugri) çiz 39*39 kare olarak*/
		for(int i=0 ; i<15 ; i++)
                {
			for(int j=0 ; j<15 ; j++)
                        {
				if(harita[i][j]==1)
                                {
					g.setColor( Color.darkGray );
					g.fillRect( i*40, j*40, 39, 39 );
				}
			}
		}		
		kayaDoldur(g);
				
	    	g.drawImage(p1, p1x*40+5,  p1y*40 ,30,40, this);		// 1.oyuncuyu ekrana yaz
                g.drawImage(p2, p2x*40+5 , p2y*40 ,30,40, this);		// 2.oyuncuyu ekrana yaz 		
         }
         
         public void kayaDoldur(Graphics g)
         {				
		for(int i=0 ; i<15 ; i++)
                {
			for(int j=0 ; j<15 ; j++)
                        {
				if(harita[i][j]==2)
                                {
					g.setColor(Color.GRAY);
					g.fillRect( i*40, j*40, 39, 39 );
				}
			}
		}
	 }
    }