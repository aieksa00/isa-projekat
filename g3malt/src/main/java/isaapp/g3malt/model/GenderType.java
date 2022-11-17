package isaapp.g3malt.model;

public enum GenderType {
	male(0),
	female(1);
	
	private final int value;
	private GenderType(int value) {
	    this.value = value;
	}
	
	public int getValue() {
	    return value;
	}
}