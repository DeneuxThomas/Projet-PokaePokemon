package main;

import java.io.IOException;

import combat.Combat;

import org.json.simple.parser.ParseException;

import capacite.Capacite;
import dresseur.HumanDresseur;
import iaexpert.DresseurMinMax;
import dresseur.DresseurIANormal;
import pokedex.Pokedex;
import pokemon.Espece;
import pokemon.Pokemon;
import type.Type;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestMain {
	
	private final static Logger loggerMain =  Logger.getLogger(TestMain.class.getName());
	
	public static void main (String[] args) throws ParseException, IOException, java.text.ParseException, InterruptedException {
		
		/*  Initialization of the Log System  */
		loggerMain.setUseParentHandlers(false);
		FileHandler logSheet = new FileHandler( "logsPokae.txt" );
		logSheet.setFormatter(new SimpleFormatter());
		loggerMain.addHandler(logSheet); 

		/*  Initialization of the Pokedex  */
		Pokedex pokedex = new Pokedex();
		pokedex.PrintPok();
		loggerMain.log(Level.INFO, "pokedex : Initialized");
		System.out.println();
		
		/*  Creation of the Espece  */
		Espece esp = new Espece();
		Espece espB = new Espece();

		/*  Initialization of all of the Species from the .CSV  */
		pokedex.initOfEspece("listePokemon1G.csv");
		loggerMain.log(Level.INFO, "Species : Initialized");

		/*  Initialization of all of the Capacities from the .CSV  */
		System.out.println();
		pokedex.initCapacity("listeCapacites.csv");
		loggerMain.log(Level.INFO, "Capacities : Initialized");
		
		/*  Initialization of all of the Efficience from the .CSV  */
		System.out.println();
		pokedex.initEfficacity("efficacites.csv");

		esp = (Espece) pokedex.getInfo(esp.getNom());
		espB = (Espece) pokedex.getInfo(espB.getNom());
		Pokemon pokemon = new Pokemon(esp);
		Pokemon pokemonB = new Pokemon(espB);

		Capacite [] cap = pokedex.FillCapaciteTab();
		
		
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~     LOADING     ~~~~~~~~~~~~~~~~~~~~~");
		
		/**
		// Creation of the AI with its Pokemon and their capacities
		DresseurIANormal ia = new DresseurIANormal("");
		ia.setRanch((Pokemon[]) pokedex.engendreRanch());

		ia.getPokemon(0).apprendCapacites(cap);
		ia.getPokemon(1).apprendCapacites(cap);
		ia.getPokemon(2).apprendCapacites(cap);
		ia.getPokemon(3).apprendCapacites(cap);
		ia.getPokemon(4).apprendCapacites(cap);
		ia.getPokemon(5).apprendCapacites(cap);

		loggerMain.log(Level.INFO, "Pokemon IA : capacities learned");
		
		System.out.println(ia.getNom());
		ia.setPokemon(ia.choisitCombattant());
		System.out.println(ia.getPokemonIA()+ " \n") ;


		// Creation of the Human Dresser with its Pokemon
		HumanDresseur dresHumain = new HumanDresseur("");
		dresHumain.setRanch((Pokemon[]) pokedex.engendreRanch());

		System.out.println("Veuillez attendre quelques instants \n");
		dresHumain.getPokemon(0).apprendCapacites(cap);
		dresHumain.getPokemon(1).apprendCapacites(cap);
		dresHumain.getPokemon(2).apprendCapacites(cap);
		dresHumain.getPokemon(3).apprendCapacites(cap);
		dresHumain.getPokemon(4).apprendCapacites(cap);
		dresHumain.getPokemon(5).apprendCapacites(cap);
		System.out.println(dresHumain.getPokemon(0));

		dresHumain.setPokemon(dresHumain.choixPokemonCombat());
		
		loggerMain.log(Level.INFO, "Pokemon dresser : capacities learned");

		System.out.println("------------------------------------------------------------------------------------------------------ \n");
		*/
		
		
		//Combat combatStart = new Combat(dresHumain,ia);
		//combatStart.commence();
		
		DresseurMinMax ia = new DresseurMinMax("Expert");
        HumanDresseur humain = new HumanDresseur("Humain");

        ia.setRanch((Pokemon[]) pokedex.engendreRanch());
        humain.setRanch((Pokemon[]) pokedex.engendreRanch());

        ia.getMinMax().initCapacitesRanch();


        System.out.println("------------------------------------------------------------------------------------------------------ \n");
        Combat combat = new Combat(ia, humain);
        ia.getMinMax().setCombat(combat);
        combat.commence();
	}
}
