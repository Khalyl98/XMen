package com.example.xmen;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//import rnu.isetzg.xmen.databinding.rnu.isetzg.xmen.EditActivityBinding;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xmen.XMenApplication;
import com.example.xmen.databinding.EditActivityBinding;

import java.io.Serializable;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private EditActivityBinding ui;
    private List<XMen> xMenList;
    private XMenApplication application;
    private Intent intent;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //   Log.d("EditActivity", "onCreate called. Instance: " + this);
        ui=EditActivityBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        application = (XMenApplication) getApplication();
        // application.initListe();
        intent= getIntent();
        xMenList = application.getListe();

        position =intent.getIntExtra("position",-1);
        if (position >= 0) {
            // This is an existing X-Men for editing

            setXMen(xMenList.get(position));
        } else {
            XMen  newXmen=new XMen();
            // This is a new X-Men for creation
            //   setXMen(newXmen);
        }
    }
    // private editBinding ui;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * créer une nouvelle instance de XMen et la remplir avec les EditText de l'interface
     */
    public void onAccept(MenuItem item) {
        XMen xmen;
        if (position >= 0) {
            xMenList.get(position).setNom(ui.editTextNom.getText().toString());
            xMenList.get(position).setAlias(ui.editTextAlias.getText().toString());
            xMenList.get(position).setDescription(ui.editTextDescription.getText().toString());
            xMenList.get(position).setConfrerie(ui.editTextConfrerie.getText().toString());
            xMenList.get(position).setPouvoirs(ui.editTextPouvoirs.getText().toString());

            //   application.modifyListe(position,xmen);
        } else {
            xmen = new XMen();
            xmen.setNom(ui.editTextNom.getText().toString());
            xmen.setAlias(ui.editTextAlias.getText().toString());
            xmen.setDescription(ui.editTextDescription.getText().toString());
            xmen.setConfrerie(ui.editTextConfrerie.getText().toString());
            xmen.setPouvoirs(ui.editTextPouvoirs.getText().toString());

            //application.addtoListe(xmen);
            application.getListe().add(xmen);
        }
        // Update the X-Men properties


        // TODO: Update other properties as needed

        setResult(RESULT_OK);
        finish();
    }

    private void setXMen(XMen xmen)
    {
// TODO mettre les informations du XMen dans les vues
        ui.editTextNom.setText(xmen.getNom());
        ui.editTextAlias.setText(xmen.getAlias());
        ui.editTextDescription.setText(xmen.getDescription());
        ui.editTextConfrerie.setText(xmen.getConfrerie());
        ui.editTextPouvoirs.setText(xmen.getPouvoirs());

    }
}
