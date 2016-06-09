package com.example.denis.finansave.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.denis.finansave.R;
import com.example.denis.finansave.model.Movimentacao;

import java.util.List;

/**
 * Created by denis on 01/06/2016.
 */
public class AnexoAdapter extends ArrayAdapter<Movimentacao>{


    Context contexto;
    int id;
    List<Movimentacao> lista;

    public AnexoAdapter(Context contexto, int id, List<Movimentacao> lista){
        super(contexto,id,lista);
        this.contexto = contexto;
        this.lista = lista;
        this.id = id;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        Movimentacao movimentacao;
        ImageView foto;
        TextView nome;
        TextView dono;
        Bitmap raw;
        byte[] fotoArray;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(R.layout.activity_adapter_despesa, null, false);
        }

        TextView txtData = (TextView) view.findViewById(R.id.txtData);
        TextView txtDesc = (TextView) view.findViewById(R.id.txtDesc);
        TextView txtValor = (TextView) view.findViewById(R.id.txtValor);
        ImageView imgFoto = (ImageView) view.findViewById(R.id.imgFoto);

        movimentacao = lista.get(position);

        txtData.setText(movimentacao.getData());
        txtDesc.setText(movimentacao.getDescricao());
        txtValor.setText(movimentacao.getValor().toString());
        fotoArray = movimentacao.getFoto();

        if(fotoArray != null){
            raw  = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            imgFoto.setImageBitmap(raw);
        }

        return view;
    }
}
