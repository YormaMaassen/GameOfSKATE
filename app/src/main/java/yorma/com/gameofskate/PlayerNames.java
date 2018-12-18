package yorma.com.gameofskate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerNames extends AppCompatActivity {

    private int current_player = 1;
    static ArrayList<String> listPlayers = new ArrayList<String>();
    private int nbPlayersTotal = HomeActivity.getNbJoueurs();

    private Button btnAdd;
    private TextView playerNb;
    private EditText playerName;


    public static ArrayList<String> getListPlayers() {
        return listPlayers;
    }

    public static void setListPlayers(ArrayList<String> listPlayers1) {
        listPlayers = listPlayers1;
    }


    public int getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(int current_player) {
        this.current_player = current_player;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player_names);

        playerNb = (TextView) findViewById(R.id.Playernb);
        btnAdd = (Button) findViewById(R.id.addPlayer);
        playerName = (EditText) findViewById(R.id.editName);

        playerNb.setText("Player n°" + current_player);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_player+1 == nbPlayersTotal){
                    btnAdd.setText("Start Game");
                }
                String namestr = playerName.getText().toString();
                if (namestr.isEmpty()){
                    playerName.setError("Field can't be empty");
                }
                else {
                    listPlayers.add(namestr);
                    System.out.println("Players : " + listPlayers.get(current_player - 1));
                    playerName.setText("");
                    current_player++;
                    playerNb.setText("Player n°" + current_player);
                    if (current_player == nbPlayersTotal+1){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(PlayerNames.this, Game.class);
                                startActivity(i);
                            }


                        }, 0 );
                        finish();
                    }
                }
            }
        });


    }
}
