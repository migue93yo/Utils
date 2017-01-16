package es.android.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.android.utils.adapters.DirectoryAdapter;
import es.android.utils.adapters.SelectDirectoryAdapter;
import es.android.utils.domain.Directory;
import es.android.utils.model.DirectoriesTable;

/**
 * Created by miguelconde on 13/01/17.
 */

public class Popups {

    private List<File> filesList;

    /**
     * Este método se encargará de abrir un popup con el layout popup_new_directory.
     *
     * @param activity Actividad de la clase que llama al popup
     */
    public void showNewDirectory(final Activity activity){
        try {
            final View layout = activity.findViewById(R.id.documents_layout);
            final RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.documents_recycler);

            layout.setBackgroundResource(R.color.shadow);

            final Display display = activity.getWindowManager().getDefaultDisplay();
            int ancho = display.getWidth();
            int alto = display.getHeight();

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.popup_new_directory, (ViewGroup) activity.findViewById(R.id.new_document_adapter_layout));

            final Directory doc = new Directory();
            final EditText ruteOrigin = (EditText) view.findViewById(R.id.new_document_adapter_rute);
            final EditText ruteDestiny = (EditText) view.findViewById(R.id.new_document_adapter_rute_destiny);

            ruteOrigin.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == 1){
                        showSelectDirectory(activity, ruteOrigin);
                    }
                    return true;
                }
            });

            ruteDestiny.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if(event.getAction() == 1){
                        showSelectDirectory(activity, ruteDestiny);
                    }
                    return true;
                }
            });

            final Switch active = (Switch) view.findViewById(R.id.new_document_adapter_active);

            OnButtonClickEvent onButtonClickEvent = new OnButtonClickEvent(doc, 0);
            Button monday = (Button) view.findViewById(R.id.new_document_adapter_monday);
            monday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 1);
            Button tuesday = (Button) view.findViewById(R.id.new_document_adapter_tuesday);
            tuesday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 2);
            Button wednesday = (Button) view.findViewById(R.id.new_document_adapter_wednesday);
            wednesday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 3);
            Button thursday = (Button) view.findViewById(R.id.new_document_adapter_thursday);
            thursday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 4);
            Button friday = (Button) view.findViewById(R.id.new_document_adapter_friday);
            friday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 5);
            Button saturday = (Button) view.findViewById(R.id.new_document_adapter_saturday);
            saturday.setOnClickListener(onButtonClickEvent);

            onButtonClickEvent = new OnButtonClickEvent(doc, 6);
            Button sunday = (Button) view.findViewById(R.id.new_document_adapter_sunday);
            sunday.setOnClickListener(onButtonClickEvent);

            //Creamos y damos la funcionalidad al popup
            final PopupWindow pw = new PopupWindow(view, ancho, alto / 2, true);
            pw.showAtLocation(view, Gravity.CENTER, 0, 0);
            pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    layout.setBackgroundResource(R.color.background_material_dark);
                }
            });

            //Acción cuando pulsemos el botón aceptar
            TextView accept = (TextView) view.findViewById(R.id.new_document_adapter_accept);
            accept.setVisibility(View.VISIBLE);
            final PopupWindow popup = pw;
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ruteOrigin.getText().toString().isEmpty()) {
                        if (!ruteDestiny.getText().toString().isEmpty()) {

                            doc.setRuteOrigin(ruteOrigin.getText().toString());
                            doc.setActive(active.isChecked());

                            DirectoriesTable directoriesTable = new DirectoriesTable(activity);
                            if (directoriesTable.newDirectory(doc)) {
                                Toast.makeText(activity, "Se ha creado corréctamente", Toast.LENGTH_SHORT).show();
                                List<Directory> listaDocumentos = directoriesTable.getAllDirectory();
                                recyclerView.setAdapter(new DirectoryAdapter(activity, listaDocumentos));
                                popup.dismiss();
                            }
                        } else {
                            Toast.makeText(activity, "Introduce una ruta de destino", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(activity, "Introduce una ruta de origen", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //Acción cuando pulsemos el botón cancelar
            TextView cancel = (TextView) view.findViewById(R.id.new_document_adapter_cancel);
            cancel.setVisibility(View.VISIBLE);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, "Has pulsado el botón cancelar", Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Este método se encargará de abrir un popup con el layout popup_select_directory.
     *
     * @param activity Actividad de la clase que llama al popup
     * @param rute Ruta en la que se guardará el directorio seleccionado
     */
    public void showSelectDirectory(final Activity activity, final EditText rute) {

        try {
            Display display = activity.getWindowManager().getDefaultDisplay();
            int ancho = display.getWidth();
            int alto = display.getHeight();

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = inflater.inflate(R.layout.popup_select_directory, (ViewGroup) activity.findViewById(R.id.select_directory_layout));

            File internalRute = new File(Environment.getExternalStorageDirectory().getPath());
            internalRute.setReadable(true);

            filesList = findDirectories(internalRute.listFiles());

            final SelectDirectoryAdapter[] adapter = {new SelectDirectoryAdapter(filesList)};
            final RecyclerView[] selectDirectoryRecycler = {(RecyclerView) view.findViewById(R.id.select_directory_recycler)};
            selectDirectoryRecycler[0].setAdapter(adapter[0]);

            final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity.getApplicationContext());
            selectDirectoryRecycler[0].setLayoutManager(mLayoutManager);

            //Creamos y damos la funcionalidad al popup
            final PopupWindow pw = new PopupWindow(view, ancho - 50, alto/2, true);
            pw.showAtLocation(view, Gravity.CENTER, 0, 0);

            adapter[0].setOnItemClickListener(new SelectDirectoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    List<File> subListFiles = findDirectories(filesList.get(position).listFiles());
                    if(!subListFiles.isEmpty()){
                        filesList = subListFiles;
                        adapter[0] = new SelectDirectoryAdapter(filesList);
                        selectDirectoryRecycler[0].setAdapter(adapter[0]);
                        adapter[0].setOnItemClickListener(this);
                    }else{
                        rute.setText(filesList.get(position).getName());
                        pw.dismiss();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve los directorios de una lista de archivos pasados por parámetro
     * @param fileList Lista de archivos
     * @return Directorios encontrados
     */
    private List<File> findDirectories(File[] fileList){
        List<File> list = new ArrayList<>();
        for(File f : fileList){
            if(f.isDirectory()){
                list.add(f);
            }
        }
        return list;
    }

    /**
     * Listener para los botones de los días de la semana
     */
    private class OnButtonClickEvent implements View.OnClickListener {

        private int buttonPosition;
        private Directory dir;

        public OnButtonClickEvent(Directory dir, int buttonPosition){
            this.buttonPosition = buttonPosition;
            this.dir = dir;
        }

        @Override
        public void onClick(View v) {
            dir.getDaysOfWeek().setDayOfWeek(buttonPosition, !dir.getDaysOfWeek().getDayOfWeek(buttonPosition));
            if (dir.getDaysOfWeek().getDayOfWeek(buttonPosition)) {
                v.setBackgroundResource(R.drawable.days_of_week_pulsed);
            } else {
                v.setBackgroundResource(R.drawable.days_of_week);
            }
        }
    }

}
