public class networkAddress{

	networkAddress(final int u){

		int[] rtrNetwork={1, 1, 1, 1};
			String tring = MPLS_TUNNELER_GUI.routers[u][1][1];
			String delims = "[.]";
			String[] tokens = tring.split(delims);
			for (int i = 0; i < tokens.length; i++) {
				rtrNetwork[i] = Integer
					.parseInt(tokens[i]);
			}

		int[] rtrSubnet ={1, 1, 1, 1};
			tring = MPLS_TUNNELER_GUI.routers[u][1][2];
			delims = "[.]";
			tokens = tring.split(delims);
			for (int i = 0; i < tokens.length; i++) {
				rtrSubnet[i] = Integer
						.parseInt(tokens[i]);
			}


		for(int x=0;x<4;x++){
			if(rtrSubnet[x]==255){/*do nothing*/}
			else{
				rtrNetwork[x]=rtrNetwork[x]&rtrSubnet[x];
			}
		}
			System.out.print("   Network Address:   "); for(int x=0;x<4;x++){System.out.print(rtrNetwork[x]);if(x!=3){System.out.print(".");}} System.out.print(" / "); for(int x=0;x<4;x++){System.out.print(rtrSubnet[x]);if(x!=3){System.out.print(".");}}

		MPLS_TUNNELER_GUI.routers[u][1][4]=(Integer.toString(rtrNetwork[0])+"."+Integer.toString(rtrNetwork[1])+"."+Integer.toString(rtrNetwork[2])+"."+Integer.toString(rtrNetwork[3]));
		MPLS_TUNNELER_GUI.rtrNetwork=rtrNetwork;
		MPLS_TUNNELER_GUI.rtrSubnet=rtrSubnet;
		MPLS_TUNNELER_GUI.routers[u][1][5]=(Integer.toString(255^rtrSubnet[0])+"."+Integer.toString(255^rtrSubnet[1])+"."+Integer.toString(255^rtrSubnet[2])+"."+Integer.toString(255^rtrSubnet[3]));

	}
}
