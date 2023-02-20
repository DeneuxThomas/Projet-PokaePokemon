package type;

import interfaces.IType;

public enum Type implements IType {
    

    Fighting("Fighting"),
    Dragon("Dragon"),
    Water("Water"),
    Electric("Electric"),
    Fire("Fire"),
    Ice("Ice"),
    Bug("Bug"),
    Normal("Normal"),
    Grass("Grass"),
    Poison("Poison"),
    Psychic("Psychic"),
    Rock("Rock"),
    Ground("Ground"),
    Ghost("Ghost"),
    Flying("Flying"),;
    
    
    
    
    

    private String name;
    
    
    Type(String name) {
        this.name=name;
    }
    
    /**
	 * Give the name of the type
	 * return the name
	 */
    @Override
    public String getNom() {
    
        return name;
    }
    
}