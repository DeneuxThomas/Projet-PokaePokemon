/**
 * Universit� C�te d'Azur
 * IUT C�te d'Azur
 * D�partement Informatique
 * @date
 * IStrategy.java
 */
package interfaces;

/**
 * @author Leo Donati
 *
 */
public interface IStrategy {
	public IPokemon choisitCombattant();
	public IPokemon choisitCombattantContre(IPokemon pok);
	public IAttaque choisitAttaque(IPokemon attaquant, IPokemon defenseur);
}
