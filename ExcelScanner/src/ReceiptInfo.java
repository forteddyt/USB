public class ReceiptInfo implements Comparable {
	private String firstName, lastName, parentName;
	private int cost, itteration;

	public ReceiptInfo(String f, String l, String p, int c) {
		firstName = f;
		lastName = l;
		parentName = p;
		cost = c;
		itteration = 0;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getParentName() {
		return parentName;
	}

	public int getCost() {
		return cost;
	}

	public int getItteration() {
		return itteration;
	}

	@Override
	public String toString() {
		return "ReceiptInfo [Name: " + firstName + " " + lastName + " "
				+ itteration + " --- Parent Name: " + parentName
				+ " --- Cost: " + cost + "]";
	}

	// EWWEWWEWEWEWEWEWEWEWE ;-;
	public int compareTo(Object o) {
		if ((firstName + lastName + ((itteration == 0) ? "" : itteration))
				.equals(((ReceiptInfo) o).getFirstName()
						+ ((ReceiptInfo) o).getLastName()
						+ ((((ReceiptInfo) o).getItteration() == 0) ? ""
								: ((ReceiptInfo) o).getItteration()))) {
			itteration++;
			return (firstName + lastName + ((itteration == 0) ? "" : itteration))
					.compareTo(((ReceiptInfo) o).getFirstName()
							+ ((ReceiptInfo) o).getLastName()
							+ ((((ReceiptInfo) o).getItteration() == 0) ? ""
									: ((ReceiptInfo) o).getItteration()));

		} else {
			return (firstName + lastName).compareTo(((ReceiptInfo) o)
					.getFirstName() + ((ReceiptInfo) o).getLastName());
		}
	}
}
