package am.kspe.contactsapp.features.contacts.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.app.base.BaseActivity;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.features.contacts.contract.UserContract;
import am.kspe.contactsapp.features.contacts.viewmodel.UsersViewModel;

public class ContactsActivity extends BaseActivity implements UserContract.OnUserInteractionListener {

    private UsersViewModel viewModel;
    private ContactsFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void onViewReady() {
        viewModel.loadData();
    }

    @Override
    public void onUserViewScrolled() {
        viewModel.loadMoreUsers();
    }

    @Override
    public void onUserDetailOpened(@NonNull User user) {
        viewModel.saveSeenUser(user);
    }

    private void init() {
        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle(R.string.app_name);

        fragment = ContactsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.content_contacts, fragment).commit();

        viewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        observeData();
    }

    private void observeData() {
        viewModel.getLoading().observe(this, loading -> fragment.showHideLoading(loading));
        viewModel.getError().observe(this, res -> fragment.showError(res));
        viewModel.loadUsers().observe(this, content -> fragment.showContacts(content));
    }
}
