package yorma.com.gameofskate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    ArrayList<String> listPlayers = PlayerNames.getListPlayers();
    ArrayList<Integer> PointsPerPlayer = new ArrayList<Integer>();
    int choixLettres = HomeActivity.getChoix();
    boolean offense = true;
    String PlayerLandedLastTrick;
    String PlayersLost = "Players eliminated : ";

    private Button Missed;
    private Button Landed;

    private TextView playersTurn;
    private TextView LettersStr;
    private TextView WhatToDO;
    private TextView lastT;
    private TextView lostPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        playersTurn = (TextView) findViewById(R.id.playerTurn);
        WhatToDO = (TextView) findViewById(R.id.whatDo);
        Missed = (Button) findViewById(R.id.trickMissed);
        Landed = (Button) findViewById(R.id.trickLanded);

        LettersStr = (TextView) findViewById(R.id.nbLettersStr);
        lastT = (TextView) findViewById(R.id.LastTry);
        lostPlayers = (TextView) findViewById(R.id.PlayerLost);
        lastT.setVisibility(View.INVISIBLE);
        playersTurn.setText("Player turn : " + listPlayers.get(0));
        PlayerLandedLastTrick = "";


        initPlayerPoints();


        //Action when player misses the trick
        Missed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!offense && (choixLettres == PointsPerPlayer.get(0)+1)){
                    System.out.println("PERDUUUUUUUUUUUUUUUU WALLAH");
                    lostPlayers.setText(lostPlayers.getText() + listPlayers.get(0));

                    listPlayers.remove(0);
                    PointsPerPlayer.remove(0);
                    if (listPlayers.size()==1){
                        System.out.println("Le gagnant est : " + listPlayers.get(0));
                        playersTurn.setText("Player turn : " + listPlayers.get(0));
                    }
                    else {
                        playersTurn.setText("Player turn : " + listPlayers.get(0));
                        LettersStr.setText("Letters : " + convertIntToLetters(PointsPerPlayer.get(0)));
                        System.out.println("Test 1");
                        if (PlayerLandedLastTrick == listPlayers.get(0) || PlayerLandedLastTrick == "") {
                            offense = true;
                            WhatToDO.setText("Offense, set the trick");
                            PlayerLandedLastTrick = "";
                        } else {
                            offense = false;
                            WhatToDO.setText("Defense, land the trick");
                        }
                        if (!offense && (choixLettres == PointsPerPlayer.get(0) + 1)) {
                            lastT.setVisibility(View.VISIBLE);
                            System.out.println("Test 2");
                        } else {
                            lastT.setVisibility(View.INVISIBLE);
                            System.out.println("Test 3");
                        }
                    }
                }
                else {
                    GoNext(listPlayers, PointsPerPlayer, offense);
                    playersTurn.setText("Player turn : " + listPlayers.get(0));
                    LettersStr.setText("Letters : " + convertIntToLetters(PointsPerPlayer.get(0)));
                    if (PlayerLandedLastTrick == listPlayers.get(0) || PlayerLandedLastTrick == "") {
                        offense = true;
                        WhatToDO.setText("Offense, set the trick");
                        PlayerLandedLastTrick = "";
                    } else {
                        offense = false;
                        WhatToDO.setText("Defense, land the trick");
                    }
                    if (!offense && (choixLettres == PointsPerPlayer.get(0) + 1)) {
                        System.out.println("PERDUUUUUUUUUUUUUUUU WALLAH 222222222");
                        lastT.setVisibility(View.VISIBLE);
                    } else {
                        lastT.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        //Action when player lands the trick
        Landed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PlayerLandedLastTrick == "") {
                    PlayerLandedLastTrick = listPlayers.get(0);
                }
                offense = true;
                GoNext(listPlayers,PointsPerPlayer,offense);
                playersTurn.setText("Player turn : " + listPlayers.get(0));
                LettersStr.setText("Letters : " + convertIntToLetters(PointsPerPlayer.get(0)));
                if (PlayerLandedLastTrick == listPlayers.get(0)) {
                    offense = true;
                    WhatToDO.setText("Offense, set the trick");
                    PlayerLandedLastTrick = "";
                }
                else {
                    offense = false;
                    WhatToDO.setText("Defense, land the trick");
                }
                if (!offense && (choixLettres == PointsPerPlayer.get(0)+1)){
                    System.out.println("PERDUUUUUUUUUUUUUUUU WALLAH 222222222");
                    lastT.setVisibility(View.VISIBLE);
                }
                else {
                    lastT.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void GoNext(ArrayList<String> arr, ArrayList<Integer> nbpts, boolean offtrue) {
        if (offtrue) {
            String temp = arr.get(0);
            arr.remove(0);
            arr.add(temp);

            Integer var = nbpts.get(0);
            nbpts.remove(0);
            nbpts.add(var);
        }
        else {
            String temp = arr.get(0);
            arr.remove(0);
            arr.add(temp);

            Integer var = nbpts.get(0);
            nbpts.remove(0);
            nbpts.add(var+1);
        }

    }

    public void initPlayerPoints(){
        int temp = 0;
        for (int i = 0; i < this.listPlayers.size(); i++) {
            this.PointsPerPlayer.add(temp);
        }
    }



    public String convertIntToLetters(int nb) {
        String res ="";
        if (choixLettres == 3) {
            switch (nb) {
                case 0:
                    res = "";
                    break;
                case 1:
                    res = "S.";
                    break;
                case 2:
                    res = "S.K.";
                    break;
            }
        }
        if (choixLettres == 5) {
            switch (nb) {
                case 0:
                    res = "";
                    break;
                case 1:
                    res = "S.";
                    break;
                case 2:
                    res = "S.K.";
                    break;
                case 3:
                    res = "S.K.A.";
                    break;
                case 4:
                    res = "S.K.A.T.";
                    break;
            }
        }
        if (choixLettres == 10) {
            switch (nb) {
                case 0:
                    res = "";
                    break;
                case 1:
                    res = "S.";
                    break;
                case 2:
                    res = "S.K.";
                    break;
                case 3:
                    res = "S.K.A.";
                    break;
                case 4:
                    res = "S.K.A.T.";
                    break;
                case 5:
                    res = "S.K.A.T.E.";
                    break;
                case 6:
                    res = "S.K.A.T.E.B.";
                    break;
                case 7:
                    res = "S.K.A.T.E.B.O.";
                    break;
                case 8:
                    res = "S.K.A.T.E.B.O.A.";
                    break;
                case 9:
                    res = "S.K.A.T.E.B.O.A.R.";
                    break;
            }
        }
        return res;
    }
}
