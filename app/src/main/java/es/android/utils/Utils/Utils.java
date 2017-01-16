package es.android.utils.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import es.android.utils.R;
import es.android.utils.adapters.SelectDirectoryAdapter;
import es.android.utils.domain.Directory;

/**
 * Created by miguelconde on 13/01/17.
 */

public class Utils {

    public static List<File> findDirectories(File[] fileList){
        List<File> list = new ArrayList<>();
        for(File f : fileList){
            if(f.isDirectory()){
                list.add(f);
            }
        }
        return list;
    }
    
}
