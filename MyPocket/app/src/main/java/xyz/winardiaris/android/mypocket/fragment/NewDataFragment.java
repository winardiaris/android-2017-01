package xyz.winardiaris.android.mypocket.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import xyz.winardiaris.android.mypocket.AfterLoginActivity;
import xyz.winardiaris.android.mypocket.R;
import xyz.winardiaris.android.mypocket.dao.DataDao;
import xyz.winardiaris.android.mypocket.domain.DataDomain;

/**
 * Created by ars on 5/30/17.
 */

public class NewDataFragment extends Fragment {
    String type = "D";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fr_new_data,container,false);

        final EditText etValue = (EditText) fragmentView.findViewById(R.id.etValue);
        final RadioButton rbDebet = (RadioButton) fragmentView.findViewById(R.id.rbDebet);
        final RadioButton rbCredit = (RadioButton) fragmentView.findViewById(R.id.rbCredit);
        final EditText etDate = (EditText) fragmentView.findViewById(R.id.etDate);
        final EditText etDescription = (EditText) fragmentView.findViewById(R.id.etDescription);
        final ImageButton ibAddReceipt = (ImageButton) fragmentView.findViewById(R.id.ibAddReceipt);

        final Button btSave = (Button) fragmentView.findViewById(R.id.btSave);

        ibAddReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int selectImageActivity = 1234;
                startActivityForResult(i,selectImageActivity);
            }

            // selanjutnya ambil simpan gambar dan generate nama
        });
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentDate=Calendar.getInstance();
                int mYear=mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth+1;
                        String selecteddate = selectedyear+"-"+selectedmonth+"-"+selectedday;
                        etDate.setText(selecteddate);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Set Date");
                mDatePicker.show();  }
        });

        rbDebet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        rbCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRadioButtonClicked(v);
            }
        });

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String v_ = etValue.getText().toString();
                String t_ = type;
                String d_ = etDate.getText().toString();
                String de_ = etDescription.getText().toString();
                String r_ = "arsarsa.jpg";

                new saveData(getActivity()).execute(v_,t_,d_,de_,r_);

            }
        });


        return  fragmentView;
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.rbDebet:
                if (checked)
                    type = "D";
                break;
            case R.id.rbCredit:
                if (checked)
                    type = "C";
                break;
        }
        Log.d("Cash type:",type);


    }

    private class saveData extends AsyncTask<String, Void, String>{
        ProgressDialog dialog;
        private Context context1;

        public saveData(Context context){this.context1=context;}
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            dialog = new ProgressDialog(context1);
            dialog.setMessage("Saving..");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... params){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            String result = "error" ;
            ArrayList parameter = new ArrayList();
            parameter.add(0,params[0]);
            parameter.add(1,params[1]);
            parameter.add(2,params[2]);
            parameter.add(3,params[3]);
            parameter.add(4,params[4]);
//[2000, C, 2017-6-2, bayar tol, arsarsa.jpg]
            DataDomain dd = new DataDomain();
            dd.setUserId(1);
            dd.setValue(new BigDecimal(params[0]));
            dd.setType(params[1]);
            try {
                dd.setDate(format.parse(params[2]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dd.setDescription(params[3]);
            dd.setReceiptImage(params[4]);

            //add to DB
            DataDao dataDao = new DataDao(getContext());
            dataDao.insertData(dd);

            Log.d("data",dd.toString());
            result = "ok";
            return result;

        }
        @Override
        protected void onPostExecute(String str){
            dialog.dismiss();
            if(str.equals("error")){
                Toast.makeText(getContext(),"Save error",Toast.LENGTH_SHORT).show();
            }
            else if(str.equals("ok")){
                Toast.makeText(getContext(),"Saving successfully",Toast.LENGTH_SHORT).show();
                gotoListData(NewDataFragment.this.getView());
            }
            Log.d("data onPostExecute",str);
        }

    }
    public void gotoListData(View v){
        FragmentTransaction ft = NewDataFragment.this
                .getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_after_login,new ListDataFragment());
        ft.commit();

//        Intent afterLoginActivity = new Intent(getContext(), AfterLoginActivity.class);
//        startActivity(afterLoginActivity);
    }
}
