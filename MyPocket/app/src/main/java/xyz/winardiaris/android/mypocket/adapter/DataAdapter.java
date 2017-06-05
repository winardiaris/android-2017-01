package xyz.winardiaris.android.mypocket.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import xyz.winardiaris.android.mypocket.R;
import xyz.winardiaris.android.mypocket.domain.DataDomain;

/**
 * Created by ars on 5/31/17.
 */

public class DataAdapter extends ArrayAdapter<DataDomain> {
    Integer saldo = 0;
    Integer tDebet = 0;
    Integer tCredit = 0;

    public DataAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<DataDomain> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.lv_data,parent,false);
        }

        TextView tvValue = (TextView) convertView.findViewById(R.id.tvValue);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        TextView tvType = (TextView) convertView.findViewById(R.id.tvType);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        ImageView ivReceipt = (ImageView) convertView.findViewById(R.id.ivReceipt);

        DataDomain d = getItem(position);
        SimpleDateFormat formatter = new SimpleDateFormat("dd mm yy");
        tvDate.setText(formatter.format(d.getDate()));

        Locale currentLocale = new Locale("id","id");
//        Currency currency = Currency.getInstance(currentLocale);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(currentLocale);
        tvValue.setText(numberFormat.format(d.getValue()));

        if(d.getType().toString().equals("D")){
            tvType.setTextColor(R.color.colorPrimary);
            tDebet += d.getValue().intValue();
        }
        else {
            tvType.setTextColor(R.color.colorAccent);
            tCredit += d.getValue().intValue();
        }
        tvType.setText(d.getType());
        tvDescription.setText(d.getDescription());
//        ivReceipt.setImageURI();

        return convertView;
    }
}
