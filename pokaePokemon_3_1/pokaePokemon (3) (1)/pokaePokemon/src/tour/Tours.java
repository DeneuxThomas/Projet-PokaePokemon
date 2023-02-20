package tour;

import dresseur.Attaque;
import interfaces.*;

import pokemon.Pokemon;

public class Tours  implements ITour {
    Pokemon pok1;
    Pokemon pok2;

    Attaque attack1;
    Attaque attack2;

    public Tours(Pokemon pok1, Attaque atk1, Pokemon pok2, Attaque atk2) {
        this.attack1 = atk1;
        this.attack2 = atk2;
        this.pok1 = pok1;
        this.pok2 = pok2;
    }
    
    /**
     * look who's starting with speed
     */
    @Override
    public void commence() {
        if (pok1.getStat().getVitesse() < pok2.getStat().getVitesse()){
            tourDresseur(pok2, pok1, attack2);
            tourDresseur(pok1, pok2, attack1);
        }else{
            tourDresseur(pok1, pok2, attack1);
            tourDresseur(pok2, pok1, attack2);
        }
    }
    
    /**
     * look if the pokemon is KO and the attack is an ability
     * @param attaquant
     * @param defenseur
     * @param attaque
     */
    private void tourDresseur(Pokemon attaquant, Pokemon defenseur, Attaque attaque){
        
    	 if (attaquant.estEvanoui() || !attaque.estUneCapacite()){
             System.out.println("L'attaque de " + attaquant.getNom() + " n'a pas touche l'adversaire");
             }else {
            	 if (defenseur.estEvanoui()){
                     System.out.println(defenseur.getNom() + " est KO");
                 }
                 if (attaqueTouche(attaque)){
                    defenseur.subitAttaqueDe(attaquant, attaque);
                    System.out.println(defenseur.getNom() + " s'est fait attaque par " + attaquant.getNom() + " avec l'attaque " + attaque.getCap().getNom());
             }
         } 
    }
    
    /**
     * see if the attack will hit or not by looking with a random number
     * @param attack
     * @return true or false
     */
    private boolean attaqueTouche(Attaque attack){
        return (Math.random()) <= attack.getCap().getPrecision();
    }
}