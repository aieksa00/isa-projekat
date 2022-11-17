package isaapp.g3malt.model;

public enum UserType {
   administrator(0),
   staff(1),
   customer(2);
	
	private int value;
	private UserType(int value){
        this.value = value;
    }
	public int getValue() {
	        return value;
	    }
}