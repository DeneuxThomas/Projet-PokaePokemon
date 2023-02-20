//////////////////////////////////////////////////////////////////////////////////////////////

package pokedex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import capacite.Capacite;
import capacite.Categorie;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IPokedex;
import interfaces.IPokemon;
import interfaces.IType;
import pokemon.Espece;
import pokemon.Pokemon;


import type.Type;

/**
 * this class initialize the pokedex with file csv
 *	
 */
public class Pokedex implements IPokedex {
	
	HashMap<Integer , Espece> IndiceEspece = new HashMap <>();
	HashMap<String , Espece> NomEspece = new HashMap <>();
	HashMap<String , Capacite> ListCapacity = new HashMap <>();
	HashMap<List<Type>, Double> ListEfficacity = new HashMap<>();
	HashMap<Capacite , Long> capDispo = new HashMap <>();
	
    private Type typeOffensif;
    private Type typeDefensif;
    private double efficiency;
    Capacite[] tab = new Capacite[20];
	int num ;
	String name ;
	int baseHP ;
	int baseAttack ;
	int baseDef ;
	int baseSpecial ;
	int baseSpeed;
	int baseExp ;
	int evHP ;
	int evAttack ;
	int evDef ;
	int evSpecial;
	int evSpeed ;
	String type1;
	String type2;
	int baseLevel ;
	int mutationLevel ;
	String evolution ;
    Capacite[] cap = new Capacite[100];
    private Type[] types = new Type[2] ;
	private String capName;
	private int pp;
	private int capNumero;
	private Categorie categorie;
	private Type typeCap;
	private int power;
	private double accuracy;
	/**
	 * @return HashMap<Capacite, Long>
	 */	
	  public HashMap<Capacite, Long> getMap() {
	         return capDispo;
	  }
	
	  /**
	   * Initialize the attributes of a pokemon from pokedex and create the species from a csv file
	   * @param FileName
	   * @throws IOException
	   */
	public void initOfEspece(String FileName) throws IOException {
		
	    try {	    	
	    	FileReader file = new FileReader(FileName);
	    	BufferedReader reader = new BufferedReader (file) ;
	    	reader.readLine();
	    	
	    	while (reader.ready()) {
	    		String line = reader.readLine();
	    		Scanner st = new Scanner(line).useDelimiter(";");

	    		 num = st.nextInt();
	    		 name = st.next();
	    		 baseHP = st.nextInt();
	    		 baseAttack = st.nextInt();
	    		 baseDef = st.nextInt();
	    		 baseSpecial = st.nextInt();
	    		 baseSpeed = st.nextInt();
	    		 baseExp = st.nextInt();
	    		 evHP = st.nextInt();
	    		 evAttack = st.nextInt();
	    		 evDef = st.nextInt();
	    		 evSpecial = st.nextInt();
	    		 evSpeed = st.nextInt();
	    		 String type1 = st.next();    		
	    		 types[0] = conversionToType(type1);
	    		 String type2 = st.next();
	    		 types[1] = conversionToType(type2);	    
	    		 baseLevel = st.nextInt();
	    		 mutationLevel = st.nextInt();
	    		 evolution = st.next();
	    		Espece pok = new Espece (num,name,baseHP,baseAttack,baseDef,baseSpecial,baseSpeed,baseExp,evHP,evAttack,evDef,evSpecial,evSpeed,types,baseLevel,mutationLevel,evolution);
	    		IndiceEspece.put(num, pok);
	    		NomEspece.put(name, pok);

	    		pok.getBaseStat().setPV(baseHP);
	    		pok.getBaseStat().setDefense(baseDef);
	    		pok.getBaseStat().setForce(baseAttack);
	    		pok.getBaseStat().setSpecial(baseSpecial);
	    		pok.getBaseStat().setVitesse(baseSpeed);
    		
	    	st.close();
	    	}
	    	reader.close();
	    	file.close();
	    	} catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
	  }
	
	
	/**
	 * Print the logo Pokemon in Ascii
	 */
	public void PrintPok(){
		
		
		 try {
			 FileReader reader = new FileReader("Title.txt");
			 int text = reader.read();
			 while(text != -1) {
				 System.out.print((char)text);
				 text = reader.read();
			 }
			 reader.close();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
	 }
	
	
	/**
	 * @return list of capacity
	 */
	public Capacite[] remplirTabDeCapacite() {
		  
	  	Capacite[] capa = new Capacite[110];//110 pokemons
	  	
	  	for(int i = 0 ; i<cap.length ; i++)
	  		
	  		for(Entry<String, Capacite> capac : ListCapacity.entrySet()) {
	  			
				cap[i] = capac.getValue();
				i++;
		}
			return capa;
  }
  

	
	/**
	 * Initialize the abilities of a pokemon and create the ability
	 * @param FileName
	 */
	public void initCapacity(String FileName) {

	    try {   	
	    	FileReader file = new FileReader(FileName);
	    	BufferedReader reader = new BufferedReader (file) ;
	    	reader.readLine();
	    	
	    	while (reader.ready()) {
	    		String line = reader.readLine();
	    		Scanner s = new Scanner(line).useDelimiter(";");
	    	
	    			capName = s.next();
	    			power = s.nextInt() ;
	    			String prec = s.next();
	    			accuracy = Double.parseDouble(prec);
	    			pp = s.nextInt();
	    			capNumero= s.nextInt();
	    			String cat = s.next() ;
	    			String typeC = s.next();
	    			categorie = conversionToCategorie(cat);
	    			typeCap = conversionToType(typeC);
		
		Capacite cap = new Capacite (capName,power,(double) accuracy,pp,capNumero,categorie,typeCap);	
		ListCapacity.put(capName, cap);
	
		s.close();
	    	}
	    	reader.close();
	    	file.close();
	    	} catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    	} catch (IOException e) {
	    	e.printStackTrace();
	    	}
		
	
  }
  
	/**
	 * Get a capacity from PokeApi with the Name of the capacity in parameter
	 * @param name
	 * @throws org.json.simple.parser.ParseException
	 * @throws ParseException
	 * @throws IOException
	 */
	public void recoverPokemonCapacity(String name) throws org.json.simple.parser.ParseException, ParseException, IOException {
      

                
                URL hpo = new URL("https://pokeapi.co/api/v2/pokemon/"+name.toLowerCase()+"/");
                
                
                    
                    HttpURLConnection hpCone = (HttpURLConnection) hpo.openConnection();
                    hpCone.connect();

                    BufferedReader streamReaders = new BufferedReader(new InputStreamReader(hpCone.getInputStream()));
                    StringBuilder responseStrBuilders = new StringBuilder();

                    String inputStrs ="";
                    
                        while ((inputStrs = streamReaders.readLine()) != null) {
                            responseStrBuilders.append(inputStrs);
                        }
                        
                inputStrs = responseStrBuilders.toString();
                streamReaders.close();

                JSONObject objV2 = (JSONObject) new JSONParser().parse(inputStrs);
                    JSONArray moves = (JSONArray) objV2.get("moves");
                    for (Object m : moves) {    
                            JSONObject jsonObjV1 = (JSONObject) m;
                                JSONObject move = (JSONObject) jsonObjV1.get("move");
                                String nameCap = (String) move.get("name");
                            	URL hp1 = new URL("https://pokeapi.co/api/v2/move/"+ move.get("name") +"/");
	                            HttpURLConnection hpCon1 = (HttpURLConnection) hp1.openConnection();
	                            hpCon1.connect();
	                            BufferedReader streamReader1 = new BufferedReader(new InputStreamReader(hpCon1.getInputStream()));
	                            StringBuilder responseStrBuilder1 = new StringBuilder();
	                            String inputStr1 ="";
	                            while ((inputStr1 = streamReader1.readLine()) != null) {
	                                responseStrBuilder1.append(inputStr1);
	                            }
	                            inputStr1 = responseStrBuilder1.toString();
	                            streamReader1.close();

	                            JSONObject obj1 = (JSONObject) new JSONParser().parse(inputStr1);
	                            JSONObject cat = (JSONObject) obj1.get("damage_class");
	                                             
	                            long pp =0;
	                            if(obj1.get("pp")!=null) {
	                            	pp = (long) obj1.get("pp");
	                            }
	                            long power=0;
	                            if(obj1.get("power")!=null) {
	                            	power = (long) obj1.get("power");
	                            }
	                            long accuracy = 0;
	                            if(obj1.get("accuracy")!=null) {
	                            	accuracy= (long) obj1.get("accuracy");
	                            }
                            
	                            long id = (long) obj1.get("id");
	                            
	                            JSONObject getType = (JSONObject) obj1.get("type");
	                            String typ = (String) getType.get("name"); 
	                            String categori = (String) cat.get("name");
                                
	                            Type type = conversionToType(typ);
	                            Categorie categorie = conversionToCategorie(categori);                             
                                
                                    JSONArray groupDet = (JSONArray) jsonObjV1.get("version_group_details");
                                    for(Object l : groupDet) {
                                            JSONObject jsonObjV2 = (JSONObject) l;
                                            JSONObject firstG = (JSONObject) jsonObjV2.get("version_group");
                                            String version = (String) firstG.get("name");
                                            long lvlcap = (long) jsonObjV2.get("level_learned_at");
                                            String gRed = "red-blue";
                                           
                                                if(version.contains(gRed) && (categori.contains("physical") || categori.contains("special")) ) {
                                                        Capacite capa = new Capacite(nameCap,power,accuracy,id,pp,categorie,type,lvlcap);
                                                        capDispo.put(capa,lvlcap);

                                                    
                                                    }
                                                    
                                            }
                            
                                        
                                    }
                            
                           }

	
	
	/**
	 * Initialize the type efficiency table 
	 * @param FileName
	 * @throws IOException
	 */
	public void initEfficacity(String FileName) throws IOException {
  
	  FileReader file = new FileReader(FileName);
      BufferedReader reader = new BufferedReader(file);
      reader.readLine();

      while(reader.ready()) {

          String line = reader.readLine();
          try (Scanner ste = new Scanner(line).useDelimiter(";")) {
			String typeOffensifString = ste.next();
			  typeOffensif = conversionToType(typeOffensifString);

			  for(Type type : Type.values()) {
			          efficiency = Double.parseDouble(ste.next());
			          typeDefensif = type;
			          List<Type> types = Arrays.asList(typeOffensif, typeDefensif);
			          ListEfficacity.put(types, efficiency);
			      }
		} catch (NumberFormatException e) {		
			e.printStackTrace();
		}
      }
      reader.close();
      file.close();
  }
  
  
	/**
	* convert types String of type To type Type
	* @param EspeceType
	* @return type
	*/
	private Type conversionToType(String EspeceType) {
    Type type = null;
		if(EspeceType.equals("fighting")){
			type = Type.Fighting;
		}
		else if(EspeceType.equals("dragon")){
			type = Type.Dragon;
		}
        else if(EspeceType.equals("water")){
			type = Type.Water;
		}
        else if(EspeceType.equals("electric")){
			type = Type.Electric;
		}
        else if(EspeceType.equals("fire")){
			type = Type.Fire;
		}
        else if(EspeceType.equals("ice")){
			type = Type.Ice;
		}
		else if(EspeceType.equals("bug")){
			type = Type.Bug;
		}
		else if(EspeceType.equals("normal")){
			type = Type.Normal;
		}
        else if(EspeceType.equals("grass")){
			type = Type.Grass;
		}
		else if(EspeceType.equals("poison")){
			type = Type.Poison;
		}
        else if(EspeceType.equals("psychic")){
			type = Type.Psychic;
		}
		else if(EspeceType.equals("rock")){
			type = Type.Rock;
		}
		else if(EspeceType.equals("ground")){
			type = Type.Ground;
		}
		else if(EspeceType.equals("ghost")){
			type = Type.Ghost;
		}
        else if(EspeceType.equals("flying")){
			type = Type.Flying;
		}

    return type;
}
	
	/**
	 * convert types String of type To type Categorie
	 * @param category
	 * @return cat
	 */
	private Categorie conversionToCategorie(String categorie) {
		Categorie cat;
		switch (categorie) {
			case "physical":
				cat = Categorie.Physique;
				break;
			case "special":
				cat = Categorie.Special;
				break;
			default:
				cat = null;
				break;
		}

		return cat;
    }
	
	
	/**
	 * Returns an array of 6 random Pokemon
	 * @return ranch
	 */
	@Override
	public IPokemon[] engendreRanch() {
        ArrayList<Espece> pokemonsLVL1 = new ArrayList<Espece>();
        Pokemon[] ranch = new Pokemon[6];

        Random random = new Random();

        
        for(Map.Entry<Integer,Espece> pokemons :  IndiceEspece.entrySet()) {
            if(pokemons.getValue().getNiveauDepart() == 1) {
            	pokemonsLVL1.add(pokemons.getValue());
            }
        }
       
        for(int i = 0; i < ranch.length; i++) {
            Pokemon pokemon = new Pokemon(pokemonsLVL1.get(random.nextInt(pokemonsLVL1.size())));
            ranch[i] = pokemon;
        }

        return ranch;
    }
	
	/**
	 * Returns an instance of the Pokemon species whose name is given
	 * @param nameEspece
	 * @return 
	 */
	@Override
	public IEspece getInfo(String nomEspece) {	
		for(Entry<String,Espece>espece : NomEspece.entrySet()) {
			if (espece.getValue().getNom().equals(nomEspece)) {
				 num = espece.getValue().getId();
	    		 name = espece.getKey();
	    		 baseHP = espece.getValue().getBaseStat().getPV();
	    		 baseAttack = espece.getValue().getBaseStat().getForce();
	    		 baseDef = espece.getValue().getBaseStat().getDefense();
	    		 baseSpecial = espece.getValue().getBaseStat().getSpecial();
	    		 baseSpeed = espece.getValue().getBaseStat().getVitesse();	    		 
	    		 types = (Type[]) espece.getValue().getTypes();
	    		 baseExp = espece.getValue().getBaseExp();
		         baseLevel = espece.getValue().getNiveauDepart();
	    		 mutationLevel = espece.getValue().getLevelEvolution();
	    		 evolution = espece.getValue().getEvolution();
	    		 evHP = espece.getValue().getGainsStat().getPV();
	    		 evAttack = espece.getValue().getGainsStat().getForce();
	    		 evDef = espece.getValue().getGainsStat().getDefense();
	    		 evSpecial = espece.getValue().getGainsStat().getSpecial();
	    		 evSpeed = espece.getValue().getGainsStat().getVitesse();	            
	        }
	    }
		Espece esp = new Espece(num,name,baseHP,baseAttack,baseDef,baseSpecial,
				baseSpeed,baseExp,evHP,evAttack,evDef,evSpecial,evSpeed,types,baseLevel,mutationLevel,evolution);		
		esp.printEspece();		
	    return esp;	    
	}
	
	/**
	 * Calculates the effectiveness of an attack type on a target type
	 * @param attack
	 * @param defense
	 * @return a list of efficacity
	 */
	@Override
	public Double getEfficacite(IType attaque, IType defense) {			
		Type att = (Type) attaque;
        Type def = (Type) defense;
        List<Type> types = Arrays.asList(def, att);
        return ListEfficacity.get(types);
	}
	
	/**
	 * Returns an instance of the capability called capabilityName
	 * @param nomCapacite
	 * @return a capacity
	 */
	@Override
	public ICapacite getCapacite(String nomCapacite) {
	
		for(Entry<String,Capacite>cap : ListCapacity.entrySet()) {
			
			if (cap.getValue().getNom().equals(nomCapacite)) {
		 
				capName = cap.getValue().getNom();
				power = cap.getValue().getPuissance();
				accuracy = (double)cap.getValue().getPrecision();
				pp = cap.getValue().getPP();
				capNumero = (int) cap.getValue().getNumber();
				categorie = (Categorie) cap.getValue().getCategorie();
				typeCap = (Type) cap.getValue().getType();
				
			}
		}
		Capacite capB = new Capacite(capName,power,(double) accuracy,pp,capNumero,categorie,typeCap);	
		capB.afficheInfo();
		return capB;
	}
	
	
	  public Capacite[] FillCapaciteTab() {
		  
		  	Capacite[] cap = new Capacite[110];
		  	for(int i = 0 ; i<cap.length ; i++)
		  		for(Entry<String, Capacite> capac : ListCapacity.entrySet()) {
					cap[i] = capac.getValue();
					i++;
			}
				return cap;
	  }
	  
	

	
	  /**
	    * Returns an instance of the capability called capabilityName
		* @param numCapacite
		* @return a capacity
		*/
	@Override
	public ICapacite getCapacite(int nunCapacite) {
		for(Entry<String,Capacite>cap : ListCapacity.entrySet()) {	
			if (cap.getValue().getNumber() == nunCapacite) {
				capName = cap.getValue().getNom();
				power = cap.getValue().getPuissance();
				accuracy = cap.getValue().getPrecision();
				pp = cap.getValue().getPP();
				capNumero = (int) cap.getValue().getNumber();
				categorie = (Categorie) cap.getValue().getCategorie();
				typeCap = (Type) cap.getValue().getType();			
			}
		}
		Capacite capA = new Capacite(capName,power,(double) accuracy,pp,capNumero,categorie,typeCap);		
		capA.afficheInfo();
		return capA;
	}

	/**
	 * Renvoie le niveau de mutation au quel le pokemon peut evoluer
	 *	
	 */	

	public int getmutationLevel() {
		System.out.println("Le niveau de mutation pour " + name + " est "+ mutationLevel);
		return mutationLevel;
	}

	/**
	 * Renvoie le HashMap<Integer, Espece>
	 *	
	 */
	public HashMap<Integer, Espece> getPokeInfoIndice() {
		return IndiceEspece;
	}

	/**
	 * Renvoie le HashMap<String, Espece>
	 *	
	 */	
	public HashMap<String, Espece> getPokeInfoNom() {
		return NomEspece;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public HashMap<String, Capacite> getCapInfo() {
		return ListCapacity;
	}
}




