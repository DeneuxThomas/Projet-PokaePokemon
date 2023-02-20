package iaexpert;

import capacite.Capacite;
import dresseur.Attaque;
import dresseur.Dresseur;
import dresseur.DresseurIANormal;
import interfaces.IPokemon;
import pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;

public class StrategyIAExpert  {

    DresseurExpert trainner1;
    Dresseur trainner2;
    int valuePok;
    int totalValuePokemon;
    ArrayList<Attaque>coupsPossibles = new ArrayList<>();
    
    /**
     * see if the trainer win
     * @param d
     * @return true or false
     */
    public boolean dGagnant(Dresseur d) {
    	
        return pokemonStatsCalculRanch(trainner1) > pokemonStatsCalculRanch(trainner2) || PowerCapacityRanch(trainner1) > PowerCapacityRanch(trainner2);
    
    }
    
    
    /**
     * 
     * @param PokemonAttaque
     * @param pokemonDefender
     * @return true or false
     */
    protected boolean attaque(Pokemon PokemonAttaque, Pokemon pokemonDefender) {
        int damageOfD1Pokemon = calculCapacityPokemon(PokemonAttaque);
        int damageOfD2Pokemon = calculCapacityPokemon(pokemonDefender);

        return damageOfD1Pokemon < damageOfD2Pokemon;
    }
    
    /**
     * True if PokemonAttaque has a capacity lower power than pokemonDefender
     * @param PokemonAttaque
     * @param pokemonDefender
     * @return true or false
     */
    private boolean changePoke(Pokemon PokemonAttaque, Pokemon pokemonDefender) {
        
        return calculCapacityPokemon(PokemonAttaque) < calculCapacityPokemon(pokemonDefender);
        
    }
    
    
    /**
     * 
     * @param D
     * @return
     */
    public int valeurTotalDresseur(Dresseur D) {
    	
        return pokemonStatsCalculRanch(D) + PowerCapacityRanch(D);
        
    }
    
    /**
     * 
     * @param D
     * @return
     */
    public int pokemonStatsCalculRanch(Dresseur D) {
        totalValuePokemon = 0;
        for (int i = 0; i < D.getRanch().length; i++) {
            valuePok = (D.getPokemon(i).getStat().getForce() + D.getPokemon(i).getStat().getDefense() +
                    D.getPokemon(i).getStat().getPV() + D.getPokemon(i).getStat().getVitesse() + D.getPokemon(i).getStat().getSpecial());
            totalValuePokemon += valuePok;
        }
        System.out.println(totalValuePokemon);
        return totalValuePokemon;
    }
    
    /**
     * 
     * @param pok
     * @return
     */
    public int StatPokemon(Pokemon pokemon) {

        valuePok = pokemon.getStat().getForce() + pokemon.getStat().getDefense() + pokemon.getStat().getPV() + pokemon.getStat().getVitesse() + pokemon.getStat().getSpecial();


        return valuePok;
    }
    
    /**
     * 
     * @param poke
     * @return
     */
    public int calculCapacityPokemon(Pokemon pokemon) {
        int total = 0;
        for (int i = 0; i < pokemon.nbCapacitesApprises(); i++) {
            Capacite capa = (Capacite) pokemon.getCapacitesApprises()[i];
            total += capa.getPuissance() + capa.getPrecision();
        }
        return total;
    }
    
    /**
     * 
     * @param D
     * @return total
     */
    public int PowerCapacityRanch(Dresseur D) {
        int total = 0;
        for (int i = 0; i < 6; i++) {
        	
            total = total + calculCapacityPokemon(D.getRanch()[i]);
            
        }
        return total;
    }
    
    
    /**
     * Print informations of ranch of trainner 1 and 2
     */
    public void PrintRanch() {
    	
        System.out.println("Ranch : " + Arrays.toString(trainner1.getRanch()));
        
        System.out.println("Ranch : " + Arrays.toString(trainner2.getRanch()));
        
    }
    
    /**
     * choise the best 
     * @param attaq
     * @return
     */
    public IPokemon PrudentPokemon(Pokemon attack) {

        int damage = 0;

        for (int j = 0; j < attack.getCapacitesApprises().length; j++) {
        	
            for (int i = 0; i < trainner1.getRanch().length; i++) {
            	
            	damage = attack.getCapacitesApprises()[j].calculeDommage(attack, trainner1.getPokemonEnMain());
            	
                if (damage <= 13) {
                	
                    trainner1.setPokemonEnMain((Pokemon) trainner1.getPokemon(i));
                }
            }
        }
        return trainner1.getPokemonEnMain();
    }
}
