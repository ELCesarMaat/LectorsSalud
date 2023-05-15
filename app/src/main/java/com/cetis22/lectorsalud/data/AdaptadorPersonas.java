package com.cetis22.lectorsalud.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.cetis22.lectorsalud.R;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class AdaptadorPersonas extends RecyclerView.Adapter<AdaptadorPersonas.MyViewHolder> {
    private onItemClickListener mListener;
    private ArrayList<Personas> personsList;

    /* loaded from: classes4.dex */
    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public AdaptadorPersonas(ArrayList<Personas> personsList) {
        this.personsList = personsList;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pacientes, parent, false);
        return new MyViewHolder(itemView, this.mListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Personas currentItem = this.personsList.get(position);
        holder.nombre.setText(currentItem.getNombre());
        holder.hospital.setText(currentItem.getHospital());
        holder.image.setImageBitmap(currentItem.getImagen());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.personsList.size();
    }

    /* loaded from: classes4.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView hospital;
        ImageView image;
        TextView nombre;

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            this.nombre = (TextView) itemView.findViewById(R.id.txt_list_persona_nombre);
            this.hospital = (TextView) itemView.findViewById(R.id.txt_list_persona_hospital);
            this.image = (ImageView) itemView.findViewById(R.id.img_list_persona_imagen);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.data.AdaptadorPersonas.MyViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    int position;
                    if (listener != null && (position = MyViewHolder.this.getAdapterPosition()) != -1) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
