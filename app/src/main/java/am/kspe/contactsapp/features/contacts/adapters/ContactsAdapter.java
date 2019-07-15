package am.kspe.contactsapp.features.contacts.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import am.kspe.contactsapp.R;
import am.kspe.contactsapp.data.entity.User;
import am.kspe.contactsapp.features.contacts.contract.UserContract.OnUserItemClickListener;
import am.kspe.contactsapp.helpers.UIHelper;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private List<User> dataSource;
    private OnUserItemClickListener listener;

    public ContactsAdapter(@Nullable OnUserItemClickListener listener) {
        dataSource = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact_item_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = getItem(position);

        UIHelper.asyncLoadAvatar(holder.thumb, user.avatarUrl());
        holder.name.setText(user.fullName());
        holder.address.setText(user.shortAddress());
        holder.phone.setText(user.phone());
        holder.bg.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(),
                user.seen() ? R.color.colorSeen : R.color.colorWhite));
        holder.itemView.setOnClickListener(view -> {
            listener.onUserItemClicked(getItem(position));
            holder.bg.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.colorSeen));
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void addMoreDataSource(List<User> dataSource) {
        int positionStart = this.dataSource.size();
        this.dataSource.addAll(dataSource);
        notifyItemRangeInserted(positionStart, dataSource.size());
    }

    private User getItem(int position) {
        return dataSource.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView name;
        AppCompatTextView address;
        AppCompatTextView phone;
        AppCompatImageView thumb;
        View bg;

        ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            address = itemView.findViewById(R.id.contact_address);
            phone = itemView.findViewById(R.id.contact_phone);
            thumb = itemView.findViewById(R.id.contact_thumb);
            bg = itemView.findViewById(R.id.contact_bg);
        }
    }
}