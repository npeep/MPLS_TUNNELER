import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class routerOptions extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public routerOptions() {
		Object[] msg = { "Host Name:", MPLS_TUNNELER_GUI.rtrName, "Serial IP:",
				MPLS_TUNNELER_GUI.srlIP, "--Serial Int Description:",
				MPLS_TUNNELER_GUI.rtrDesc, "Internal IP:",
				MPLS_TUNNELER_GUI.internalNet, "--Internal Subnet:",
				MPLS_TUNNELER_GUI.internalSub };

		JOptionPane op = new JOptionPane(msg, JOptionPane.QUESTION_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION, null, null);

		JDialog dialog = op.createDialog(this, "Router Info");
		dialog.setVisible(true);

		int result = JOptionPane.OK_OPTION;

		try {
			result = ((Integer) op.getValue()).intValue();
		} catch (Exception uninitializedValue) {
		}

		if (result == JOptionPane.OK_OPTION) {
			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][0][0] = MPLS_TUNNELER_GUI.rtrName
					.getText();
			System.out
					.println("\nRouter #"
							+ MPLS_TUNNELER_GUI.amtRouters
							+ "    \""
							+ MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][0][0]
							+ "\"");
			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][0] = MPLS_TUNNELER_GUI.srlIP
					.getText();
			System.out
					.println("   Serial IP:     "
							+ MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][0]);
			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][3] = MPLS_TUNNELER_GUI.rtrDesc
					.getText();
			System.out
					.println("    -Description: "
							+ MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][3]);
			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][1] = MPLS_TUNNELER_GUI.internalNet
					.getText();
			System.out
					.println("   Internal IP:   "
							+ MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][1]);
			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][2] = MPLS_TUNNELER_GUI.internalSub
					.getText();
			System.out
					.println("    -Subnet Mask: "
							+ MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][1][2]);


			new networkAddress(MPLS_TUNNELER_GUI.amtRouters);

			MPLS_TUNNELER_GUI.routers[MPLS_TUNNELER_GUI.amtRouters][2][0] = ("3");
			MPLS_TUNNELER_GUI.amtRouters++;
		}// end if
		else {
			System.out.println("Canceled");
		}
	}// end routerOptions
}
