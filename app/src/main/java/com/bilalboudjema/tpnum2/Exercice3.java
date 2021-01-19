package com.bilalboudjema.tpnum2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Exercice3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
     // to exucut after   specified  time
    final Handler handler = new Handler();

    private static final int PICK_IMAGE = 100;
    android.widget.Spinner Spinner;
    Button btnPick;
    Button btnSend;
    Button btnActivity;
    Button btnActivity3551;
    EditText edtName;
    EditText edtMail;
    EditText edtPhone;
    EditText edtMoy;
    RadioButton radioButton;
    RadioButton rdbSi;
    RadioButton rdbIsil;
    RadioGroup radioGroup;
    ImageView img;
    CheckBox chbxMail;
    CheckBox chbxSms;
    String specialite;
    String phoneUniversity ="0356547820";
    String emailUniversity = "Faculté.mi@univ-bba.dz";
    String sujetMail = "Choix Master";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice3);

        final String TAG=MainActivity.class.getSimpleName();
        final int PICK_IMAGE = 100;

        Spinner=(android.widget.Spinner)findViewById(R.id.Spinner);
        Spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Spcial, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner.setAdapter(adapter);
        chbxSms=findViewById(R.id.chbxSms);
        chbxMail=findViewById(R.id.chbxMail);
        rdbSi=(RadioButton)findViewById(R.id.rdbSi);
        rdbIsil=(RadioButton)findViewById(R.id.rdbIsil);
        edtName=(EditText)findViewById(R.id.edtName);
        edtMoy=(EditText)findViewById(R.id.edtMoy);
        edtMail=(EditText)findViewById(R.id.edtMail);
        edtPhone=(EditText)findViewById(R.id.edtPhone);
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


        if (rdbSi.isChecked() || rdbIsil.isChecked())
        {
            System.out.print("TAG="+TAG);
            Log.v(TAG,"Une Spécialité a ete choisis");
        }

        btnActivity=(Button)findViewById(R.id.btnActivity);
        btnActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent exercice2 = new Intent(Exercice3.this, com.bilalboudjema.tpnum2.Exercice2.class);
                startActivity(exercice2);

            }
        });

        btnActivity3551=(Button)findViewById(R.id.btnActivity3551);
        btnActivity3551.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent exercice1 = new Intent(Exercice3.this,MainActivity.class);
                startActivity(exercice1);

            }
        });

        btnSend=(Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // pour le radio button
                int rdbSelection = radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(rdbSelection);

                if (edtName.getText().toString().equals("") ||edtMoy.getText().toString().equals("") || rdbSelection == -1 ||edtPhone.getText().toString().equals("")||edtMail.getText().toString().equals(""))
                {
                    Toast.makeText(Exercice3.this, "Informations manquantes remplire Svp tout le formulaire!!!", Toast.LENGTH_LONG).show();
                }

                 if (chbxMail.isChecked() && chbxSms.isChecked())
                    {
                        openEmail();

                        // envoyer Sms après 5 seconde
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                openSms();
                            }
                        },5000);

                    }

                if (chbxMail.isChecked() && !chbxSms.isChecked())
                {
                    openEmail();
                }else if(chbxSms.isChecked()&& !chbxMail.isChecked())
                {
                    openSms();
                }
                
            }
        });


    }


    private void pickImage()
    {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
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


    public void openSms()
    {
        int choixspecialite = radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(choixspecialite);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms",phoneUniversity,null));
        intent.putExtra("sms_body",edtName.getText().toString()+"\n Spécialité: "+radioButton.getText()+"\nEmail: "+edtMail.getText().toString()+"\n Numéro Tel: "+edtPhone.getText().toString()+"\nMaster en: "+specialite);
        startActivity(intent);
    }

    public void openEmail()
    {
        String recipientList = emailUniversity;
        String[] recipients = recipientList.split(",");
        String subject = sujetMail;
        int chois = radioGroup.getCheckedRadioButtonId();
        String nom = edtName.getText().toString();
        String mail = edtMail.getText().toString();
        String phone = edtPhone.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, "Nom: "+nom+"\n Spécialité: "+chois +"\nEmail: "+mail+"\nTéléphone: "+phone +"\n Master En : "+specialite);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }




}
