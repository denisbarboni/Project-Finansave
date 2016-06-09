package com.example.denis.finansave.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.denis.finansave.R;
import com.example.denis.finansave.model.Movimentacao;

import java.util.ArrayList;

/**
 * Created by denis on 02/05/2016.
 */
public class TesteAdapter extends BaseAdapter {

    ArrayList<Movimentacao> _lst;
    Context _context;

    public TesteAdapter(Context cxt, ArrayList<Movimentacao> lst)
    {
        _context = cxt;
        _lst = lst;
    }

    @Override
    public int getCount() {
        return _lst.size();
    }

    @Override
    public Movimentacao getItem(int position) {
        return _lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;

        if(row == null)
        row = LayoutInflater.from(_context).inflate(R.layout.activity_adapter_despesa, null, false);

        TextView txtData = (TextView) row.findViewById(R.id.txtData);
        TextView txtDesc = (TextView) row.findViewById(R.id.txtDesc);
        TextView txtValor = (TextView) row.findViewById(R.id.txtValor);

        txtData.setText(_lst.get(position).getData().toString());
        txtDesc.setText(_lst.get(position).getDescricao().toString());
        txtValor.setText(_lst.get(position).getValor().toString());

        return row;
    }
}
