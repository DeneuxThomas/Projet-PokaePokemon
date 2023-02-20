package capacite;
import java.io.FileNotFoundException;
import java.io.IOException;
import interfaces.ICapacite;
import interfaces.ICategorie;
import interfaces.IPokemon;
import interfaces.IType;
import pokedex.Pokedex;
import type.Type;

/**
 * This class expresses the abilities of a pokemon
 */
public class Capacite implements ICapacite  {

	private String name;
	private long power;
	private double accuracy;
	private long PP;
	private long number;
	private Categorie category;
	private Type type;
	private long lvl;
	
	/**
	 * constructor of the class
	 * @param name of the capacity
	 */
	public Capacite(String name) {
		this.name = name;
	}
	
	/**
	 * Constructeur de capacite a partir d'une capacite en parametre
	 *	
	 */
	public Capacite(ICapacite capacite) {
		this.name = capacite.getNom();
		this.power = capacite.getPuissance();
		this.accuracy = capacite.getPrecision();
		this.PP = capacite.getPP();
		this.number = 0;
		this.category = (Categorie) capacite.getCategorie();
		this.type = (Type) capacite.getType();
	}
	
	
	
	/**
	 * constructor of the class with all param of a capacity
	 * @param name
	 * @param power
	 * @param accuracy
	 * @param PP
	 * @param number
	 * @param category
	 * @param type
	 */
	public Capacite(String name, long power, double accuracy, long PP, long number, Categorie category, Type type,long lvl) {
		super();
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.PP = PP;
		this.number = number;
		this.category = category;
		this.type = type;
		this.lvl = lvl;
	}
	
	/**
	 * constructor of the class with all param of a capacity without level
	 * @param name
	 * @param power
	 * @param accuracy
	 * @param PP
	 * @param number
	 * @param category
	 * @param type
	 */
	public Capacite(String name, long power, double accuracy, long PP, long number, Categorie category, Type type) {
		super();
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.PP = PP;
		this.number = number;
		this.category = category;
		this.type = type;
	}
	

	/**
	 * constructor of the class
	 */
	public Capacite() {
		
	}



	public void afficheInfo() {
		System.out.println( "Capacite [name=" + name + ", power=" + power + ", accuracy=" + accuracy + ", PP=" + PP + ", number="
				+ number + ", category=" + category + ", type=" + type + ", lvl=" + lvl + "]");
	}

	/**
	 * the damage that the pokemon will receive
	 * @param launcher
	 * @param receiver
	 * @return points of damage
	 */
	@Override
	public int calculeDommage(IPokemon launcher, IPokemon receiver) {

		double damage;
		if(accuracy<=0.3) {
			return receiver.getStat().getPV();
		}
		
		if(category.isSpecial()) {
			damage = (((0.4 * launcher.getNiveau() + 2) * launcher.getStat().getSpecial() * power)  /  (50 * receiver.getStat().getSpecial()) + 2);
		}else {
			damage = (((0.4 * launcher.getNiveau() + 2) * launcher.getStat().getForce() * power)  /  (50 * receiver.getStat().getDefense()) + 2);
		}

		try {
			damage *= coefficientMultiplier(launcher, receiver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (int)damage;
	}
	
	/**
	 * calculation of the  coefficient multiplier
	 * @param launcher
	 * @param receiver
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private double coefficientMultiplier(IPokemon launcher, IPokemon receiver) throws FileNotFoundException, IOException{
		double RandomProduct;
		double efficiency;
		double stab = 1;


		if(launcher.getEspece().getTypes()[1] != null) {
			if(launcher.getEspece().getTypes()[0].equals(type) || launcher.getEspece().getTypes()[1].equals(type)) {
				stab = 1.5;
			}
		}else {
			if(launcher.getEspece().getTypes()[0].equals(type)) {
				stab = 1.5;
			}
		}
		RandomProduct = 0.85 + ((0.15) * Math.random());

		Pokedex pokedex = new Pokedex();
		pokedex.initEfficacity("efficacites.csv");

		efficiency = pokedex.getEfficacite(this.type, receiver.getEspece().getTypes()[0]);
		if(receiver.getEspece().getTypes()[1] != null) {
			efficiency *= pokedex.getEfficacite(this.type, receiver.getEspece().getTypes()[1]);
		}

		return stab * RandomProduct * efficiency;
	}

	@Override
	public void utilise(){
		PP--;
		
	}


	/**
	 * give the name
	 * @return name of the capacity
	 */
	@Override
	public String getNom() {
		
		return name;
	}
	
	/**
	 * give the accuracy
	 * @return accuracy
	 */
	@Override
	public double getPrecision() {
		
		return accuracy;
	}
	/**
	 * give the power
	 * @return the power
	 */
	@Override
	public int getPuissance() {
		
		return (int)power;
	}

	/**
	 * @return The number of times this capability can be used
	 */
	@Override
	public int getPP() {
		
		return (int)PP;
	}

	/**
	 * make PP to maximum capacity
	 */
	@Override
	public void resetPP() {

		
	}

	/**
	 * @return Category of ability (Physical or Special)
	 */
	@Override
	public ICategorie getCategorie() {
			
		return category;
	}
	
	
	/**
	 * give the id
	 * @return id
	 */
	public int getNumber() {
		return (int)number;
	}
	
	/**
	 * @return type
	 */
	@Override
	public IType getType() {
		
		return type;
	}
	
	/**
	 * give the level
	 * @return lvl
	 */
	public long getLvl() {
		return lvl;
	}
	
	
}