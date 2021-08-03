package com.prometteur.fooddeliveryboy.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prometteur.fooddeliveryboy.Adapters.NotificationsAdapter;
import com.prometteur.fooddeliveryboy.PojoModels.LoginUserDetailsPojo;
import com.prometteur.fooddeliveryboy.R;
import com.prometteur.fooddeliveryboy.RetroFit.ApiConfig;
import com.prometteur.fooddeliveryboy.Utils.CommonMethods;
import com.prometteur.fooddeliveryboy.Utils.LoadingDialog;
import com.prometteur.fooddeliveryboy.Utils.Preference;
import com.prometteur.fooddeliveryboy.Utils.StringConstants;
import com.prometteur.fooddeliveryboy.databinding.FragmentMyRidesBinding;
import com.prometteur.fooddeliveryboy.databinding.FragmentNotificationsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.isNetworkAvailable;
import static com.prometteur.fooddeliveryboy.Utils.CommonMethods.showFailToast;


public class NotificationsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "NotificationsFragment";
    FragmentNotificationsBinding viewBinding;
    NotificationsAdapter notifyAdapter;
    Context nContext;
    Dialog loadingDialog;
    

    public NotificationsFragment(Context context) {
        // Required empty public constructor
        this.nContext=context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewBinding= FragmentNotificationsBinding.inflate(inflater);
        loadingDialog= LoadingDialog.getLoadingDialog(nContext);
        getNotificationsApiCall();
        //initRecyclerView();
        viewBinding.ivSearch.setOnClickListener(this);
        return viewBinding.getRoot();
    }

    private void getNotificationsApiCall() {
        loadingDialog.show();
        Call<LoginUserDetailsPojo> notificationsCall=null;

        if(isNetworkAvailable(nContext)){
            if(StringConstants.userApiDetails!=null && StringConstants.userApiDetails.getId()!=null) {
                notificationsCall= ApiConfig.getApiClient().getNotifications(StringConstants.userApiDetails.getId());
            }else{
                notificationsCall=ApiConfig.getApiClient().getNotifications(Preference.getPrefData(nContext,StringConstants.USERID));
            }
            if(notificationsCall!=null){
                notificationsCall.enqueue(new Callback<LoginUserDetailsPojo>() {
                    @Override
                    public void onResponse(Call<LoginUserDetailsPojo> call, Response<LoginUserDetailsPojo> response) {
                        LoginUserDetailsPojo apiResponse=response.body();
                        if (response.isSuccessful() && apiResponse != null &&  apiResponse.getStatus() == 1) {
                            //notificationsCall=apiResponse.getResult().get(0);

                            initRecyclerView();

                        }
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginUserDetailsPojo> call, Throwable t) {
                        loadingDialog.dismiss();
                        Log.e(TAG, "onFailure: "+t.getMessage() );
                    }
                });
            }

        }else{
            loadingDialog.cancel();
            showFailToast(nContext,nContext.getResources().getString(R.string.no_internet));
        }
    }



    private void initRecyclerView() {
        notifyAdapter=new NotificationsAdapter(nContext);
        viewBinding.recycleNotify.setLayoutManager(new LinearLayoutManager(nContext, RecyclerView.VERTICAL,false));
        viewBinding.recycleNotify.setAdapter(notifyAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivSearch:
                viewBinding.tvBarTitle.setVisibility(View.GONE);
                viewBinding.ivSearch.setVisibility(View.GONE);
                viewBinding.edtSearch.setVisibility(View.VISIBLE);
                break;
        }
    }
}