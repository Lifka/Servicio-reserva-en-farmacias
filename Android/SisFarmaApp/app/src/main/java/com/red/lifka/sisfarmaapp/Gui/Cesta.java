package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.LineaCesta;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoGenerico;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class Cesta extends Activity implements View.OnClickListener {

    private Button efectuarcompra_button;
    private Button vaciarcesta_button;
    private ListView lista;
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cesta);

        efectuarcompra_button = (Button)findViewById(R.id.efectuarcompra_button);
        vaciarcesta_button = (Button)findViewById(R.id.vaciarcesta_button);
        lista = (ListView)findViewById(R.id.listView_cesta);

        efectuarcompra_button.setOnClickListener(this);
        vaciarcesta_button.setOnClickListener(this);

        loadCesta();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.efectuarcompra_button) {
            boolean correcto = ClienteFachada.getInstance().getUsuario().totalCesta() > 0;
            if (!correcto){
                Toast.makeText(this, R.string.errorcesta, Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Integer> sin_stock = ClienteFachada.getInstance().buy();

                if (sin_stock.size() != 0){
                    ArrayList<ProductoGenerico> productos_sin_stock = ClienteFachada.getInstance().getProductosByIds(sin_stock);
                    String cadena = "";

                    if (productos_sin_stock.size() > 0)
                        cadena = productos_sin_stock.get(0).getNombre();

                    for(int i = 1; i < productos_sin_stock.size()-1; i++){
                        cadena += ", " + productos_sin_stock.get(i).getNombre();
                    }

                    Toast.makeText(this, R.string.errorstock + cadena, Toast.LENGTH_SHORT).show();
                }

                startActivity(new Intent(this, Compra.class));
            }
        }

        if (v.getId() == R.id.vaciarcesta_button) {
            ClienteFachada.getInstance().getUsuario().clearCesta();
            loadCesta();
        }
    }


    private void loadCesta(){
        ArrayList<LineaCesta> cesta_lineas = ClienteFachada.getInstance().getCesta();

        adaptador = new ProductoCestaArrayAdapter(this, cesta_lineas);
        lista.setAdapter(adaptador);
    }
}
