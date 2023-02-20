package dresseur;

import java.util.ArrayList;
import java.lang.Math;
import capacite.Capacite;
import interfaces.*;
import pokemon.Espece;
import pokemon.Pokemon;

public class DresseurIANormal extends Dresseur implements IStrategy {

	ArrayList<String> nameOfTrainners = new ArrayList<>();
	private Pokemon pokeIA;
	
	public DresseurIANormal(String nom) {
		super(nom);
		this.setNom(generateRandomName());
	}
	
	/**
	 * Allow us to get the Pokemon of the IA
	 * @return pokeIA
	 */
	public Pokemon getPokemonIA () {
		return pokeIA;
	}

	/**
	 * Allow us to set the pokeIA variable
	 * @param pok
	 */
	public void setPokemon(IPokemon pok) {
		this.pokeIA = (Pokemon) pok;
	}

	/**
	 * Gives random Pokemon then print the name of the IA
	 * @return pokeIA
	 */
	public IPokemon choisitCombattant() {
		pokeIA = getRandomPokemon();
		System.out.println(pokeIA.getNom());
		return pokeIA;
	}
	
	/**
	 * @param pok
	 * @return choisitCombattant()
	 */
	public IPokemon choisitCombattantContre(IPokemon pok) {
		return choisitCombattant();
	}

	/**
	 * Makes the AI changes its Pokemon
	 * @return pokeIA
	 */
	public IPokemon echangeCombattant() {
		boolean different = false;
		while(!different) {
			int randomIndex = (int) (Math.random() * getRanch().length);
			if(!getRanch()[randomIndex].getNom().equals(pokeIA.getNom())) {
				setPokemon(getRanch()[randomIndex]);
				different = true;
			}
		}
		
		return pokeIA;
	}


	/**
	 * Allow a Pokemon to learn a new capacity
	 * @param pok
	 * @param caps
	 */
	public void enseigne(IPokemon pok, ICapacite[] caps) {
		Espece esp = new Espece();
		esp.setName(pok.getNom().toLowerCase());
		Capacite[] capacites = (Capacite[]) esp.getCapSet();
		for (int j = 0; j < caps.length; j++) {
			for (int k = 0; k < capacites.length; k++) {
				if (caps[j].getNom().equals(capacites[k].getNom())) {
					for (int i = 0; i < getRanch().length; i++) {
						if (pok.getNom().equals(getRanch()[i].getNom()) && pok.getNiveau() >= capacites[k].getLvl()) {
							int randomIndex = (int) (Math.random() * pok.getCapacitesApprises().length);
								try {
									pok.remplaceCapacite(randomIndex, capacites[k]);
								} catch (Exception e) {
									throw new RuntimeException(e);
								}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Gives a random Pokemon to the IA
	 * @return getRanch()[randomIndex] which is a random Pokemon in the ranch
	 */
	public Pokemon getRandomPokemon() {
		int randomIndex = (int) (Math.random() * getRanch().length);
		return getRanch()[randomIndex];
    }
	
	/**
	 * List of random names that can be picked by the AI
	 * @return nomDresseur.get(randomIndex)
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
	 * Makes the IA picks a random capacity for its Pokemon
	 * @param attaquant
	 * @param defenseur
	 * @return attack
	 */
	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		int randomIndex = (int) (Math.random() * 4);
		int random = (int) (Math.random() * 2);
		Attaque attack = new Attaque();
		
		if(random == 1 || attaquant.estEvanoui()){
			pokeIA = (Pokemon) choisitCombattant();
			attack.setPokemon(pokeIA);
		}
		
		else if(random == 0 ) {
			attack.setCap((Capacite) attaquant.getCapacitesApprises()[randomIndex]);
		}
		
		return attack;
	}

	
	public void initCapacitesRanch() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * make a copy of the ranch
	 */
	public IPokemon[] getRanchCopy() {
		Pokemon[] ranch2 = getRanch();
		return ranch2;
	}
	
}







	







