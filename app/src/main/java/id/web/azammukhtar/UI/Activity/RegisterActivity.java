package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.edtRegisterName)
    EditText mName;

    @BindView(R.id.edtRegisterEmail)
    EditText mEmail;

    @BindView(R.id.edtRegisterPassword)
    EditText mPassword;

    @BindView(R.id.edtRegisterNoHp)
    EditText mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.btnRegister)
    void doRegister(){

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Register...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        String name, email, password, phone;
        name = mName.getText().toString();
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();
        phone = mPhone.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()){
            Toasty.warning(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            Call<User> call = ApiNetwork.getApiInterface().doRegister(name, email, phone, password);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()){
                        Toasty.success(RegisterActivity.this, "Register Berhasil, silahkan login untuk melanjutkan", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Register gagal, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e(TAG, "onFailure: ",t);
                    Toasty.error(RegisterActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
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
