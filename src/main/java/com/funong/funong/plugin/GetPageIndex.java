package com.funong.funong.plugin;

import com.funong.funong.backPojo.BackPageIndex;

public class GetPageIndex {
    public BackPageIndex getPageIndex(int page){
        BackPageIndex backPageIndex =new BackPageIndex();
        int start = (page-1)*10;
        int end = (page)*10;
        backPageIndex.setStart(start);
        backPageIndex.setEnd(end);
        return backPageIndex;

    }
}
