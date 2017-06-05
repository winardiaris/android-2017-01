package xyz.winardiaris.android.mypocket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import xyz.winardiaris.android.mypocket.R;
import xyz.winardiaris.android.mypocket.adapter.DataAdapter;
import xyz.winardiaris.android.mypocket.dao.DataDao;
import xyz.winardiaris.android.mypocket.domain.DataDomain;

/**
 * Created by ars on 5/30/17.
 */

public class ListDataFragment extends Fragment {
//    private DataDao dataDao = new DataDao();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_list_data,container,false);
        DataDao dataDao = new DataDao(getContext());
        final ListView lvData = (ListView) fragmentView.findViewById(R.id.lvData);

        List<DataDomain> dataList = null;
        try {
            dataList = dataDao.allData();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TextView tvSaldo = (TextView) fragmentView.findViewById(R.id.tvSaldo);
        int saldo = dataDao.getSaldo();
        tvSaldo.setText(NumberFormat.getCurrencyInstance(new Locale("id","id")).format(saldo));

        lvData.setAdapter(new DataAdapter(getContext(),R.layout.lv_data,dataList));

        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {

                Toast.makeText(getContext(), lvData.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                return  true;
            }
        });



        return fragmentView;
    }
}
