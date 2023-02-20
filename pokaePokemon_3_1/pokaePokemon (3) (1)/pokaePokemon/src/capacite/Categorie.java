package capacite;

import interfaces.ICategorie;
/**
 * This is the category of a capacity:
 * Physical or Special
 */
public enum Categorie implements ICategorie{

	Physique("Physique"), Special("Special");
	
	private String name;
	
	Categorie(String name) {
		this.name = name;
	}


	/**
	 * if name is special true otherwise is false
	 * @return true or false
	 */
	@Override
	public boolean isSpecial() {
		if(name == "Special"){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * returns the name of the category
	 * @return name
	 */
	@Override
	public String getNom() {
		
		return name;
	}

}
