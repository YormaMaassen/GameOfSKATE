package yorma.com.gameofskate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class PlayerWon extends AppCompatActivity {

    String winner ;

    private TextView winName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player_won);
        System.out.println("OUDZFHOIEZHFOZBEFPOJEZBPIFEZPIIFPZEIBFP");

        winner = Game.getPlayerWon();

        winName = (TextView) findViewById(R.id.playerWonName);

        winName.setText(winner);


        HomeActivity.setChoix(-1);
        HomeActivity.setNbJoueurs(-1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(PlayerWon.this, HomeActivity.class);
                startActivity(i);
                finish();
            }


        }, 3000 );
    }
}
