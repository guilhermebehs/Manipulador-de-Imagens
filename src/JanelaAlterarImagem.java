
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class JanelaAlterarImagem extends JPanel {
    
    private BufferedImage imagem;
    private Rotinas rotinas;
    private double graus;
    private double posicaox;
    private double posicaoy;
    private double escalonamentox;
    private double escalonamentoy;
    private int brilho;
    private int contraste;
    private int tipo;
    private int threshold;
    private static final int ROTACAO=1;
    private static final int TRANSLACAO=2;
    private static final int ESPELHAMENTO=3;
    private static final int ESCALONAMENTO=4;
    private static final int BRILHOCONTRASTE=5;
    private static final int GRAYSCALE=6;
    private static final int BORDAS=7;
    private static final int FRAMESIZE=1000;
    
    
    public JanelaAlterarImagem(){
        rotinas = new Rotinas();
    }
    
    public static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
    
    
    public void rotacao(BufferedImage imagem, double graus){
        
        this.imagem = copyImage(imagem);
        this.graus = graus;
        tipo = ROTACAO;
        JFrame frame = new JFrame("Rotação");
        setLayout(null);
        frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    public void translacao(BufferedImage imagem, double posicaox, double posicaoy){
        
        this.imagem = copyImage(imagem);
        this.posicaox = posicaox;
        this.posicaoy = posicaoy;
        tipo = TRANSLACAO;
        JFrame frame = new JFrame("Translação");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    public void brilhoContraste(BufferedImage imagem, int brilho, int contraste){ 
        
        this.imagem = copyImage(imagem);
        this.brilho = brilho;
        this.contraste = contraste;
        tipo = BRILHOCONTRASTE;
        JFrame frame = new JFrame("Alteração de Brilho e Contraste");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }

    public void grayscale(BufferedImage imagem){
        
        this.imagem = copyImage(imagem);
        tipo = GRAYSCALE;
        JFrame frame = new JFrame("Grayscale");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    
    public void bordas(BufferedImage imagem,int threshold){
        
        this.imagem = copyImage(imagem);
        this.threshold = threshold;
        tipo = BORDAS;
        JFrame frame = new JFrame("Detectar Bordas");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

    }
    
    


    

    public void escalonamento(BufferedImage imagem, double escalonamentox, double escalonamentoy){
        
        this.imagem = copyImage(imagem);
        this.escalonamentox = escalonamentox;
        this.escalonamentoy = escalonamentoy;
        tipo = ESCALONAMENTO;
        JFrame frame = new JFrame("Escalonamento");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    public void espelhamento(BufferedImage imagem){
        
        this.tipo = ESPELHAMENTO;
        this.imagem = copyImage(imagem);
        JFrame frame = new JFrame("Espelhamento");
        setLayout(null);
         frame.setBounds(1, 1, imagem.getWidth()+30,imagem.getHeight()+50);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        
            AffineTransform at = AffineTransform.getTranslateInstance(1, 1);
       
        switch (tipo) {
            case ROTACAO:
                at.rotate(Math.toRadians(graus),200,200);
                break;
            case TRANSLACAO:
                at.translate(posicaox, posicaoy);
                break;
            case ESCALONAMENTO:
                at.scale(escalonamentox, escalonamentoy);
                break;
            case BRILHOCONTRASTE:
                imagem = rotinas.alterarBrilhoContraste(imagem,brilho, contraste);
                break;
            case GRAYSCALE:
                imagem = rotinas.aplicarGrayscale(imagem);
                break;    
            case BORDAS:
                imagem = rotinas.detectarBordas(imagem, threshold);
                break;     
            default:
                at = AffineTransform.getScaleInstance(-1, 1);
                at.translate(-imagem.getWidth(null), 0);
                break;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imagem, at, null);
        
    }
    
    
    
}
