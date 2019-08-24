
package id.web.azammukhtar.Model.Upload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail {

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

}
