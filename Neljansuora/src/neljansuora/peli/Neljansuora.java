
package neljansuora.peli;


public class Neljansuora {
    
    private Pelilauta pelilauta;
    
    public Neljansuora(int leveys, int korkeus){
        
       this.luoPelilauta(leveys, korkeus);
       
    }
    
    public void luoPelilauta(int leveys, int korkeus){
        this.pelilauta = new Pelilauta(leveys, korkeus);
    }
    
    public Pelilauta getPelilauta(){
        return this.pelilauta;
    }
    
}
