package tableConstructors;
/**
 * @author Matt DeROsa
 */
public class Medication {
    private String name;
    private String quantity;
    private String supplierId;

    public Medication() {
    }

    public Medication(String name, String quantity, String supplierId) {
        this.name = name;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@Override
	public String toString() {
		return "Medication [name=" + name + ", quantity=" + quantity + ", supplierId=" + supplierId + "]";
	}

    
    
}
