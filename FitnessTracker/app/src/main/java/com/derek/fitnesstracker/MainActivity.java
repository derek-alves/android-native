package com.derek.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private View btnImc;
    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.main_rv);

        List<MainItem> mainItems = new ArrayList<>();

        mainItems.add(new MainItem(1,R.drawable.ic_sharp_brightness_high_24,R.string.label_imc, Color.GREEN));
        mainItems.add(new MainItem(2,R.drawable.ic_baseline_directions_run_24,R.string.label_tmb, Color.GRAY));
        mainItems.add(new MainItem(2,R.drawable.ic_baseline_directions_run_24,R.string.label_tmb, Color.BLUE));
        mainItems.add(new MainItem(2,R.drawable.ic_baseline_directions_run_24,R.string.label_tmb, Color.YELLOW));

        //1-> Definir o comportamento de exibição do layout
            //mosaic
            //grid
            // linear (horizontal | vertical)
            //        rvMain.setLayoutManager(new LinearLayoutManager(this));

        rvMain.setLayoutManager(new GridLayoutManager(this,2));

        MainAdapter adapter = new MainAdapter(mainItems);
        adapter.setListener(id -> {
            switch (id){
                case 1:
                    startActivity(new Intent(MainActivity.this,imcActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this,TmbActivity.class));
                    break;
            }

        });
        rvMain.setAdapter(adapter);




    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
        private List<MainItem> mainItems;
        private OnItemClickListener listener;

        public void setListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public MainAdapter(List<MainItem> mainItems){
            this.mainItems = mainItems;
        }

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }



        //Acesso direto aos componetes que estão sendo exibidos dentro da recyclerView
        private class MainViewHolder extends RecyclerView.ViewHolder{

            public MainViewHolder(@NonNull View itemView) {
                super(itemView);

            }

            public void bind(MainItem item){
                TextView txtName = itemView.findViewById(R.id.item_txt_name);
                ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
                LinearLayout btnImc = (LinearLayout)itemView.findViewById(R.id.btn_imc);

                btnImc.setOnClickListener(v -> {
                    listener.onClick(item.getId());
                });


                txtName.setText(item.getTextStringId());
                imgIcon.setImageResource(item.getDrawableId());
                btnImc.setBackgroundColor(item.getColor());

            }
        }
    }



}