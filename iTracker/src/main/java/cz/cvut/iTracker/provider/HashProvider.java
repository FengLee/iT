package cz.cvut.iTracker.provider;

public interface HashProvider {
	/**
	 * Computes hash of of the given string
	 * @param s string
	 * @return hash(s)
	 */
	public String computeHash(String s);
}
