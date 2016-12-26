package com.example.michael.testperformance;

import android.content.Context;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by michael on 16-12-23.
 */

public class AppBlockCanaryContext extends BlockCanaryContext {
    @Override
    public String provideQualifier() {
//        return super.provideQualifier();
        return "unknown";
    }

    @Override
    public String provideUid() {
//        return super.provideUid();
        return "uid";
    }

    @Override
    public String provideNetworkType() {
//        return super.provideNetworkType();
        return "unknown";
    }

    @Override
    public int provideMonitorDuration() {
//        return super.provideMonitorDuration();
        return -1;
    }

//    private static final int BLOCK_THRESHOLD = 500;
    private static final int BLOCK_THRESHOLD = 2000;

    @Override
    public int provideBlockThreshold() {
//        return super.provideBlockThreshold();
        return BLOCK_THRESHOLD;
    }

    public int provideDumpInterval() {
        return provideBlockThreshold();
    }

    @Override
    public String providePath() {
//        return super.providePath();
        return "/blockcanary/";
    }

    @Override
    public boolean displayNotification() {
//        return super.displayNotification();
        return true;
    }

    @Override
    public boolean zip(File[] src, File dest) {
//        return super.zip(src, dest);
        return false;
    }

    @Override
    public void upload(File zippedFile) {
//        super.upload(zippedFile);
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> concernPackages() {
//        return super.concernPackages();
        return null;
    }

    @Override
    public boolean filterNonConcernStack() {
//        return super.filterNonConcernStack();
        return false;
    }

    @Override
    public List<String> provideWhiteList() {
//        return super.provideWhiteList();
        LinkedList<String> whiteList = new LinkedList<>();
        whiteList.add("org.chromium");
        return whiteList;
    }

    @Override
    public boolean deleteFilesInWhiteList() {
//        return super.deleteFilesInWhiteList();
        return true;
    }

    @Override
    public void onBlock(Context context, BlockInfo blockInfo) {
//        super.onBlock(context, blockInfo);

    }

    /*
    @Override
    public int getConfigBlockThreshold() {
//        return super.getConfigBlockThreshold();
        return 500;
    }

    @Override
    public boolean isNeedDisplay() {
//        return super.isNeedDisplay();
        return true;
    }

    @Override
    public String getLogPath() {
//        return super.getLogPath();
        return "/blockcanary/performance";
    }
    */
}
