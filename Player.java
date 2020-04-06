//Note: See KMTData.java's note

public class Player {
	public int characterID;
	public int vehicleID;
	
	public Player(int characterID, int vehicleID) {
		this.vehicleID = vehicleID;
		this.characterID = characterID;
	}
	public Player() {
		this.vehicleID = 0x01;
		this.characterID = 0x00;
	}
}
