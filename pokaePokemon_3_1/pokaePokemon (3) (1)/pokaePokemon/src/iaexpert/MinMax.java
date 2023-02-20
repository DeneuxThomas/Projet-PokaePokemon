package iaexpert;

import capacite.Capacite;
import combat.Combat;
import dresseur.Attaque;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;
import interfaces.IStrategy;
import pokedex.Pokedex;
import pokemon.Pokemon;

import java.util.logging.Logger;

public class MinMax implements IStrategy {
    private Dresseur dresseur;
    private Combat combat;
    private final int PROFONDEUR = 5;
    
    /**
     * Constructor of MinMax
     * @param d
     */
    public MinMax(Dresseur d) { dresseur = d; }
    
    /**
     * calcul of MinMax 
     * @param <Attaque>
     * @param combat
     * @param attaque
     * @param maximizing
     * @param dresseurOriginal
     * @param maxDepth
     * @param pokemon
     * @return
     */
    public static <Attaque> double minimax(Combat combat, Attaque attaque, boolean maximizing, Dresseur dresseurOriginal, int maxDepth, Pokemon pokemon) {
        
        if (combat.etatduJeu(combat.getOpposingTrainner(dresseurOriginal),dresseurOriginal)==0 || maxDepth == 0) {
            return combat.fonctionEvaluation(combat.getOpposingTrainner(dresseurOriginal),dresseurOriginal);
        }
        
        if (maximizing) {
            
            double x = Double.NEGATIVE_INFINITY;

            
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                
                double resultat = minimax(combat, atk, false, dresseurOriginal, maxDepth - 1, pokemon);
                
                x = Math.max(resultat, (x));
            }
            return x;
        } else {
            
            double x = Double.POSITIVE_INFINITY;
            
            for (dresseur.Attaque atk : combat.getListeCoups(dresseurOriginal, pokemon)) {
                
                double resultat = minimax(combat, atk, true, dresseurOriginal, maxDepth - 1, pokemon); 
                
                x = Math.min(resultat, (x));
            }
            

            return x;
        }
    }

    /**
     * 
     * @param <Attaque>
     * @param combat
     * @param maxDepth
     * @param dresseur
     * @param pokemon
     * @return
     */
    public static <Attaque> Attaque meilleurCoup(Combat combat, int maxDepth, Dresseur dresseur, Pokemon pokemon) {
        
        double bestValue = Double.NEGATIVE_INFINITY;
        
        Attaque bestCoup = null;
        for (dresseur.Attaque attaque : combat.getListeCoups(dresseur, pokemon)) {
            
            double resultat = minimax(combat, attaque, false, dresseur, maxDepth, pokemon);
            for(int i = 0 ; i < dresseur.getRanchCopy().length ; i++){
                if (dresseur.getRanchCopy()[i].getPourcentagePV() > pokemon.getPourcentagePV()  || resultat > bestValue) {
                    bestValue = resultat;
                    bestCoup = (Attaque) attaque;
                }
                else {
                    bestValue = resultat;
                    bestCoup = (Attaque) attaque;
                }

            }


        }
        return bestCoup;
    }
    
    
    public IPokemon choisitCombattant(){
        Pokemon pok = null;
        while (pok == null){
            int randomIndex = (int) (Math.random() * dresseur.getRanch().length);
            if (!dresseur.getPokemon(randomIndex).estEvanoui())
                pok = dresseur.getRanch()[randomIndex];
        }

        return pok;
    }
    /**
     * 
     * @param p
     * @param d
     * @return
     */
    public IPokemon choisitPokemonPrudent(Pokemon p, Dresseur d) {
        int dmg = 0;

        for (int j = 0; j < p.getCapacitesApprises().length; j++) {
            for (int i = 0; i < d.getRanch().length; i++) {
                dmg = p.getCapacitesApprises()[j].calculeDommage(p, d.getPokemonEnMain());
                if (dmg <= 13) {
                    d.setPokemonEnMain((Pokemon) d.getPokemon(i));
                }
            }
        }

        return d.getPokemonEnMain();
    }
    /**
     * 
     * @param attaquant
     * @param defenseur
     * @return
     */
    public static IAttaque attaquePrudente(Pokemon attaquant, Pokemon defenseur){
        int maxValeur = 0; int val = 0;
        Capacite capaciteLaPlusEfficace = new Capacite();
        Attaque attaque = new Attaque();

        for(int i = 0; i < attaquant.nbCapacitesApprises(); i++){
            val = attaquant.getCapacitesApprises()[i].calculeDommage(attaquant, defenseur);
            val *= attaquant.getCapacitesApprises()[i].getPrecision();
            if (val > maxValeur){
                capaciteLaPlusEfficace = (Capacite) attaquant.getCapacitesApprises()[i];
                attaque.setCap(capaciteLaPlusEfficace);
                maxValeur = val;
            }
        }
        return attaque;
    }
    
    /**
     * set the Combat
     * @param c
     */
    public void setCombat(Combat c){combat = c;}
    
    
    /**
     * 
     */
    public IPokemon choisitCombattantContre(IPokemon pok){
        return meilleurCoup(combat, PROFONDEUR, dresseur, combat.getPokemon1());
    }
    
    /**
     * 
     */
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur){

        return meilleurCoup(combat,PROFONDEUR,dresseur,combat.getOpposingPokemon(combat.getOpposingTrainner(dresseur)));

    }
    
    /**
     * init the ranch with capacity
     */
    public void initCapacitesRanch(){
        Pokedex pokedex = new Pokedex();
        pokedex.initCapacity("listeCapacites.csv");
        for(int i = 0 ; i<6 ; i++){
            dresseur.getRanch()[i].apprendCapacites(pokedex.remplirTabDeCapacite());
        }
    }
    
    
    
    /**
     * 
     * @param pokemon
     * @return
     */
    private Capacite retourneCapacite(Pokemon pokemon){
        int nbDeCapacites = 0;
        Capacite cap = null;
        Pokedex pokedex = new Pokedex();
        pokedex.initCapacity("listeCapacites.csv");

        for (int i = 0; i < pokemon.getCapacitesApprises().length; i++){
            if (pokemon.getCapacitesApprises()[i] != null){
                nbDeCapacites++;
            }
        }

        if (nbDeCapacites <= 0){
            cap = new Capacite(pokedex.getCapacite("struggle"));
        }else{
            while (cap == null){
                int randomIndex = (int) (Math.random() *  ((Pokemon) pokemon).nbCapacitesApprises());

                if (pokemon.getCapacitesApprises()[randomIndex].getPP() > 0){
                    cap = pokemon.getCapacite()[randomIndex];
                }
            }
        }

        return cap;
    }
}
