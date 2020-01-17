package com.mycompany.aimisencargos.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mycompany.aimisencargos.R;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText Nombre,Apellido,Telefono,Mail,Direccion,Dni,Buscar;
    DatabaseReference reference;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textResultado);
        Buscar=findViewById(R.id.txtBuscar);
        Dni=findViewById(R.id.txtDni);
        Nombre=findViewById(R.id.txtNombre);
        Apellido=findViewById(R.id.txtApellido);
        Telefono=findViewById(R.id.txtTelefono);
        Mail=findViewById(R.id.txtMail);
        Direccion=findViewById(R.id.txtDireccion);
        reference= FirebaseDatabase.getInstance().getReference();

        
    }


    public void Agregar(View view){
        String dni=Dni.getText().toString();
        String nombre=Nombre.getText().toString();
        String apellido=Apellido.getText().toString();
        int telefono=Integer.parseInt(Telefono.getText().toString());
        String mail=Mail.getText().toString();
        String direccion=Direccion.getText().toString();

        Map<String,Object>DatosUsuario=new HashMap<>();
        DatosUsuario.put("nombre",nombre);
        DatosUsuario.put("apellido",apellido);
        DatosUsuario.put("telefono",telefono);
        DatosUsuario.put("mail",mail);
        DatosUsuario.put("direccion",direccion);

        reference.child("Usuarios").child(dni).setValue(DatosUsuario);

        Limpiar();

    }



    public void Ver(View view){
        String buscar=Buscar.getText().toString();
        reference.child("Usuarios").child(buscar).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String nombre=dataSnapshot.child("nombre").getValue().toString();
                    String apellido=dataSnapshot.child("apellido").getValue().toString();
                    int telefono= Integer.parseInt(dataSnapshot.child("telefono").getValue().toString());
                    String mail=dataSnapshot.child("mail").getValue().toString();
                    String direccion=dataSnapshot.child("direccion").getValue().toString();


                    textView.setText("Nombre : "+nombre+"\nApellido : "+apellido+"\nDireccion : "+direccion+
                            "\nTelefono : "+telefono+"\nEmail : "+mail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void Limpiar(){
        Nombre.setText("");
        Apellido.setText("");
        Telefono.setText("");
        Mail.setText("");
        Direccion.setText("");
        Buscar.setText("");
    }
}
