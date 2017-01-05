package com.app.viajarsoft.ventatiquetes.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class ImpresionAdapter extends RecyclerView.Adapter<ImpresionAdapter.ViewHolder> {

    private ArrayList<DiscoveredPrinter> listaimpresora;



    public ImpresionAdapter() {
        listaimpresora = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_impresora, parent, false);

        ViewHolder vh = new ViewHolder(item); //instancia controlador
        return vh;
    }

    //Mostrar datos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoveredPrinter item = listaimpresora.get(position);
        holder.bindProspect(item);

    }

    @Override
    public int getItemCount() {
        return listaimpresora.size();
    }

    public void addPrinterItem(DiscoveredPrinter discoveredPrinter) {
        listaimpresora.add(discoveredPrinter);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        DiscoveredPrinter discoveredPrinter;
        private TextView itemImpresora_tvMac;
        private TextView itemImpresora_tvNombre;

        public ViewHolder(View item) {
            super(item);
            itemImpresora_tvMac = (TextView) item.findViewById(R.id.itemImpresora_tvMac);
            itemImpresora_tvNombre = (TextView) item.findViewById(R.id.itemImpresora_tvNombre);
        }



        public void bindProspect(DiscoveredPrinter p) {
            itemImpresora_tvNombre.setText(p.address);
            // nombre.setText(p.getDiscoveryDataMap().keySet());
            discoveredPrinter = p;
        }
    }
}
