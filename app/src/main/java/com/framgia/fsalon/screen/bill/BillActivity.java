package com.framgia.fsalon.screen.bill;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.framgia.fsalon.R;
import com.framgia.fsalon.data.source.ServiceRepository;
import com.framgia.fsalon.data.source.StylistRepository;
import com.framgia.fsalon.data.source.api.FSalonServiceClient;
import com.framgia.fsalon.data.source.remote.ServiceRemoteDataSource;
import com.framgia.fsalon.data.source.remote.StylistRemoteDataSource;
import com.framgia.fsalon.databinding.ActivityBillBinding;

/**
 * Bill Screen.
 */
public class BillActivity extends AppCompatActivity {
    private BillContract.ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BillViewModel(this);
        BillContract.Presenter presenter =
            new BillPresenter(mViewModel, new StylistRepository(new StylistRemoteDataSource(
                FSalonServiceClient.getInstance())), new ServiceRepository(new
                ServiceRemoteDataSource(FSalonServiceClient.getInstance())));
        mViewModel.setPresenter(presenter);
        ActivityBillBinding binding =
            DataBindingUtil.setContentView(this, R.layout.activity_bill);
        binding.setViewModel((BillViewModel) mViewModel);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewModel.onStart();
    }

    @Override
    protected void onStop() {
        mViewModel.onStop();
        super.onStop();
    }
}
