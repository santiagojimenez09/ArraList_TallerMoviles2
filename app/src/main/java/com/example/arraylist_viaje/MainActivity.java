package com.example.arraylist_viaje;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView jtvvlrtransporte,jtvvlralimentacion,jtvvlriva,jtvtotal;
    EditText jetcantidad,jetcodigo;
    RadioButton jrbavion,jrbbus,jrbautomovil;
    CheckBox jcbalimento;
    Button jbtguardar,jbtconsultar,jbtanular,jbtcancelar,jbtcalcular;
    ClsViajes obviajes=new ClsViajes();
    ArrayList<ClsViajes> alviajes;
    int pos,sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        jtvvlrtransporte=findViewById(R.id.tvvlrtransporte);
        jtvvlralimentacion=findViewById(R.id.tvvlralimentacion);
        jtvvlriva=findViewById(R.id.tvvlriva);
        jtvtotal=findViewById(R.id.tvtotal);
        jetcantidad=findViewById(R.id.etcantidad);
        jetcodigo=findViewById(R.id.etcodigo);
        jrbavion=findViewById(R.id.rbavion);
        jrbbus=findViewById(R.id.rbbus);
        jrbautomovil=findViewById(R.id.rbautomovil);
        jcbalimento=findViewById(R.id.cbalimentacion);
        jbtguardar=findViewById(R.id.btguardar);
        jbtconsultar=findViewById(R.id.btconsultar);
        jbtanular=findViewById(R.id.btanular);
        jbtcancelar=findViewById(R.id.btcancelar);
        jbtcalcular=findViewById(R.id.btcalcular);
        alviajes=new ArrayList<ClsViajes>();

    }

    public void Guardar(View view){
        String codigo,transporte,cantidad,alimentacion,iva,total;
        codigo = jetcodigo.getText().toString();
        transporte = jtvvlrtransporte.getText().toString();
        cantidad = jetcantidad.getText().toString();
        alimentacion = jtvvlralimentacion.getText().toString();
        iva = jtvvlriva.getText().toString();
        total = jtvtotal.getText().toString();
        if (codigo.isEmpty() || cantidad.isEmpty()){
            Toast.makeText(this,"El codigo y la cantidad de personas son requeridas",Toast.LENGTH_LONG).show();
            jetcodigo.requestFocus();
        }else{
            if (sw==1){
                alviajes.get(pos).setTransporte(transporte);
                alviajes.get(pos).setCantidad(cantidad);
                alviajes.get(pos).setAlimentacion(alimentacion);
                alviajes.get(pos).setIva(iva);
                alviajes.get(pos).setTotal(total);
                Toast.makeText(this,"Campos modificados",Toast.LENGTH_LONG).show();
                limpiar_campos();
                sw=0;
            }else{
                pos=0;
                sw=0;
                    while (pos<alviajes.size() && sw==0){
                        obviajes = alviajes.get(pos);
                        if (obviajes.getCodigo().equals(codigo))
                            sw=1;
                        else
                            pos ++;
                    }
                if (sw==0){
                    obviajes = new ClsViajes(codigo,transporte,cantidad,alimentacion,iva,total);
                    alviajes.add(obviajes);
                    Toast.makeText(this,"Campos Guardados",Toast.LENGTH_LONG).show();
                    limpiar_campos();
                }else{
                    limpiar_campos();
                    Toast.makeText(this,"Codigo ya esta registrado",Toast.LENGTH_LONG).show();
                    sw=0;
                }
            }
        }
    }

    public void Consultar(View view){
        String codigo;
        pos=0;
        sw=0;
        codigo = jetcodigo.getText().toString();
        if (codigo.isEmpty()){
            Toast.makeText(this,"El codigo es requerido",Toast.LENGTH_LONG).show();
            jetcodigo.requestFocus();
        }else{
            while (pos<alviajes.size() && sw==0){
                obviajes = alviajes.get(pos);
                if (obviajes.getCodigo().equals(codigo))
                    sw=1;
                else
                    pos ++;
            }if (sw==0)
                Toast.makeText(this,"Codigo no registrado",Toast.LENGTH_LONG).show();
            else{
                jtvvlrtransporte.setText(obviajes.getTransporte());
                jetcantidad.setText(obviajes.getCantidad());
                jtvvlralimentacion.setText(obviajes.getAlimentacion());
                jtvvlriva.setText(obviajes.getIva());
                jtvtotal.setText(obviajes.getTotal());
            }
            jetcodigo.requestFocus();
        }
    }


    private void calcular_total(){
        String cantidad;
        cantidad=jetcantidad.getText().toString();
        if (cantidad.isEmpty()){
            Toast.makeText(this,"La cantidad de personas es requerida",Toast.LENGTH_LONG).show();
            jetcantidad.requestFocus();
        }else{
            int vlrtransporte,vlralimentacion,vlriva,vlrtotal,cantpersonas;

            if (jrbavion.isChecked())
                vlrtransporte = 700000;
            else if (jrbbus.isChecked())
                vlrtransporte = 200000;
            else
                vlrtransporte = 400000;
            if(jcbalimento.isChecked()){
                cantpersonas = Integer.parseInt(cantidad);
                vlralimentacion = cantpersonas * 70000;
            }else
                vlralimentacion = 0;

            vlriva = (vlrtransporte + vlralimentacion) * 19/100;
            vlrtotal = vlrtransporte + vlralimentacion + vlriva;

            jtvvlrtransporte.setText(String.valueOf(vlrtransporte));
            jtvvlralimentacion.setText(String.valueOf(vlralimentacion));
            jtvvlriva.setText(String.valueOf(vlriva));
            jtvtotal.setText(String.valueOf(vlrtotal));

        }


    }

    public void Cancelar(View view){
        limpiar_campos();
    }

    private void limpiar_campos(){
        jetcodigo.setText("");
        jrbavion.setChecked(true);
        jtvvlrtransporte.setText("700000");
        jetcantidad.setText("");
        jcbalimento.setChecked(false);
        jtvvlralimentacion.setText("0");
        jtvvlriva.setText("0");
        jtvtotal.setText("0");
        jetcodigo.requestFocus();
        sw=0;

    }

    public void Calcular(View view){
        calcular_total();
    }

}