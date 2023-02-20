package dresseur;

import org.junit.Test;

import combat.Combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;
import java.util.Arrays;

import pokedex.Pokedex;
import pokemon.Espece;
import pokemon.Pokemon;
import type.Type;


public class TestDresseur {


	@Test
    public void nomIATest() throws IOException {
		DresseurIANormal ia = new DresseurIANormal("");
		String nomIA = ia.getNom();
		boolean test = false;
    	String [] tab = {"Jean","Guillaume","Luc","Sarah","Julie","Chen","Tili","Oak","Andrea","Sacha","Pedro","Mickael","Enzo","Antonin"};
    	
    	for (int i = 0; i < tab.length ; i++) {
    		if(nomIA == tab[i]) {
    			test = true;
    		}
    	}
    	
    	assertEquals(test, true);
    }
	
	@Test 
	public void IAestKO() throws IOException{
		DresseurIANormal ia = new DresseurIANormal("");
		Pokedex pok = new Pokedex();
		pok.initOfEspece("listePokemon1G.csv");
		
		ia.setRanch((Pokemon[]) pok.engendreRanch());
		Pokemon pokeIA;
		
		for (int i = 0; i<6; i++) {
			pokeIA = ia.getRandomPokemon();
			int pv = pokeIA.getStat().getPV();
			
			while(pv == 0) {
				pokeIA = ia.getRandomPokemon();
				pv = pokeIA.getStat().getPV();
			}
			
			pokeIA.getStat().setPV(0);
			
		}
		
		boolean test = ia.dresseurEvanoui();		
		assertEquals(test, true);
		
	}
	
	@Test
	public void IAChangePoke() throws IOException{
		
		Attaque attaque = new Attaque();
		
		DresseurIANormal ia = new DresseurIANormal("");
		Pokedex pok = new Pokedex();
		pok.initOfEspece("listePokemon1G.csv");
		
		ia.setRanch((Pokemon[]) pok.engendreRanch());
		Pokemon pokeIA1 = ia.getRanch()[0];
		Pokemon pokeIA2 = ia.getRanch()[0];
		
		assertEquals(pokeIA1, pokeIA2);
		
	//	ia.echangeCombattant().getEspece();
		pokeIA2 = ia.getRanch()[1];
		attaque.setPokemon(pokeIA2);
		attaque.setCap(null);
		
		assertNotEquals(pokeIA1, pokeIA2);
	}
	
	@Test
	public void IAgetRandomPoke() throws IOException{
		
		DresseurIANormal ia = new DresseurIANormal("");
		Pokedex pok = new Pokedex();
		pok.initOfEspece("listePokemon1G.csv");
		
		ia.setRanch((Pokemon[]) pok.engendreRanch());
		Pokemon pokeIA1 = ia.getRanch()[0];
		Pokemon pokeIA2 = ia.getRanch()[0];
		
		while(pokeIA2 == pokeIA1) {
			pokeIA2 = ia.getRandomPokemon();
		}
		
		assertNotEquals(pokeIA1, pokeIA2);
	}
	
		
}

