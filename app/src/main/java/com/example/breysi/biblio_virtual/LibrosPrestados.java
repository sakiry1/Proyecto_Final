package com.example.breysi.biblio_virtual;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class LibrosPrestados extends AppCompatActivity {
    private DatabaseReference Refprestados;
    public static ArrayList<LibrosPrestadosClass> listaLP = new ArrayList<LibrosPrestadosClass>();
    ArrayList<HashMap<String, String>> valores = new ArrayList<HashMap<String, String>>();
    ArrayList<Libro> l_libro = new ArrayList<>();
    ListView listaPrestados;
    Libro itemlibro = new Libro();
    String keydb;

    Boolean siexiste = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.libros_prestados);
        listaPrestados = (ListView) findViewById(R.id.listaprestados);
        //db si tiene librosprestados
        //Get Intent

        listaLP = getIntent().getParcelableArrayListExtra("listaPrestados");
       // Log.e("tamañolista", "--->" + listaLP.size());

        for (final LibrosPrestadosClass prestadosClass : listaLP) {
            final String prestamo = prestadosClass.getFecha_prestamo();
            final String entrega = prestadosClass.getFecha_devolución();
            String libro = prestadosClass.getId_libro();
            Refprestados = FirebaseDatabase.getInstance().getReference();
            //recoge la llave "0"
            // String key = Refprestados.child("libro").push().getKey();
            // Log.e("tamañolista","--->"+key);
            //  Log.e("MIAU011","--->"+id_libro);
            final Query info_libro = Refprestados.child("libro").orderByChild("id").equalTo(libro);

            info_libro.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot keysnap: dataSnapshot.getChildren()) {
                        // keydb = keysnap.getKey();
                        itemlibro = keysnap.getValue(Libro.class);
                         Log.e("MIAU","--->"+itemlibro.getTitulo());
                        Log.e("MIAU333","--->"+itemlibro.getTitulo());
                        HashMap hashMap = new HashMap<String, String>();
                        hashMap.put("prestamo", prestamo);
                        hashMap.put("entrega", entrega);
                        hashMap.put("nom_libro",itemlibro.getTitulo());
                        valores.add(hashMap);

                        ListAdapter adapter = new SimpleAdapter(
                                LibrosPrestados.this, valores,
                                R.layout.lista_libro,
                                new String[]{"prestamo", "entrega", "nom_libro"},
                                new int[]{R.id.tv_autor, R.id.tv_titulo, R.id.tv_fentrega});
                        listaPrestados.setAdapter(adapter);
                        listaPrestados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Intent i = new Intent(LibrosPrestados.this, );

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            //Toast.makeText(LibrosPrestados.this, nom_libro, Toast.LENGTH_LONG).show();


        }

    }

    public void datosLibro(String id_libro) {

        Refprestados = FirebaseDatabase.getInstance().getReference();
        //recoge la llave "0"
       // String key = Refprestados.child("libro").push().getKey();
       // Log.e("tamañolista","--->"+key);
      //  Log.e("MIAU011","--->"+id_libro);
        final Query info_libro = Refprestados.child("libro").orderByChild("id").equalTo(id_libro);

        info_libro.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot keysnap: dataSnapshot.getChildren()) {
                    // keydb = keysnap.getKey();
                    itemlibro = keysnap.getValue(Libro.class);
                   // Log.e("MIAU","--->"+itemlibro.getTitulo());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
