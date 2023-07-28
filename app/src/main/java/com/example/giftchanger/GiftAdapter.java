package com.example.giftchanger;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {

    private List<Gift> giftList;

    public GiftAdapter(List<Gift> giftList) {
        this.giftList = giftList;
    }

    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Создание представления для элемента списка
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        return new GiftViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftViewHolder holder, int position) {
        Gift gift = giftList.get(position);

        holder.textViewName.setText(gift.getName());
        holder.textViewGift.setText(gift.getGift());
        holder.textViewAge.setText(String.valueOf(gift.getAgeIds()));
        holder.textViewGender.setText(gift.getGender());
    }


    @Override
    public int getItemCount() {
        // Возвращает количество элементов в списке
        return giftList.size();
    }

    // ViewHolder, который содержит представление элемента списка
    // ViewHolder, который содержит представление элемента списка
    class GiftViewHolder extends RecyclerView.ViewHolder {
        TextView textViewGift;
        TextView textViewAge;
        TextView textViewGender;
        TextView textViewName;

        GiftViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewGift = itemView.findViewById(R.id.textViewGift);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void updateGiftList(List<Gift> updatedGiftList) {
        this.giftList = updatedGiftList;
        notifyDataSetChanged();
    }
}