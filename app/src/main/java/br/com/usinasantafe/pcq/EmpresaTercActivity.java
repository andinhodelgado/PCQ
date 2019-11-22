package br.com.usinasantafe.pcq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EmpresaTercActivity extends Activity {

    private PCQContext pcqContext;
    private EditText editTextEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_terc);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetEmpresa = (Button) findViewById(R.id.buttonRetEmpresa);
        Button buttonAvancaEmpresa = (Button) findViewById(R.id.buttonAvancaEmpresa);
        editTextEmpresa = (EditText) findViewById(R.id.editTextEmpresa);

        buttonRetEmpresa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(EmpresaTercActivity.this, MsgActivity.class);
                startActivity(it);
                finish();
            }
        });

        buttonAvancaEmpresa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextEmpresa.getText().toString().equals("")) {

                    pcqContext.getFormularioCTR().setEmpresaTercCabec(editTextEmpresa.getText().toString());

                    Intent it = new Intent(EmpresaTercActivity.this, MsgActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder( EmpresaTercActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NOME DA EMPRESA QUE AUXÍLIO NO COMBATE AO INCÊNDIO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();
                }

            }

        });

    }

    public void onBackPressed() {
    }

}
