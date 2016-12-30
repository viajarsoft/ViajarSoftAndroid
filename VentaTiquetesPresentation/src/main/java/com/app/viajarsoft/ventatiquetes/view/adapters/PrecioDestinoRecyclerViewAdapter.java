package com.app.viajarsoft.ventatiquetes.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.viajarsoft.ventatiquetes.R;
import com.app.viajarsoft.ventatiquetes.utilities.helpers.Formats;
import com.app.viajarsoft.ventatiquetes.view.views_activities.IVenderPasajesView;
import com.app.viajarsoft.ventatiquetesdomain.business_models.DestinationPrice;

import java.util.List;

/**
 * Created by jose on 28/12/16.
 */

public class PrecioDestinoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<DestinationPrice> destinationPriceList;
    private IVenderPasajesView venderPasajesView;

    public PrecioDestinoRecyclerViewAdapter(Context context, List<DestinationPrice> destinationPriceList, IVenderPasajesView venderPasajesView) {
        this.context = context;
        this.destinationPriceList = destinationPriceList;
        this.venderPasajesView = venderPasajesView;
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView precioDestino_tvDestino;
        public TextView precioDestino_tvTotal;
        public Button precioDestino_btnGenerar;

        CustomViewHolder(View itemView) {
            super(itemView);
            precioDestino_tvDestino = (TextView) itemView.findViewById(R.id.precioDestino_tvDestino);
            precioDestino_tvTotal = (TextView) itemView.findViewById(R.id.precioDestino_tvTotal);
            precioDestino_btnGenerar = (Button) itemView.findViewById(R.id.precioDestino_btnGenerar);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.template_precio_destino, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CustomViewHolder customViewHolder = (CustomViewHolder) holder;
        customViewHolder.precioDestino_tvDestino.setText(destinationPriceList.get(position).getDestino());
        double totalValue = destinationPriceList.get(position).getValorTiquete() + destinationPriceList.get(position).getValorSeguro();
        String currency = getCurrency(totalValue);
        customViewHolder.precioDestino_tvTotal.setText(currency);
        customViewHolder.precioDestino_btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                venderPasajesView.prepareDataToSellTicket(destinationPriceList.get(position));
            }
        });
    }

    private String getCurrency(double totalValue) {
        String currency = Formats.getCurrency(totalValue);
        return "$".concat(currency);
    }

    @Override
    public int getItemCount() {
        return destinationPriceList.size();
    }
}
