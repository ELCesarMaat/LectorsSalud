package com.cetis22.lectorsalud.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cetis22.lectorsalud.R;

import java.util.ArrayList;

public class AdaptadorPersonas extends RecyclerView.Adapter<AdaptadorPersonas.MyViewHolder> {

    private ArrayList<Personas> personsList;
    private onItemClickListener mListener;

    public AdaptadorPersonas(ArrayList<Personas> personsList) {
        this.personsList = personsList;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pacientes, parent, false);
        return new MyViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Personas currentItem = personsList.get(position);
        holder.nombre.setText(currentItem.getNombre());
        holder.hospital.setText(currentItem.getHospital());
        //holder.image.setImageBitmap(currentItem.getImagen());
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, hospital;
        ImageView image;

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.txt_list_persona_nombre);
            hospital = itemView.findViewById(R.id.txt_list_persona_hospital);
            //image = itemView.findViewById(R.id.img_list_persona_imagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
