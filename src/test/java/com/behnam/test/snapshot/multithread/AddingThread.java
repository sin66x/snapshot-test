package com.behnam.test.snapshot.multithread;

import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;

public class AddingThread extends Thread {
    private int objectsInVersion = 10;
    private int snapshotCounts = 10;

    SynchronizedUnbalancedSnapshotList<String> snapshotList;

    public AddingThread(SynchronizedUnbalancedSnapshotList snapshotList,
                        int objectsInVersion,
                        int snapshotCounts) {
        this.snapshotList = snapshotList;
        this.objectsInVersion = objectsInVersion;
        this.snapshotCounts = snapshotCounts;
    }

    public void run() {
        try {
            for (int versionCounter = 0; versionCounter < snapshotCounts; versionCounter++) {
                for (int i = 0; i < objectsInVersion; i++) {
                    snapshotList.add("Object " + i + " of version" + versionCounter);
                }
                snapshotList.snapshot();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
