package com.example.covid_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.model.IndiaData;

import java.util.List;

public class IndiaAdapter extends RecyclerView.Adapter<IndiaAdapter.Indiaview_holder> {

    private final List<IndiaData> indiadata;

    public IndiaAdapter(List<IndiaData> indiadata) {
        this.indiadata = indiadata;
    }

    @NonNull
    @Override
    public Indiaview_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.view_entry, parent, false);
        Indiaview_holder indiaview_holder=new Indiaview_holder(view);
        return indiaview_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Indiaview_holder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return this.indiadata.size();
    }

    public class Indiaview_holder extends RecyclerView.ViewHolder{

        private TextView TvState;
        private TextView TvCases;
        private TextView TvCured;
        private TextView TvDeath;

        public Indiaview_holder(@NonNull View itemView) {
            super(itemView);

            TvState=itemView.findViewById(R.id.tv_state);
            TvCases=itemView.findViewById(R.id.tv_cases);
            TvCured=itemView.findViewById(R.id.tv_cured);
            TvDeath=itemView.findViewById(R.id.tv_death);

        }
        public void bind(int position){

            IndiaData data=indiadata.get(position);
            TvState.setText(data.getState());
            TvCases.setText(data.getConfirm());
            TvCured.setText(data.getCured());
            TvDeath.setText(data.getDeath());

        }
    }
}
