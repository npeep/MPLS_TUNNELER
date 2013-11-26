import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MPLS_TUNNELER_GUI extends JPanel
                                    implements PropertyChangeListener,FocusListener,ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public static int[] rtrTunnelIP = new int[4];
	public static int[] rtrNetwork = new int[4];
	public static int[] rtrSubnet = new int[4];
	public static int[] rtrSerialIP = new int[4];
		public static String rtrTunnelIPstring;
	public static int rtrTunnelNumber;
	public static String scratchString;

	public static int[] boitTunnelIP = new int[4];
	public static int[] testARR = new int[7];
	public static int boitTunnelNumber;

	public static int clientVLAN;
	public static int bandwidth;


	public static JTextField rtrName = new JTextField();
	public static JTextField rtrDesc = new JTextField();
	public static JTextField srlIP = new JTextField();
	public static JTextField internalNet = new JTextField();
	public static JTextField internalSub = new JTextField();
	public static int amtRouters;
	public static String[][][] routers = new String[25][25][25];

	public static int amtPrinted;
	public static Boolean keepGoing = true;
	public static Boolean skipLines = false;
	public static Boolean startScratch = false;

	public static JPanel custPanel;
	public static JPanel boitPanel;
	public static JPanel miscPanel;
	public static JPanel actionPanel;
	public static JPanel optionPanel;

	public static JFormattedTextField rtrTunnelIPField;
		public static JLabel rtrTunnelIPLabel;
	public static JFormattedTextField rtrTunnelNumberField;
		public static JLabel rtrTunnelNumberLabel;
	public static JFormattedTextField boitTunnelIPField;
		public static JLabel boitTunnelIPLabel;
	public static JFormattedTextField boitTunnelNumberField;
		public static JLabel boitTunnelNumberLabel;
	public static JFormattedTextField clientVLANField;
		public static JLabel clientVLANLabel;
	public static JFormattedTextField bandwidthField;
		public static JLabel bandwidthLabel;
	public static JButton addRouter;
	public static JButton generate;
	public static JButton editRouter;
	public static JCheckBox startScratchCheck;
	public static JButton preFill;
	public static JRadioButton cisco2600;
	public static JRadioButton cisco1800;

	public MPLS_TUNNELER_GUI() {

		custPanel = new JPanel();
    		custPanel.setLayout(new GridLayout(4, 1));
		boitPanel = new JPanel();
   	 		boitPanel.setLayout(new GridLayout(4, 1));
   	 	miscPanel = new JPanel();
    		miscPanel.setLayout(new GridLayout(4, 1));
    	actionPanel = new JPanel();
    		actionPanel.setLayout(new GridLayout(3, 1));
		optionPanel = new JPanel();
    		optionPanel.setLayout(new GridLayout(3, 1));

		rtrTunnelIPField = new JFormattedTextField();
			rtrTunnelIPField.setValue("");
			rtrTunnelIPField.addFocusListener(new java.awt.event.FocusAdapter() {@Override
			public void focusGained(java.awt.event.FocusEvent evt) {SwingUtilities.invokeLater( new Runnable() {@Override public void run()
				{rtrTunnelIPField.selectAll();
				rtrTunnelIPstring = boitTunnelIPField.getValue().toString();
				}});}});

			rtrTunnelIPLabel = new JLabel("First Usable Tunnel IP:");
			rtrTunnelIPLabel.setLabelFor(rtrTunnelIPField);

		rtrTunnelNumberField = new JFormattedTextField();
			rtrTunnelNumberField.setValue("");
			rtrTunnelIPField.addFocusListener(new java.awt.event.FocusAdapter() {@Override
			public void focusGained(java.awt.event.FocusEvent evt) {SwingUtilities.invokeLater( new Runnable() {@Override public void run()
				{rtrTunnelNumberField.selectAll();}});}});
			rtrTunnelNumberLabel = new JLabel("First Usable Tunnel #:");
			rtrTunnelNumberLabel.setLabelFor(rtrTunnelNumberField);

		boitTunnelIPField = new JFormattedTextField();
			boitTunnelIPField.setValue("");
			rtrTunnelIPField.addFocusListener(new java.awt.event.FocusAdapter() {@Override
			public void focusGained(java.awt.event.FocusEvent evt) {SwingUtilities.invokeLater( new Runnable() {@Override public void run()
				{boitTunnelIPField.selectAll();}});}});
			boitTunnelIPLabel = new JLabel("First Usable Tunnel IP:");
			boitTunnelIPLabel.setLabelFor(boitTunnelIPField);

		boitTunnelNumberField = new JFormattedTextField();
			boitTunnelNumberField.setValue("");
			boitTunnelNumberLabel = new JLabel("First Usable Tunnel #:");
			boitTunnelNumberLabel.setLabelFor(boitTunnelNumberField);
			boitTunnelNumberField.addPropertyChangeListener("value", this);

		clientVLANField = new JFormattedTextField();
			clientVLANField.setValue("");
			clientVLANField.setEditable(false);
			clientVLANLabel = new JLabel("Client VLAN #:");
			clientVLANLabel.setLabelFor(clientVLANField);

		bandwidthField = new JFormattedTextField();
			bandwidthField.setValue("");
			bandwidthField.addFocusListener(new java.awt.event.FocusAdapter() {@Override
			public void focusGained(java.awt.event.FocusEvent evt) {SwingUtilities.invokeLater( new Runnable() {@Override public void run()
				{bandwidthField.selectAll();}});}});
			bandwidthField.setValue("1540");
			bandwidthLabel = new JLabel("Bandwidth:");
			bandwidthLabel.setLabelFor(bandwidthField);

		addRouter = new JButton("Add Router");
			addRouter.addActionListener(this);
		generate = new JButton("Generate!");
			generate.addActionListener(this);
		editRouter = new JButton("Edit Router");
			editRouter.setEnabled(false);
			editRouter.addActionListener(this);
		//preFill = new JButton("Prefill Form");
		//	preFill.setEnabled(false);
		//	preFill.addActionListener(this);
		startScratchCheck = new JCheckBox("New Router Configs?", true);
			startScratchCheck.addActionListener(this);
		cisco2600 = new JRadioButton("Cisco 2600 Series");
			cisco2600.addActionListener(this);
		cisco1800 = new JRadioButton("Cisco 1800 Series", true);
			cisco1800.addActionListener(this);

		Border blackline;
			blackline = BorderFactory.createLineBorder(Color.black);

		//Configure Titles
		TitledBorder custTitle = BorderFactory.createTitledBorder(blackline, "Customer Info");
			custTitle.setTitleJustification(TitledBorder.CENTER);
		TitledBorder boitTitle = BorderFactory.createTitledBorder(blackline, "BOIT Info");
			boitTitle.setTitleJustification(TitledBorder.CENTER);
		TitledBorder miscTitle = BorderFactory.createTitledBorder(blackline, "Misc. Info");
			miscTitle.setTitleJustification(TitledBorder.CENTER);
		TitledBorder actionTitle = BorderFactory.createTitledBorder(blackline, "Actions");
			actionTitle.setTitleJustification(TitledBorder.CENTER);
		TitledBorder optionTitle = BorderFactory.createTitledBorder(blackline, "Options");
			optionTitle.setTitleJustification(TitledBorder.CENTER);

		//Set Titles
		custPanel.setBorder(custTitle);
		boitPanel.setBorder(boitTitle);
		miscPanel.setBorder(miscTitle);
		actionPanel.setBorder(actionTitle);
		optionPanel.setBorder(optionTitle);


		//Add items to panels
		custPanel.add(rtrTunnelIPLabel);
		custPanel.add(rtrTunnelIPField);

		boitPanel.add(boitTunnelIPLabel);
		boitPanel.add(boitTunnelIPField);

		custPanel.add(rtrTunnelNumberLabel);
		custPanel.add(rtrTunnelNumberField);

		boitPanel.add(boitTunnelNumberLabel);
		boitPanel.add(boitTunnelNumberField);

		miscPanel.add(bandwidthLabel);
		miscPanel.add(bandwidthField);

		miscPanel.add(clientVLANLabel);
		miscPanel.add(clientVLANField);

		actionPanel.add(addRouter);
		actionPanel.add(editRouter);
		actionPanel.add(generate);

		optionPanel.add(startScratchCheck);
		optionPanel.add(cisco2600);
		optionPanel.add(cisco1800);
		//optionPanel.add(preFill);

	    // Add the panels to the frame.
		setLayout(new GridLayout(1,4));
			add(custPanel);//, BorderLayout.WEST);
			add(boitPanel);//, BorderLayout.CENTER);
			add(miscPanel);//, BorderLayout.EAST);
			add(actionPanel);//, BorderLayout.EAST);
			add(optionPanel, BorderLayout.EAST);


	}//end MPLS_TUNNELER_GUI

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addRouter)
			new routerOptions();
		if (source == generate)
			new printConfig();
		if (source == editRouter)
			editRouter();
		if (source == preFill)
			preFill();
		if (source == cisco1800){
			cisco1800.setSelected(true);
			cisco2600.setSelected(false);
		}
		if (source == cisco2600){
			cisco2600.setSelected(true);
			cisco1800.setSelected(false);
		}
	}//end actionPerformed

	@Override
	public void focusGained(FocusEvent e) {
		Object source = e.getSource();
		if (source == boitTunnelNumberField) {
			boitTunnelNumberField.selectAll();
		}
	}//end focusGained

	@Override
	public void focusLost(FocusEvent e) {
		Object source = e.getSource();
	    	if (source == boitTunnelNumberField) {
				char[] charARR = new char[9];
					charARR = boitTunnelNumberField.getValue().toString().toCharArray();
					String catString = new String("");
					for(int i=4;i<charARR.length;i++){
						catString = catString + Character.toString(charARR[i]);
						boitTunnelNumber=Integer.parseInt(catString);
					}//end for
					catString="";
					for(int i=0;i<4;i++){
						catString = catString + Character.toString(charARR[i]);
						clientVLAN=Integer.parseInt(catString);
						clientVLANField.setText(Integer.toString(clientVLAN));
					}//end for
			}//end if
	}//end focusLost

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();
		if (source == boitTunnelNumberField) {
			char[] charARR = new char[9];
			charARR = boitTunnelNumberField.getValue().toString().toCharArray();
			String catString = new String("");
			for(int i=4;i<charARR.length;i++){
				catString = catString + Character.toString(charARR[i]);
				boitTunnelNumber=Integer.parseInt(catString);
			}//end for
			catString="";
			for(int i=0;i<4;i++){
				catString = catString + Character.toString(charARR[i]);
				clientVLAN=Integer.parseInt(catString);
				clientVLANField.setText(Integer.toString(clientVLAN));
			}//end for
		}//end if
	}//end propertyChange


	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("BOIT MPLS Tunneler Tool");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add contents to the window.
		frame.add(new MPLS_TUNNELER_GUI());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}//end createAndShowGUI

	public static void main(String[] args) {
		amtPrinted=0;
		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
	    		//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
	    		createAndShowGUI();
			}//end run
		});//end invokeLater
	}//end main

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////MAIN




	public void preFill(){
		//clientVLANField.setText("");
		rtrTunnelIPField.setText("11.11.11.1");
		rtrTunnelNumberField.setText("1");
		boitTunnelIPField.setText("10.10.83.1");
		//boitTunnelNumberField.setText("");
	}//end preFill



	public static void editRouter() {
		int rtr2edit = Integer.parseInt(JOptionPane.showInputDialog(miscPanel, "Router Number to Edit (0-"+(amtRouters-1)+"):"));
		routers[rtr2edit][0][0]= JOptionPane.showInputDialog(miscPanel, ""+routers[rtr2edit][0][0]+"'s New Name?");
		routers[rtr2edit][1][0]= JOptionPane.showInputDialog(miscPanel, ""+routers[rtr2edit][0][0]+"'s New Serial IP?");
	}//end editRouter



	public static void listRouters() {
		skipLines=true;
		for(int i=0;i<amtRouters;i++){
			System.out.println("");
			for(int j=0;j<2;j++)
				System.out.println(routers[i][j][0]);
		}//end for
	}//end listRouters

}//end MPLS_TUNNELER
