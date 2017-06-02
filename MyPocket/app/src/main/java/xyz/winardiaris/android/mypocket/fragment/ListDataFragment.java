package xyz.winardiaris.android.mypocket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.text.ParseException;

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
        ListView lvData = (ListView) fragmentView.findViewById(R.id.lvData);
        try {
            lvData.setAdapter(new DataAdapter(getContext(),R.layout.lv_data,dataDao.allData()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fragmentView;
    }
}
