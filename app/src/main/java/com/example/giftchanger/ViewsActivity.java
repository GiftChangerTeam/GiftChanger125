package com.example.giftchanger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewsActivity extends AppCompatActivity implements View.OnClickListener {
    private FloatingActionButton buttonBack;
    private RecyclerView recyclerView;
    private GiftAdapter adapter;
    private GiftsDatabaseHelper databaseHelper;
    List<Gift> gifts = new ArrayList<>();

    private String age;
    private String gender;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonBack = findViewById(R.id.fabBack);
        buttonBack.setOnClickListener(this);

        age = getIntent().getStringExtra("age");
        gender = getIntent().getStringExtra("gender");

        // Вручную заполняем список подарков
        gifts.add(new Gift(1, "Подарок 1", "Описание подарка 1", Arrays.asList(18, 20), "Мужской"));
        gifts.add(new Gift(2, "Подарок 2", "Описание подарка 2", Arrays.asList(25, 30), "Женский"));
        gifts.add(new Gift(3, "Подарок 3", "Описание подарка 3", Arrays.asList(18, 25, 30), "Мужской"));
        gifts.add(new Gift(4, "Подарок 4", "Описание подарка 4", Arrays.asList(30, 35), "Женский"));

        List<Gift> filteredGifts = new ArrayList<>();

        // Фильтруем список подарков по возрасту и полу
        for (Gift gift : gifts) {
            if (gift.getGender().equals(gender) && gift.getAgeIds().contains(Integer.parseInt(age))) {
                filteredGifts.add(gift);
            }
        }

        adapter = new GiftAdapter(filteredGifts);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fabBack) {
            Intent intentBack = new Intent(getApplicationContext(), ChangeActivity.class);
            startActivity(intentBack);
            finish();
        }
    }

    // Дополнительных методы для редактирования данных подарков

    public void editGiftName(int position, String name) {
        Gift gift = adapter.getGiftList().get(position);
        gift.setName(name);
        databaseHelper.updateGift(gift);
        adapter.notifyItemChanged(position);
    }

    public void editGiftAge(int position, List<Integer> ageIds) {
        Gift gift = adapter.getGiftList().get(position);
        gift.setAgeIds(ageIds);
        databaseHelper.updateGift(gift);
        adapter.notifyItemChanged(position);
    }


    public void editGiftGender(int position, String gender) {
        Gift gift = adapter.getGiftList().get(position);
        gift.setGender(gender);
        databaseHelper.updateGift(gift);
        adapter.notifyItemChanged(position);
    }
}