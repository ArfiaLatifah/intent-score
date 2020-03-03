package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText;
    private TextView awayText;
    private ImageView homeImage;
    private ImageView awayImage;
    private int skorHome;
    private TextView homeScore;
    private int skorAway;
    private TextView awayScore;

    private static final String HASIL_KEY = "hasil";

    //1.Menampilkan detail match sesuai data dari main activity
    //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
    //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage=findViewById(R.id.home_logo);
        awayImage=findViewById(R.id.away_logo);
        homeScore=findViewById(R.id.score_home);
        awayScore=findViewById(R.id.score_away);

        skorHome=0;
        skorAway=0;

        homeScore.setText("0");
        awayScore.setText("0");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // TODO: display value here
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("imageHome");
            Bitmap bmp2 = extra.getParcelable("imageAway");

            homeImage.setImageBitmap(bmp);
            awayImage.setImageBitmap(bmp2);

            homeText.setText(extras.getString("home"));
            awayText.setText(extras.getString("away"));
        }

    }

    public void handleAddHome(View view) {
        skorHome++;
        homeScore.setText(String.valueOf(skorHome));

    }

    public void handleAddAway(View view) {
        skorAway++;
        awayScore.setText(String.valueOf(skorAway));
    }

    public void handleHasil(View view) {
        String hasil;

        if (skorHome>skorAway){
            hasil = homeText.getText().toString();
        } else if (skorAway>skorHome){
            hasil = awayText.getText().toString();
        } else {
            hasil = "DRAW";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }
}