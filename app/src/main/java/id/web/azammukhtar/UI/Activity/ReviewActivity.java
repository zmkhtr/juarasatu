package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.Model.Transaksi.Data;
import id.web.azammukhtar.Model.Transaksi.Transaksi;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.web.azammukhtar.UI.Activity.FormActivity.KEY_DATA;

public class ReviewActivity extends AppCompatActivity {
    private static final String TAG = "ReviewActivity";
    public static final String LOGO_DOMPET = "logoDompet";
    public static final String NOMINAL = "nominal";
    Data data;

    @BindView(R.id.textReviewDompetAnda)
    TextView mDompetAnda;

    @BindView(R.id.textReviewDompetTujuan)
    TextView mDompetTujuan;

    @BindView(R.id.textReviewKodeUnik)
    TextView mKodeUnik;

    @BindView(R.id.textReviewNominal)
    TextView mNominal;

    @BindView(R.id.textReviewTotalTransfer)
    TextView mTransfer;

    @BindView(R.id.textReviewNominalTransfer)
    TextView mNominalTransfer;

    @BindView(R.id.textReviewNomorTujuan)
    TextView mNomorTujuan;

    String totalTransfer;
    int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this);
        Intent intent = getIntent();

        data = intent.getParcelableExtra(KEY_DATA);
        Log.d(TAG, "onCreate: " + data.getDompetAsal());
        mDompetAnda.setText(data.getDompetAsal());
        mDompetTujuan.setText(data.getDompetTujuan());
        mNominal.setText("Rp " + data.getNominal());
        mNomorTujuan.setText(data.getNomor());
        mNominalTransfer.setText("Rp " + data.getNominal());
        int randomNum = ThreadLocalRandom.current().nextInt(100, 998 + 1);
        mKodeUnik.setText(String.valueOf(randomNum));
        total = Integer.parseInt(data.getNominal()) + randomNum;
        totalTransfer = "Rp " + total;
        mTransfer.setText(totalTransfer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Review</font>"));
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        }
    }

    private void sendData(){
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Proses data...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        Call<Transaksi> call = ApiNetwork.getApiInterface().postTransaksi
                ("Bearer " + SessionManager.getInstance().getUserToken(),
                        data.getJenis(),
                        data.getDompetAsal(),
                        data.getDompetTujuan(),
                        data.getNomorAsal(),
                        data.getNomor(),
                        String.valueOf(total));
        call.enqueue(new Callback<Transaksi>() {
            @Override
            public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
                if (response.isSuccessful()){
                    Toasty.success(ReviewActivity.this, "Pengajuan berhasil, Upload bukti transfer agar diproses lebih cepat", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReviewActivity.this, KirimActivity.class);
                    intent.putExtra(LOGO_DOMPET, data.getDompetAsal());
                    intent.putExtra(NOMINAL, String.valueOf(total));
                    startActivity(intent);
                    finish();
                    progressDialog.dismiss();
                } else {
                    Toasty.error(ReviewActivity.this, "Gagal proses data", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Transaksi> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t);
                Toasty.error(ReviewActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    @OnClick(R.id.btnReviewLanjut)
    void lanjut(){
        sendData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
