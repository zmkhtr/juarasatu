package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.web.azammukhtar.Model.History.HistoryData;
import id.web.azammukhtar.R;

import static id.web.azammukhtar.UI.Fragment.CatatanFragment.HISTORY_KEY;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    @BindView(R.id.textDetailDompetAsal)
    TextView mDompetAsal;
    @BindView(R.id.textDetailDompetTujuan)
    TextView mDompetTujuan;
    @BindView(R.id.textDetailNomorTujuan)
    TextView mNomorTujuan;
    @BindView(R.id.textDetailStatus)
    TextView mStatus;
    @BindView(R.id.textDetailTanggal)
    TextView mTanggal;
    @BindView(R.id.textDetailTotalTransfer)
    TextView mTotalTransfer;
    @BindView(R.id.btnKeUpload)
    Button mKeUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Detail History</font>"));
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        HistoryData historyData = intent.getParcelableExtra(HISTORY_KEY);
        mDompetAsal.setText(historyData.getDompetAsal());
        mDompetTujuan.setText(historyData.getDompetTujuan());
        mNomorTujuan.setText(historyData.getNomor());
        String tgl = historyData.getCreatedAt().substring(0,10);
        mTanggal.setText(tgl);
        mTotalTransfer.setText(historyData.getNominal());

        switch (historyData.getStatus()){
            case "0":
                mStatus.setText("Belum diproses");
                mStatus.setTextColor(Color.parseColor("#A1A1A1"));
                break;
            case "1":
                mStatus.setText("Sudah diterima");
                mStatus.setTextColor(Color.parseColor("#FFEA00"));
                mKeUpload.setVisibility(View.INVISIBLE);
                mKeUpload.setEnabled(false);
                break;
            case "2":
                mStatus.setText("Sedang diproses");
                mStatus.setTextColor(Color.parseColor("#0071bc"));
                break;
            case "3":
                mStatus.setText("Telah dikirim");
                mStatus.setTextColor(Color.parseColor("#00E676"));
                mKeUpload.setVisibility(View.INVISIBLE);
                mKeUpload.setEnabled(false);
                break;
            case "4":
                mStatus.setText("Ditolak");
                mStatus.setTextColor(Color.parseColor("#C6FF0000"));
                break;
            case "5":
                mStatus.setText("Dicancel");
                mStatus.setTextColor(Color.parseColor("#C6FF0000"));
                break;
        }
    }

    @OnClick(R.id.btnKeUpload)
    void keUpload(){
        startActivity(new Intent(this, UploadActivity.class));
    }
}
