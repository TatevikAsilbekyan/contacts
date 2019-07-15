package am.kspe.contactsapp.features.contacts.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import java.util.List;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.features.contacts.adapters.ContactsAdapter;
import am.kspe.contactsapp.features.contacts.contract.UserContract;
import am.kspe.contactsapp.features.contacts.contract.UserContract.OnUserInteractionListener;
import am.kspe.contactsapp.helpers.InfiniteScrollProvider;
import am.kspe.contactsapp.helpers.UIHelper;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ContactsFragment extends Fragment implements UserContract.OnUserItemClickListener {

    private OnUserInteractionListener listener;
    private ProgressBar loading;
    private ConstraintLayout transitionLayout;
    private LinearLayout empty;
    private ContactsAdapter adapter;

    public ContactsFragment() {
    }

   static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnUserInteractionListener) {
            listener = (OnUserInteractionListener) context;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        transitionLayout = view.findViewById(R.id.contactsRoot);
        loading = view.findViewById(R.id.loading);
        empty = view.findViewById(R.id.empty);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);

        adapter = new ContactsAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        InfiniteScrollProvider provider = new InfiniteScrollProvider();
        provider.attach(recyclerView, () -> listener.onUserViewScrolled());

        listener.onViewReady();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onUserItemClicked(@NonNull User user) {
        ContactDetailFragment.newInstance(user).showNow(getChildFragmentManager(), "ContactItemDetail");
        listener.onUserDetailOpened(user);
    }

    void showContacts(List<User> data) {
        adapter.addMoreDataSource(data);
        empty.setVisibility(adapter.getItemCount() == 0 ? VISIBLE : GONE);
    }

    void showHideLoading(boolean state) {
        TransitionManager.beginDelayedTransition(transitionLayout);
        loading.setVisibility(state ? VISIBLE : GONE);
    }

    void showError(int res) {
        UIHelper.showSnackBar(transitionLayout, res);
    }
}
