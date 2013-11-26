public class scratchConfig {
	public scratchConfig(final int g) {
		MPLS_TUNNELER_GUI.scratchString = "";
		if (MPLS_TUNNELER_GUI.startScratchCheck.isSelected()) {
			MPLS_TUNNELER_GUI.scratchString += ("\r\n\r\nenable");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nconfig t");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nservice password-encryption");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nhostname " + MPLS_TUNNELER_GUI.routers[g][0][0]);
			MPLS_TUNNELER_GUI.scratchString += ("\r\nenable secret N0way123");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nconfig-reg 0x2102");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nip cef");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nno ip https server");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nno ip http server");

MPLS_TUNNELER_GUI.scratchString += ("\r\n    class-map match-any Internal_Traffic");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      match access-group 106");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    class-map match-any RDP");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      match access-group 107");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    class-map match-any Voice");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      match access-group 105");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    !");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    !");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    policy-map ToBranch");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class Voice");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       priority 768");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 5");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class Internal_Traffic");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 3");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class class-default");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       fair-queue");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 2");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    policy-map ToBranch-Tunnel");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class class-default");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       shape average 1537000");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       service-policy ToBranch");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    policy-map ToBOIT");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class RDP");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       priority 768");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 3");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class Internal_Traffic");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 3");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class class-default");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       fair-queue");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       set ip precedence 2");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    policy-map ToBOIT-Tunnel");
MPLS_TUNNELER_GUI.scratchString += ("\r\n      class class-default");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       shape average 1537000");
MPLS_TUNNELER_GUI.scratchString += ("\r\n       service-policy ToBOIT");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    !");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 105 remark *** Match Phone System Ports ***");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 105 permit udp any any range 16384 18384");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 105 permit udp any any range 65000 65010");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 105 permit udp any any range 5060 5061");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 106 remark *** Match Local Network Traffic ***");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 106 permit ip any  " + MPLS_TUNNELER_GUI.routers[g][1][4] + " " + MPLS_TUNNELER_GUI.routers[g][1][5]);
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 107 remark *** Match RDP Traffic ***");
MPLS_TUNNELER_GUI.scratchString += ("\r\n    access-list 107 permit tcp any any eq 3389");

			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 97 permit " + MPLS_TUNNELER_GUI.routers[g][1][4] + " " + MPLS_TUNNELER_GUI.routers[g][1][5]);
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 97 permit 10.200.200.0 0.0.0.255");

			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit gre any any");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit icmp any any");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit udp any range snmp snmptrap any");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit tcp any eq bgp any");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit tcp any any eq telnet");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit tcp any any established");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit udp any any eq tftp");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 permit udp host 10.200.200.200 any");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-list 150 deny   ip any any");

			MPLS_TUNNELER_GUI.scratchString += ("\r\n!\r\nline console 0");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  Logging synchronous");
			MPLS_TUNNELER_GUI.scratchString += ("\r\nline vty 0 4");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  access-class 97 in");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  Logging synchronous");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  password N0way123");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  login");

			MPLS_TUNNELER_GUI.scratchString += ("\r\n!\r\ninterface FastEthernet0/0");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  ip address "
					+ MPLS_TUNNELER_GUI.routers[g][1][1] + " " + MPLS_TUNNELER_GUI.routers[g][1][2]);
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  ip tcp adjust-mss 1390");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  no shutdown");
			if(MPLS_TUNNELER_GUI.cisco1800.isSelected()){
				MPLS_TUNNELER_GUI.scratchString += ("\r\n!\r\ninterface Serial0/0/0");
			}
			else if(MPLS_TUNNELER_GUI.cisco2600.isSelected()){
			MPLS_TUNNELER_GUI.scratchString += ("\r\n!\r\ninterface Serial0/0");
			}
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  description " + MPLS_TUNNELER_GUI.routers[g][1][3]);
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  ip address "
					+ MPLS_TUNNELER_GUI.routers[g][1][0] + " 255.255.255.252");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  encapsulation ppp");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  service-module t1 timeslots 1-24");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  service-module t1 remote-alarm-enable");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  ip access-group 150 in");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  no shutdown");

			MPLS_TUNNELER_GUI.scratchString += ("\r\n!\r\nrouter bgp 65001");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  no synchronization");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  bgp log-neighbor-changes");
			String tringo = MPLS_TUNNELER_GUI.routers[g][1][0].toString();
			tringo = tringo.replaceAll("\\s+$", "");
			String delims2 = "[.]";
			String[] tokens2 = tringo.split(delims2);
			for (int i = 0; i < tokens2.length; i++) {
				MPLS_TUNNELER_GUI.rtrSerialIP[i] = Integer.parseInt(tokens2[i]);
			}
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  neighbor "
					+ MPLS_TUNNELER_GUI.rtrSerialIP[0] + "."
					+ MPLS_TUNNELER_GUI.rtrSerialIP[1] + "."
					+ MPLS_TUNNELER_GUI.rtrSerialIP[2] + "."
					+ (MPLS_TUNNELER_GUI.rtrSerialIP[3] - 1) + " remote-as 7018");
			MPLS_TUNNELER_GUI.scratchString += ("\r\n  no auto-summary");
		}// end if
	}// end scratchConfig
}
