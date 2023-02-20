package dresseur;

import java.util.Arrays;
import pokemon.Espece;
import pokemon.Pokemon;
import capacite.Capacite;
import interfaces.IDresseur;
import interfaces.IPokemon;
import interfaces.IAttaque;
import interfaces.ICapacite;

public abstract class Dresseur implements IDresseur {

	private String nom;
	private int niveau = 1;
	private Pokemon[] ranch = new Pokemon[6];
	private Pokemon pokemonEnMain;

	public Dresseur(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Return the Pokemon that is at the "i" position in the ranch
	 * @param i
	 * @return ranch[i]
	 */
	@Override
	public IPokemon getPokemon(int i) {
		return ranch[i];
	}

	/**
	 * Allow us to get the ranch
	 * @return ranch
	 */
	public Pokemon[] getRanch() {
		return ranch;
	}

	/**
	 * Allow us to set the ranch variable
	 * @param ranch
	 */
	public void setRanch(Pokemon[] ranch) {
		this.ranch = ranch;
	}

	/**
	 * Allow us to set the name variable
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Allow us to get the name variable
	 * @return nom
	 */
	@Override
	public String getNom() {
		return nom;
	}

	/**
	 * Allow us to get the level variable
	 * @return niveau
	 */
	@Override
	public int getNiveau() {
		return niveau;
	}

	/**
	 * Allow a Pokemon to learn a new capacity
	 * @param pok
	 * @param caps
	 */
	@Override
	public void enseigne(IPokemon pok, ICapacite[] caps) {

		Espece esp = new Espece();
		esp.setName(pok.getNom().toLowerCase());
		Capacite[] capaDispo = (Capacite[]) esp.getCapSet();
		for (int j = 0; j < caps.length; j++) {
			for (int k = 0; k < capaDispo.length; k++) {
				if (caps[j].getNom().equals(capaDispo[k].getNom())) {
					for (int i = 0; i < ranch.length; i++) {
						if (pok.getNom().equals(ranch[i].getNom())) {
							boolean tabRempli = true;
                            for (int t = 0; t < pok.getCapacitesApprises().length; t++) {
                                if (pok.getCapacitesApprises()[t] == null) {
                                    tabRempli = false;
                                }
                            }
                           	 if (!tabRempli) {
                            	ranch[i].setCapacite(capaDispo[k]);

                            }

						}

					}

				}

			}
		}
	}
	
	/**
	 * 
	 */
	public int getPVOfRanch() {
		double valeur = 0;
		for (Pokemon pokemon : ranch){
			valeur = valeur + pokemon.getPourcentagePV();
		}
		return (int)valeur;
	}
	
	/**
	 * make a copy of the ranch
	 * @return copy of ranch
	 */
	public IPokemon[] getRanchCopy() {
		Pokemon[] ranch2 = getRanch();
		return ranch2;
	}
	
	/**
	 * give the pokémon
	 * @return a pokémon
	 */
	public IPokemon getPokemonEnMain() {
		return pokemonEnMain;
	}
	
	/**
	 * set the pokemon on pokemonEnMain
	 * @param pokemon
	 */
	public void setPokemonEnMain(Pokemon pokemon) {
		pokemonEnMain= pokemon;
		
	}
	
	/**
	 * look if dresser is alive or not
	 * @return true if dresser is passed out
	 */
	public boolean dresseurEvanoui(){
		boolean resultat = true;

		for (Pokemon pokemon : ranch){
			if (!pokemon.estEvanoui()){
				resultat = false;
			}
		}

		return resultat;
	}
	
	/**
	 * Give full HP to every Pokemon on the ranch
	 */
	@Override
	public void soigneRanch() {
		for (Pokemon pokemon : ranch) {
			pokemon.soigne();
		}
	}

	/**
	 * Sends the Pokemon in first place in the ranch
	 * @return ranch[0] which is the first Pokemon in the ranch
	 */
	@Override
	public IPokemon choisitCombattant() {
		return ranch[0];
	}

	/**
	 * @param pok
	 * @return choisitCombattant
	 */
	@Override
	public IPokemon choisitCombattantContre(IPokemon pok) {
		return choisitCombattant();
	}
	
	/**
	 * Send the capacity of the Pokemon that is using it to its opponent
	 * @param attaquant
	 * @param defenseur
	 */
	@Override
	public abstract IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);
	
	
	@Override
	public String toString() {
		return "Dresseur [nom=" + getNom() + ", niveau=" + niveau + ", ranch=" + Arrays.toString(ranch) + "]";
	}

	

}
