
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JanelaImagemOriginal extends JPanel {
    
    private  JFrame frame;
    private  BufferedImage imagem;
    
       public JanelaImagemOriginal(BufferedImage imagem){
        this.imagem = imagem;
        frame = new JFrame("Imagem original");
        setLayout(null);
        frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       }
       
       
       public void exibir(){
           
           frame.setVisible(true);
           
       }
       
       @Override
    public void paintComponent(Graphics g){       
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imagem, null, null);
        
    }
    
}
