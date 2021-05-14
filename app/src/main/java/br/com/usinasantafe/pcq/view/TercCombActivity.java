package br.com.usinasantafe.pcq.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import br.com.usinasantafe.pcq.PCQContext;
import br.com.usinasantafe.pcq.R;
import br.com.usinasantafe.pcq.model.bean.estaticas.TercCombBean;
import br.com.usinasantafe.pcq.util.ConexaoWeb;

public class TercCombActivity extends ActivityGeneric {

    private List<TercCombBean> tercCombList;
    private PCQContext pcqContext;
    private RadioGroup radioGroupItemTercComb;
    private int posicao;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terc_comb);

        pcqContext = (PCQContext) getApplication();

        Button buttonRetTercComb = (Button) findViewById(R.id.buttonRetTercComb);
        Button buttonAvancaTercComb = (Button) findViewById(R.id.buttonAvancaTercComb);
        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);
        radioGroupItemTercComb = (RadioGroup) findViewById(R.id.radioGroupItemTercComb);

        posicao = -1;

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled}
                },
                new int[] {
                        Color.BLACK
                        ,Color.BLUE
                }
        );

        tercCombList = pcqContext.getFormularioCTR().tercCombList();

        for (TercCombBean tercCombBean : tercCombList) {
            RadioButton radioButtonItem = new RadioButton(getApplicationContext());
            radioButtonItem.setText(tercCombBean.getDescrTercComb());
            radioButtonItem.setTextColor(Color.BLACK);
            radioButtonItem.setTextSize(22F);
            radioButtonItem.setButtonTintList(colorStateList);
            radioGroupItemTercComb.addView(radioButtonItem);
        }

        radioGroupItemTercComb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                posicao = radioGroup.indexOfChild(radioButton);
            }
        });

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(TercCombActivity.this)) {

                            progressBar = new ProgressDialog(TercCombActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            pcqContext.getFormularioCTR().atualDadosTercComb(TercCombActivity.this, TercCombActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(TercCombActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }
        });

        buttonRetTercComb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(pcqContext.getTipoTela() == 1){
                    Intent it = new Intent(TercCombActivity.this, BrigadistaActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonAvancaTercComb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TercCombBean tercCombBean = new TercCombBean();

                if(posicao > -1) {
                    tercCombBean = tercCombList.get(posicao);
                }
                else {
                    tercCombBean.setIdTercComb(0L);
                }

                pcqContext.getFormularioCTR().setTercCombCabec(tercCombBean.getIdTercComb(), pcqContext.getTipoTela());

                if(pcqContext.getTipoTela() == 1) {
                    Intent it = new Intent(TercCombActivity.this, AceiroCanavialActivity.class);
                    startActivity(it);
                    finish();
                }
                else{
                    Intent it = new Intent(TercCombActivity.this, RelacaoCabecActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }

    public void onBackPressed() {
    }

}
