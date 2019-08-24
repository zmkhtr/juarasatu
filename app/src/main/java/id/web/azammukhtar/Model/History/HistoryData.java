
package id.web.azammukhtar.Model.History;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryData implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("jenis")
    @Expose
    private String jenis;
    @SerializedName("dompet_tujuan")
    @Expose
    private String dompetTujuan;
    @SerializedName("nomor")
    @Expose
    private String nomor;
    @SerializedName("nomor_asal")
    @Expose
    private String nomorAsal;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("dompet_asal")
    @Expose
    private String dompetAsal;
    @SerializedName("foto")
    @Expose
    private String foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDompetTujuan() {
        return dompetTujuan;
    }

    public void setDompetTujuan(String dompetTujuan) {
        this.dompetTujuan = dompetTujuan;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNomorAsal() {
        return nomorAsal;
    }

    public void setNomorAsal(String nomorAsal) {
        this.nomorAsal = nomorAsal;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDompetAsal() {
        return dompetAsal;
    }

    public void setDompetAsal(String dompetAsal) {
        this.dompetAsal = dompetAsal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.jenis);
        dest.writeString(this.dompetTujuan);
        dest.writeString(this.nomor);
        dest.writeString(this.nomorAsal);
        dest.writeString(this.nominal);
        dest.writeString(this.status);
        dest.writeString(this.userId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeString(this.dompetAsal);
        dest.writeString(this.foto);
    }

    public HistoryData() {
    }

    protected HistoryData(Parcel in) {
        this.id = in.readInt();
        this.jenis = in.readString();
        this.dompetTujuan = in.readString();
        this.nomor = in.readString();
        this.nomorAsal = in.readString();
        this.nominal = in.readString();
        this.status = in.readString();
        this.userId = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.dompetAsal = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<HistoryData> CREATOR = new Parcelable.Creator<HistoryData>() {
        @Override
        public HistoryData createFromParcel(Parcel source) {
            return new HistoryData(source);
        }

        @Override
        public HistoryData[] newArray(int size) {
            return new HistoryData[size];
        }
    };
}
