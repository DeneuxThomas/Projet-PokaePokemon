package pokemon;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import type.Type;

public class TestPokemon {
	
	Type typeTab[] = {Type.Normal, null};

	@Test
    public void getNomTest() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp);
		assertEquals(pok.getNom(),"Rattata");
    }	
	
	@Test
	void testGetNiveau() throws IOException { 
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp,"Rattata");
		assertEquals(pok.getNiveau(),11);
	}
	
	@Test
	void testGetPourcentagePV() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp);
		assertEquals(pok.getPourcentagePV(),100);		
	}
		
    @Test
	void testEstEvanoui() throws IOException { 
    	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp, "Rattata");
		assertEquals(pok.estEvanoui(), false);
	}
		
	@Test
	void testAChangeNiveau() throws IOException { 
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp, "Rattata");
		assertEquals(pok.aChangeNiveau(),false);
	}
		
	@Test
	void testPeutMuter() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp, "Rattata");
		assertEquals(pok.peutMuter(),true);
	}
	
    @Test
    public void getEvolutionTest() throws IOException {
    	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp, "Rattata");
		assertEquals(pok.getEspece().getEvolution(0),"Raticate");
    }
		
    @Test
    public void soigneTest() throws IOException {
    	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		Pokemon pok = new Pokemon(esp, "Rattata");
		assertEquals(pok.getStat().getPV(),pok.getMaxHP());   	 	  	
    }
    
}