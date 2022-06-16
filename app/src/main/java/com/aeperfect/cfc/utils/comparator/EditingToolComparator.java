package com.aeperfect.cfc.utils.comparator;

import com.aeperfect.cfc.models.editor.EditingTool;

import java.util.Comparator;

public class EditingToolComparator implements Comparator<EditingTool> {
    @Override
    public int compare(EditingTool tool1, EditingTool tool2) {
        return tool1.getId() - tool2.getId();
    }
}