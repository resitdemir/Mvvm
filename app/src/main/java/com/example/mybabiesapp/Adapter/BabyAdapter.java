package com.example.mybabiesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mybabiesapp.Helper.DataConverter;
import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.R;
import com.example.mybabiesapp.View.BabyProfile;

import java.util.ArrayList;
import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.BabyHolder> {
    private List<Baby> babies = new ArrayList<>();
    private OnItemClickListener listener;
    private Context context;

    public BabyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BabyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new BabyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BabyHolder holder, final int position) {
        Baby currentBaby = babies.get(position);
        holder.tvtitle.setText(currentBaby.getAd());
        holder.img_book_thumbnail.setImageBitmap(DataConverter.converByteArray2Image(currentBaby.getResimUrl()));
    }

    public void setBabies(List<Baby> babies) {
        this.babies = babies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return babies.size();
    }

    public Baby getBabyAt(int position) {
        return babies.get(position);
    }

    class BabyHolder extends RecyclerView.ViewHolder {
        ImageView img_book_thumbnail;
        private TextView tvtitle;
        private Button btnEdit;
        private CardView cardView;

        public BabyHolder(@NonNull View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.title_id);
            img_book_thumbnail = itemView.findViewById(R.id.img_id);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            cardView = (CardView) itemView.findViewById(R.id.card_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(babies.get(position));
                    }
                }
            });

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onEditClick(babies.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Baby baby);
        void onEditClick(Baby position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}