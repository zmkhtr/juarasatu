package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.Path;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.Model.User;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.edtLoginEmail)
    EditText mEmail;

    @BindView(R.id.edtiLoginPassword)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        startActivity(new Intent(this, MainActivity.class));
        if (SessionManager.getInstance().isLoggedIn()) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @OnClick(R.id.textToRegister)
    void toRegsiter(){
        startActivity(new Intent(this, RegisterActivity.class));
    }
    @OnClick(R.id.btnLogin)
    void doLogin(){

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Login...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        String email, password;
        password = mPassword.getText().toString();
        email = mEmail.getText().toString();
        if (password.isEmpty() || email.isEmpty()) {
            Toasty.warning(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            Call<User> call = ApiNetwork.getApiInterface().doLogin(mEmail.getText().toString(), mPassword.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        Toasty.success(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        SessionManager.getInstance().setLogin(false,response.body().getToken());
                        SessionManager.getInstance().setData(response.body().getNama(), response.body().getPhone());
                        Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                        startActivity(intent);
                        finish();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(LoginActivity.this, "User tidak terdaftar", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e(TAG, "onFailure: ",t);
                    Toasty.error(LoginActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }

    }
}
