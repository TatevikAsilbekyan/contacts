package am.kspe.contactsapp.features.contacts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.helpers.UIHelper;

public class ContactDetailFragment extends BottomSheetDialogFragment {

    private static final String ARG_CONTENT = "arg_content";

    private User user;

    public ContactDetailFragment() {
    }

   static ContactDetailFragment newInstance(@NonNull User content) {
        final ContactDetailFragment fragment = new ContactDetailFragment();
        final Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CONTENT, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_CONTENT)) {
            user = getArguments().getParcelable(ARG_CONTENT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact_item_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (user != null) {
            AppCompatImageView thumb = view.findViewById(R.id.contact_item_detail_cover);
            AppCompatImageView avatar = view.findViewById(R.id.contact_item_detail_avatar);
            AppCompatImageView flag = view.findViewById(R.id.contact_item_detail_nationality);
            AppCompatTextView name = view.findViewById(R.id.contact_item_detail_name);
            AppCompatTextView username = view.findViewById(R.id.contact_item_detail_username);
            AppCompatTextView birthday = view.findViewById(R.id.contact_item_detail_dob);
            AppCompatTextView address = view.findViewById(R.id.contact_item_detail_address);
            AppCompatTextView email = view.findViewById(R.id.contact_item_detail_email);
            AppCompatTextView phone = view.findViewById(R.id.contact_item_detail_phone);
            AppCompatTextView timezone = view.findViewById(R.id.contact_item_detail_timezone);
            AppCompatImageView close = view.findViewById(R.id.contact_item_detail_close);

            FloatingActionButton phoneFab = view.findViewById(R.id.contact_item_detail_phone_fab);
            FloatingActionButton emailFab = view.findViewById(R.id.contact_item_detail_email_fab);
            FloatingActionButton mapFab = view.findViewById(R.id.contact_item_detail_map_fab);

            UIHelper.asyncLoadThumb(thumb, user.coverUrl());
            UIHelper.asyncLoadThumb(flag, user.flagUrl());
            UIHelper.asyncLoadAvatar(avatar, user.avatarUrl());

            name.setText(user.fullNameWithTitle());
            username.setText(user.username());
            birthday.setText(user.birthday());
            address.setText(user.address());
            email.setText(user.email());
            phone.setText(user.phones());
            timezone.setText(user.timezone());

            phoneFab.setOnClickListener(view1 -> UIHelper.call(getActivity(), user.cell()));
            emailFab.setOnClickListener(view1 -> UIHelper.email(getContext(), user.email()));
            mapFab.setOnClickListener(view1 -> UIHelper.direction(getContext(), user.location()));
            close.setOnClickListener(view3 -> dismiss());
        }
    }
}
