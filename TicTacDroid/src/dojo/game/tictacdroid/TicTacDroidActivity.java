package dojo.game.tictacdroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import dojo.game.logic.Game;

public class TicTacDroidActivity extends Activity {
    
	private Game game;
	private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        gridView = (GridView) findViewById(R.id.gridView);   
        
        newGame();

        gridView.setOnItemClickListener(new OnItemClickListener() {
        	
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				String lastPlayer = "";
				try{
					game.setMarked(position);
					lastPlayer = game.getPlayerMarked(position);
					((TextView) view).setText(lastPlayer);
				}
				catch(RuntimeException ex){
					Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
				}
				
				if (game.hasEnded()){
					AlertDialog alertDialog = new AlertDialog.Builder(TicTacDroidActivity.this).create();               
		            alertDialog.setTitle("Game Ended");
		            alertDialog.setMessage(String.format("Player %s is the winner", lastPlayer));
		            alertDialog.setButton("OK", new android.content.DialogInterface.OnClickListener(){
		                public void onClick(DialogInterface dialog, int which){
		                	newGame();
		                }
		            });
		            alertDialog.show();
				}
			}
		});
        
        Button btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnNewGame.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				newGame();
			}
			
		});
    }
    
    private void newGame(){
    	game = new Game();
    	
    	ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, game.getCells());
        gridView.setAdapter(listAdapter);
    }
}