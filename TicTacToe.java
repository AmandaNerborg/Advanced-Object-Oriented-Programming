import javax.swing.ImageIcon;

/*
 * Creating a three-in-a-row type game with the use of the SimpleBoardGame framework intended 
 * for two players using mouse to make a move. Making a legal move switches the turn to the other player.
 */

public class TicTacToe extends SimpleBoardGame{
	
	// @invariance board[n][n] != null for all n ^ board == null
	// @invariance figures[n][n] != null for all n ^ figures == null
	// @invariance posClicked.length == 2 ^ posClicked == null
	// @invariance whiteClickedPos.length == 5
	// @invariance blackClickedPos.length == 5
	// @invariance playerTurn == 2
	
	ImageIcon[][] board;
	String[][] figures;
	int[] posClicked;
	int[] whiteClickedPos = new int[5];
	int[] blackClickedPos = new int[5];
	int playerTurn = 2;
	
	@Override
	public int evaluateBoard() {
		int win = 0;

		if(figures[0][0] == "whiteo" && figures[1][0] == "blacko" && figures[2][0] == "whiteo") win = 1;		
		if(figures[0][1] == "blacko" && figures[1][1] == "whiteo" && figures[2][1] == "blacko") win = 1;		
		if(figures[0][2] == "whiteo" && figures[1][2] == "blacko" && figures[2][2] == "whiteo") win = 1;
		
		if(figures[0][0] == "whiteo" && figures[0][1] == "blacko" && figures[0][2] == "whiteo") win = 1;		
		if(figures[1][0] == "blacko" && figures[1][1] == "whiteo" && figures[1][2] == "blacko") win = 1;		
		if(figures[2][0] == "whiteo" && figures[2][1] == "blacko" && figures[2][2] == "whiteo") win = 1;
		
		if(figures[0][0] == "whiteo" && figures[1][1] == "whiteo" && figures[2][2] == "whiteo") win = 1;		
		if(figures[0][2] == "whiteo" && figures[1][1] == "whiteo" && figures[2][0] == "whiteo") win = 1;
		
		if(figures[0][0] == "whitex" && figures[1][0] == "blackx" && figures[2][0] == "whitex") win = 1;		
		if(figures[0][1] == "blackx" && figures[1][1] == "whitex" && figures[2][1] == "blackx") win = 1;		
		if(figures[0][2] == "whitex" && figures[1][2] == "blackx" && figures[2][2] == "whitex") win = 1;
		
		if(figures[0][0] == "whitex" && figures[0][1] == "blackx" && figures[0][2] == "whitex") win = 1;		
		if(figures[1][0] == "blackx" && figures[1][1] == "whitex" && figures[1][2] == "blackx") win = 1;		
		if(figures[2][0] == "whitex" && figures[2][1] == "blackx" && figures[2][2] == "whitex") win = 1;
		
		if(figures[0][0] == "whitex" && figures[1][1] == "whitex" && figures[2][2] == "whitex") win = 1;		
		if(figures[0][2] == "whitex" && figures[1][1] == "whitex" && figures[2][0] == "whitex") win = 1;
		
		assert win == 0 || win == 1;
		
		return win;
	}
	
	@Override
	public ImageIcon[][] startBoard() {
		board = new ImageIcon[3][3];
		figures = new String[3][3];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(i==j || i==0&&j==2 || i==2 && j==0 ) {
					board[i][j] = new ImageIcon("white.png");
					figures[i][j] = "white";
				}
				else {
					board[i][j] = new ImageIcon("black.png");
					figures[i][j] = "black";
				}
			}		
		}
		
		assert board.length == figures.length;
		
		return board;
	}

	@Override
	public ImageIcon[][] iconClicked() {
		 posClicked = this.getIconClickedPos();
		 if(playerTurn==1){
		 
			 if(board[posClicked[1]][posClicked[0]].toString()=="white.png"){
				 board[posClicked[1]][posClicked[0]] = new ImageIcon("whitex.png");
				 figures[posClicked[1]][posClicked[0]] = "whitex";
				 playerTurn = 2;
			 }
			 else if(board[posClicked[1]][posClicked[0]].toString()=="black.png"){
				 board[posClicked[1]][posClicked[0]] = new ImageIcon("blackx.png");
				 figures[posClicked[1]][posClicked[0]] = "blackx";
				 playerTurn = 2;
			 }
		 }
		 else{
			 if(board[posClicked[1]][posClicked[0]].toString()=="white.png"){
				 board[posClicked[1]][posClicked[0]] = new ImageIcon("whiteo.png");
				 figures[posClicked[1]][posClicked[0]] = "whiteo";
				  playerTurn = 1;
			 }
			 else if(board[posClicked[1]][posClicked[0]].toString()=="black.png"){
				 board[posClicked[1]][posClicked[0]] = new ImageIcon("blacko.png");	
				 figures[posClicked[1]][posClicked[0]] = "blacko";
				 playerTurn = 1;
			 }

		 }
		 
		 assert playerTurn == 1 || playerTurn == 2;
		 
		 return board;
	}	
	
	@Override
	public ImageIcon[][] leftKeyPressed(){return null;}
	
	@Override
	public ImageIcon[][] rightKeyPressed(){return null;}
	
	@Override
	public ImageIcon[][] upKeyPressed(){return null;}
	
	@Override
	public ImageIcon[][] downKeyPressed(){return null;}
	
	@Override
	public String winningSound() {
		return "duhwinning.wav";
	}

	@Override
	public String lostSound() {return null;}

}
