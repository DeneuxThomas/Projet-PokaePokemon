package combat;

import java.util.ArrayList;

import capacite.Capacite;
import dresseur.Attaque;
import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.ICombat;
import interfaces.IDresseur;
import interfaces.IPokemon;
import interfaces.ITour;
import pokemon.Pokemon;
import tour.Tours;

public class Combat implements ICombat{
    private Dresseur dresser1;
    private Dresseur dresser2;
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Pokemon getPokemon1() {
		return pokemon1;
	}

	public Pokemon getPokemon2() {
		return pokemon2;
	}


	private int nbTours = 1;
    
    /**
     * constructor of combat
     * @param d1
     * @param d2
     */
    public Combat(Dresseur d1, Dresseur d2){
        dresser1 = d1;
        dresser2 = d2;
    }

    /**
     * Battle until someone looses
     */
    @Override
    public void commence() {
        Tours tour = null;
        Attaque atk1 = null;
        Attaque atk2 = null;

        System.out.println("Que le meilleur gagne !");

        /* Allow dressers to choose the first Pokemon to battle */
        pokemon1 = (Pokemon) dresser1.choisitCombattant();
        pokemon2 = (Pokemon) dresser2.choisitCombattant();

        while (!dresser1.dresseurEvanoui() && !dresser2.dresseurEvanoui()){
            /* Allow dressers to choose an attack */
            atk1 = (Attaque) dresser1.choisitAttaque(pokemon1, pokemon2);
            atk2 = (Attaque) dresser2.choisitAttaque(pokemon2, pokemon1);

            /* If a dresser wants to switch Pokemon */
            if (!atk1.estUneCapacite()) {
                System.out.println(pokemon1.getNom() + " revient. " + atk1.echangeCombattant().getNom() + " à toi de jouer !");
                pokemon1 = (Pokemon) atk1.echangeCombattant();
            }
            else if (!atk2.estUneCapacite()) {
                System.out.println(pokemon2.getNom() + " revient. " + atk2.echangeCombattant().getNom() + " à toi de jouer !");
                pokemon2 = (Pokemon) atk2.echangeCombattant();
            }

            tour = (Tours) nouveauTour(pokemon1, atk1, pokemon2, atk2);
            System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");
            System.out.println("Nombre de tours : " + nbTours);
            System.out.println(pokemon1.getNom() + " (" + pokemon1.getPourcentagePV()+")  VS "+pokemon2.getNom() + " (" + pokemon2.getPourcentagePV() + ")");


            //start
            tour.commence();

            
            if(pokemon1.estEvanoui() && !dresser1.dresseurEvanoui()) {
            	pokemon1 = (Pokemon) dresser1.choisitCombattant();
            }
            else if(pokemon2.estEvanoui() && !dresser2.dresseurEvanoui()) {
            	pokemon2 = (Pokemon) dresser2.choisitCombattant();
            }

            nbTours++;
        }

        termine();
    }

    /**
     * Getter for the dresser1 variable
     * @return dresser1
     */
    @Override
    public IDresseur getDresseur1() {
        return dresser1;
    }

    /**
     * Getter for the dresser2 variable
     * @return dresser2
     */
    @Override
    public IDresseur getDresseur2() {
    	return dresser2;
    }
    
    /**
     * Give the opposing Trainner in the fight
     * @param d
     * @return the opposite Trainner
     */
    public Dresseur getOpposingTrainner(Dresseur d) {
    	if(d.equals(dresser1)) {
    		return dresser2;
    	}
    	else {
    		return dresser1;
    	}
		
    }
    
    /**
     * give the pokemon of opposing Trainner
     * @param D
     * @return pokemon 
     */
    public Pokemon getOpposingPokemon(Dresseur D) {
    	if (D.equals(dresser1)){
            return pokemon2;
        }else{
            return pokemon1;
        }
    }
    
    /**
     * describes the state of the game (list of pokemon of each player, life points)
     * @param D1
     * @param D2
     * @return etatduJeuTerminal
     */
    public double etatduJeuWithPV(Dresseur D1,Dresseur D2) {
    	return D1.getPVOfRanch() / (D1.getPVOfRanch() + D2.getPVOfRanch());
    }
    /**
     * 
     * @param dresseurMin
     * @param dresseurMax
     * @return
     */
    public boolean etatduJeuTerminal(Dresseur dresseurMin , Dresseur dresseurMax){
        return dresseurMin.dresseurEvanoui() ||dresseurMax.dresseurEvanoui();
    }
    /**
     * the probability of victory after this phase of the game
     * It is an expectation, because the state of the system F(X,c1,c2) 
     * starting from the state X knowing the moves played by 
     * players 1 and 2 is not deterministic.
     * @param D1
     * @param D2
     * @return the probability
     */
    public double fonctionEvaluation(Dresseur D1,Dresseur D2) {
    	int nb=0;
    	if (etatduJeu(D1,D2)==1) {
            return 1;
        } else if (etatduJeu(D1,D2)==0) {
            return 0;
        }
		return nb;	
    }



    /**
     * look if the fight is done or not
     * @param D1
     * @param D2
     * @return
     */
    public int etatduJeu(Dresseur D1 , Dresseur D2){
    	
    	if (D1.dresseurEvanoui()) {
    		return 0;
    	}
    	else if(D2.dresseurEvanoui()){
    		return 0;
    	}
        return 1;
    }
    
    /**
     * Creates a new round with both Pokemon and both attacks
     * @param pok1
     * @param atk1
     * @param pok2
     * @param atk2
     * @return a new round with updated parameters
     */
    @Override
    public ITour nouveauTour(IPokemon pok1, IAttaque atk1, IPokemon pok2, IAttaque atk2) {
        return new Tours((Pokemon) pok1, (Attaque) atk1, (Pokemon) pok2, (Attaque) atk2);
    }
    
    /**
     * 
     * @param dresseur
     * @param pokemon
     * @return
     */
    public ArrayList<Attaque> getListeCoups(Dresseur dresseur, Pokemon pokemon){
        ArrayList<Attaque> attaques = new ArrayList<>();

        for (int i = 0; i < pokemon.nbCapacitesApprises(); i++){
            if(pokemon.getCapacitesApprises()[i].getPP() > 0){
                Attaque a = new Attaque();
                a.setCap((Capacite) pokemon.getCapacitesApprises()[i]);
                attaques.add(a);
            }
        }
        for (int i = 0; i < dresseur.getRanch().length; i++){
            if (!dresseur.getPokemon(i).estEvanoui() && dresseur.getPokemon(i) != pokemon) {
                Attaque echange = new Attaque();
                echange.setPokemon(dresseur.getPokemon(i));
                attaques.add(echange);
            }
        }

        return attaques;
    }
    

    /**
     * Gives the name of the winner and of the looser when the battle is over
     */
    @Override
    public void termine() {
        dresser1.soigneRanch();
        dresser2.soigneRanch();

        if (dresser1.dresseurEvanoui()){
            System.out.println("Le dresseur " + dresser1.getNom() + "ne peut plus se battre...\n" + dresser2.getNom() + " remporte le combat !");
        }
        else {
            System.out.println("Le dresseur " + dresser2.getNom() + "ne peut plus se battre...\n" + dresser1.getNom() + " remporte le combat !");
        }
    }
}