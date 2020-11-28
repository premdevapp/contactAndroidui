package com.example.contactmanagerui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactmanagerui.DetailsActivity;
import com.example.contactmanagerui.R;
import com.example.contactmanagerui.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.name_contact.setText(contact.getName());
        holder.phoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name_contact;
        public TextView phoneNumber;
        public ImageView iconButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           itemView.setOnClickListener(this);
            name_contact = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone);
            iconButton = itemView.findViewById(R.id.icon_button);
            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Contact contact = contactList.get(pos);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("name", contact.getName());
            intent.putExtra("phone", contact.getPhoneNumber());

            context.startActivity(intent);

            /*switch (v.getId()){

                case R.id.cardView:
                    Log.d("click", "Whole onClick: " + contact.getName() + " " + contact.getPhoneNumber());
                    break;
                case R.id.icon_button:
                    Log.d("click", "ICON onClick: " + contact.getName() + " " + contact.getPhoneNumber());
                    break;

            }*/

        }
    }
}
