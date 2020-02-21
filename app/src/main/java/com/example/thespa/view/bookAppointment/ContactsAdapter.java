package com.example.thespa.view.bookAppointment;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.view.models.Contact;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>  {

    private LayoutInflater mInflater;
    private ArrayList<Contact> contacts;
    private Context context;
    private MutableLiveData<String> friend;

    ContactsAdapter(Context context, ArrayList<Contact> contacts, MutableLiveData<String> friend) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.contacts = contacts;
        this.friend = friend;

    }
    /**
     *inflates the cell layout from xml when needed
     */
    @Override
    @NonNull
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_contact, parent, false);
        return new ContactsAdapter.ViewHolder(view);
    }

    /**
     *binds the data to the view in each cell
     */
    @Override
    public void onBindViewHolder(@NonNull final ContactsAdapter.ViewHolder holder, final int position) {
        holder.name.setText(contacts.get(position).getName());
        holder.phone_number.setText(contacts.get(position).getMobileNumber());

//        Glide.with(context)
//                .load(offerModels.get(position).getOfferImage())
//                .into(holder.offer_image);

        holder.radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                friend.setValue(contacts.get(position).getName());
            }
        });

    }
    /**
     *total number of cells
     */
    @Override
    public int getItemCount() {
        return contacts.size();
    }

    /**
     *stores and recycles views as they are scrolled off screen
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView phone_number;
        ImageView image;
        RadioButton radio_button;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone_number = itemView.findViewById(R.id.phone_number);
            image = itemView.findViewById(R.id.image);
            radio_button = itemView.findViewById(R.id.radio_button);

        }
    }
}
