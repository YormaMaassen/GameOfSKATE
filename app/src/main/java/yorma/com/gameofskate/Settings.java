package yorma.com.gameofskate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private Button btnPlus;
    private Button btnMoins;
    private TextView nbPlayers;
    private Button btnSave;


    private RadioButton c3;
    private RadioButton c5;
    private RadioButton c10;

    private TextView errortext;

    private int nbdefault = 2;
    static int nb = -1;
    private int choix;
    private int choixdefault;
    private String errorMesssage;
    private String blabla = "ojsfoisrpofnzrobgfo^zrgôro^gqfo^dngôdqfngondfkgjbfkdbgkjdfgoizrheoiv ov zoirnfo zor nb";


    public static int getNb() {
        return nb;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMoins = (Button) findViewById(R.id.btnMoins);
        nbPlayers = (TextView) findViewById(R.id.nbPlayers);
        btnSave = (Button) findViewById(R.id.btnSave);

        c3 = (RadioButton) findViewById(R.id.Rbtn3);
        c5 = (RadioButton) findViewById(R.id.Rbtn5);
        c10 = (RadioButton) findViewById(R.id.Rbtn10);

        errortext = (TextView) findViewById(R.id.Errortxt);




        if (HomeActivity.getChoix() == -1) {
            choix = choixdefault;
        }
        else {
            choix = HomeActivity.getChoix();
            switch (choix){
                case 3:
                    c3.setChecked(true);
                    break;
                case 5:
                    c5.setChecked(true);
                    break;
                case 10:
                    c10.setChecked(true);
            }
        }

        if (HomeActivity.getNbJoueurs() == -1){
            nb = nbdefault;
            nbPlayers.setText(Integer.toString(nbdefault));
            errortext.setText("please choose a number of letters");
            errortext.setTextColor(0xFFFF0000);
        }
        else {
            nb = HomeActivity.getNbJoueurs();
            nbPlayers.setText(Integer.toString(nb));
        }




        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nb==10) {
                    errortext.setText("Maximum 10 players allowed");
                    errortext.setTextColor(0xFFFF0000);
                }
                else {
                    nb = Integer.parseInt(nbPlayers.getText().toString());
                    nb = nb + 1;
                    nbPlayers.setText(Integer.toString(nb));
                }
            }
        });

        btnMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nb==2) {
                    errortext.setText("At least 2 players required");
                    errortext.setTextColor(0xFFFF0000);
                }
                else {
                    nb = Integer.parseInt(nbPlayers.getText().toString());
                    nb = nb -1;
                    nbPlayers.setText(Integer.toString(nb));
                }
            }
        });



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!c3.isChecked() && !c5.isChecked() && !c10.isChecked()) {
                    errortext.setText("please choose a number of letters");
                }
                else {
                    if (c3.isChecked()) {
                        choix = 3;
                    } else if (c5.isChecked()) {
                        choix = 5;
                    } else if (c10.isChecked()) {
                        choix = 10;
                    }
                    HomeActivity.setNbJoueurs(nb);
                    HomeActivity.setChoix(choix);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(Settings.this, HomeActivity.class);
                            startActivity(i);
                        }


                    }, 0 );
                    finish();
                }

            }
        });


        System.out.println("val = " + nb);
    }
    public String getErrorMesssage() {
        return errorMesssage;
    }

    public void setErrorMesssage(String errorMesssage) {
        this.errorMesssage = errorMesssage;
    }
}
