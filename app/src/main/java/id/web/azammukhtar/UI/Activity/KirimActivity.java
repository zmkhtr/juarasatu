package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.web.azammukhtar.Model.Last.Last;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.web.azammukhtar.UI.Activity.ReviewActivity.LOGO_DOMPET;
import static id.web.azammukhtar.UI.Activity.ReviewActivity.NOMINAL;

public class KirimActivity extends AppCompatActivity {
    private static final String TAG = "KirimActivity";
    public static final String ID_UPLOAD = "idUpload";

    @BindView(R.id.imageKirimTujuan)
    ImageView mImage;

    @BindView(R.id.textKirimTotalTransfer)
    TextView mTotalTransfer;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim);
        ButterKnife.bind(this);
        intent = getIntent();
        mTotalTransfer.setText("Rp " + intent.getStringExtra(NOMINAL));
        String logo = intent.getStringExtra(LOGO_DOMPET);
        switch (logo){
            case "gopay":
                mImage.setImageResource(R.drawable.gopay);
                break;
            case "dana":
                mImage.setImageResource(R.drawable.dana);
                break;
            case "ovo":
                mImage.setImageResource(R.drawable.ovo);
                break;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Detail Transfer</font>"));
        }
    }

    @OnClick(R.id.buttonKirimLanjut)
    void lanjutKeUpload(){
        Intent intent = new Intent(KirimActivity.this, UploadActivity.class);
        startActivity(intent);
        finish();
    }

}
