package bomberman;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

    public class Oyun extends JPanel 
    {
         Random randomSayi = new Random();  
	 
	 public int randomKaya;	   // Haritadaki patlayabilen duvarları rastgele oluşturmak için
        
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
         public void paint(Graphics g)
         {
		super.paint(g);
		/* Haritadaki sabit alanları koyu gri çiz 39*39 kare olarak*/
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
