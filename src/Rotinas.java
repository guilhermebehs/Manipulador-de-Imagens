
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class Rotinas 
{
    
 private BufferedImage imagem;   
  
    
      
   public String histograma(int imagem[][]){
       
       String histograma ="";
       
        int tamanhoLinha= imagem.length;
        int tamanhoColuna= imagem[0].length;
        Map<String, Integer> histogramaMap = new LinkedHashMap();
        String corPixel="";
        
        for(int i=0; i < tamanhoLinha; i++)
            for(int y=0; y < tamanhoColuna; y++){
                corPixel = String.valueOf(imagem[i][y]);
                if(histogramaMap.containsKey(corPixel)){
                   int novoValor = histogramaMap.get(corPixel);
                   novoValor++;
                   histogramaMap.put(corPixel, novoValor);
                }
                else
                   histogramaMap.put(corPixel,1);        
            }
       
       for(Map.Entry<String, Integer> entry : histogramaMap.entrySet()){
           
           histograma+= "Pixel:"+entry.getKey()+"    Frequência:"+entry.getValue()+"\n";
       }
        
       return histograma;
       
       
   }

        
    public double media(int imagem[][]){
        
        int tamanhoLinha= imagem.length;
        int tamanhoColuna= imagem[0].length;
        double media;
        double soma=0.0;
        
         for(int i=0; i < tamanhoLinha; i++)
            for(int y=0; y < tamanhoColuna; y++)
                 soma+= imagem[i][y];
        
        media = soma/(tamanhoLinha*tamanhoColuna);
        return media;
    }
    
    
    
    
    public double variancia(int imagem[][]){
        
         double soma=0;
         double media = media(imagem    );
         double variancia;
        
        int tamanhoLinha= imagem.length;
        int tamanhoColuna= imagem[0].length;
        for(int i=0; i < tamanhoLinha; i++)
            for(int y=0; y < tamanhoColuna; y++)
                 soma+= Math.pow(imagem[i][y] - media ,2) ;
        
        variancia = soma/(tamanhoLinha  * tamanhoColuna);
        
        return variancia;
    }
    
    
    
    public int moda(int imagem[][]){
        
        int tamanhoLinha= imagem.length;
        int tamanhoColuna= imagem[0].length;
        String maisFrequente="-1";
        int maisFrequenteFrequencia=0;
        Map<String, Integer> frequenciaValores = new HashMap();
        
          for(int i=0; i < tamanhoLinha; i++)
              for(int y=0; y < tamanhoColuna; y++)
                  if(frequenciaValores.containsKey(imagem[i][y]+""))
                      frequenciaValores.put(imagem[i][y]+"", frequenciaValores.get(imagem[i][y]+"")+1);
                  else
                     frequenciaValores.put(imagem[i][y]+"", 1); 
          
          
          for (Map.Entry<String, Integer> entry : frequenciaValores.entrySet())
                if(entry.getValue() > maisFrequenteFrequencia){
                    maisFrequenteFrequencia = entry.getValue();
                    maisFrequente = entry.getKey();
                }
  
          return Integer.parseInt(maisFrequente);
        
    }
    
    
    public int mediana(int imagem[][]){
        
        List<Integer> lista = new ArrayList();
        List<Integer> listaOrdenada = new ArrayList();
        int tamanhoLinha= imagem.length;
        int tamanhoColuna= imagem[0].length;
        
         for(int i=0; i < tamanhoLinha; i++)
              for(int y=0; y < tamanhoColuna; y++)
                  lista.add(imagem[i][y]);
        lista.sort(Comparator.naturalOrder());
          
        return lista.get(Math.floorDiv(lista.size()-1,2));
    }
 
    
   public BufferedImage aplicarGrayscale(BufferedImage imagem){
        
        int pixel;

            for(int y=0; y < imagem.getHeight(); y++)
                 for(int x=0; x < imagem.getWidth(); x++){ 
     
                       Color color = new Color(imagem.getRGB(x, y));
                     
                       pixel = (color.getBlue()+color.getRed()+color.getGreen())/3;
                           
                       Color novaColor = new Color(pixel,pixel,pixel);
                       imagem.setRGB(x, y, novaColor.getRGB());
                 }
            
            return imagem;
        
    } 
   
   
   public BufferedImage detectarBordas(BufferedImage imagem, int threshold){

        int[][] xKernel = {
                            {0,0,0},
                            {0,-1,0},
                            {0,0,1}
                        };
        
        
        int[][] yKernel = {
                            {0,0,0},
                            {0,0,-1},
                            {0,1,0}
                        };

        int width=imagem.getWidth();

        int height=imagem.getHeight();        

        int widthM1=width-1;

        int heightM1= height - 1;

        int pixel;
        
        int i,j;

        double v, gx, gy, g;
        
        for(int y=1; y < heightM1; y++){
            for(int x=1; x < widthM1; x++){ 
                gx=gy=0;
                for(i=0; i < 3; i++){
                    for(j=0; j < 3; j++){
                    Color color = new Color(imagem.getRGB(x+(i-1), y+(j-1)));
                    v = color.getBlue();
                    gx += v*xKernel[i][j];
                    gy += v*yKernel[i][j];
                }
               }     
         
            g = Math.sqrt(Math.pow(gx,2) + Math.pow(gy,2));
            pixel = 0;
            if(g > threshold)
               pixel = 255; 

           Color novaColor = new Color(pixel,pixel,pixel);
           imagem.setRGB(x, y, novaColor.getRGB());
         }
        }
        
        return imagem;

    }

    public BufferedImage alterarBrilhoContraste(BufferedImage imagem, int brilho, int contraste){
        
        int pixel;
        for(int y=0; y < imagem.getHeight(); y++)
                 for(int x=0; x < imagem.getWidth(); x++){ 
     
                       Color color = new Color(imagem.getRGB(x, y));
                     
                       pixel = contraste*color.getRed()+brilho;
                       if(pixel > 255)
                          pixel = 255;
                       else 
                         if(pixel < 0)
                            pixel = 0;
                       else
                         pixel = contraste*color.getRed()+brilho;    
                       Color novaColor = new Color(pixel,pixel,pixel);
                       imagem.setRGB(x, y, novaColor.getRGB());
                 }  
    
        return imagem;
    }
    

}
