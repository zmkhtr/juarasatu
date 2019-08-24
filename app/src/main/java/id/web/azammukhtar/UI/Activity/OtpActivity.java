package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.nio.file.Path;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.Model.Otp;
import id.web.azammukhtar.Model.OtpVerif;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    private static final String TAG = "OtpActivity";

    @BindView(R.id.otp_view)
    OtpView otpView;

    @BindView(R.id.textOtpNoHP)
    TextView mPhone;

    private String phonePlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Verifikasi Akun</font>"));
            getSupportActionBar().setElevation(0);
        }
        String phone = SessionManager.getInstance().getUserPhone();
        String cut = phone.substring(1);
        phonePlus = "+62" + cut;
        mPhone.setText(phonePlus);
        getOtp();
        validateOTP();
    }

    private void validateOTP(){
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mengirim OTP...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                progressDialog.show();
                Call<OtpVerif> call = ApiNetwork.getApiInterface().verifOtp("3","60",otp,"4");
                call.enqueue(new Callback<OtpVerif>() {
                    @Override
                    public void onResponse(Call<OtpVerif> call, Response<OtpVerif> response) {
                        if (response.isSuccessful()) {
                            Toasty.success(OtpActivity.this, "OTP Benar", Toast.LENGTH_SHORT).show();
                            SessionManager.getInstance().setLogin(true, SessionManager.getInstance().getUserToken());
                            Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            progressDialog.dismiss();
                        } else {
                            Toasty.error(OtpActivity.this, "OTP salah", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<OtpVerif> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                        Toasty.error(OtpActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }
        });
    }

    private void getOtp(){
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Mengirim OTP...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        Call<Otp> call = ApiNetwork.getApiInterface().getOtp("3",phonePlus,"60","{{otp}}","4");
        call.enqueue(new Callback<Otp>() {
            @Override
            public void onResponse(Call<Otp> call, Response<Otp> response) {
                if (response.isSuccessful()){
                    Toast.makeText(OtpActivity.this, "Silahkan masukkan OTP yang telah kami kirim.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(OtpActivity.this, "Gagal mengirim OTP", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Otp> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toasty.error(OtpActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.btnOtpKirimUlang)
    void reSendOtp(){
        getOtp();
    }

}
