package es.android.utils.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import es.android.utils.R;
import es.android.utils.Utils.Utils;

/**
 * Created by miguelconde on 11/01/17.
 */

public class SelectDirectoryAdapter extends RecyclerView.Adapter<SelectDirectoryAdapter.SelectDirectoryViewHolder>{

    private OnItemClickListener mItemClickListener;
    private List<File> directoriesList;
    private File parent;

    public SelectDirectoryAdapter(List<File> directoriesList){
        this.directoriesList = directoriesList;
        if(directoriesList != null || !directoriesList.isEmpty())
            parent = new File(directoriesList.get(1).getParent());
        else
            parent = null;
    }

    @Override
    public SelectDirectoryAdapter.SelectDirectoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_select_directory, parent, false);

        return new SelectDirectoryAdapter.SelectDirectoryViewHolder(view);
    }

    public static class SelectDirectoryViewHolder extends RecyclerView.ViewHolder {

        private TextView rute;
        private ImageView leftIcon;
        private ImageView rightIcon;

        public SelectDirectoryViewHolder(View itemView) {
            super(itemView);
            rute = (TextView) itemView.findViewById(R.id.select_directory_rute);
            leftIcon = (ImageView) itemView.findViewById(R.id.select_directory_left_icon);
            rightIcon = (ImageView) itemView.findViewById(R.id.select_directory_right_icon);
        }
    }

    @Override
    public void onBindViewHolder(SelectDirectoryAdapter.SelectDirectoryViewHolder holder, final int position) {
        final File file = directoriesList.get(position);
        if(file != null) {
            holder.rute.setText(file.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, position);
                    }
                }
            });

            if(!Utils.findDirectories(file).isEmpty()) {
                holder.rightIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemClickListener != null) {
                            mItemClickListener.onItemClick(v, position);
                        }
                    }
                });
            }else{
                holder.rightIcon.setVisibility(View.GONE);
            }
        }else{
            holder.rute.setText("Volver");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    directoriesList = Utils.findDirectories(parent);
                    parent = parent.getParentFile();

                }
            });
            holder.leftIcon.setBackgroundResource(R.drawable.arrow_return);
            holder.rightIcon.setVisibility(View.GONE);
        }
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