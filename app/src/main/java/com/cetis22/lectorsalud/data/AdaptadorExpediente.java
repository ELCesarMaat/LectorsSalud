package com.cetis22.lectorsalud.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cetis22.lectorsalud.R;
import com.cetis22.lectorsalud.data.AdaptadorExpediente;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class AdaptadorExpediente extends RecyclerView.Adapter<AdaptadorExpediente.MyViewHolder> {
    private ArrayList<Expediente> expedientesList;
    private onItemClickListener mListener;

    /* loaded from: classes4.dex */
    public interface onItemClickListener {
        void onItemClick(int i);
    }

    public AdaptadorExpediente(ArrayList<Expediente> expedientesList) {
        this.expedientesList = expedientesList;
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }

    public ArrayList<Expediente> getExpedientesList() {
        return this.expedientesList;
    }

    public void setExpedientesList(ArrayList<Expediente> expedientesList) {
        this.expedientesList = expedientesList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_expediente, parent, false);
        return new MyViewHolder(itemView, this.mListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Expediente currentItem = this.expedientesList.get(position);
        holder.titulo.setText(currentItem.getTitulo());
        holder.subtitulo.setText(currentItem.getSubtitulo());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.expedientesList.size();
    }

    /* loaded from: classes4.dex */
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView subtitulo;
        TextView titulo;

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            this.titulo = (TextView) itemView.findViewById(R.id.txt_expediente_titulo);
            this.subtitulo = (TextView) itemView.findViewById(R.id.txt_expediente_subtitulo);
            itemView.setOnClickListener(new View.OnClickListener() { // from class: com.cetis22.lectorsalud.data.AdaptadorExpediente$MyViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdaptadorExpediente.MyViewHolder.this.m128x780cc836(listener, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$new$0$com-cetis22-lectorsalud-data-AdaptadorExpediente$MyViewHolder  reason: not valid java name */
        public /* synthetic */ void m128x780cc836(onItemClickListener listener, View v) {
            int position;
            if (listener != null && (position = getAdapterPosition()) != -1) {
                listener.onItemClick(position);
            }
        }
    }
}
