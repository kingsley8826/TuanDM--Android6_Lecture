package com.example.tuan_fpt.lab5_recyclerview.eventbus;

/**
 * Created by Tuan-FPT on 20/12/2016.
 */

public class OnUpdateRecyclerView {
    private boolean completedView;

    public OnUpdateRecyclerView(boolean completedView) {
        this.completedView = completedView;
    }

    public boolean isCompletedView() {
        return completedView;
    }
}
