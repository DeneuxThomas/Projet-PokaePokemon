package iaexpert;

import dresseur.Dresseur;
import interfaces.IAttaque;
import interfaces.IPokemon;

public class DresseurMinMax extends Dresseur {
    private MinMax minMax;

    public DresseurMinMax(String nom){
        super(nom);
        this.minMax = new MinMax(this);
    }

    public MinMax getMinMax(){
        return minMax;
    }

    @Override
    public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur) {
        return minMax.choisitAttaque(attaquant, defenseur);
    }

    @Override
    public IPokemon choisitCombattant() {
        return super.choisitCombattant();
    }

    @Override
    public IPokemon choisitCombattantContre(IPokemon pok) {
        return super.choisitCombattant();
    }
}
