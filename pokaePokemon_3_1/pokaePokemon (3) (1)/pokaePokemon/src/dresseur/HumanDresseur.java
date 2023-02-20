package dresseur;

import capacite.Capacite;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IPokemon;

import pokemon.Espece;
import pokemon.Pokemon;

import java.util.Arrays;
import java.util.Scanner;

public class HumanDresseur extends Dresseur {

	Scanner choixDresseur = new Scanner(System.in);
	private Pokemon pokeDresseur;

	public HumanDresseur(String nom) {
		super(nom);
		System.out.println("Bienvenue, quel est ton nom ?: ");
		nom = choixDresseur.nextLine();
		System.out.println("Bienvenue " + nom + " :)");

	}
	
	/**
	 * Allow us to set the pokeDresseur variable
	 * @param pok
	 */
	public void setPokemon(IPokemon pok) {
		this.pokeDresseur = (Pokemon) pok;

	}

	/**
	 * Allow us to get the pokeDresseur variable
	 * @return pokeDresseur
	 */
	public Pokemon getPokemon() {
		return pokeDresseur;
	}

	/**
	 * Allow the dresser to see the Pokemons he just received
	 * @return pokeDresseur
	 */
	public Pokemon choixPokemonCombat() {
		System.out.println("Voil� les Pok�mons dont vous disposez: \n");
		int cptID = 0;

		for (int i = 0; i < getRanch().length; i++) {
			System.out.println(" " + "(" + cptID + ")" + " " + getRanch()[i].getNom() + " \n");
			cptID++;
		}

		while (pokeDresseur == null) {
			System.out.println("Choisisez un pokemon : \t");
			choisirPok();
		}
		
		return pokeDresseur;
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
					for (int i = 0; i < getRanch().length; i++) {
						if (pok.getNom().equals(getRanch()[i].getNom()) && pok.getNiveau() >= capaDispo[k].getLvl()) {

							System.out.println(Arrays.toString(pok.getCapacitesApprises()));
							boolean remp = false;
							while (!remp) {
								System.out.println("Choississez une capacité à enlever à votre pokémon");
								String nomCapacite = choixDresseur.nextLine();
								for (int t = 0; t < pok.getCapacitesApprises().length; t++) {
									if (pok.getCapacitesApprises()[t].getNom().equals(nomCapacite)) {
										try {
											pok.remplaceCapacite(t,capaDispo[k]);
										} catch (Exception e) {
											throw new RuntimeException(e);
										}
										remp = true;
										System.out.println(Arrays.toString(getRanch()));
									}

								}

							}

						}
					}
				}
			}
		}

	}
	
	/**
	 * Allow the dresser to pick the capacity that he wants to use
	 * @return cap
	 */
	public Capacite choisirCapCombat() {
		int cptID = 0;
		for (int i = 0; i < pokeDresseur.getCapacitesApprises().length; i++) {

			System.out.println(" " + "(" + cptID + ")" + " " + pokeDresseur.getCapacitesApprises()[i].getNom() + " \n");
			cptID++;
		}
		
		Capacite cap = null;
		while (cap == null) {
			System.out.println("Que doit faire " + pokeDresseur.getNom() + " ? \t");
			int choixCapacite = choixDresseur.nextInt();
			switch (choixCapacite) {
				case 0 -> cap = (Capacite) pokeDresseur.getCapacitesApprises()[0];
				case 1 -> cap = (Capacite) pokeDresseur.getCapacitesApprises()[1];
				case 2 -> cap = (Capacite) pokeDresseur.getCapacitesApprises()[2];
				case 3 -> cap = (Capacite) pokeDresseur.getCapacitesApprises()[3];
			}

		}

		return cap;
	}
	
	
	/**
	 * Allow the dresser to choose if he wants to attack or switch his Pokemon
	 * @param attaquant
	 * @param defenseur
	 * @return attaque or pokeDresseur if the dresser wanted to switch
	 */
	@Override
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
		System.out.println(" Que souhaites tu faire ?: \n");
		System.out.println(" (1) " + "Changer de Pokemon \n");
		System.out.println(" (2) " + "Choisir une attaque \n");

		int decisionDresseur = choixDresseur.nextInt();

		Attaque attaque = new Attaque();


		if(decisionDresseur == 1 || attaquant.estEvanoui()){

			System.out.println("Quel Pokemon souhaites-tu envoyer " +getNom()+ " : ");
			pokeDresseur = choisirPok();
			attaque.setPokemon(pokeDresseur);
			attaque.setCap(null);
		}
		else{
			System.out.println("-----------------------------------------");
			attaque.setCap(choisirCapCombat());
			attaque.utilise();
			System.out.println(attaque.getCap().getPP());
		}
		return attaque;

	}
	
	/**
	 * Allow the dresser to switch Pokemon
	 * @return pokeDresseur
	 */
	public Pokemon choisirPok() {
		int pokID = choixDresseur.nextInt();
		switch (pokID) {
			case 0 -> {
				pokeDresseur = getRanch()[0];
			}
			case 1 -> {
				pokeDresseur = getRanch()[1];
			}
			case 2 -> {
				pokeDresseur = getRanch()[2];
			}
			case 3 -> {
				pokeDresseur = getRanch()[3];
			}
			case 4 -> {
				pokeDresseur = getRanch()[4];
			}
			case 5 -> {
				pokeDresseur = getRanch()[5];
			}
		}
		return pokeDresseur;
	}

	public IPokemon[] getRanchCopy() {
		Pokemon[] ranch2 = getRanch();
		return ranch2;
	}



}
