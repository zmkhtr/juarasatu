package id.web.azammukhtar.UI.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;
import id.web.azammukhtar.UI.Activity.DetailActivity;
import id.web.azammukhtar.Model.History.History;
import id.web.azammukhtar.Model.History.HistoryData;
import id.web.azammukhtar.Network.ApiNetwork;
import id.web.azammukhtar.R;
import id.web.azammukhtar.Utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CatatanFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "CatatanFragment";
    public static final String HISTORY_KEY = "historyKey";
    private Unbinder unbinder;

    @BindView(R.id.recyclerCatatan)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshCatatan)
    SwipeRefreshLayout swipeRefreshLayout;

    List<HistoryData> mHistoryData = new ArrayList<>();
    CatatanAdapter catatanAdapter;
    LinearLayoutManager linearLayoutManager;
    Context context;

    public CatatanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        context = view.getContext();

        catatanAdapter = new CatatanAdapter();

        swipeRefreshLayout.setOnRefreshListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(catatanAdapter);
        getData();
        catatanAdapter.setOnItemClickListener(position -> {
            HistoryData historyData = mHistoryData.get(position);
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(HISTORY_KEY, historyData);
            startActivity(intent);
        });
    }

    private void getData() {
        Call<History> call = ApiNetwork.getApiInterface().getHistory("Bearer " + SessionManager.getInstance().getUserToken());
        call.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                if (response.isSuccessful()) {
                    mHistoryData.clear();
                    mHistoryData.addAll(response.body().getHistory());
                    catatanAdapter.setHistoryData(mHistoryData);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    Toast.makeText(getContext(), "Trouble Getting Data", Toast.LENGTH_SHORT).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toasty.error(getContext(), "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        getData();

    }
}
