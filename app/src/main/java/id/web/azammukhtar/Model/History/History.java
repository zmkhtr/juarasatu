
package id.web.azammukhtar.Model.History;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class History {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("history")
    @Expose
    private List<HistoryData> history = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<HistoryData> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryData> history) {
        this.history = history;
    }

}
