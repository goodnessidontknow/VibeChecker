package com.example.vibechecker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VibeCheckLab {

    private static VibeCheckLab sVibeCheckLab;

    private List<VibeCheck> mVibeChecks;
    private List<UUID> mId;


    public static VibeCheckLab get(Context context){
        if (sVibeCheckLab == null){
            sVibeCheckLab = new VibeCheckLab(context);
        }
        return sVibeCheckLab;
    }

    private VibeCheckLab(Context context){
        mVibeChecks = new ArrayList<>();
        mId = new ArrayList<>();
    }

    public List<VibeCheck> getVibeChecks(){
        return mVibeChecks;
    }


    public void addVibeCheck(VibeCheck vibeCheck) {
        mVibeChecks.add(vibeCheck);
        mId.add(vibeCheck.getId());
    }

}
