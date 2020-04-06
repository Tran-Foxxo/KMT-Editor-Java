//Note: I was a dumbass and had the this. on the wrong side of the = sign and took like 2 hours to figure out what was wrong :3

public class KMTData {
	public int missionRunFile;
	public int gameModeID;
	public int courseID;
	//public int characterID;
	//public int vehicleID
	public int engineClass;
	public int timeLimit;
	public long scoreRequired;
	public int cameraMode;
	public int cannonFlag;
	public int cpuAmount;
	public Player[] playerArray;
	
	public KMTData(int missionRunFile, int gameModeID, int courseID, int engineClass, int timeLimit, long scoreRequired, int cameraMode, int cannonFlag, int cpuAmount, Player[] playerArray) {
		this.missionRunFile = missionRunFile;
		this.gameModeID = gameModeID;
		this.courseID = courseID;
		this.engineClass = engineClass;
		this.timeLimit =timeLimit ;
		this.scoreRequired =scoreRequired ;
		this.cameraMode = cameraMode;
		this.cannonFlag = cannonFlag;
		this.cpuAmount = cpuAmount;
		this.playerArray = playerArray;
	}
	public KMTData() {
		this.missionRunFile = 0x01;
		this.gameModeID = 0x00;
		this.courseID = 0x00;
		this.engineClass = 0x00;
		this.timeLimit = 0x00;
		this.scoreRequired = 0x00;
		this.cameraMode = 0x00;
		this.cannonFlag = 0x00;
		this.cpuAmount = 0x00;
		this.playerArray = new Player[12];
		for (int i=0; i<12; i++) {
			this.playerArray[i] = new Player();
		}
	}
}
