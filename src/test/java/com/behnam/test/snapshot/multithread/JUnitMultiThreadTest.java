package com.behnam.test.snapshot.multithread;

import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JUnitMultiThreadTest {

    private final int THREAD_COUNT=10;
    private final int OBJECTS_IN_VERSION=20;
    private final int SNAPSHOT_COUNTS=10;


    @Test
    public void addParallel() throws InterruptedException {
        SynchronizedUnbalancedSnapshotList<String> snapshotList = new SynchronizedUnbalancedSnapshotList<>();

        List<AddingThread> addingThreads = new ArrayList<>();
        for (int threadCounter = 0; threadCounter < THREAD_COUNT; threadCounter++) {
            addingThreads.add(new AddingThread(snapshotList,OBJECTS_IN_VERSION,SNAPSHOT_COUNTS));
        }

        for (int threadCounter = 0; threadCounter < THREAD_COUNT; threadCounter++) {
            addingThreads.get(threadCounter).start();
        }

        for (int threadCounter = 0; threadCounter < THREAD_COUNT; threadCounter++) {
            addingThreads.get(threadCounter).join();
        }

        Assert.assertEquals(snapshotList.size(),OBJECTS_IN_VERSION*THREAD_COUNT*SNAPSHOT_COUNTS);

    }

}
