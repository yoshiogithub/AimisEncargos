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
import com.mycompany.aimisencargos.Entidades.Usuario;
import com.mycompany.aimisencargos.R;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    EditText Nombre,Apellido,Telefono,Mail,Direccion;
    DatabaseReference reference;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.textView5);
        Nombre=findViewById(R.id.txtNombre);
        Apellido=findViewById(R.id.txtApellido);
        Telefono=findViewById(R.id.txtTelefono);
        Mail=findViewById(R.id.txtMail);
        Direccion=findViewById(R.id.txtDireccion);
        reference= FirebaseDatabase.getInstance().getReference();

        
    }


    public void Agregar(View view){


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

        reference.child("Usuarios").push().setValue(DatosUsuario);

        Limpiar();

    }

    private void Limpiar(){
        Nombre.setText("");
        Apellido.setText("");
        Telefono.setText("");
        Mail.setText("");
        Direccion.setText("");
    }
}
