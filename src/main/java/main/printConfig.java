import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class printConfig {
	public printConfig() {
		try {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						"MPLS_TUNNELS_" + MPLS_TUNNELER_GUI.routers[0][0][0]
								+ ".txt", true));
				if (MPLS_TUNNELER_GUI.amtPrinted != 0) {
					out = new BufferedWriter(new FileWriter("MPLS_TUNNELS_"
							+ MPLS_TUNNELER_GUI.routers[0][0][0]
							+ MPLS_TUNNELER_GUI.amtPrinted + ".txt", true));
				}// end try

				MPLS_TUNNELER_GUI.skipLines = true;
				MPLS_TUNNELER_GUI.bandwidth = Integer
						.parseInt(MPLS_TUNNELER_GUI.bandwidthField.getValue()
								.toString());
				String tring = MPLS_TUNNELER_GUI.rtrTunnelIPField.getValue()
						.toString();
				String delims = "[.]";
				String[] tokens = tring.split(delims);
				for (int i = 0; i < tokens.length; i++) {
					MPLS_TUNNELER_GUI.rtrTunnelIP[i] = Integer
							.parseInt(tokens[i]);
				}
				MPLS_TUNNELER_GUI.rtrTunnelNumber = Integer
						.parseInt(MPLS_TUNNELER_GUI.rtrTunnelNumberField
								.getValue().toString());

				tring = MPLS_TUNNELER_GUI.boitTunnelIPField.getValue()
						.toString();
				delims = "[.]";
				tokens = tring.split(delims);
				for (int i = 0; i < tokens.length; i++) {
					MPLS_TUNNELER_GUI.boitTunnelIP[i] = Integer
							.parseInt(tokens[i]);
				}

				if (MPLS_TUNNELER_GUI.startScratchCheck.isSelected()) {
					System.out.println("Configuring from scratch.....");
				} else {
					System.out.println("NOT configuring from scratch..");
				}

				for (int t = 0; t < MPLS_TUNNELER_GUI.amtRouters; t++) {// for
																		// every
																		// router....
					out.write("\r\n\r\n__________________________________________________________________\r\n"
							+ MPLS_TUNNELER_GUI.routers[t][0][0]
							+ " ROUTER\r\n");
					new scratchConfig(t);
					out.write(MPLS_TUNNELER_GUI.scratchString);

					for (int u = t; u < MPLS_TUNNELER_GUI.amtRouters - 1; u++) {// try
																				// to
																				// go
																				// up
						out.write("\r\n!\r\nInterface Tunnel"
								+ MPLS_TUNNELER_GUI.rtrTunnelNumber);
						out.write("\r\n  description Tunnel to "
								+ MPLS_TUNNELER_GUI.routers[u + 1][0][0]);
						out.write("\r\n  bandwidth "
								+ MPLS_TUNNELER_GUI.bandwidth);
						out.write("\r\n  service-policy output ToBranch-Tunnel");
						out.write("\r\n  ip address "
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[0] + "."
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[1] + "."
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[2] + "."
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[3]
								+ " 255.255.255.252");
						out.write("\r\n  tunnel source Serial0/0");
						out.write("\r\n  tunnel path-mtu-discovery");
						out.write("\r\n  tunnel destination "
								+ MPLS_TUNNELER_GUI.routers[u + 1][1][0]
								+ "\r\n!");
						out.write("\r\n      ip route "
								+ MPLS_TUNNELER_GUI.routers[u + 1][1][4] + " "
								+ MPLS_TUNNELER_GUI.routers[u + 1][1][2]
								+ " Tunnel" + MPLS_TUNNELER_GUI.rtrTunnelNumber
								+ "\r\n!");
						out.write("\r\n    access-list 106 permit ip any "+MPLS_TUNNELER_GUI.routers[u + 1][1][4] + " " + MPLS_TUNNELER_GUI.routers[u + 1][1][5]);

						MPLS_TUNNELER_GUI.routers[u + 1][Integer
								.parseInt(MPLS_TUNNELER_GUI.routers[u + 1][2][0])][0] = Integer
								.toString(MPLS_TUNNELER_GUI.rtrTunnelNumber);
						MPLS_TUNNELER_GUI.routers[u + 1][Integer
								.parseInt(MPLS_TUNNELER_GUI.routers[u + 1][2][0])][1] = Integer
								.toString(MPLS_TUNNELER_GUI.rtrTunnelIP[3] + 1);
						MPLS_TUNNELER_GUI.rtrTunnelIP[3] += 4;
						MPLS_TUNNELER_GUI.routers[u + 1][2][0] = Integer
								.toString(Integer
										.parseInt(MPLS_TUNNELER_GUI.routers[u + 1][2][0]) + 1);
						MPLS_TUNNELER_GUI.rtrTunnelNumber++;
					}// end for Tunnel up

					for (int u = t; u > 0; u--) {// try to go down
						MPLS_TUNNELER_GUI.routers[t][2][0] = Integer
								.toString(Integer
										.parseInt(MPLS_TUNNELER_GUI.routers[t][2][0]) - 1);
						out.write("\r\n!\r\nInterface Tunnel"
								+ MPLS_TUNNELER_GUI.routers[t][Integer
										.parseInt(MPLS_TUNNELER_GUI.routers[t][2][0])][0]);
						out.write("\r\n  description Tunnel to "
								+ MPLS_TUNNELER_GUI.routers[u - 1][0][0]);
						out.write("\r\n  bandwidth "
								+ MPLS_TUNNELER_GUI.bandwidth);
						out.write("\r\n  service-policy output ToBranch-Tunnel");
						out.write("\r\n  ip address "
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[0]
								+ "."
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[1]
								+ "."
								+ MPLS_TUNNELER_GUI.rtrTunnelIP[2]
								+ "."
								+ MPLS_TUNNELER_GUI.routers[t][Integer
										.parseInt(MPLS_TUNNELER_GUI.routers[t][2][0])][1]
								+ " 255.255.255.252");
						out.write("\r\n  tunnel source Serial0/0");
						out.write("\r\n  tunnel path-mtu-discovery");
						out.write("\r\n  tunnel destination "
								+ MPLS_TUNNELER_GUI.routers[u - 1][1][0]
								+ "\r\n!");
						out.write("\r\n      ip route "
								+ MPLS_TUNNELER_GUI.routers[u - 1][1][4]
								+ " "
								+ MPLS_TUNNELER_GUI.routers[u - 1][1][2]
								+ " Tunnel"
								+ MPLS_TUNNELER_GUI.routers[t][Integer
										.parseInt(MPLS_TUNNELER_GUI.routers[t][2][0])][0]
								+ "\r\n!");
						out.write("\r\n    access-list 106 permit ip any "+MPLS_TUNNELER_GUI.routers[u - 1][1][4] + " " + MPLS_TUNNELER_GUI.routers[u - 1][1][5]);

					}// end for Tunnel down

					out.write("\r\n!\r\nInterface Tunnel0");
					out.write("\r\n  description  Tunnel to BOIT");
					out.write("\r\n  bandwidth " + MPLS_TUNNELER_GUI.bandwidth);
					out.write("\r\n  service-policy output ToBOIT-Tunnel");
					out.write("\r\n  ip address "
							+ MPLS_TUNNELER_GUI.boitTunnelIP[0] + "."
							+ MPLS_TUNNELER_GUI.boitTunnelIP[1] + "."
							+ MPLS_TUNNELER_GUI.boitTunnelIP[2] + "."
							+ (MPLS_TUNNELER_GUI.boitTunnelIP[3] + 1)
							+ " 255.255.255.252");
					MPLS_TUNNELER_GUI.routers[t][2][2] = Integer
							.toString(MPLS_TUNNELER_GUI.boitTunnelIP[3]);
					MPLS_TUNNELER_GUI.boitTunnelIP[3] += 4;
					out.write("\r\n  tunnel source Serial0/0");
					out.write("\r\n  tunnel path-mtu-discovery");
					out.write("\r\n  tunnel destination 10.200.200.1");
					out.write("\r\n  tunnel key "
							+ MPLS_TUNNELER_GUI.clientVLAN
							+ (MPLS_TUNNELER_GUI.boitTunnelNumber) + "\n\n");
					MPLS_TUNNELER_GUI.routers[t][0][2] = Integer
							.toString(MPLS_TUNNELER_GUI.boitTunnelNumber);
					MPLS_TUNNELER_GUI.boitTunnelNumber += 1;
					out.write("\r\n!\r\n      ip route 0.0.0.0 0.0.0.0 Tunnel0");
					out.write("\r\n!\r\nend");
					out.write("\r\n!\r\nwrite mem");
					out.write("\r\n!\r\n");
				}// end for t Every Router

				out.write("\r\n__________________________________________________________________\r\nBOIT MPLS ROUTER\r\n");
									out.write("\r\n!\r\nip vrf vlan"
											+ MPLS_TUNNELER_GUI.clientVLAN);
									out.write("\r\nrd "
											+ MPLS_TUNNELER_GUI.clientVLAN
											+ ":"
											+ MPLS_TUNNELER_GUI.clientVLAN);

									out.write("\r\n!\r\ninterface GigabitEthernet0/0."
											+ MPLS_TUNNELER_GUI.clientVLAN);
									out.write("\r\n description "
											+ MPLS_TUNNELER_GUI.routers[0][0][0]
											+ " VLAN"
											+ MPLS_TUNNELER_GUI.clientVLAN);
									out.write("\r\n encapsulation dot1Q "
											+ MPLS_TUNNELER_GUI.clientVLAN);
									out.write("\r\n ip vrf forwarding vlan"
											+ MPLS_TUNNELER_GUI.clientVLAN);
									out.write("\r\n  ip address "
											+ "10.200."
											+ MPLS_TUNNELER_GUI.boitTunnelIP[2]
											+ ".1"
											+ " 255.255.255.0");
									out.write("\r\n ip tcp adjust-mss 1390");
					out.write("\r\n no cdp enable");
				for (int i = 0; i < MPLS_TUNNELER_GUI.amtRouters; i++) {





					out.write("\r\n!\r\nInterface Tunnel"
							+ MPLS_TUNNELER_GUI.clientVLAN
							+ MPLS_TUNNELER_GUI.routers[i][0][2]);
					out.write("\r\n  description Tunnel to "
							+ MPLS_TUNNELER_GUI.routers[i][0][0]);
					out.write("\r\n  bandwidth " + MPLS_TUNNELER_GUI.bandwidth);
					out.write("\r\n  ip vrf forwarding vlan"
							+ MPLS_TUNNELER_GUI.clientVLAN);
					out.write("\r\n  ip address "
							+ MPLS_TUNNELER_GUI.boitTunnelIP[0] + "."
							+ MPLS_TUNNELER_GUI.boitTunnelIP[1] + "."
							+ MPLS_TUNNELER_GUI.boitTunnelIP[2] + "."
							+ MPLS_TUNNELER_GUI.routers[i][2][2]
							+ " 255.255.255.252");
					out.write("\r\n  tunnel path-mtu-discovery");
					out.write("\r\n  tunnel source 10.200.200.1");
					out.write("\r\n  tunnel destination "
							+ MPLS_TUNNELER_GUI.routers[i][1][0]);
					out.write("\r\n  tunnel key "
							+ MPLS_TUNNELER_GUI.clientVLAN
							+ MPLS_TUNNELER_GUI.routers[i][0][2]);

					out.write("\r\n      ip route vrf vlan"
								+ MPLS_TUNNELER_GUI.clientVLAN
								+ " "
								+ MPLS_TUNNELER_GUI.routers[i][1][4]
								+ " "
								+ MPLS_TUNNELER_GUI.routers[i][1][2]
								+ " Tunnel"
								+ MPLS_TUNNELER_GUI.clientVLAN
								+ MPLS_TUNNELER_GUI.routers[i][0][2]
								+ "\r\n!");
				}// end for BOIT MPLS Router

				out.close();
			} catch (IOException e) {
				System.out
						.println("Uh oh.  Some issue with writing to the file. :\\ ");
			}

			if (MPLS_TUNNELER_GUI.amtPrinted == 0) {
				String file = "MPLS_TUNNELS_"
						+ MPLS_TUNNELER_GUI.routers[0][0][0] + ".txt";
				String program = "c:\\windows\\system32\\notepad.exe";
				Runtime.getRuntime().exec(program + " " + file);
			}// end if
			else {
				String file = "MPLS_TUNNELS_"
						+ MPLS_TUNNELER_GUI.routers[0][0][0]
						+ MPLS_TUNNELER_GUI.amtPrinted + ".txt";
				String program = "c:\\windows\\system32\\notepad.exe";
				Runtime.getRuntime().exec(program + " " + file);
			}// end else
		}// end try
		catch (IOException e) {
			System.out
					.println("Yikes.  Some issue with launching notepad.  Probably isn't where I hardcoded the program to look... :(");
		}
		MPLS_TUNNELER_GUI.amtPrinted++;
	}// end printConfig
}
