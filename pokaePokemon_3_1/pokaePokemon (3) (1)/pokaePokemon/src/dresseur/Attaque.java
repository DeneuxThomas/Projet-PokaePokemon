package dresseur;

import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.IEchange;
import interfaces.IPokemon;
import pokemon.Pokemon;


public class Attaque implements IAttaque, IEchange {

    private Pokemon pokemon;
    private Capacite cap = new Capacite();
    
    /**
     * constructor of Attaque
     */
    public Attaque() {
    	
    }    

    /**
     * Allow the Pokemon to use a capacity
     */
    @Override
    public void utilise() {
        cap.utilise();
    }
    
    /**
     * Return either it's a capacity or not
     * @return false if the capacity is null
     */
    public boolean estUneCapacite(){
        return cap != null;
    }

    /**
     * Allow us to set the Pokemon
     * @param poke
     */
    @Override
    public void setPokemon(IPokemon poke) {
        pokemon = (Pokemon) poke;
    }
    
    /**
     * Allow us to get the capacity variable
     * @return cap
     */
    public Capacite getCap() {
        return cap;
    }

    /**
     * Allow us to set the capacity variable
     * @param cap
     */
    public void setCap(Capacite cap) {
        this.cap = cap;
    }
    
    /**
     * Allow the dresser to switch his pokemon with another
     * @return pokemon
     */
    @Override
    public IPokemon echangeCombattant() {
        return pokemon;
    }
    
    /**
     * @param lanceur
     * @param receveur
     * @return 0
     */
    @Override
    public int calculeDommage(IPokemon lanceur, IPokemon receveur) {
        return 0;
    }
    
    @Override
    public String toString() {
        return "Attaque{" + "pokemon=" + pokemon +", cap=" + cap +'}';
    }
}
