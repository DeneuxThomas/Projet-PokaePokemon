package pokemon;

import dresseur.Attaque;
import interfaces.IAttaque;
import interfaces.ICapacite;
import interfaces.IEspece;
import interfaces.IPokemon;
import interfaces.IStat;

import java.util.Arrays;

import capacite.Capacite;
import stats.Stats;

public class Pokemon implements IPokemon{

	private int id = 0;
	private String name = "";
	private int level;
	private double experience;
	private int HP;
	private int strength;
	private int defense;
	private int special;
	private int speed;
	private int HPEv;
	private int strengthEv;
	private int defenseEv;
	private int specialEv;
	private int speedEv;
	private int HPDv;
	private int strengthDv;
	private int defenseDv;
	private int specialDv;
	private int speedDv;


	private int HPMax;
	private static int generatorID;
	private Capacite[] capacite = new Capacite[4];
	private int LenghtTabCapacite = 0;
	private Espece espece;




	 public Pokemon(Espece espece) {
		 this.name = espece.getNom();
	      this.espece = espece;

	        this.id = generatorID;
	       generatorID++;

		   this.level = espece.getNiveauDepart();

	         setDVStat();
			 gagneStats();
		  soigne();
	 }



    public Pokemon(Espece espece, String name) {
        this.espece = espece;
        this.name = name;
        this.id = generatorID;
        generatorID++;

        this.level = espece.getNiveauDepart();
        setDVStat();
        gagneStats();
        soigne();

	 }


    private void gagneStats() {
        // Stats

        setMaxHP(gainPV());

        strength = gainStat(getEspece().getBaseStat().getForce(), strengthDv, strengthEv);
        defense = gainStat(getEspece().getBaseStat().getDefense(), defenseDv, defenseEv);
        speed = gainStat(getEspece().getBaseStat().getVitesse(), speedDv, speedEv);
        special = gainStat(getEspece().getBaseStat().getSpecial(), specialDv, specialEv);
    	}


    /**
     * Allows you to calculate the stats of a Pok�mon
     * @param baseStat
     * @param dV
     * @param eV
     * @return the stats of a Pok�mon
     */
    private int gainStat(int baseStat, int dV, int eV) {

        return (((2*(baseStat + dV) + eV/4)*level) / 100) + 5;

    	}


    /**
     * Allows you to calculate the HP of a Pok�mon
     * @return the HP of the Pok�mon
     */
    private int gainPV() {
        return (((2*(getEspece().getBaseStat().getPV() + HPDv) + HPEv/4)*level) / 100) + level + 10;
    }


	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", level=" + level + ", experience=" + experience + ", HP=" + HP
				+ ", PourcentagePV=" + this.getPourcentagePV() + ", strength=" + strength + ", defense=" + defense + ", special="
				+ special + ", speed=" + speed + ", HPDv=" + HPDv + ", strengthDv=" + strengthDv + ", defenseDv="
				+ defenseDv + ", specialDv=" + specialDv + ", speedDv=" + speedDv + ", HPEv=" + HPEv + ", strengthEv="
				+ strengthEv + ", defenseEv=" + defenseEv + ", specialEv=" + specialEv + ", speedEv=" + speedEv
				+ ", HPMax=" + HPMax + ", capacite=" + Arrays.toString(capacite) + ", espece=" + espece + "]";
	}



	/**
	 * Give the stats of the Pok�mon
	 * @return the stat variable
	 */
	@Override
	public IStat getStat() {

		Stats stat = new Stats() {

			/**
			 * Allows us to access the HP variable
			 * @return the HP variable
			 */
			@Override
			public int getPV() {

				return HP;
			}

			/**
			 * Allows us to access the strength variable
			 * @return the strength variable
			 */
			@Override
			public int getForce() {

				return strength;
			}

			/**
			 * Allows us to access the defense variable
			 * @return the defense variable
			 */
			@Override
			public int getDefense() {

				return defense;
			}


			/**
			 * Allows us to access the special variable
			 * @return the special variable
			 */
			@Override
			public int getSpecial() {

				return special;
			}

			/**
			 * Allows us to access the speed variable
			 * @return the speed variable
			 */
			@Override
			public int getVitesse() {

				return speed;
			}

			/**
			 * Allows us to set the HP variable
			 * @param i
			 */
			@Override
			public void setPV(int i) {

				HP = i;

			}

			/**
			 * Allows us to set the strength variable
			 * @param i
			 */
			@Override
			public void setForce(int i) {

				strength = i;
			}

			/**
			 * Allows us to set the defense variable
			 * @param i
			 */
			@Override
			public void setDefense(int i) {

				defense = i;
			}

			/**
			 * Allows us to set the speed variable
			 * @param i
			 */
			@Override
			public void setVitesse(int i) {

				speed = i;
			}

			/**
			 * Allows us to set the special variable
			 * @param i
			 */
			@Override
			public void setSpecial(int i) {

				special = i;
			}

		};

		return stat;
	}

	/**
	 * Allows the Pok�mon to replace a capacity
	 * @param i
	 * @param cap
	 * @throws Exception
	 */
	@Override
	public void remplaceCapacite(int i, ICapacite cap) throws Exception {
		this.getCapacitesApprises()[i]=cap;

	}

	/**
	 * Set the DVs Stats to a random value
	 */
	private void setDVStat() {
        String HPBinaire = "";

        this.strengthDv = (int)(Math.random() * (16));
        this.defenseDv = (int)(Math.random() * (16));
        this.speedDv = (int)(Math.random() * (16));
        this.specialDv = (int)(Math.random() * (16));

        HPBinaire += lastBit(strengthDv);
        HPBinaire += lastBit(defenseDv);
        HPBinaire += lastBit(speedDv);
        HPBinaire += lastBit(specialDv);

        this.HPDv = Integer.parseInt(HPBinaire, 2);
    }

	/**
	 * Allows the Pok�mon to level up if it has enough experience
	 * @param pokAdverse
	 */
	@Override
	public void gagneExperienceDe(IPokemon pokAdverse) {
		double gainXp = (1.5 * pokAdverse.getNiveau() * pokAdverse.getExperience())/7;

		double xpLevelUp = 0.8*(this.getNiveau())*(this.getNiveau())*(this.getNiveau());

		double xpAct = this.experience;

		if (gainXp+xpAct >= xpLevelUp) {
			aChangeNiveau();
		}

	}

	/**
	 * Allows the Pok�mon to fully heal
	 */
	@Override
	public void soigne() {

		this.HP = this.HPMax;
	}

	/**
	 * Give the capacities of the Pok�mon
	 * @return the capacities
	 */
	@Override
	public ICapacite[] getCapacitesApprises() {
		return capacite;
	}

	/**
	 * Give the percentage of HP of the Pok�mon
	 * @return the percentage of HP
	 */
	@Override
	public double getPourcentagePV() {
		return this.HP;
	}

	/**
	 * Give the experience of the Pok�mon
	 * @return the experience
	 */
	@Override
	public double getExperience() {
		return experience;
	}

	/**
	 * Give the level of the Pok�mon
	 * @return the level
	 */
	@Override
	public int getNiveau() {
		return level;
	}

	/**
	 * Give the id of the Pok�mon
	 * @return the id
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Give the name of the Pok�mon
	 * @return the name
	 */
	@Override
	public String getNom() {
		return name;
	}


	/**
	 * Give the espece of the Pok�mon
	 * @return the espece
	 */
	@Override
	public IEspece getEspece() {

		return espece;
	}

	/**
	 * Give the next evolution of the Pok�mon
	 * @param esp
	 * @return the espece
	 */
	@Override
	public void vaMuterEn(IEspece esp) {
		this.espece = (Espece) esp;

	}


	/**
	 * Allows the Pok�mon to learn a new capacity
	 * @param caps
	 */
	@Override
	public void apprendCapacites(ICapacite[] caps) {
		Espece esp = new Espece();
		esp.setName(name.toLowerCase());
		int j=0;
		Capacite[] capacitesDispoPoke = (Capacite[]) esp.getCapSet();
		for (int i = 0; i < caps.length; i++)
			for (int k = 0; k < capacitesDispoPoke.length; k++) {
				if ((capacitesDispoPoke[k].getNom().equals(caps[i].getNom()))
						&& (capacitesDispoPoke[k].getLvl() < 2)&& j<4) {
					this.capacite[j] = (Capacite) caps[i];
					this.LenghtTabCapacite++;
					j++;
				}

			}

	}


	/**
	 * Reduce the Hp of the Pok�mon when it gets damaged
	 * @param pokAttaquant
	 * @param atk
	 */
	@Override
	public void subitAttaqueDe(IPokemon pokAttaquant, IAttaque atk) {
		this.HP -= ((Attaque) atk).getCap().calculeDommage(pokAttaquant, this);
		System.out.println("Le pokemon " + this.getNom() + " a pris " + ((Attaque) atk).getCap().calculeDommage(pokAttaquant, this) + "point de degats");
		if (this.estEvanoui()) {
			pokAttaquant.gagneExperienceDe(this);
		}
	}

	/**
	 * @return TRUE if the Hp is 0
	 */
	@Override
	public boolean estEvanoui() {
		boolean resultat = false;

		if (this.HP <= 0.1){
			resultat = true;
		}

		return resultat;
	}

	/**
	 * @return true if the pok�mon just leveled up 
	 * @return 
	 */
	@Override
	public boolean aChangeNiveau() {
        return false;
    }

	/**
	 * @return true if the Pok�mon can evolve
	 */
	@Override
	public boolean peutMuter() {
		
		return getEspece().getEvolution(level) != null;
				
	}


	/**
	 * @return the last digit that is used to generate the HP value
	 * @param i
	 * @return 0 or 1
	 */
    private String lastBit(int i) {
        if(i%2 == 0) {
            return "0";
        }else {
            return "1";
        }
    }


    /**
	 * Allows us to access the HPMax variable of the Pok�mon
	 * @return HPMax
	 */
	public int getMaxHP() {
		return HPMax;
	}


	/**
	 * Allows us to set the HPMax
	 * @param HPMax
	 */
	public void setMaxHP(int HPMax) {
		this.HPMax = gainPV();
	}
	
	/**
	 * 
	 * @param pvMax
	 */
	public void setPvMax(int pvMax) {
		this.HPMax = gainPV();

	}

	public Capacite[] getCapacite() {
		return capacite;
	}

	public void setCapacite(Capacite[] capacite) {
		this.capacite = capacite;
	}

	public void setCapacite(Capacite caps) {
		this.capacite[LenghtTabCapacite] = caps;
		LenghtTabCapacite++;
	}
	
	public int nbCapacitesApprises() {
		if(capacite[0]==null) {
			return 0;
		}
		else if(capacite[1]==null){
			return 1;
		}
		else if(capacite[2]==null){
			return 2;
		}
		else if(capacite[3]==null){
			return 3;
		}
		else {return 4;}
	}

}
