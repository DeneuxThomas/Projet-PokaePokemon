package stats;

import interfaces.IStat;

public class Stats implements IStat{

	private int baseHP;
	private int baseAttack;
	private int baseDef;
	private int baseSpecialDef;
	private int baseSpeed;
	private int evHP;
	private int evAttack;
	private int evDef;
	private int evSpecial;
	private int evSpeed;
	
	public Stats () {
		
	}

	
	@Override
	public String toString() {
		return "Stats [baseHP=" + baseHP + ", baseAttack=" + baseAttack + ", baseDef=" + baseDef + ", baseSpecialDef="
				+ baseSpecialDef + ", baseSpeed=" + baseSpeed + ", evHP=" + evHP + ", evAttack=" + evAttack
				+ ", evDef=" + evDef + ", evSpecial=" + evSpecial + ", evSpeed=" + evSpeed + "]";
	}

	/**
	 * Allows us to get the baseHP of a Pok�mon
	 * @return the baseHP variable
	 */
	@Override
	public int getPV() {
		// TODO Auto-generated method stub
		return baseHP;
	}

	/**
	 * Allows us to get the baseAttack of a Pok�mon
	 * @return the baseAttack variable
	 */
	@Override
	public int getForce() {
		// TODO Auto-generated method stub
		return  baseAttack;
	}

	/**
	 * Allows us to get the baseDefense of a Pok�mon
	 * @return the baseDef variable
	 */
	@Override
	public int getDefense() {
		// TODO Auto-generated method stub
		return baseDef;
	}

	/**
	 * Allows us to get the baseSpecialDefense of a Pok�mon
	 * @return the baseSpecialDef variable
	 */
	@Override
	public int getSpecial() {
		// TODO Auto-generated method stub
		return baseSpecialDef;
	}

	/**
	 * Allows us to get the baseSpeed of a Pok�mon
	 * @return the baseSpeed variable
	 */
	@Override
	public int getVitesse() {
		// TODO Auto-generated method stub
		return  baseSpeed;
	}

	/**
	 * Allows us to set the baseHP of a Pok�mon
	 * @param i
	 */
	@Override
	public void setPV(int i) {
		// TODO Auto-generated method stub
		baseHP = i;
	}

	/**
	 * Allows us to set the baseAttack of a Pok�mon
	 * @param i
	 */
	@Override
	public void setForce(int i) {
		// TODO Auto-generated method stub
		baseAttack = i;
	}

	/**
	 * Allows us to set the baseDefense of a Pok�mon
	 * @param i
	 */
	@Override
	public void setDefense(int i) {
		// TODO Auto-generated method stub
		baseDef = i;
	}

	/**
	 * Allows us to set the baseSpeed of a Pok�mon
	 * @param i
	 */
	@Override
	public void setVitesse(int i) {
		// TODO Auto-generated method stub
		baseSpeed = i;
	}

	/**
	 * Allows us to set the baseSpecialDefense of a Pok�mon
	 * @param i
	 */
	@Override
	public void setSpecial(int i) {
		// TODO Auto-generated method stub
		baseSpecialDef = i;
	}

}
