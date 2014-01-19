package planetguy.simpleLoader;

public interface SLModContainer {
	
	/**
	 * Sets whether to use the static loader (the one from code generation). If static loading is enabled,
	 * SimpleLoader stops doing anything.
	 * @param isStatic
	 */
	
	public void setStaticLoading(boolean isStatic);
	
}
