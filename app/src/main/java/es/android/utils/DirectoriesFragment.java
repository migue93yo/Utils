package es.android.utils;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.android.utils.adapters.DirectoryAdapter;
import es.android.utils.domain.Directory;
import es.android.utils.model.DirectoriesTable;

public class DirectoriesFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_directories, container, false);

        //Localizamos nuestras copias de seguridad y las insertamos en el RecyclerView
        DirectoriesTable directoriesTable = new DirectoriesTable(getContext());
        List<Directory> directoriesList = directoriesTable.getAllDirectory();

        recyclerView = (RecyclerView) view.findViewById(R.id.documents_recycler);
        recyclerView.setAdapter(new DirectoryAdapter(getActivity(), directoriesList));

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        //Le damos funcionalidad al botón flotante
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.documents_floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Popups popups = new Popups();
                popups.showNewDirectory(getActivity());
            }
        });

        return view;
    }
}
