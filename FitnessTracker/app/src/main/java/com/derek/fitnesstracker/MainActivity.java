package com.derek.fitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    private View btnImc;
    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.main_rv);


        //1-> Definir o comportamento de exibição do layout
            //mosaic
            //grid
            // linear (horizontal | vertical)
        rvMain.setLayoutManager(new LinearLayoutManager(this));

        MainAdapter adapter = new MainAdapter();
        rvMain.setAdapter(adapter);



//        btnImc = findViewById(R.id.btn_imc);
//        btnImc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(MainActivity.this,imcActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private class MainAdapter extends RecyclerView.Adapter<MainViewHolder>{

        @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return 15;
        }
    }


    //Acesso direto aos componetes que estão sendo exibidos dentro da recyclerView
    private class MainViewHolder extends RecyclerView.ViewHolder{

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(int position){
            TextView textTest = itemView.findViewById(R.id.textview_test);
            textTest.setText("teste de rolagem" + position);
        }
    }
}