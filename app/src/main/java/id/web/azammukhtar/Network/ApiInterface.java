package id.web.azammukhtar.Network;

import java.io.File;

import id.web.azammukhtar.Model.History.History;
import id.web.azammukhtar.Model.Last.Last;
import id.web.azammukhtar.Model.Otp;
import id.web.azammukhtar.Model.OtpVerif;
import id.web.azammukhtar.Model.Transaksi.Transaksi;
import id.web.azammukhtar.Model.Upload.Upload;
import id.web.azammukhtar.Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    String BASE_URL = "http://spin.azammukhtar.web.id/api/";

    @FormUrlEncoded
    @POST("login")
    Call<User> doLogin(@Field("email") String email,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<User> doRegister(@Field("name") String name,
                            @Field("email") String email,
                            @Field("phone") String phone,
                            @Field("password") String password);

    @FormUrlEncoded
    @Headers("x-api-key: kmn6kNpP2uiPvCF5IdcuPR4XSOXkeOqw")
    @PUT("https://api.thebigbox.id/sms-otp/1.0.0/otp/kmn6kNpP2uiPvCF5IdcuPR4XSOXkeOqw")
    Call<Otp> getOtp(@Field("maxAttempt") String attempt, //3
                     @Field("phoneNum") String phoneNum, //+62
                     @Field("expireIn") String expire, //60 second
                     @Field("content") String content, //{{otp}}
                     @Field("digit") String digit); //4

    @FormUrlEncoded
    @Headers("x-api-key: kmn6kNpP2uiPvCF5IdcuPR4XSOXkeOqw")
    @POST("https://api.thebigbox.id/sms-otp/1.0.0/otp/kmn6kNpP2uiPvCF5IdcuPR4XSOXkeOqw/verifications")
    Call<OtpVerif> verifOtp(@Field("maxAttempt") String attempt, //3
                            @Field("expireIn") String expire, //60 second
                            @Field("otpstr") String otpstr, //{{otp}}
                            @Field("digit") String digit); //4

    @FormUrlEncoded
    @POST("buat_transaksi")
    Call<Transaksi> postTransaksi(@Header("Authorization") String token,
                                  @Field("jenis") String jenis,
                                  @Field("dompet_asal") String dompetAsal,
                                  @Field("dompet_tujuan") String dompetTujuan,
                                  @Field("nomor_asal") String nomorAsal,
                                  @Field("nomor") String nomorTujuan,
                                  @Field("nominal") String nominal); // regular || priority

    @GET("history_transaksi")
    Call<History> getHistory(@Header("Authorization") String token);

    @GET("last_transaksi")
    Call<Last> getLast(@Header("Authorization") String token);


    @Multipart
    @POST("uploadbukti/{id}")
    Call<Upload> uploadBukti(@Header("Authorization") String token, @Path("id") Integer id, @Part MultipartBody.Part foto);


}
