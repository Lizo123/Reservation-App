package com.example.thespa.offers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thespa.R;
import com.example.thespa.models.Offer;

public class OfferCardFragment extends Fragment {
    private CardView cardView;
    Offer offer;

    public static Fragment getInstance(Offer offer) {
        OfferCardFragment f = new OfferCardFragment();
        Bundle args = new Bundle();

        args.putParcelable("offer",offer);
        f.setArguments(args);

        return f;
    }



    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_card_offer, container, false);
        offer = (Offer) getArguments().getParcelable("offer");
        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);
        ImageView offer_image=(ImageView) view.findViewById(R.id.offer_image);
//        if(offer.getOfferImage()!=null)
//        {
//            Glide.with(this)
//                    .load(offer.getOfferImage())
//                    .into(offer_image);
//        }
//        else {
//
//           // offer_image.setImageResource(R.drawable.card_image);
//        }

        TextView Offer_name=view.findViewById(R.id.Offer_name);
        Offer_name.setText(offer.getOfferName());

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i =new Intent(getContext(), ProductDetailsActivity.class);
//
//                i.putExtra("Source","Change_product");
//                i.putExtra("Products", offer.getProducts());
//
//                i.putExtra("ProductName",offer.getName());
//
//                getContext().startActivity(i);
            }
        });




        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
