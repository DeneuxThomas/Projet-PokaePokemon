package iaexpert;

import capacite.Capacite;
import dresseur.Attaque;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import pokedex.Pokedex;
import pokemon.Pokemon;

import static java.lang.constant.ConstantDescs.NULL;


public class DresseurExpert extends Dresseur{

    
    private StrategyIAExpert strategy ;
    Dresseur A ;
    Dresseur B;
    
    ArrayList<String> nameOfTrainners = new ArrayList<>();
    
    /**
     * Constructor of DresseurExpert
     * @param name
     * @param strategy
     */
    public DresseurExpert(String name , StrategyIAExpert strategy) {
        super(name);
        this.strategy = strategy;
        setNom(generateRandomName());
    }
    
    
    /**
     * 
     * @param attaquant
     * @param deff
     * @return attaque
     */
    public IAttaque attaqueCap (Pokemon attaquant, Pokemon deff){
        Attaque attaque = new Attaque();
        attaque.setCap(retourneCapacite(attaquant));

        return attaque;
    }
    /**
     * 
     * @param attaquant
     * @return attaque
     */
    public IAttaque attaqueEchange (Pokemon attaquant){
        Attaque attaque = new Attaque();
       attaquant = (Pokemon) choisitCombattant();
       attaque.setPokemon(attaquant);
       return attaque;
    }
    
    /**
     * generate name random
     * @return name
     */
    public String generateRandomName() {

    	nameOfTrainners.add("Jean");
    	nameOfTrainners.add("Guillaume");
    	nameOfTrainners.add("Luc");
    	nameOfTrainners.add("Sarah");
    	nameOfTrainners.add("Julie");
    	nameOfTrainners.add("Chen");
    	nameOfTrainners.add("Tili");
    	nameOfTrainners.add("Oak");
    	nameOfTrainners.add("Andrea");
    	nameOfTrainners.add("Sacha");
    	nameOfTrainners.add("Pedro");
    	nameOfTrainners.add("Mickael");
    	nameOfTrainners.add("Enzo");
    	nameOfTrainners.add("Antonin");
		
		int randomIndex = (int) (Math.random() * nameOfTrainners.size());
		return nameOfTrainners.get(randomIndex);
    }
    
    
    /**
     * @param pokemon
     * @return capacite of a pokemon
     */
    private Capacite retourneCapacite(Pokemon pokemon){
        int nbCapa = 0;
        Capacite cap = null;
        Pokedex pokedex = new Pokedex();
        
        for (int i = 0; i < pokemon.getCapacitesApprises().length; i++){
        	
            if (pokemon.getCapacitesApprises()[i] != null){
            	
            	nbCapa++;
            	
            }
        }
        
        if (nbCapa <= 0){
        	
        	Capacite cap1 = (Capacite) pokedex.getCapacite("struggle");
            cap = new Capacite(cap1);
            
        }else{
        	
            while (cap == null){
                int randomIndex = (int) (Math.random() *  3);

                if (pokemon.getCapacitesApprises()[randomIndex].getPP() > 0){
                    cap = pokemon.getCapacite()[randomIndex];
                }
            }
        }

        return cap;
    }
    
    
    /**
     * make a copy of ranch
     * @return ranch
     */
    public IPokemon[] getRanchCopy() {
		Pokemon[] ranch2 = getRanch();
		return ranch2;
	}



	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		// TODO Auto-generated method stub
		return null;
	}

}