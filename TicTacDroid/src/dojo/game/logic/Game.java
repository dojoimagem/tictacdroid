package dojo.game.logic;

public class Game {
	private final String BLANK_CELL =  "-";
	private String[] cells = new String[9];
	private String player = "X";
	
	public Game(){
		for (int i=0; i<cells.length; i++) {
			cells[i] = BLANK_CELL;
		}
	}
	
	public void setMarked(int position){
		if (cells[position] != BLANK_CELL) {
			throw new RuntimeException("Can't play on already filled cell");
		}
		cells[position] = player;
		togglePlayer();
	}
	
	public boolean getMarked(int position){
		return cells[position] != BLANK_CELL;
	}
	

	public String getPlayerMarked(int position) {
		return cells[position];
	}
	
	private void togglePlayer() {
		player = (player.equals("X") ? "O" : "X");
	}

	public boolean hasEnded() {
			
		if(hasAnyRowInSamePlayer())
		{
			return true;
		}
		
		for(String cell: cells)
		{
			if(cell == BLANK_CELL)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private boolean hasAnyRowInSamePlayer()
	{
		for(int i=0; i < 9; i+=3 )
		{	
			String marked = getPlayerMarked(i);
			if(! marked.equals(BLANK_CELL))
			{
				if(marked.equals(getPlayerMarked(i+1)) && 
				   marked.equals(getPlayerMarked(i+2))){
					return true;
				}				
			}
		}
		
		return false;
	}
	
	
	public String[] getCells(){
		return cells;
	}
}
