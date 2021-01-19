package com.bilalboudjema.tpnum2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    public static final String TAG=MainActivity.class.getSimpleName();
    private static final int PICK_IMAGE = 100;

    Spinner Spinner;
    Button btnPick;
    Button btnSend;
    Button btnActivity;
    Button btnActivity311;
    EditText edtName;
    EditText edtMoy;
    RadioButton radioButton;
    RadioButton rdbSi;
    RadioButton rdbIsil;
    RadioGroup radioGroup;
    ImageView img;
    String specialite;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner=(android.widget.Spinner)findViewById(R.id.Spinner);
        Spinner.setOnItemSelectedListener(this);
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
        btnActivity311=(Button)findViewById(R.id.btnActivity3333);
        edtName=(EditText)findViewById(R.id.edtName);
        edtMoy=(EditText)findViewById(R.id.edtMoy);
        radioGroup = findViewById(R.id.radioGroup);
        btnPick=(Button)findViewById(R.id.btnCharger);
        btnPick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pickImage();
            }
        });
        img=(ImageView)findViewById(R.id.img);
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
                    Toast.makeText(MainActivity.this, "Informations manquantes remplire Svp tout le formulaire!!!", Toast.LENGTH_LONG).show();
                }else
                    {


                        Toast.makeText(MainActivity.this, ""+edtName.getText().toString()+"\nSpécialité: "+radioButton.getText()+"\nLa moyenne: "+edtMoy.getText().toString()+"\nMaster en "+specialite, Toast.LENGTH_LONG).show();
                    }

            }
        });

        btnActivity=(Button)findViewById(R.id.btnActivity);
        btnActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent exercice2 = new Intent(MainActivity.this, com.bilalboudjema.tpnum2.Exercice2.class);
                startActivity(exercice2);

            }
        });

        btnActivity311.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent exo3 = new Intent(MainActivity.this,Exercice3.class);
                startActivity(exo3);
            }
        });



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

    private void pickImage()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageView imageView = findViewById(R.id.img);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
