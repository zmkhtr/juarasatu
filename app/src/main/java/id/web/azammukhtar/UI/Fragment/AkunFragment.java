package id.web.azammukhtar.UI.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import id.web.azammukhtar.R;
import id.web.azammukhtar.UI.Activity.LoginActivity;
import id.web.azammukhtar.Utils.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {
    private static final String TAG = "AkunFragment";

    @BindView(R.id.textAkunNama)
    TextView mNama;

    @BindView(R.id.textAkunPhone)
    TextView mPhone;

    private Context context;
    private Unbinder unbinder;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_akun, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        unbinder = ButterKnife.bind(this, view);
        mNama.setText(SessionManager.getInstance().getUserName());
        mPhone.setText(SessionManager.getInstance().getUserPhone());
        context = view.getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buttonAkunLogout)
    void doLogout(){
        SessionManager.getInstance().clearData();
        startActivity(new Intent(context, LoginActivity.class));
        getActivity().finish();
    }

}
