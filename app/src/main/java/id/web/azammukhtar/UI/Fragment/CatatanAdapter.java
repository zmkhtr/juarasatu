package id.web.azammukhtar.UI.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.web.azammukhtar.Model.History.HistoryData;
import id.web.azammukhtar.R;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.ViewHolder> {
    private static final String TAG = "CatatanAdapter";

    private List<HistoryData> historyData = new ArrayList<>();
    private OnItemClick listener;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catatan  , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryData data = historyData.get(position);
        holder.mDompetAsal.setText(data.getDompetAsal());
        holder.mDompetTujuan.setText(data.getDompetTujuan());
        String nominal = "Rp " + data.getNominal();
        holder.mNominal.setText(nominal);
        holder.mNomorTujuan.setText(data.getNomor());

        switch (data.getStatus()){
            case "0":
                holder.mStatus.setText("Belum diproses");
                holder.mStatus.setTextColor(Color.parseColor("#A1A1A1"));
                break;
            case "1":
                holder.mStatus.setText("Sudah diterima");
                holder.mStatus.setTextColor(Color.parseColor("#FFEA00"));
                break;
            case "2":
                holder.mStatus.setText("Sedang diproses");
                holder.mStatus.setTextColor(Color.parseColor("#0071bc"));
                break;
            case "3":
                holder.mStatus.setText("Telah dikirim");
                holder.mStatus.setTextColor(Color.parseColor("#00E676"));
                break;
            case "4":
                holder.mStatus.setText("Ditolak");
                holder.mStatus.setTextColor(Color.parseColor("#C6FF0000"));
                break;
            case "5":
                holder.mStatus.setText("Dicancel");
                holder.mStatus.setTextColor(Color.parseColor("#C6FF0000"));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return historyData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textItemDompetAsal)
        TextView mDompetAsal;
        @BindView(R.id.textItemDompetTujuan)
        TextView mDompetTujuan;
        @BindView(R.id.textItemNominal)
        TextView mNominal;
        @BindView(R.id.textItemNomorTujuan)
        TextView mNomorTujuan;
        @BindView(R.id.textItemStatus)
        TextView mStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
            });
        }
    }

    public List<HistoryData> getHistoryData() {
        return historyData;
    }

    public void setHistoryData(List<HistoryData> historyData) {
        this.historyData = historyData;
        notifyDataSetChanged();
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
