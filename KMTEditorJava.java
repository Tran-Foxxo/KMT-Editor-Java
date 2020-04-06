import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class KMTEditorJava extends JFrame implements ItemListener, ChangeListener, ActionListener {

	private static final long serialVersionUID = 1L; //Eclipse wanted this for some reason
	private JPanel contentPane;
	private JSpinner cpuAmtSpinner;
	private JLabel cpuAmtLabel;
	private Choice cpuSelectionChoice;
	private JLabel creditText;
	private JLabel openedFileLabel;
	private JButton saveBtn;
	private JButton openFileBtn;
	private JLabel playerSelectLabel;
	private JButton newFileBtn;
	private Choice missionEntrySelect;
	private JLabel missionRunFileNumbLabel;
	private JSpinner missionRunFileNumberSpinner;
	private Choice gameModeChoice;
	private JLabel gameModeLabel;
	private JLabel selectACourseLabel;
	private Choice courseChoice;
	private JLabel selectAEngineClassLabel;
	private Choice engineClassChoice;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel inputATimeLimitLabel;
	private JLabel requiredScoreLabel;
	private JSpinner requiredScoreSpinner;
	private JSeparator separator_2;
	private JLabel cameraModeLabel;
	private Choice cameraChoice;
	private JLabel characterLabel;
	private JLabel kartLabel;
	private JSeparator separator_3;
	private Choice characterChoice;
	private Choice kartChoice;
	private JLabel cannonFlagLabel;
	private Choice cannonFlagChoice;
	private JSpinner timeLimitHoursSpinner;
	private JLabel lblNewLabel_8;
	private JSpinner timeLimitMinutesSpinner;
	private JLabel lblNewLabel_9;
	private JSpinner timeLimitSecondsSpinner;
	private JLabel lblNewLabel;
	
	public Integer testthing = 0;
	public Integer previousSpinnerVal = 0;
	public KMTData[] kmtData = new KMTData[64];
	public Player[] playerArray = new Player[12];
	public int previousCPUSelected = 0;
	public int previousMissionEntry = 0;
	public byte[] fileArray;
	public boolean doEvent = true;
	//public File kmtFile;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KMTEditorJava frame = new KMTEditorJava();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KMTEditorJava() throws IOException{
		for (int i=0; i<64; i++) {
			kmtData[i]=new KMTData();
			kmtData[i].missionRunFile=(i+1); //Make the default better:tm:
		}
		for (int i=0; i<12; i++) {
			playerArray[i]=new Player();
		}
		
		
		setTitle("KMT Editor Java");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		openFileBtn = new JButton("Open File");
		openFileBtn.addActionListener(this);
		
		saveBtn = new JButton("Save as");
		saveBtn.addActionListener(this);
		
		openedFileLabel = new JLabel("Open File: None");
		
		lblNewLabel = new JLabel("Saved File: None");
		
		separator = new JSeparator();
		
		missionEntrySelect = new Choice();
		missionEntrySelect.setFont(new Font("Consolas", Font.PLAIN, 12));
		for (int i=0; i<64; i++) {
			missionEntrySelect.add("Mission "+(i+1));
		}
		missionEntrySelect.addItemListener(this);
		
		separator_1 = new JSeparator();
		
		missionRunFileNumbLabel = new JLabel("Mission Run File Number (mr#####.szs): ");
		
		gameModeLabel = new JLabel("Select a game mode:");
		
		selectACourseLabel = new JLabel("Select a course: ");
		
		courseChoice = new Choice();
		courseChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
		courseChoice.setEnabled(true);
			courseChoice.add("0x00: Mario Circuit");
			courseChoice.add("0x01: Moo Moo Meadows");
			courseChoice.add("0x02: Mushroom Gorge");
			courseChoice.add("0x03: Grumble Volcano");
			courseChoice.add("0x04: Toad's Factory");
			courseChoice.add("0x05: Coconut Mall");
			courseChoice.add("0x06: DK Summit");
			courseChoice.add("0x07: Wario's Gold Mine");
			courseChoice.add("0x08: Luigi Circuit");
			courseChoice.add("0x09: Daisy Circuit");
			courseChoice.add("0x0A: Moonview Highway");
			courseChoice.add("0x0B: Maple Treeway");
			courseChoice.add("0x0C: Bowser's Castle");
			courseChoice.add("0x0D: Rainbow Road");
			courseChoice.add("0x0E: Dry Dry Ruins");
			courseChoice.add("0x0F: Koopa Cape");
			courseChoice.add("0x10: GCN Peach Beach");
			courseChoice.add("0x11: GCN Mario Circuit");
			courseChoice.add("0x12: GCN Waluigi Stadium");
			courseChoice.add("0x13: GCN DK Mountain");
			courseChoice.add("0x14: DS Yoshi Falls");
			courseChoice.add("0x15: DS Desert Hills");
			courseChoice.add("0x16: DS Peach Gardens");
			courseChoice.add("0x17: DS Delfino Square");
			courseChoice.add("0x18: SNES Mario Circuit 3");
			courseChoice.add("0x19: SNES Ghost Valley 2");
			courseChoice.add("0x1A: N64 Mario Raceway");
			courseChoice.add("0x1B: N64 Sherbet Land");
			courseChoice.add("0x1C: N64 Bowser's Castle");
			courseChoice.add("0x1D: N64 DK Jungle Parkway");
			courseChoice.add("0x1E: GBA Bowser Castle 3");
			courseChoice.add("0x1F: GBA Shy Guy Beach");
			courseChoice.add("0x20: Delfino Pier");
			courseChoice.add("0x21: Block Plaza");
			courseChoice.add("0x22: Chain Chomp Wheel");
			courseChoice.add("0x23: Funky Stadium");
			courseChoice.add("0x24: Thwomp Desert");
			courseChoice.add("0x25: GCN Cookie Land");
			courseChoice.add("0x26: DS Twilight House");
			courseChoice.add("0x27: SNES Battle Course 4");
			courseChoice.add("0x28: GBA Battle Course 3");
			courseChoice.add("0x29: N64 Skyscraper");
			courseChoice.add("0x36: Galaxy Colosseum");
			courseChoice.add("0x37: winingrun_demo");
			courseChoice.add("0x38: loser_demo");
			courseChoice.add("0x39: draw_demo");
			courseChoice.add("0x3A: ending_demo");
		
		selectAEngineClassLabel = new JLabel("Select a engine class / mode: ");
		
		engineClassChoice = new Choice();
		engineClassChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
			engineClassChoice.add("0x00: Race 50cc");	
			engineClassChoice.add("0x01: Race 100cc");
			engineClassChoice.add("0x02: Race 150cc");
			engineClassChoice.add("0x03: Battle Mode");
		
		inputATimeLimitLabel = new JLabel("Input a time limit (H:M:S): ");
		
		requiredScoreLabel = new JLabel("Input the required score: ");
		
		requiredScoreSpinner = new JSpinner();
		requiredScoreSpinner.setModel(new SpinnerNumberModel(0, 0, 2147483647, 1));
		
		cameraModeLabel = new JLabel("What camera should be used: ");
		
		
		cameraChoice = new Choice();
		cameraChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
			cameraChoice.add("0x03: Normal Camera");
			cameraChoice.add("0x07: Top-Down Camera");
		
		cannonFlagLabel = new JLabel("Cannon flag: ");
		
		cannonFlagChoice = new Choice();
			cannonFlagChoice.add("0x00: No cannon");
			cannonFlagChoice.add("0x01: Cannon");
		
		separator_2 = new JSeparator();
		
		cpuAmtLabel = new JLabel("Amount Of CPUs: ");
		
		playerSelectLabel = new JLabel("Select a player: ");
		
		cpuSelectionChoice = new Choice();
		cpuSelectionChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
		cpuSelectionChoice.add("0x05-0x06: Player 1");
		cpuSelectionChoice.addItemListener(this);
		
		separator_3 = new JSeparator();
		
		characterLabel = new JLabel("Character: ");
		
		kartLabel = new JLabel("Kart / Bike: ");
		
		characterChoice = new Choice();
		characterChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
			characterChoice.add("0x00: Mario (Medium)");
			characterChoice.add("0x01: Baby Peach (Small)");
			characterChoice.add("0x02: Waluigi (Large)");
			characterChoice.add("0x03: Bowser (Large)");
			characterChoice.add("0x04: Baby Daisy (Small)");
			characterChoice.add("0x05: Dry Bones (Small)");
			characterChoice.add("0x06: Baby Mario (Small)");
			characterChoice.add("0x07: Luigi (Medium)");
			characterChoice.add("0x08: Toad (Small)");
			characterChoice.add("0x09: Donkey Kong (Large)");
			characterChoice.add("0x0A: Yoshi (Medium)");
			characterChoice.add("0x0B: Wario (Large)");
			characterChoice.add("0x0C: Baby Luigi (Small)");
			characterChoice.add("0x0D: Toadette (Small)");
			characterChoice.add("0x0E: Koopa (Small)");
			characterChoice.add("0x0F: Daisy (Medium)");
			characterChoice.add("0x10: Peach (Medium)");
			characterChoice.add("0x11: Birdo (Medium)");
			characterChoice.add("0x12: Diddy Kong (Medium)");
			characterChoice.add("0x13: King Boo (Large)");
			characterChoice.add("0x14: Bowser Jr. (Medium)");
			characterChoice.add("0x15: Dry Bowser (Large)");
			characterChoice.add("0x16: Funky Kong (Large)");
			characterChoice.add("0x17: Rosalina (Large)");
			characterChoice.add("0x18: S Mii AM");
			characterChoice.add("0x19: S Mii AF");
			characterChoice.add("0x1A: S Mii BM");
			characterChoice.add("0x1B: S Mii BF");
			characterChoice.add("0x1C: S Mii CM");
			characterChoice.add("0x1D: S Mii CF");
			characterChoice.add("0x1E: M Mii AM");
			characterChoice.add("0x1F: M Mii AF");
			characterChoice.add("0x20: M Mii BM");
			characterChoice.add("0x21: M Mii BF");
			characterChoice.add("0x22: M Mii CM");
			characterChoice.add("0x23: M Mii CF");
			characterChoice.add("0x24: L Mii AM");
			characterChoice.add("0x25: L Mii AF");
			characterChoice.add("0x26: L Mii BM");
			characterChoice.add("0x27: L Mii BF");
			characterChoice.add("0x28: L Mii CM");
			characterChoice.add("0x29: L Mii CF");
			characterChoice.add("0x2A: M Mii");
			characterChoice.add("0x2B: S Mii");
			characterChoice.add("0x2C: L Mii");
		
		kartChoice = new Choice();
		kartChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
			kartChoice.add("0x00: Standard Kart (Small)");
			kartChoice.add("0x01: Standard Kart (Medium)");
			kartChoice.add("0x02: Standard Kart (Large)");
			kartChoice.add("0x03: Booster Seat (Small)");
			kartChoice.add("0x04: Classic Dragster (Medium)");
			kartChoice.add("0x05: Offroader (Large)");
			kartChoice.add("0x06: Mini Beast (Small)");
			kartChoice.add("0x07: Wild Wing (Medium)");
			kartChoice.add("0x08: Flame Flyer (Large)");
			kartChoice.add("0x09: Cheep Charger (Small)");
			kartChoice.add("0x0A: Super Blooper (Medium)");
			kartChoice.add("0x0B: Piranha Prowler (Large)");
			kartChoice.add("0x0C: Tiny Titan (Small)");
			kartChoice.add("0x0D: Daytripper (Medium)");
			kartChoice.add("0x0E: Jetsetter (Large)");
			kartChoice.add("0x0F: Blue Falcon (Small)");
			kartChoice.add("0x10: Sprinter (Medium)");
			kartChoice.add("0x11: Honeycoupe (Large)");
			kartChoice.add("0x12: Standard Bike (Small)");
			kartChoice.add("0x13: Standard Bike (Medium)");
			kartChoice.add("0x14: Standard Bike (Large)");
			kartChoice.add("0x15: Bullet Bike (Small)");
			kartChoice.add("0x16: Mach Bike (Medium)");
			kartChoice.add("0x17: Flame Runner (Large)");
			kartChoice.add("0x18: Bit Bike (Small)");
			kartChoice.add("0x19: Sugarscoot (Medium)");
			kartChoice.add("0x1A: Wario Bike (Large)");
			kartChoice.add("0x1B: Quacker (Small)");
			kartChoice.add("0x1C: Zip Zip (Medium)");
			kartChoice.add("0x1D: Shooting Star (Large)");
			kartChoice.add("0x1E: Magikruiser (Small)");
			kartChoice.add("0x1F: Sneakster (Medium)");
			kartChoice.add("0x20: Spear (Large)");
			kartChoice.add("0x21: Jet Bubble (Small)");
			kartChoice.add("0x22: Dolphin Dasher (Medium)");
			kartChoice.add("0x23: Phantom (Large)");
		
		cpuAmtSpinner = new JSpinner();
		cpuAmtSpinner.setToolTipText("");
		cpuAmtSpinner.setModel(new SpinnerNumberModel(0, 0, 11, 1));
		cpuAmtSpinner.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) { //CODE
	        	int returnIndex = (Integer) cpuSelectionChoice.getSelectedIndex();
	        	 //creditText.setText(Integer.toString((int)cpuAmtSpinner.getValue()));
	        	 
	        	 //IF HIGHER THAN THE PREVIOUS VALUE ADD CPUS TO THE LIST
	        	 if (previousSpinnerVal<(Integer)cpuAmtSpinner.getValue()) {
	        		 for (int i=(int)previousSpinnerVal+1; i<((int)cpuAmtSpinner.getValue()+1); i++) {
	        			 //cpuSelectionChoice.add("CPU"+i);
	        			 cpuSelectionChoice.add("0x"+(Integer.toHexString((i*2)+0x5A)).toUpperCase()+"-0x"+(Integer.toHexString((i*2)+0x5A+1)).toUpperCase()+": CPU"+i);
	        			 
	        		 }
	        		 //cpuSelectionChoice.add("CPU"+cpuAmtSpinner.getValue());
	        	 }
	        	 
	        	 //IF LOWER THAN THE PREVIOUS VALUE REMOVE CPUS FROM THE LIST (BUT NOT THE DATA IN THE ARRAY)
	        	 if (previousSpinnerVal>(Integer)cpuAmtSpinner.getValue()) {
	        		 for (int i=(int)previousSpinnerVal; i>=((int)cpuAmtSpinner.getValue()+1); i--) {
	        			playerArray[i] = savePlayerDataToArray();
	        			cpuSelectionChoice.select(i-1);
	        			cpuSelectionChoice.remove(i);
	        		 }
	        		 loadPlayerDataFromArray(playerArray[cpuSelectionChoice.getSelectedIndex()]);
	        		 previousCPUSelected = cpuSelectionChoice.getSelectedIndex();
	        		 //cpuSelectionChoice.remove(previousSpinnerVal);
	        	 }
	        	 previousSpinnerVal = (Integer)cpuAmtSpinner.getValue();
	        	 if (returnIndex>(Integer)(cpuAmtSpinner.getValue())) {}
	        	 else {cpuSelectionChoice.select(returnIndex);}
	        	 previousCPUSelected = cpuSelectionChoice.getSelectedIndex();
	         }
	      });
		
		creditText = new JLabel("Created by @Tran_Foxxo");
		
		newFileBtn = new JButton("New File");
		newFileBtn.addActionListener(this);
		
		gameModeChoice = new Choice();
		gameModeChoice.setFont(new Font("Consolas", Font.PLAIN, 12));
		gameModeChoice.setEnabled(true);
			gameModeChoice.add("0x00: Complete miniturbos");
			gameModeChoice.add("0x01: VS. Mode (Complete 3 laps)");
			gameModeChoice.add("0x02: VS. Mode (Complete 3 laps)");
			gameModeChoice.add("0x03: Drift to increase score");
			gameModeChoice.add("0x04: Break item boxes");
			gameModeChoice.add("0x05: Hit enemies with items");
			gameModeChoice.add("0x06: Attack bosses");
			gameModeChoice.add("0x07: [UNUSED]");
			gameModeChoice.add("0x08: Collect coins");
			gameModeChoice.add("0x09: Drive through star gates");
			gameModeChoice.add("0x0A: Complete a boost start");
			gameModeChoice.add("0x0B: Hit CPUs with items");
			gameModeChoice.add("0x0C: Wheelie to increase score");
			gameModeChoice.add("0x0D: Get slipstreams from CPUs");
			
			
		missionRunFileNumberSpinner = new JSpinner();
		missionRunFileNumberSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		timeLimitHoursSpinner = new JSpinner();
		timeLimitHoursSpinner.setModel(new SpinnerNumberModel(0, 0, 19, 1));
		timeLimitHoursSpinner.addChangeListener(this);
		
		lblNewLabel_8 = new JLabel(":");
		
		timeLimitMinutesSpinner = new JSpinner();
		timeLimitMinutesSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
		timeLimitMinutesSpinner.addChangeListener(this);
		
		lblNewLabel_9 = new JLabel(":");
		
		//thanks windowbuilder
		timeLimitSecondsSpinner = new JSpinner();
		timeLimitSecondsSpinner.setModel(new SpinnerNumberModel(0, -1, 60, 1));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(openFileBtn, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(newFileBtn, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(saveBtn, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(separator, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(missionEntrySelect, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(missionRunFileNumbLabel)
					.addGap(4)
					.addComponent(missionRunFileNumberSpinner, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(gameModeLabel)
					.addGap(4)
					.addComponent(gameModeChoice, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(selectACourseLabel)
					.addGap(4)
					.addComponent(courseChoice, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(selectAEngineClassLabel)
					.addGap(4)
					.addComponent(engineClassChoice, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(inputATimeLimitLabel)
					.addGap(4)
					.addComponent(timeLimitHoursSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblNewLabel_8)
					.addGap(4)
					.addComponent(timeLimitMinutesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(lblNewLabel_9)
					.addGap(4)
					.addComponent(timeLimitSecondsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(requiredScoreLabel)
					.addGap(4)
					.addComponent(requiredScoreSpinner, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(cameraModeLabel)
					.addGap(4)
					.addComponent(cameraChoice, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(cannonFlagLabel)
					.addGap(4)
					.addComponent(cannonFlagChoice, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(cpuAmtLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cpuAmtSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(445))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(playerSelectLabel)
					.addGap(4)
					.addComponent(cpuSelectionChoice, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(kartLabel)
						.addComponent(characterLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(characterChoice, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
						.addComponent(kartChoice, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
					.addGap(7))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(openedFileLabel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(436, Short.MAX_VALUE)
					.addComponent(creditText, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(openFileBtn)
						.addComponent(newFileBtn)
						.addComponent(saveBtn))
					.addGap(9)
					.addComponent(openedFileLabel)
					.addGap(14)
					.addComponent(lblNewLabel)
					.addGap(13)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(missionEntrySelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(missionRunFileNumbLabel))
						.addComponent(missionRunFileNumberSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(gameModeLabel))
						.addComponent(gameModeChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(selectACourseLabel))
						.addComponent(courseChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(selectAEngineClassLabel))
						.addComponent(engineClassChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(inputATimeLimitLabel))
						.addComponent(timeLimitHoursSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_8))
						.addComponent(timeLimitMinutesSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_9))
						.addComponent(timeLimitSecondsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(requiredScoreLabel))
						.addComponent(requiredScoreSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(cameraModeLabel))
						.addComponent(cameraChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(cannonFlagLabel))
						.addComponent(cannonFlagChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cpuAmtSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cpuAmtLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(playerSelectLabel))
						.addComponent(cpuSelectionChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(characterLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(characterChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(4)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(kartChoice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(4)
							.addComponent(kartLabel)))
					.addGap(7)
					.addComponent(creditText)
					.addGap(26))
		);
		contentPane.setLayout(gl_contentPane);
		timeLimitSecondsSpinner.addChangeListener(this);
		
		//Final init suff
		loadPlayerDataFromArray(playerArray[0]);
		
		
		
	}
	public void stateChanged(ChangeEvent e) {
		if (doEvent) {
			doEvent=false;
			int min = (Integer)timeLimitMinutesSpinner.getValue();
			int hrs = (Integer)timeLimitHoursSpinner.getValue();
			int sec = (Integer)timeLimitSecondsSpinner.getValue();

			if (sec == 60) {
				timeLimitSecondsSpinner.setValue(Integer.valueOf(0));min++;
				timeLimitMinutesSpinner.setValue(min);
			}
			if (min == 60) {
				timeLimitMinutesSpinner.setValue(Integer.valueOf(0));hrs++;
				timeLimitHoursSpinner.setValue(Integer.valueOf(hrs));
			}
			if (sec == -1) {
				if (min>0) {
					min--;
					timeLimitMinutesSpinner.setValue(Integer.valueOf(min));
					timeLimitSecondsSpinner.setValue(Integer.valueOf(59));
				}
				else if (hrs>0) {
					hrs--;
					timeLimitHoursSpinner.setValue(Integer.valueOf(hrs));
					timeLimitMinutesSpinner.setValue(Integer.valueOf(59));
					timeLimitSecondsSpinner.setValue(Integer.valueOf(59));
				}
				else {timeLimitSecondsSpinner.setValue(Integer.valueOf(0));}
			}
			if (min == -1) {
				if (hrs>0) {
					timeLimitHoursSpinner.setValue(Integer.valueOf(hrs-1));
					timeLimitMinutesSpinner.setValue(Integer.valueOf(59));
				}
				else {timeLimitMinutesSpinner.setValue(Integer.valueOf(0));}
			}
			if (sec+(min*60)+(hrs*60*60)>65535) {
				timeLimitHoursSpinner.setValue(Integer.valueOf(18));
				timeLimitMinutesSpinner.setValue(Integer.valueOf(12));
				timeLimitSecondsSpinner.setValue(Integer.valueOf(15));
			}
			doEvent=true;
		}//doevent
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==openFileBtn) {
			//OPEN FILE
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("KMT File", "kmt"));
			int returnVal = fc.showOpenDialog(KMTEditorJava.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File kmtFile = fc.getSelectedFile();
				try {fileArray = Files.readAllBytes(kmtFile.toPath());}
				catch (Exception exception) {}
				
				kmtData = byteArrayToKMTDataArray(fileArray);
				loadKMTDataFromArray(kmtData[0]);
				playerArray = kmtData[0].playerArray;
				cpuSelectionChoice.select(0);
				missionEntrySelect.select(0);
				loadPlayerDataFromArray(playerArray[0]);
				previousCPUSelected = 0;
				
				Date date = new Date();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
				openedFileLabel.setText("Opened File: ["+kmtFile.getAbsolutePath()+"] at ["+dateFormat.format(date).toString()+"]");
				
				//creditText.setText(Integer.toHexString(fileArray.length));
			}
			else {}
			
		}
		if (e.getSource()==newFileBtn) {
			kmtData = new KMTData[64];
			for (int i=0; i<64; i++) {
				kmtData[i]=new KMTData();
				kmtData[i].missionRunFile=(i+1); //Make the default better:tm: 2
			}
			
			missionEntrySelect.select(0);
			loadKMTDataFromArray(kmtData[0]);
			loadPlayerDataFromArray(kmtData[0].playerArray[0]);
			previousCPUSelected = 0;
			cpuSelectionChoice.select(0);
			
			openedFileLabel.setText("Opened File: New File");
		}
		if (e.getSource()==saveBtn) {
			//SAVE AS
			JFileChooser fc = new JFileChooser();
			fc.setFileFilter(new FileNameExtensionFilter("KMT File", "kmt"));
			int returnVal = fc.showSaveDialog(KMTEditorJava.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fc.getSelectedFile().toPath().toString();
			    if (filePath.endsWith(".kmt")) {}
			    else {filePath=filePath+".kmt";}
			    
			    saveDataToArrays();   
			    byte[] fileArray = kmtDataToByteArray(kmtData);
			    try (FileOutputStream fos = new FileOutputStream(filePath)) {fos.write(fileArray);}
			    catch (Exception exception) {}
			    
			    
			    
			    Date date = new Date();
			    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
				lblNewLabel.setText("Saved file: ["+filePath+"] at ["+dateFormat.format(date).toString()+"]");
			}
			else {lblNewLabel.setText("No file saved");}
		}
	}//Action event
	
	public void itemStateChanged(ItemEvent e) {
		//missionEntrySelect
		//cpuSelectionChoice
		if (e.getSource()==missionEntrySelect) {
			//WHEN A MISSION ENTRY IS CHANGED
			saveDataToArrays();
		}
		if (e.getSource()==cpuSelectionChoice) {
			//WHEN THE CPU IS CHANGED
			playerArray[previousCPUSelected] = savePlayerDataToArray();
			loadPlayerDataFromArray(playerArray[cpuSelectionChoice.getSelectedIndex()]);
			previousCPUSelected = cpuSelectionChoice.getSelectedIndex();
		}
	}
	
	public void saveDataToArrays() {
		playerArray[cpuSelectionChoice.getSelectedIndex()] = savePlayerDataToArray();
		kmtData[previousMissionEntry] = saveKMTDataToArray(playerArray); //SAVE FOR PREVIOUS
		loadKMTDataFromArray(kmtData[missionEntrySelect.getSelectedIndex()]); //LOAD DATA FOR CURRENT
		previousMissionEntry = missionEntrySelect.getSelectedIndex(); //SET INT FOR PREVIOUS
		playerArray = kmtData[missionEntrySelect.getSelectedIndex()].playerArray;
		previousCPUSelected = 0;
		cpuSelectionChoice.select(0);
		loadPlayerDataFromArray(playerArray[0]);
	}
	
	public KMTData saveKMTDataToArray(Player[] playerlist) {
		//Old constructor I used as a reference 
		//public KMTData(int missionRunFile, int gameModeID, int courseID, int engineClass, int timeLimit, int scoreRequired, int cameraMode, boolean cannonFlag, int cpuAmount, Player[] playerlist)
		int min = (Integer)timeLimitMinutesSpinner.getValue();
		int hrs = (Integer)timeLimitHoursSpinner.getValue();
		int sec = (Integer)timeLimitSecondsSpinner.getValue();
		
		
		int missionRunFile = (Integer) missionRunFileNumberSpinner.getValue();
		int gameModeID = Integer.decode(gameModeChoice.getSelectedItem().substring(0,4));
		int courseID = Integer.decode(courseChoice.getSelectedItem().substring(0,4));
		int engineClass = Integer.decode(engineClassChoice.getSelectedItem().substring(0,4));
		int timeLimit = (sec+(min*60)+(hrs*60*60));
		long scoreRequired = Long.valueOf(requiredScoreSpinner.getValue().toString());
		int cameraMode = Integer.decode(cameraChoice.getSelectedItem().substring(0,4));
		int cannonFlag = Integer.decode(cannonFlagChoice.getSelectedItem().substring(0,4));
		int cpuAmount = (int)cpuAmtSpinner.getValue();
		

		return new KMTData(missionRunFile, gameModeID, courseID, engineClass, timeLimit, scoreRequired, cameraMode, cannonFlag, cpuAmount, playerlist);
	}
	
	public void loadKMTDataFromArray(KMTData kmtData) {
		missionRunFileNumberSpinner.setValue(Integer.valueOf(kmtData.missionRunFile));
		gameModeChoice.select(kmtData.gameModeID);
		if (kmtData.courseID>0x35) {courseChoice.select(kmtData.courseID-12);} //Account for ID gap
		else {courseChoice.select(kmtData.courseID);}
		engineClassChoice.select(kmtData.engineClass);
		requiredScoreSpinner.setValue(kmtData.scoreRequired);
		if (kmtData.cameraMode==0x07) {cameraChoice.select(1);}
		else {cameraChoice.select(0);}
		cannonFlagChoice.select(kmtData.cannonFlag);
		cpuAmtSpinner.setValue(kmtData.cpuAmount);
		int time = kmtData.timeLimit;
		timeLimitHoursSpinner.setValue(Integer.valueOf((time/3600)%60));
		timeLimitMinutesSpinner.setValue(Integer.valueOf((time/60)%60));
		timeLimitSecondsSpinner.setValue(Integer.valueOf((time/1)%60));
		cpuSelectionChoice.select(0);
		
		return;
	}
	
	public Player savePlayerDataToArray() {
		int characterID = (int)Integer.decode(characterChoice.getSelectedItem().substring(0,4));
		int vehicleID = (int)Integer.decode(kartChoice.getSelectedItem().substring(0,4));
		return new Player(characterID, vehicleID);
	}
	
	public void loadPlayerDataFromArray(Player player) {
		characterChoice.select((Integer.valueOf(player.characterID)));
		kartChoice.select((Integer.valueOf(player.vehicleID)));
	}
	
	public KMTData[] byteArrayToKMTDataArray (byte[] fileArray) {
		
		KMTData[] newKMTData = new KMTData[64];
		Player[] newPlayerArray = new Player[12];
		for (int i=0; i<64; i++) {
			newKMTData[i]=new KMTData();
		}
		for (int i=0; i<12; i++) {
			newPlayerArray[i]=new Player();
		}
		
		if (fileArray.length < 0x1C10) {
			byte[] resizedByteArr = new byte[0x1C10];
			for (int i=0; i<fileArray.length; i++) {
				resizedByteArr[i] = fileArray[i];
			}
			fileArray = resizedByteArr;
		}
		int currentByteIndex = 0x10;
		for (int i=0; i<64; i++) {
			byte byte1;
			byte byte2;
			byte byte3;
			byte byte4;
			
			for (int kmtOffset=0; kmtOffset<0x70; kmtOffset++) {
				if (kmtOffset==0) { //MISSION RUN FILE
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].missionRunFile = bytesToUInt16(byte1, byte2);
				}
				if (kmtOffset==2) { //GAMEMODE
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].gameModeID = bytesToUInt16(byte1, byte2);
				}
				if (kmtOffset==4) { //COURSE CHARA Vehicle CLASS
					newKMTData[i].courseID = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].playerArray[0].characterID = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].playerArray[0].vehicleID = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].engineClass = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					kmtOffset=0x2C;
				}
				if (kmtOffset==0x2C) { //TIME
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].timeLimit = bytesToUInt16(byte1, byte2);
					kmtOffset=0x30;
				}
				if (kmtOffset==0x30) { //SCORE
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte3 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte4 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					newKMTData[i].scoreRequired = bytesToUInt32(byte1,byte2,byte3,byte4);
					kmtOffset=0x48;
				}
				if (kmtOffset==0x48) { //CAMERA
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					if ((byte1==0)&&(byte2==7)) {
						//cam
						newKMTData[i].cameraMode=0x07;
					}
					else {
						//def
						newKMTData[i].cameraMode=0x03;
					}
					kmtOffset=0x50;
				}
				if (kmtOffset==0x50) { //Cannon flag (0 if no cannon)
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					if ((byte1!=0)||(byte2!=0)) { //if either non zero
						newKMTData[i].cannonFlag=1; //true
					}
					else {newKMTData[i].cannonFlag=0;}
					kmtOffset=0x58;
				}
				if (kmtOffset==0x58) {
					byte1 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					byte2 = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					int cpus = bytesToUInt16(byte1, byte2);
					if (cpus>11) {cpus=11;}
					newKMTData[i].cpuAmount = cpus; 
					for (int k=1; k<12; k++) {
						newKMTData[i].playerArray[k].characterID = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
						newKMTData[i].playerArray[k].vehicleID = fileArray[currentByteIndex+kmtOffset];kmtOffset++;
					}
				}
			}//FOR kmtOffset
			currentByteIndex = currentByteIndex + 0x70;
		}// FOR ENTRIES
		return newKMTData;
	}
	public static int bytesToUInt16 (byte byte1, byte byte2) {
		String string1 = (Integer.toBinaryString(byte1&0xFF));
		String string2 = (Integer.toBinaryString(byte2&0xFF));
		String binStr1 = (String.format("%08d", ((int)Integer.decode(string1))));
		String binStr2 = (String.format("%08d", ((int)Integer.decode(string2))));
		return (Integer.parseInt(binStr1+binStr2,2));
	}
	public static long bytesToUInt32 (byte byte1, byte byte2, byte byte3, byte byte4) {
		String string1 = (Integer.toBinaryString(byte1&0xFF));
		String string2 = (Integer.toBinaryString(byte2&0xFF));
		String string3 = (Integer.toBinaryString(byte3&0xFF));
		String string4 = (Integer.toBinaryString(byte4&0xFF));
		String binStr1 = (String.format("%08d", ((int)Integer.decode(string1))));
		String binStr2 = (String.format("%08d", ((int)Integer.decode(string2))));
		String binStr3 = (String.format("%08d", ((int)Integer.decode(string3))));
		String binStr4 = (String.format("%08d", ((int)Integer.decode(string4))));
		return (Long.parseLong(binStr1+binStr2+binStr3+binStr4,2));
	}
	public byte[] kmtDataToByteArray (KMTData[] kmtData) {
		byte[] byteArr = new byte[0x1C10];
		int byteIndex = 0x00;
		//Hardcode header 52 4B 4D 54 00 00 00 00 00 00 00 00 00 00 00 00
		byteArr[byteIndex] = (byte)0x52 & 0xFF; byteIndex++;
		byteArr[byteIndex] = (byte)0x4B & 0xFF; byteIndex++;
		byteArr[byteIndex] = (byte)0x4D & 0xFF; byteIndex++;
		byteArr[byteIndex] = (byte)0x54 & 0xFF; byteIndex++;
		byteIndex = 0x10;
		//... 0x10
		
		
		for (int i=0; i<64; i++) {
			for (int kmtOffset=0; kmtOffset<0x70; kmtOffset++) {
				if (kmtOffset==0) { //MISSION RUN FILE
					short shortVal = (short)kmtData[i].missionRunFile;
					byteArr[kmtOffset+byteIndex] = (byte)((shortVal >> 8) & 0xff);				kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(shortVal & 0xff);						kmtOffset++;
				}
				if (kmtOffset==2) { //GAMEMODE
					short shortVal = (short)kmtData[i].gameModeID;
					byteArr[kmtOffset+byteIndex] = (byte)((shortVal >> 8) & 0xff);				kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(shortVal & 0xff);						kmtOffset++;
				}
				if (kmtOffset==4) { //COURSE CHARA Vehicle CLASS
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].courseID) & 0xFF);						kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].playerArray[0].characterID) & 0xFF);	kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].playerArray[0].vehicleID) & 0xFF);		kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].engineClass) & 0xFF);					kmtOffset++;
					kmtOffset=0x2C;
				}
				if (kmtOffset==0x2C) { //TIME
					short shortVal = (short)kmtData[i].timeLimit;
					byteArr[kmtOffset+byteIndex] = (byte)((shortVal >> 8) & 0xff);				kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(shortVal & 0xff);						kmtOffset++;
					kmtOffset=0x30;
				}
				if (kmtOffset==0x30) { //SCORE
					long score = kmtData[i].scoreRequired;
					byteArr[kmtOffset+byteIndex] = (byte) ((score >> 24) & 0xFF);	kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte) ((score >> 16) & 0xFF);   kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte) ((score >> 8) & 0xFF);   	kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte) (score & 0xFF);   		kmtOffset++;
					kmtOffset=0x48;
				}
				if (kmtOffset==0x48) { //CAMERA
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].cameraMode) & 0xFF);	kmtOffset++;
					kmtOffset=0x50;
				}
				if (kmtOffset==0x50) { //Cannon flag (0 if no cannon)
					byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].cannonFlag) & 0xFF);	kmtOffset++;
					kmtOffset=0x58;
				}
				if (kmtOffset==0x58) { //CPUS
					short shortVal = (short)kmtData[i].cpuAmount;
					byteArr[kmtOffset+byteIndex] = (byte)((shortVal >> 8) & 0xff);				kmtOffset++;
					byteArr[kmtOffset+byteIndex] = (byte)(shortVal & 0xff);						kmtOffset++;
					for (int k=1; k<12; k++) {
						byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].playerArray[k].characterID) & 0xFF);	kmtOffset++;
						byteArr[kmtOffset+byteIndex] = (byte)(((byte)kmtData[i].playerArray[k].vehicleID) & 0xFF);	kmtOffset++;
					}//for
				}//if
			}//for kmt offset
			byteIndex = byteIndex + 0x70;
		}
		return byteArr;
	}//kmttobytearr
}
