package com.xyz.geekfest;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by naman on 22/03/15.
 */
public class RecipeDialog extends DialogFragment {


    public RecipeDialog(){

    }
    TextView title,recipe;
    String title1,recipe1;

    public static RecipeDialog newInstance(String title,String recipe) {
       RecipeDialog f = new RecipeDialog();
        Bundle b = new Bundle();
        b.putString("title",title);
        b.putString("recipe",recipe);
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        title=(TextView) v.findViewById(R.id.title);
        recipe=(TextView) v.findViewById(R.id.recipe);
        title.setText(getArguments().getString("title"));
        recipe.setText(getArguments().getString("recipe"));



        return v;

    }
}
