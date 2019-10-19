import javax.swing.ImageIcon;

/*
 * Creating a Sokoban type game with the use of the SimpleBoardGame framework using the arrow keys to move.
 */

public class Sakoban extends SimpleBoardGame{
	
	// @invariance playerPos.length == 2 ^ playerPos == null
	// @invariance prevPlayerPos.length == 2 ^ prevPlayerPos == null
	// @invariance nextPlayerPos.length == 2 ^ nextPlayerPos == null
	// @invariance board[n][n] != null for all n ^ board == null
	// @invariance figures[n][n] != null for all n ^ figures == null

	int[] playerPos;
	int[] prevPlayerPos;
	int[] nextPlayerPos;
	ImageIcon[][] board;
	String[][] figures;
	ImageIcon underPlayer;
	ImageIcon prevUnderPlayer;
	
	private void changePlayerPos(int x, int y){
		
		assert x != 0 || y != 0;
		
		boolean change = legalMove(x, y);
		
		if(change == true){		
			prevUnderPlayer = underPlayer;
			underPlayer = board[nextPlayerPos[1]][nextPlayerPos[0]];
			
			prevPlayerPos[0] = playerPos[0];
			prevPlayerPos[1] = playerPos[1];
			
			playerPos[0] = nextPlayerPos[0];
			playerPos[1] = nextPlayerPos[1];
			
			board[prevPlayerPos[1]][prevPlayerPos[0]] = prevUnderPlayer;
			board[playerPos[1]][playerPos[0]] = new ImageIcon ("player.png");
		}
		
	}

	public boolean legalMove(int x, int y){
		
		assert x != 0 || y != 0;
		
		nextPlayerPos[0] += x;
		nextPlayerPos[1] += y;
		
		if(figures[nextPlayerPos[1]][nextPlayerPos[0]] == "wall"){
			nextPlayerPos[0] -= x;
			nextPlayerPos[1] -= y;
			return false;
		}
		else if(figures[nextPlayerPos[1]][nextPlayerPos[0]] == "blankmarked"){
			return true;
		}
		else if(figures[nextPlayerPos[1]][nextPlayerPos[0]] == "crate"){
			
			if(figures[nextPlayerPos[1]+y][nextPlayerPos[0]+x] == "blank"){
				board[nextPlayerPos[1]+y][nextPlayerPos[0]+x] = new ImageIcon ("crate.png");
				figures[nextPlayerPos[1]+y][nextPlayerPos[0]+x] = "crate";
				board[nextPlayerPos[1]][nextPlayerPos[0]] = new ImageIcon ("blank.png");
				figures[nextPlayerPos[1]][nextPlayerPos[0]] = "blank";
				return true;
			}
			else if(figures[nextPlayerPos[1]+y][nextPlayerPos[0]+x] == "blankmarked"){
				board[nextPlayerPos[1]+y][nextPlayerPos[0]+x] = new ImageIcon ("cratemarked.png");
				figures[nextPlayerPos[1]+y][nextPlayerPos[0]+x] = "cratemarked";
				board[nextPlayerPos[1]][nextPlayerPos[0]] = new ImageIcon ("blank.png");
				figures[nextPlayerPos[1]][nextPlayerPos[0]] = "blank";
				return true;
			}
			else{
				nextPlayerPos[0] -= x;
				nextPlayerPos[1] -= y;
				return false;
			}
		}
		else if(figures[nextPlayerPos[1]][nextPlayerPos[0]] == "cratemarked"){
			nextPlayerPos[0] -= x;
			nextPlayerPos[1] -= y;
			return false;
		}
		
		else return true;
		
	}
	
	@Override
	public int evaluateBoard() {
		
		int status = 1;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(figures[i][j] == "crate") status = 0;				
			}
		}		
		if(figures[1][3] == "crate" || 
				figures[6][8] == "cratemarked" && figures[6][6] != "cratemarked" ||
				figures[6][8] == "cratemarked" && figures[7][4] != "cratemarked" ||
				figures[6][8] == "cratemarked" && figures[3][3] != "cratemarked" ||
				figures[6][6] == "cratemarked" && figures[6][7] == "crate" ||
				figures[7][8] == "crate" ||
				figures[4][6] == "crate" ||
				figures[1][6] == "crate" || 
				figures[1][8] == "crate" ){
			status = -1;
		}
		
		assert status >= -1 || status <= 1;
		
		return status;		
	}
	
	@Override
	public ImageIcon[][] startBoard() {
		
		playerPos = new int[2];
		prevPlayerPos = new int[2];
		nextPlayerPos = new int[2];
		
		playerPos[0] = 1;
		playerPos[1] = 1;			
		prevPlayerPos[0] = 1;
		prevPlayerPos[1] = 1;
		nextPlayerPos[0] = 1;
		nextPlayerPos[1] = 1;
		
		board = new ImageIcon[10][10];
		underPlayer = new ImageIcon ("blank.png");
		prevUnderPlayer = new ImageIcon ("blank.png");
		figures = new String[10][10];
					
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(i==0 || j== 0 || j==9 || i==9){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i==2 && j>=1 && j<=2){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i==5 && j>=1 && j<=6){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i>=1 && i<=3 && j==4){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i>=6 && i<=7 && j==5){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i>=3 && i<=5 && j==6){
					board[j][i] = new ImageIcon ("wall.png");
					figures[j][i] = "wall";
				}
				else if(i==3 && j==3 || i==1 && j==5 || i==4 && j==7 || i==6 && j==6 || i==8 && j==6){
					board[j][i] = new ImageIcon ("blankmarked.png");
					figures[j][i] = "blankmarked";
				}
				else if(i==3 && j==2 || i==3 && j==5 || i==5 && j==7 || i==7 && j==6 || i==7 && j==3){
					board[j][i] = new ImageIcon ("crate.png");
					figures[j][i] = "crate";
				}
				else if(i==playerPos[0] && j==playerPos[1]){
					board[j][i] = new ImageIcon ("player.png");
					figures[j][i] = "player";
				}
				else{
					board[j][i] = new ImageIcon ("blank.png");
					figures[j][i] = "blank";
				}
				
			}
		}
		
		assert playerPos.length == 2;
		assert prevPlayerPos.length == 2;
		assert nextPlayerPos.length == 2;
		assert underPlayer != null;
		assert prevUnderPlayer != null;
		assert board.length == figures.length;
		
		return board;
	}

	@Override
	public ImageIcon[][] leftKeyPressed() {
		changePlayerPos(-1,0);
		return board;
	}

	@Override
	public ImageIcon[][] rightKeyPressed() {
		changePlayerPos(1,0);
		return board;
	}

	@Override
	public ImageIcon[][] upKeyPressed() {
		changePlayerPos(0,-1);
		return board;
	}

	@Override
	public ImageIcon[][] downKeyPressed() {
		changePlayerPos(0, 1);
		return board;
	}

	@Override
	public ImageIcon[][] iconClicked() {return null;}

	@Override
	public String winningSound() {
		return "duhwinning.wav";
	}

	@Override
	public String lostSound() {
		return "doh.wav";
	}
	 
}
