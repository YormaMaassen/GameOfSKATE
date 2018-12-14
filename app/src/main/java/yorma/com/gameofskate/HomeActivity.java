package yorma.com.gameofskate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnExit;
    private Button btnParam;
    static int nbJoueurs = -1;
    static int choix=-1;


    public static int getChoix() {
        return choix;
    }

    public static void setChoix(int choix) {
        HomeActivity.choix = choix;
    }

    public static int getNbJoueurs() {
        return nbJoueurs;
    }

    public static void setNbJoueurs(int nbJoueurs) {
        HomeActivity.nbJoueurs = nbJoueurs;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        btnExit = (Button) findViewById(R.id.btnquit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnParam = (Button) findViewById(R.id.btnParam);
        btnParam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(HomeActivity.this, Settings.class);
                        startActivity(i);
                    }

                }, 0 );
                if (choix == -1){

                }
                else {
                    finish();
                }
            }
        });

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nbJoueurs == -1 || choix == -1) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(HomeActivity.this, Settings.class);
                            startActivity(i);
                        }

                    }, 0 );
                }
                else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(HomeActivity.this, PlayerNames.class);
                            startActivity(i);
                        }

                    }, 0 );
                }
            }
        }) ;

        System.out.println("val = " + nbJoueurs);

        System.out.println("val choix = " + choix);

    }
}
