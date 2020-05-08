package com.funong.funong.plugin;

import com.funong.funong.backPojo.BackPageIndex;
import com.funong.funong.backPojo.BackTypeIndex;

public class GetPageIndex {
    public BackPageIndex getPageIndex(int page){
        BackPageIndex backPageIndex =new BackPageIndex();
        int start = (page-1)*10;
        int end = (page)*10;
        backPageIndex.setStart(start);
        backPageIndex.setEnd(end);
        return backPageIndex;

    }
    public BackTypeIndex getTypeIndex(int page){
        BackTypeIndex backTypeIndex = new BackTypeIndex();
        int start = (page-1)*10;
        int end = (page)*10;
        backTypeIndex.setStart(start);
        backTypeIndex.setEnd(end);
        return backTypeIndex;
    }
}
