package id.web.azammukhtar.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.Model.Last.Last;
import id.web.azammukhtar.Model.Upload.Upload;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.FileUtil;
import id.web.azammukhtar.Utils.SessionManager;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity implements IPickResult {
    private static final String TAG = "UploadActivity";

    @BindView(R.id.imageUploadPhoto)
    ImageView mImage;

    @BindView(R.id.buttonUploadSkip)
    Button mSkip;

    @BindView(R.id.buttonUploadUpload)
    Button mUpload;

    private Uri filepath;
    private File file;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>Upload Bukti Transfer</font>"));
        }
    }

    @OnClick(R.id.imageUploadPhoto)
    void selectPhoto(){
        PickImageDialog.build(new PickSetup().setWidth(100).setHeight(100)).show(getSupportFragmentManager());
    }

    @OnClick(R.id.buttonUploadSkip)
    void skip(){
        finish();
    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {

            filepath = r.getUri();
            mImage.setImageURI(filepath);

            try {
                file = FileUtil.from(this, r.getUri());
                file = new Compressor(this).compressToFile(file);
                mImage.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "onPickResult: ", e);
            }


            Log.d(TAG, "onPickResult: filepath " + filepath);

        } else {
            Log.d(TAG, "onPickResult: error image picker " + r.getError().getMessage());
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.buttonUploadUpload)
    void uploadPhoto(){
        Call<Last> call = ApiNetwork.getApiInterface().getLast("Bearer " + SessionManager.getInstance().getUserToken());
        call.enqueue(new Callback<Last>() {
            @Override
            public void onResponse(Call<Last> call, Response<Last> response) {
                if (response.isSuccessful()) {
                    id = response.body().getDetail().getId();
                    uploadPoto(id);
                    Log.d(TAG, "onResponse: ININIH " + id);
                } else {
                    Toast.makeText(UploadActivity.this, "Error melanjutkan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Last> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(UploadActivity.this, "Error melanjutkan, terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadPoto(Integer idd){
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        if (filepath == null){
            Toast.makeText(this, "Tolong upload bukti terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else{

            MultipartBody.Part filePart = MultipartBody.Part.createFormData(
                    "foto", file.getName(),
                    RequestBody.create(MediaType.parse("image/*"), file));


            Call<Upload> call = ApiNetwork.getApiInterface().uploadBukti("Bearer " +
                    SessionManager.getInstance().getUserToken(), idd, filePart);
            call.enqueue(new Callback<Upload>() {
                @Override
                public void onResponse(Call<Upload> call, Response<Upload> response) {
                    if (response.isSuccessful()){
                        Toasty.success(UploadActivity.this, "Sukses upload bukti, tunggu verifikasi", Toast.LENGTH_SHORT).show();
                        finish();
                        progressDialog.dismiss();
                    } else {
                        Toasty.error(UploadActivity.this, "Gagal upload bukti", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Upload> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                    Toasty.error(UploadActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        }
    }


}
