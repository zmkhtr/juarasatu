package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.Model.Transaksi.Data;
import id.web.azammukhtar.Model.Transaksi.Transaksi;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.UI.Activity.ReviewActivity;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    private static final String TAG = "FormActivity";
    public static final String KEY_DATA = "keyData";

    @BindView(R.id.edtFormNoHpAnda)
    EditText mPhoneAnda;

    @BindView(R.id.edtFormNoHpTujuan)
    EditText mPhoneTujuan;

    @BindView(R.id.edtFormNominal)
    EditText mNominal;

    @BindView(R.id.dropdownFormDompetAnda)
    AutoCompleteTextView mDompetAnda;

    @BindView(R.id.dropdownFormDompetTujuan)
    AutoCompleteTextView mDompetTujuan;

    @BindView(R.id.checkFormPriority)
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Kirim Uang Digital</font>"));
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setData();
    }

    @OnClick(R.id.btnFormSubmit)
    void submitForm(){
        submitData();
    }

    private void submitData(){
        String phone, phoneTujuan, nominal, dompetAnda, dompetTujuan, jenis;
        phone = mPhoneAnda.getText().toString();
        phoneTujuan = mPhoneTujuan.getText().toString();
        nominal = mNominal.getText().toString();
        dompetAnda = mDompetAnda.getText().toString();
        dompetTujuan = mDompetTujuan.getText().toString();

        if (checkBox.isChecked()){
            jenis = "priority";
        } else {
            jenis = "regular";
        }

        Data data = new Data();
        data.setDompetAsal(dompetAnda);
        data.setDompetTujuan(dompetTujuan);
        data.setJenis(jenis);
        data.setNominal(nominal);
        data.setNomor(phoneTujuan);
        data.setNomorAsal(phone);

        if(phone.isEmpty() || phoneTujuan.isEmpty() || nominal.isEmpty() || dompetAnda.isEmpty() || dompetTujuan.isEmpty()){
            Toasty.warning(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra(KEY_DATA, data);
            startActivity(intent);
            finish();
        }

//        if(phone.isEmpty() || phoneTujuan.isEmpty() || nominal.isEmpty() || dompetAnda.isEmpty() || dompetTujuan.isEmpty()){
//            Toasty.warning(this, "Form tidak boleh kosong", Toast.LENGTH_SHORT).show();
//        } else {
//            Call<Transaksi> call = ApiNetwork.getApiInterface().postTransaksi
//                    ("Bearer " + SessionManager.getInstance().getUserToken(),
//                            jenis, dompetAnda, dompetTujuan, phone, phoneTujuan, nominal);
//            call.enqueue(new Callback<Transaksi>() {
//                @Override
//                public void onResponse(Call<Transaksi> call, Response<Transaksi> response) {
//                    if (response.isSuccessful()){
//                        Toasty.success(FormActivity.this, "Berhasil proses data", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toasty.error(FormActivity.this, "Gagal proses data", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Transaksi> call, Throwable t) {
//                    Log.e(TAG, "onFailure: ",t);
//                    Toasty.error(FormActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    private void setData(){
        mPhoneAnda.setText(SessionManager.getInstance().getUserPhone());
        List<String> dompetAnda = new ArrayList<>();
        dompetAnda.add("gopay");
        dompetAnda.add("dana");
        dompetAnda.add("ovo");
        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        dompetAnda);

        mDompetAnda.setAdapter(adapterSize);
        mDompetTujuan.setAdapter(adapterSize);
    }
}
