package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;

public class DataActivity extends ActivityGeneric {

    private DatePickerDialog datePickerDialog;
    private Button editTextData;
    private PCQContext pcqContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        pcqContext = (PCQContext) getApplication();

        initDatePicker();
        editTextData = (Button) findViewById(R.id.editTextData);
        Button buttonSalvarData = (Button) findViewById(R.id.buttonSalvarData);
        Button buttonCancData = (Button) findViewById(R.id.buttonCancData);

        editTextData.setText(getTodaysDate());

        buttonSalvarData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pcqContext.getFormularioCTR().setDataInsCabec(editTextData.getText().toString(), pcqContext.getTipoTela());

                if(pcqContext.getTipoTela() == 1) {
                    Intent it = new Intent(DataActivity.this, ColabActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pcqContext.getTipoTela() == 1){
                    Intent it = new Intent(DataActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(DataActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                editTextData.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year)
    {
        return String.format("%02d", day) + "/" +  String.format("%02d", month) + "/" + year;
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


}