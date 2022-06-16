package com.aeperfect.cfc.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aeperfect.cfc.R;
import com.aeperfect.cfc.interfaces.IToolClickListener;
import com.aeperfect.cfc.models.editor.EditingTool;
import com.aeperfect.cfc.utils.GraphicUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableAdapterTools extends BaseExpandableListAdapter {

    private final ArrayList<String> titles;
    private final HashMap<String, List<EditingTool>> items;
    private IToolClickListener toolClickListener;

    public ExpandableAdapterTools(HashMap<String, List<EditingTool>> items) {
        this.items = items;
        titles = new ArrayList<>(items.keySet());
    }

    public void setToolClickListener(IToolClickListener toolClickListener) {
        this.toolClickListener = toolClickListener;
    }

    @Override
    public int getGroupCount() {
        return titles.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return items.get(titles.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return titles.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {

        return items.get(titles.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {

            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.expandable_list_parent, viewGroup, false);
        }

        String groupTitle = titles.get(i);

        ImageView imageViewParent = view.findViewById(R.id.image_view_parent);
        imageViewParent.setImageResource(R.drawable.ic_more_tools);

        TextView textViewParent = view.findViewById(R.id.text_view_parent);
        textViewParent.setText(groupTitle);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        if (view == null) {

            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.expandable_list_child, viewGroup, false);
        }

        EditingTool editingTool = (EditingTool) getChild(i, i1);

        ImageView imageViewChild = view.findViewById(R.id.image_view_child);
        imageViewChild.setImageDrawable(GraphicUtils.getToolLogo(editingTool.getId()));

        TextView textViewChild = view.findViewById(R.id.text_view_child);
        textViewChild.setText(editingTool.getTitle());

        view.setOnClickListener(view1 -> {

            if(toolClickListener != null){

                toolClickListener.onEditingToolClick(editingTool);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}