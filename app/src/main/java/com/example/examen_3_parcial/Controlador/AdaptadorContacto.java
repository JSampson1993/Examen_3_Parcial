package com.example.examen_3_parcial.Controlador;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_3_parcial.Modelo.Medicamento;
import com.example.examen_3_parcial.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class AdaptadorContacto extends RecyclerView.Adapter<AdaptadorContacto.ViewHolderPersona> {
   static ArrayList <Medicamento> listpersonas;

    public AdaptadorContacto(ArrayList<Medicamento> listpersonas) {
        this.listpersonas = listpersonas;

    }

    @NonNull
    @NotNull
    @Override
    public AdaptadorContacto.ViewHolderPersona onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lista_item_contacto, null, false);
        return  new ViewHolderPersona(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderPersona holder, int position) {
        Medicamento persona = listpersonas.get(position);
     //   holder.foto.setImageBitmap(listpersonas.get(position).getImagen());
        holder.tvnombre.setText(listpersonas.get(position).getDescripcion());
        holder.tvTelefono.setText(listpersonas.get(position).getCantidad().toString());
        holder.tvLatitud.setText(listpersonas.get(position).getTiempo());
        holder.tvLongitud.setText(listpersonas.get(position).getId().toString());
        holder.btnEliminar.setOnClickListener(new eventoEliminar(persona));
    }

    @Override
    public int getItemCount() {
        return listpersonas.size();
    }



    class eventoEliminar implements View.OnClickListener {
        private Medicamento medicamento;

        public eventoEliminar(Medicamento persona) {
            this.medicamento = persona;
        }

        @Override
        public void onClick(View v) {
            AuxMedica.OpcionEliminar(medicamento);
        }
    }

    public  class ViewHolderPersona extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView tvnombre;
        TextView tvTelefono;
        TextView tvLatitud;
        TextView tvLongitud;
        Button btnEliminar;
        public ViewHolderPersona(@NonNull @NotNull View itemView) {
            super(itemView);
         //   foto= itemView.findViewById(R.id.image);
            tvnombre=itemView.findViewById(R.id.nametxt);
            tvTelefono=itemView.findViewById(R.id.teltxt);
                tvLatitud=itemView.findViewById(R.id.cord);
            tvLongitud=itemView.findViewById(R.id.cordtxt);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);

        }
    }
    public static void eliminarPersona(Medicamento persona) {
        listpersonas.remove(persona);
    }
}
