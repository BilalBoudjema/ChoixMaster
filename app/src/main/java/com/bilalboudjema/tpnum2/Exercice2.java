package com.bilalboudjema.tpnum2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Exercice2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    public static final String TAG=MainActivity.class.getSimpleName();
    private static final int PICK_IMAGE_CAMERA = 123;
    Button btnChargerCamera;
    android.widget.Spinner Spinner;
    Button btnPick;
    Button btnSend;
    Button btnActivity31;
    Button btnActivity;
    EditText edtName;
    EditText edtMoy;
    RadioButton radioButton;
    RadioButton rdbSi;
    RadioButton rdbIsil;
    RadioGroup radioGroup;
    ImageView Image;
    String specialite;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice2);
        Spinner=(android.widget.Spinner)findViewById(R.id.Spinner);
        Spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spcial, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);


        rdbSi=(RadioButton)findViewById(R.id.rdbSi);
        rdbIsil=(RadioButton)findViewById(R.id.rdbIsil);

        if (rdbSi.isChecked() || rdbIsil.isChecked())
        {
            System.out.print("TAG="+TAG);
            Log.v(TAG,"Une Spécialité a ete choisis");
        }

        edtName=(EditText)findViewById(R.id.edtName);
        edtMoy=(EditText)findViewById(R.id.edtMoy);
        radioGroup = findViewById(R.id.radioGroup);
        btnPick=(Button)findViewById(R.id.btnChargerCamera);
        btnPick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickImageCamera();
            }
        });
        Image=(ImageView)findViewById(R.id.image);
        btnSend=(Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int rdbSelection = radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(rdbSelection);

                if (edtName.getText().toString().equals("") ||edtMoy.getText().toString().equals("") || rdbSelection == -1)
                {
                    Toast.makeText(Exercice2.this, "Informations manquantes remplire Svp tout le formulaire!!!", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(Exercice2.this, ""+edtName.getText().toString()+"\nSpécialité: "+radioButton.getText()+"\nLa moyenne: "+edtMoy.getText().toString()+"\nMaster en"+specialite, Toast.LENGTH_SHORT).show();
            }
        });

        btnActivity=(Button)findViewById(R.id.btnActivity);
        btnActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent exercice3 = new Intent(Exercice2.this,Exercice3.class);
                startActivity(exercice3);

            }
        });

        btnActivity31=(Button)findViewById(R.id.btnActivity31);
        btnActivity31.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent exercice1 = new Intent(Exercice2.this,MainActivity.class);
                startActivity(exercice1);

            }
        });
    }

    private void pickImageCamera()
    {
        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, PICK_IMAGE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_CAMERA) {

            Bitmap photo = (Bitmap)data.getExtras().get("data");

            Image.setImageBitmap(photo);
        }

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        specialite = (String) Spinner.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
