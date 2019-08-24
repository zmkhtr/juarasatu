
package id.web.azammukhtar.Model.Last;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Last {

    @SerializedName("detail")
    @Expose
    private Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

}
