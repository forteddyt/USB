package CSGOWild;

public class WildGame {
	private int matchID, offerID;
	private String amount;

	public WildGame() {

	}

	public WildGame(int mID, String a, int oID) {
		matchID = mID;
		amount = a;
		offerID = oID;
	}

	public String returnMatchURL() {
		return "http://csgowild.com/coinflip?id=" + matchID;
	}

	public int getMatchID() {
		return matchID;
	}

	public int getOfferID() {
		return offerID;
	}

	public String getAmount() {
		return amount;
	}
}
