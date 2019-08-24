
package id.web.azammukhtar.Model.Transaksi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable {

    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("dompet_asal")
    @Expose
    private String dompetAsal;
    @SerializedName("dompet_tujuan")
    @Expose
    private String dompetTujuan;
    @SerializedName("nomor_asal")
    @Expose
    private String nomorAsal;
    @SerializedName("nomor")
    @Expose
    private String nomor;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("user_id")
    @Expose
    private int userId;

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDompetAsal() {
        return dompetAsal;
    }

    public void setDompetAsal(String dompetAsal) {
        this.dompetAsal = dompetAsal;
    }

    public String getDompetTujuan() {
        return dompetTujuan;
    }

    public void setDompetTujuan(String dompetTujuan) {
        this.dompetTujuan = dompetTujuan;
    }

    public String getNomorAsal() {
        return nomorAsal;
    }

    public void setNomorAsal(String nomorAsal) {
        this.nomorAsal = nomorAsal;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.jenis);
        dest.writeString(this.dompetAsal);
        dest.writeString(this.dompetTujuan);
        dest.writeString(this.nomorAsal);
        dest.writeString(this.nomor);
        dest.writeString(this.nominal);
        dest.writeInt(this.userId);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.jenis = in.readString();
        this.dompetAsal = in.readString();
        this.dompetTujuan = in.readString();
        this.nomorAsal = in.readString();
        this.nomor = in.readString();
        this.nominal = in.readString();
        this.userId = in.readInt();
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
