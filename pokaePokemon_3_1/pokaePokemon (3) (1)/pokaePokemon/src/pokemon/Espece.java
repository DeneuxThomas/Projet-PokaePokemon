package pokemon;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;


import capacite.Capacite;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IStat;
import interfaces.IType;
import pokedex.Pokedex;
import stats.Stats;
import type.Type;

public class Espece implements IEspece{
	private int id;
	private String name;
	private int baseHP;
	private int baseStrength;
	private int baseDefense;
	private int baseSpecial;
	private int baseSpeed;
	private int baseExperience;
	private int StartingLevel;
	private int MutationLevel;
	private String evolution;
	private Type type1;
	private Type type2;
	private int hpGain;
    private int StrengthGain;
    private int defenseGain;
    private int specialGain;
    private int SpeedGain;
	
	


	public Espece(String name, int baseHP, int forceBase, int defBase, int specialBase, int vitesseBase, int expBase,
			int hpGain, int StrengthGain, int defenseGain, int specialGain, int SpeedGain, Type[] types, int niveauBase,
			int nivMutation, String evolution) {
		
		this.name = name;
		this.baseHP = baseHP;
		this.baseStrength= forceBase;
		this.baseDefense = defBase;
		this.baseSpecial = specialBase;
		this.baseSpeed = vitesseBase;
		this.baseExperience = expBase;
		this.hpGain = hpGain;
		this.StrengthGain = StrengthGain;
		this.defenseGain = defenseGain;
		this.type1 = types[0];
		this.type2 = types[1];
		this.specialGain = specialGain;
		this.SpeedGain = SpeedGain;
		this.StartingLevel = niveauBase;
		this.MutationLevel = nivMutation;
		this.evolution = evolution;		
	}
	


	public Espece () {
		
	}

	public Espece(int num, String name2, int baseHP2, int baseAttack, int baseDef, int baseSpecial2, int baseSpeed2,
			int baseExp, int evHP, int evAttack, int evDef, int evSpecial, int evSpeed, Type[] types, int baseLevel,
			int mutationLevel2, String evolution2) {
		// TODO Auto-generated constructor stub
		this.id = num;
		this.name = name2;
		this.baseHP = baseHP2;
		this.baseStrength= baseAttack;
		this.baseDefense = baseDef;
		this.baseSpecial = baseSpecial2;
		this.baseSpeed = baseSpeed2;
		this.baseExperience = baseExp;
		this.hpGain = evHP;
		this.StrengthGain = evAttack;
		this.defenseGain = evDef;
		this.type1 = types[0];
		this.type2 = types[1];
		this.specialGain = evSpecial;
		this.SpeedGain = evSpeed;
		this.StartingLevel = baseLevel;
		this.MutationLevel = mutationLevel2;
		this.evolution = evolution2;	
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	
	public void printEspece() {
		System.out.println( "Espece [id=" + id + ", name=" + name + ", baseHP=" + baseHP + ", baseStrength=" + baseStrength
				+ ", baseDefense=" + baseDefense + ", baseSpecial=" + baseSpecial + ", baseSpeed=" + baseSpeed
				+ ", baseExperience=" + baseExperience + ", StartingLevel=" + StartingLevel + ", MutationLevel="
				+ MutationLevel + ", evolution=" + evolution + ", type1=" + type1 + ", type2=" + type2 + ", hpGain="
				+ hpGain + ", StrengthGain=" + StrengthGain + ", defenseGain=" + defenseGain + ", specialGain="
				+ specialGain + ", SpeedGain=" + SpeedGain + "]");
	}


	/**
	 * Give the stats of the Espece of the Pok�mon
	 * * @return the stat variable
	 */
	@Override
	public IStat getBaseStat() {
		
		Stats stat = new Stats() {

			/**
			 * Allows us to access the baseHP variable
			 * @return the baseHP variable
			 */
			public int getPV() {
				return baseHP ;
			}

			/**
			 * Allows us to access the baseStrength variable
			 * @return the baseStrength variable
			 */
			@Override
			public int getForce() {
				
				return baseStrength;
			}

			/**
			 * Allows us to access the baseDefense variable
			 * @return the baseDefense variable
			 */
			@Override
			public int getDefense() {
				
				return baseDefense;
			}

			/**
			 * Allows us to access the baseSpecial variable
			 * @return the baseSpecial variable
			 */
			@Override
			public int getSpecial() {
				
				return baseSpecial;
			}

			/**
			 * Allows us to access the baseSpeed variable
			 * @return the baseSpeed variable
			 */
			@Override
			public int getVitesse() {
				
				return baseSpeed;
			}

			/**
			 * Allows us to set the baseHP variable
			 * @param i
			 */
			@Override
			public void setPV(int i) {
				
				baseHP = i;
			}

			/**
			 * Allows us to set the baseStrength variable
			 * @param i
			 */
			@Override
			public void setForce(int i) {
				
				baseStrength = i;
			}

			/**
			 * Allows us to set the baseDefense variable
			 * @param i
			 */
			@Override
			public void setDefense(int i) {
				
				baseDefense = i;
			}

			/**
			 * Allows us to set the baseSpeed variable
			 * @param i
			 */
			@Override
			public void setVitesse(int i) {
				
				baseSpeed = i;
			}

			/**
			 * Allows us to set the baseSpecial variable
			 * @param i
			 */
			@Override
			public void setSpecial(int i) {
				
				baseSpecial =i;
			}
		};
		return stat;
	}

	/**
	 * Give the name of the Espece
	 * @return the name
	 */
	@Override
	public String getNom() {
		
		return name;
	}

	/**
	 * Give the starting level of the Espece
	 * @return the starting level
	 */
	@Override
	public int getNiveauDepart() {
		
		return StartingLevel;
	}

	/**
	 * Give the basic experience of the Espece
	 * @return the basic experience
	 */
	@Override
	public int getBaseExp() {
		
		return baseExperience;
	}

	
	@Override
	public IStat getGainsStat() {
		
		Stats gainSt = new Stats() {

			/**
			 * Allows us to access the hpGain variable
			 @return the hpGain variable
			 */
			public int getPV() {
				
				return hpGain ;
			}

			/**
			 * Allows us to access the StrengthGain variable
			 * @return the StrengthGain variable
			 */
			@Override
			public int getForce() {
				
				return StrengthGain;
			}

			/**
			 * Allows us to access the defenseGain variable
			 * @return the defenseGain variable
			 */
			@Override
			public int getDefense() {
				
				return defenseGain;
			}

			/**
			 * Allows us to access the specialGain variable
			 @return the specialGain variable
			 */
			@Override
			public int getSpecial() {
				
				return specialGain;
			}

			/**
			 * Allows us to access the SpeedGain variable
			 @return the SpeedGain variable
			 */
			@Override
			public int getVitesse() {
				
				return SpeedGain;
			}

			/**
			 * Allows us to set the hpGain variable
			 * @param i
			 */
			@Override
			public void setPV(int i) {
				
				hpGain = i;
			}

			/**
			 * Allows us to set the StrengthGain variable
			 * @param i
			 */
			@Override
			public void setForce(int i) {
				
				StrengthGain = i;
			}

			/**
			 * Allows us to set the defenseGain variable
			 * @param i
			 */
			@Override
			public void setDefense(int i) {
				
				defenseGain = i;
			}

			/**
			 * Allows us to set the SpeedGain variable
			 * @param i
			 */
			@Override
			public void setVitesse(int i) {
				
				SpeedGain = i;
			}

			/**
			 * Allows us to set the specialGain variable
			 * @param i
			 */
			@Override
			public void setSpecial(int i) {
				
				specialGain = i;
			}
		};
		
		return gainSt;
	}


	/**
	 * Give the evolution of the espece
	 * @param level
	 * @return esp or NULL if there is no evolution available 
	 */
	@Override
	public IEspece getEvolution(int level) {
        Espece esp=new Espece();
        Pokedex pok = new Pokedex();
    if (level >= MutationLevel && MutationLevel!=0) {
            try {
                FileReader fichier = new FileReader("listePokemon1G.csv");
                BufferedReader reader = new BufferedReader(fichier);
                reader.readLine();
                while (reader.ready()) {
                    String line = reader.readLine();
                    Scanner scan = new Scanner(line).useDelimiter(";");
                    scan.next();
                    if (evolution.equals(scan.next())) {
                            pok.initOfEspece("listePokemon1G.csv");
                            esp= (Espece) pok.getInfo(evolution);
                    }
                    scan.close();
                }
                reader.close();
                fichier.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return esp;
        }
        else {
            return null;
        }
}

	/**
	 * Give the type(s) of the espece
	 * @return the type
	 */
	@Override
	public IType[] getTypes() {
	
            Type[] type = new Type[2];
            type[0]=this.type1;
			type[1]=this.type2;
            
    	return type;
	}
	
	/**
	 * Give the level of the evolution
	 * @return the mutationLevel
	 */
	public int getLevelEvolution() {
		return MutationLevel;
	}

	/**
	 * Give the evolution of the espece
	 * @return the evolution
	 */
	public String getEvolution() {
		return evolution;
	}

	/**
	 * Set the name of the espece
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public ICapacite[] getCapSet() {
		Pokedex pok = new Pokedex();
		pok.getMap();
		try {
			pok.recoverPokemonCapacity(this.name);
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			throw new RuntimeException(e);
		}

		Capacite[] cap = new Capacite[pok.getMap().size()];
		int i=0;
		for(Map.Entry<Capacite, Long> capac : pok.getMap().entrySet()) {
			cap[i] = capac.getKey();
			i++;
		}
		return cap;
	}
}
