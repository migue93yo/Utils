package es.android.utils.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.android.utils.R;
import es.android.utils.domain.Directory;

/**
 * Created by miguelconde on 11/01/17.
 */

public class SelectDirectoryAdapter extends RecyclerView.Adapter<SelectDirectoryAdapter.SelectDirectoryViewHolder>{

    private OnItemClickListener mItemClickListener;
    private List<File> directoriesList;

    public SelectDirectoryAdapter(List<File> directoriesList){
        this.directoriesList = directoriesList;
    }

    @Override
    public SelectDirectoryAdapter.SelectDirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_select_directory, parent, false);

        return new SelectDirectoryAdapter.SelectDirectoryViewHolder(view);
    }

    public static class SelectDirectoryViewHolder extends RecyclerView.ViewHolder {

        private TextView rute;

        public SelectDirectoryViewHolder(View itemView) {
            super(itemView);
            rute = (TextView) itemView.findViewById(R.id.select_directory_rute);
        }
    }

    @Override
    public void onBindViewHolder(SelectDirectoryAdapter.SelectDirectoryViewHolder holder, final int position) {
        File file = directoriesList.get(position);
        holder.rute.setText(file.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(v, position);
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return directoriesList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}