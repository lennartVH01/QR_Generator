package QR_Generator;

public class qr_header extends qrcode {
	public void header() {
		// creates positioning pattern
		Size dimentions = new Size(text);
		int dimention = dimentions.getDimentions();
		boolean[][] header = new boolean[9][9];
		

		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				header[i][j] = true;
			}
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				header[i][j] =  false;
			}
		}
		for(int i = 0; i <= 7; i++) {
			header[i][1] = false;
			header[1][i] = false;
			header[i][7] = false;
			header[7][i] = false;
		}
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(header[i][j] + ", ");

			}
			System.out.println("");
		}	
		//puts positioning pattern in qr-code matrix
		
		
		//puts header on to qr_code
		for(int i = 0; i <= 2; i++) {
			int xxi = 0;
			int xxj = 0;

			if (i == 1) {
				xxi = dimention - 9;
			} else {
				xxj = dimention - 9;
			}
			for(int j = 0; j < 9; j++) {
				for(int k = 0; k < 9; k++) {
					System.out.println("Dimentions: " + dimention + "  x: " + (i+xxi) + " y: " + (j+xxj));
					
					used[j+xxi][k+xxj] = true;
					qr_code[j+xxi][k+xxj] = header[j][k];
				}
			}
			String text;
			for(int j = 0; j <= dimention; j++) {
				for(int k = 0; k <= dimention; k++) {
					if (used[j][k] == true) {
						text = "+";
					} else {
						text = "-";
					}
					System.out.print(text + " ");
				}
				System.out.println("");
			}
		}
		
		//Formatting information Data
		int formatInfo = 0;
		String ECInfo = parameters.getECLevel();
		
			//Formatting info: Error correction
			switch(ECInfo) {
				case "L":
					formatInfo = 0b01;
					break;
				case "M":
					formatInfo = 0b00;
					break;
				case "Q":
					formatInfo = 0b11;
					break;
				case "H":
					formatInfo = 0b10;
					break;
			default:
					formatInfo = 0b01;
				System.out.println("Errr: Error correction formatting info not found);");
			}
		
		//applying fixed pattern
		int fpd = dimention - 18;
        int j = 0;
		boolean fp = false;
		for(int i = 0; i <= fpd; i++) {
			j += fpd;
			if(fp == false) {
				qr_code[9][j] = true;
				qr_code[j][9] = true;
				fp = true;
			} else {
				qr_code[9][j] = false;
				qr_code[j][9] = false;
				fp = false;
			}
			used[9][j] = true;
			used[j][9] = true;
		}
	}
}
