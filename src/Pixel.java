
public class Pixel {

    
 public Pixel(){
     
 }
 
 public Pixel(int x, int y, int corRGB){
     
     this.x = x;
     this.y = y;
     this.corRGB = corRGB;
 }

 private int corRGB;
 private int x;
 private int y;

    /**
     * @return the corRGB
     */
    public int getCorRGB() {
        return corRGB;
    }

    /**
     * @param corRGB the corRGB to set
     */
    public void setCorRGB(int corRGB) {
        this.corRGB = corRGB;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
