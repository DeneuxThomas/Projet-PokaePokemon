package pokemon;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import pokedex.Pokedex;
import type.Type;

public class TestEspece {
	
	
	Type typeTab[] = {Type.Normal, null};

	@Test
    public void getNomTest() throws IOException {
    	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getNom(),"Rattata");
    }
	
	@Test
    public void getNiveauDepartTest() throws IOException {
    	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getNiveauDepart(),11);
    }
	
	@Test
    public void getBaseExpTest() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getBaseExp(),35);
    }
	
	@Test
    public void getNiveauMutationTest() throws IOException {
    	Pokedex pok = new Pokedex();
        Espece esp = new Espece();
        pok.initOfEspece("listePokemon1G.csv");
        Pokemon rattata = new Pokemon(pok.getNomEspece().get("Rattata"));
		assertEquals(pok.getNomEspece().get("Rattata").getLevelEvolution(),20);
    }
	
	@Test
    public void getEvolutionTest() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getEvolution(),"Raticate");
	}
	
	@Test
    public void setType1Test() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getTypes()[0],Type.Normal);	
	}

	@Test
    public void setType2Test() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getTypes()[1],null);
	
	}

	@Test
    public void setNomTest() throws IOException {
		
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		esp.setName("Le Rat");
		assertEquals(esp.getNom(),"Le Rat");
	}  
	
	
	@Test
    public void getStatPvTest() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getBaseStat().getPV(),10);   	
    }
	    
  @Test
    public void getStatForceTest() throws IOException {
	  
	  Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getBaseStat().getForce(),15);   
	
    }

  @Test
    public void getStatDefenseTest() throws IOException {
		Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getBaseStat().getDefense(),20);    	
    }

  @Test
    public void getStatSpecialTest() throws IOException {
	  	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
		assertEquals(esp.getBaseStat().getSpecial(),25);     	
    }

  @Test
    public void getStatSetForceTest() throws IOException {
	  	Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
	  	esp.getBaseStat().setForce(12);;
		assertEquals(esp.getBaseStat().getForce(),12);
  	
    }

  @Test
    public void getStatSetDefenseTest() throws IOException {
	  Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
	  	esp.getBaseStat().setDefense(69);;
		assertEquals(esp.getBaseStat().getDefense(),69);  	
    }

  @Test
    public void getStatSetSpecialTest() throws IOException {
	  Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
	  	esp.getBaseStat().setSpecial(12);;
		assertEquals(esp.getBaseStat().getSpecial(),12);  	
    }
	    
   @Test
    public void getStatSetVitesseTest() throws IOException {
	   Espece esp = new Espece("Rattata",10,15,20,25,30,35,5,4,2,7,11,typeTab,11,22,"Raticate");
	  	esp.getBaseStat().setVitesse(69);;
		assertEquals(esp.getBaseStat().getVitesse(),69);   	
    }
	
}
