
package neljansuora.domain;


public class Nappula {
    
    private int x;
    private int y;
    
    public Nappula(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int palautaX(){
        return this.x;
    }
    
    public int palautaY(){
        return this.y;
    }
    
}
