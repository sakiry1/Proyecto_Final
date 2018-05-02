package com.example.breysi.biblio_virtual;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class Perfil extends AppCompatActivity implements View.OnClickListener {

    TextView tx;
    private DatabaseReference myRef;
    //--------------------------------------------------------
    ImageButton btn_libros_prestados;
    ImageButton btn_anadir_libros;
    ImageButton btn_cuenta;
    ImageButton btn_configuracion;
    Usuario user;
    String nom_user;
    //---------------------------------------------------------
    public ArrayList<LibrosPrestadosClass> listaLibrosPrestados = new ArrayList<>();
    LibrosPrestadosClass prestadosClass = new LibrosPrestadosClass();
    int posicion=-1;
    ListView listaPrestados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tx = (TextView) findViewById(R.id.textView);
        btn_libros_prestados = (ImageButton) findViewById(R.id.btn_libros_prestados);
        btn_anadir_libros = (ImageButton) findViewById(R.id.btn_buscar_anadir_libros);
        btn_cuenta = (ImageButton) findViewById(R.id.btn_cuenta);
        btn_configuracion = (ImageButton) findViewById(R.id.btn_configuracion);

        btn_libros_prestados.setOnClickListener(this);
        btn_anadir_libros.setOnClickListener(this);
        btn_cuenta.setOnClickListener(this);
        btn_configuracion.setOnClickListener(this);
       // Recogemos el usuario
        user = (Usuario) getIntent().getParcelableExtra("usuarioo");
        String nom_user = user.getNombre();
        //tx.setText(user.getNombre());
         tx.setText(nom_user); //  SOLO POR EL NOMBRE
       // Toast.makeText(Perfil.this, user.getCurso(), Toast.LENGTH_SHORT).show();
        Toast.makeText(Perfil.this,"Bienvenido " +user.getApellido(), Toast.LENGTH_SHORT).show();
        Log.i("estudiante", user.getApellido());
        librosPrestados();
    }

    public void librosPrestados() {
        //----------LIBRO------------------------
        myRef = FirebaseDatabase.getInstance().getReference();
        //consulta sobre los librosPrestados que tiene el usuario, usando su dni
        final Query q_librosPrestados = myRef.child("libros_prestados").orderByChild("id_usuario").equalTo(user.getDni());

        q_librosPrestados.addValueEventListener(new ValueEventListener() {//valueEventListener ya que leera una lista de datos o no
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // posicion =-1;

                if (dataSnapshot.exists()) {
                    //contiene all childrens bajo el nodo libros_prestados
                    for (DataSnapshot snapshotlibrosPrestados: dataSnapshot.getChildren()) {
                      // prestadosClass.setFecha_prestamo((String) snapshotlibrosPrestados.child("fecha_prestamo").getValue());
                      // prestadosClass.setFecha_devolución((String) snapshotlibrosPrestados.child("fecha_devolución").getValue());
                     //prestadosClass.setId_libro((Usuario) snapshotlibrosPrestados.child("id_usuario").getValue());
                       // prestadosClass.setIdlibro((Libro) snapshotlibrosPrestados.child("id_libro").getValue());
                        prestadosClass = snapshotlibrosPrestados.getValue(LibrosPrestadosClass.class);
                        //Long l = (Long) snapshotlibrosPrestados.child("id").getValue();
                      //  prestadosClass.setId(l.intValue());

                        listaLibrosPrestados.add(prestadosClass);
                        // posicion++;
                        ;
                    }
                 //   Intent intent= new Intent(Perfil.this,LibrosPrestados.class);
                   // intent.putExtra("listaPrestados",listaLibrosPrestados);
                 /*   Log.e("tamañolista","--->"+listaLibrosPrestados.size());
                    Log.e("librodatos : ","-->"+listaLibrosPrestados+"--"+
                            prestadosClass.getFecha_prestamo()+"--"+
                            prestadosClass.getFecha_devolución()+"--");
                    Log.e("datos2: ",dataSnapshot.getValue().toString());*/
                }

                else{
                    //si el usuario no tiene NADA prestado te envia
                    Intent intent= new Intent(Perfil.this,LibrosPrestados.class);
                    intent .putExtra("Noprestamos",R.string.prestamo_failed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }


    @Override
    public void onClick(View view) {
        Intent intent;
        Intent intent1;
        Intent intent2;
        switch (view.getId()) {
            case R.id.btn_libros_prestados:
                intent = new Intent(Perfil.this, LibrosPrestados.class);
                intent.putExtra("listaPrestados",listaLibrosPrestados);
                startActivity(intent);
                break;
            case R.id.btn_buscar_anadir_libros:
                intent1 = new Intent(Perfil.this, BuscarLibros.class);
                startActivity(intent1);
                break;
            case R.id.btn_cuenta:
                intent2 = new Intent(Perfil.this, CuentaUsuario.class);
                startActivity(intent2);
                break;

            default:
                Toast.makeText(Perfil.this, "Error", Toast.LENGTH_LONG).show();
        }
    }

}



//-----------------
   /* public void autentificar_usuario(final String auth_email) { SOLO POR EL NOMBRE
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

      //  final Query q_email = myRef.child("usuario").orderByChild("nombre").equalTo(auth_email); SOLO POR EL NOMBRE

    //    final Query q_pass = myRef.child("usuario").orderByChild("clave").equalTo(auth_password);


        //consulta
         final Usuario tokenUser = new Usuario();

        q_email.addListenerForSingleValueEvent(new ValueEventListener() { // SOLO POR EL NOMBRE
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean claveCorrecta = false;
                if (dataSnapshot.exists()) {
                     //tokenUser =  new Usuario(); ;
                    //String nombre_usuario = "", apellido_usuario = "";
                    for (DataSnapshot item : dataSnapshot.getChildren()) {
                        tokenUser.setApellido((String) item.child("apellido").getValue());
                        tokenUser.setClave((String) item.child("clave").getValue());
                        tokenUser.setCurso((String) item.child("curso").getValue());
                        tokenUser.setDni((String)item.child("dni").getValue());
                        tokenUser.setEmail((String)item.child("email").getValue());
                        // tokenUser.setFechaNacimiento((Date) item.child("fechaNacimiento").getValue());
                        tokenUser.setNombre((String)item.child("nombre").getValue());
                        tokenUser.setPoblacion((String)item.child("poblacion").getValue());
                        tokenUser.setProvincia((String)item.child("provincia").getValue());
                        tokenUser.setTelefono((String)item.child("telefono").getValue());
                    }
                    Toast.makeText(Perfil.this, "HOLAAAAAA", Toast.LENGTH_LONG).show();

                    String apellido = tokenUser.getApellido();
                        Toast.makeText(Perfil.this, apellido, Toast.LENGTH_LONG).show();
                    //q_pass.getRef().getDatabase();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */


   /*   ArrayList<HashMap<String, String>> valores = new ArrayList<HashMap<String, String>>();
                    ArrayList<LibrosPrestadosClass> listaLP = new ArrayList<LibrosPrestadosClass>();
                    for(final LibrosPrestadosClass prestadosClass: listaLP){
                        String prestamo = prestadosClass.getFechaPrestamo();
                        String entrega = prestadosClass.getFechaDevolucion();
                        Libro nom_libro= prestadosClass.getIdlibro();

                        HashMap hashMap = new HashMap<String, String>();
                        hashMap.put("fecha_prestamo", prestamo);
                        hashMap.put("fecha_devolución", entrega);
                        hashMap.put("id_libro", nom_libro);
                        valores.add(hashMap);

                        ListAdapter adapter = new SimpleAdapter(
                                Perfil.this,valores,
                                R.layout.lista_libro,
                                new String[]{"fecha_prestamo","fecha_devolución","id_libro"},
                                new int[]{R.id.tv_fentrega,R.id.tv_titulo,R.id.tv_autor});
                        listaPrestados.setAdapter(adapter);
                        listaPrestados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        });}*/

/*
    public void librosPrestados() {
        //----------LIBRO------------------------
        myRef = FirebaseDatabase.getInstance().getReference();
        //consulta sobre los librosPrestados que tiene el usuario, usando su dni
        final Query q_librosPrestados = myRef.child("libros_prestados").orderByChild("id_usuario").equalTo(user.getDni());

        q_librosPrestados.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //posicion =-1;
                if (dataSnapshot.exists()) {
                    DataSnapshot libroPrestados= dataSnapshot.child("libros_prestados");
                    Iterable<DataSnapshot> libroPrestadoChildren = libroPrestados.getChildren();
                    //contiene all childrens bajo el nodo libros_prestados
                    for (DataSnapshot snapshotlibrosPrestados: libroPrestadoChildren) {
                        LibrosPrestadosClass lpc = dataSnapshot.getValue(LibrosPrestadosClass.class);

                        listaLibrosPrestados.add(lpc);
                        // posicion++;
                    }
                    Intent intent= new Intent(Perfil.this,LibrosPrestados.class);
                    intent.putExtra("listaPrestados",listaLibrosPrestados);
                    Log.e("tamañolista","--->"+listaLibrosPrestados.size());
                    Log.e("tamaño2","--->"+libroPrestadoChildren);

                }
                else{
                    //si el usuario no tiene NADA prestado te envia
                    Intent intent= new Intent(Perfil.this,LibrosPrestados.class);
                    intent .putExtra("Noprestamos",R.string.prestamo_failed);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }
    */