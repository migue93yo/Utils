package es.android.utils.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import es.android.utils.Popups;
import es.android.utils.R;
import es.android.utils.domain.Directory;
import es.android.utils.model.DirectoriesTable;

/**
 * Created by miguelconde on 11/01/17.
 */

public class DirectoryAdapter
        extends RecyclerView.Adapter<DirectoryAdapter.ChooseDirectoryViewHolder> {

    private Activity activity;
    private List<Directory> directoriesList;

    public DirectoryAdapter(Activity activity, List<Directory> directoriesList) {
        this.activity = activity;
        this.directoriesList = directoriesList;
    }

    @Override
    public ChooseDirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_directory, parent, false);

        return new ChooseDirectoryViewHolder(view);
    }

    public static class ChooseDirectoryViewHolder extends RecyclerView.ViewHolder {

        private EditText ruteOrigin;
        private EditText ruteDestiny;
        private Switch active;
        private Button[] daysOfWeek = new Button[7];

        private RelativeLayout hiddenLayout;
        private ImageView desploy;
        private TextView accept;
        private TextView cancel;

        public ChooseDirectoryViewHolder(final View itemView) {
            super(itemView);

            ruteOrigin = (EditText) itemView.findViewById(R.id.directories_adapter_rute_origin);
            ruteDestiny = (EditText) itemView.findViewById(R.id.directories_adapter_rute_end);
            active = (Switch) itemView.findViewById(R.id.directories_adapter_active);
            desploy = (ImageView) itemView.findViewById(R.id.directories_adapter_desploy);
            accept = (TextView) itemView.findViewById(R.id.directories_adapter_accept);
            cancel = (TextView) itemView.findViewById(R.id.directories_adapter_cancel);

            daysOfWeek[0] = (Button) itemView.findViewById(R.id.directories_adapter_monday);
            daysOfWeek[1] = (Button) itemView.findViewById(R.id.directories_adapter_tuesday);
            daysOfWeek[2] = (Button) itemView.findViewById(R.id.directories_adapter_wednesday);
            daysOfWeek[3] = (Button) itemView.findViewById(R.id.directories_adapter_thursday);
            daysOfWeek[4] = (Button) itemView.findViewById(R.id.directories_adapter_friday);
            daysOfWeek[5] = (Button) itemView.findViewById(R.id.directories_adapter_saturday);
            daysOfWeek[6] = (Button) itemView.findViewById(R.id.directories_adapter_sunday);

            hiddenLayout = (RelativeLayout) itemView.findViewById(R.id.directories_adapter_hidden_menu);
        }
    }

    @Override
    public void onBindViewHolder(final ChooseDirectoryViewHolder holder, final int position) {
        final Directory dir = directoriesList.get(position);

        holder.ruteOrigin.setText(dir.getRuteOrigin());
        holder.ruteDestiny.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == 1){
                    Popups popups = new Popups();
                    popups.showSelectDirectory(activity, holder.ruteDestiny);
                }
                return true;
            }
        });
        holder.active.setChecked(dir.isActive());
        holder.desploy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDesployButtons(holder);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toUndesployButtons(holder);
            }
        });
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!holder.ruteOrigin.getText().toString().isEmpty()) {
                    dir.setRuteOrigin(holder.ruteOrigin.getText().toString());
                    dir.setRuteDestiny(holder.ruteDestiny.getText().toString());
                    dir.setActive(holder.active.isChecked());

                    DirectoriesTable directoriesTable = new DirectoriesTable(activity);
                    boolean b = directoriesTable.updateDirectory(dir);
                    Toast.makeText(activity, String.valueOf(b), Toast.LENGTH_SHORT).show();
                    if (b) {
                        List<Directory> listaDocumentos = directoriesTable.getAllDirectory();
                        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.documents_recycler);
                        recyclerView.setAdapter(new DirectoryAdapter(activity, listaDocumentos));
                        toUndesployButtons(holder);
                    }
                } else {
                    Toast.makeText(activity, "Introduce una ruta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        for(int i = 0 ; i < 7 ; i++){
            if(dir.getDaysOfWeek().getDayOfWeek(i)){
                holder.daysOfWeek[i].setBackgroundResource(R.drawable.days_of_week_pulsed);;
            }
            OnButtonClickEvent onButtonClickEvent = new OnButtonClickEvent(dir, i);
            holder.daysOfWeek[i].setOnClickListener(onButtonClickEvent);
        }
    }

    @Override
    public int getItemCount() {
        return directoriesList.size();
    }

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

    private void toDesployButtons(final ChooseDirectoryViewHolder holder){
        holder.desploy.setVisibility(View.GONE);
        holder.ruteOrigin.setEnabled(true);
        holder.hiddenLayout.setVisibility(View.VISIBLE);
        holder.ruteOrigin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == 1){
                    Popups popups = new Popups();
                    popups.showSelectDirectory(activity, holder.ruteOrigin);
                }
                return true;
            }
        });
    }

    private void toUndesployButtons(final ChooseDirectoryViewHolder holder){
        holder.ruteOrigin.setEnabled(false);
        holder.hiddenLayout.setVisibility(View.GONE);
        holder.ruteOrigin.setOnTouchListener(null);
        holder.desploy.setVisibility(View.VISIBLE);
    }

}