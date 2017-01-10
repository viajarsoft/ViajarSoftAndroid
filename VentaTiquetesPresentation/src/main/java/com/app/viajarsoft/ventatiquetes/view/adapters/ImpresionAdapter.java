package com.app.viajarsoft.ventatiquetes.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.helpers.ImpresionZpl;
import com.app.viajarsoft.ventatiquetes.view.views_activities.ImpresionClick;
import com.zebra.sdk.printer.discovery.DiscoveredPrinter;
import com.zebra.sdk.printer.discovery.DiscoveredPrinterBluetooth;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by USUARIO on 4/01/2017.
 */

public class ImpresionAdapter extends RecyclerView.Adapter<ImpresionAdapter.ViewHolder> {
    private final ImpresionClick listener;
    private ArrayList<DiscoveredPrinterBluetooth> listaimpresora;



    public ImpresionAdapter(ImpresionClick listener) {
        listaimpresora = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_impresora, parent, false);

        ViewHolder viewHolder = new ViewHolder(item, listener);
        return viewHolder;
    }

    //Mostrar datos
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscoveredPrinterBluetooth item = listaimpresora.get(position);
        holder.bindImpresion(item);

    }

    @Override
    public int getItemCount() {
        return listaimpresora.size();
    }

    public void limpiarLista() {
        listaimpresora.clear();
    }


    public void addPrinterItem(DiscoveredPrinter discoveredPrinter) {
        listaimpresora.add((DiscoveredPrinterBluetooth) discoveredPrinter);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemImpresora_tvMac;
        private TextView itemImpresora_tvNombre;
        DiscoveredPrinterBluetooth discoveredPrinter;

        public ViewHolder(View item, final ImpresionClick listener) {
            super(item);
            itemImpresora_tvMac = (TextView) item.findViewById(R.id.itemImpresora_tvMac);
            itemImpresora_tvNombre = (TextView) item.findViewById(R.id.itemImpresora_tvNombre);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(discoveredPrinter);
                }
            });
        }

        public void bindImpresion(DiscoveredPrinterBluetooth discoveredPrinterBluetooth) {
            itemImpresora_tvNombre.setText(discoveredPrinterBluetooth.address);
            itemImpresora_tvMac.setText(discoveredPrinterBluetooth.friendlyName);
            discoveredPrinter = discoveredPrinterBluetooth;
        }

    }
}
