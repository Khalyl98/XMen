package com.example.xmen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xmen.databinding.ActivityMainBinding;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding ui;
    private XMenAdapter adapter;
    private XMenApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mise en place de l'interface
        super.onCreate(savedInstanceState);

        Log.d("MainActivity", "onCreate called. Instance: " + this);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        application = (XMenApplication) getApplication();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ui.recycler.setLayoutManager(layoutManager);
        adapter = new XMenAdapter(application.getListe());
        adapter.filter("","");
        ui.recycler.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(ui.recycler.getContext(), layoutManager.getOrientation());
        ui.recycler.addItemDecoration(dividerItemDecoration);
        adapter.setOnItemClickListener(new XMenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // récupérer le X-Men concerné
                XMen xmen = application.getListe().get(position);
                    // changer l'image du X-Men
                xmen.setIdImage(R.drawable.undef);
                    // signaler à l'adaptateur que l'élément a changé
                adapter.notifyItemChanged(position);

            }
        });
        ui.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });
    }
    private void performSearch() {
       String query = ui.editTextSearch.getText().toString();
       String selectedFilter=ui.spinnerSearchBy.getSelectedItem().toString();
       adapter.filter(query,selectedFilter);
        Toast.makeText(this,selectedFilter,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,query,Toast.LENGTH_SHORT).show();
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.reinit) {
            application.initListe();
            adapter.notifyDataSetChanged();
            // FIXME signaler le changement
            return true;
        } else if (item.getItemId() == R.id.create) {
            onEdit(-1);
            // TODO voir exercice suivant
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * lancer l'édition sur un XMen indiqué par position, -1 si création
     *
    */
    private void onEdit(int filteredPosition ) {
        int originalPosition = adapter.getOriginalPosition(filteredPosition);
        Intent intent = new Intent(MainActivity.this, EditActivity.class);

        intent.putExtra("position", originalPosition);

        startActivityForResult(intent, 0);
    }

    /**
     * Cette méthode est appelée quand on revient dans cette activité après avoir
     * appelé une autre par startActivityForResult(intent, requestCode).
     *
     * @param requestCode : celui qui avait été passé à startActivityForResult
     * @param resultCode  : RESULT_OK (l'uti a validé l'autre activité) ou
     *                    RESULT_CANCELED (l'uti a fait back ou annuler), voir aussi setResult(resultCode)
     * @param intent        : un intent qui serait fourni par l'activité appelée
     */
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_CANCELED) return;
// TODO prévenir l'adaptateur que la liste a changé
        if(requestCode == 0){
            adapter.filter("","");
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
// récupérer la position de l'élément concerné par le menu
        int position = item.getOrder();
// selon le bouton de menu
        switch (item.getItemId()) {
            case XMenViewHolder.MENU_EDIT:
                onEdit(position);
                return true;
            case XMenViewHolder.MENU_DELETE:
                onDelete(position);
// TODO onDelete(position);
                return true;

        }
        return super.onContextItemSelected(item);
    }

    private void onReallyDelete(int position) {
        application.getListe().remove(position);
        adapter.filter("","");
        adapter.notifyDataSetChanged();
// TODO signaler à l'adaptateur que cette position a été supprimée
    }

    private void onDelete(int filteredPosition) {
        int originalPosition = adapter.getOriginalPosition(filteredPosition);
        XMen xmen = application.getListe().get(originalPosition);
        new AlertDialog.Builder(this)
                .setTitle(xmen.getNom())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Vous confirmez la suppression ?")
                .setPositiveButton(android.R.string.ok,
                        (dialog, idbtn) -> onReallyDelete(originalPosition))
                .setNegativeButton(android.R.string.cancel, null).show();

    }

}