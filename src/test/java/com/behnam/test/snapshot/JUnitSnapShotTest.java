package com.behnam.test.snapshot;

import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnitSnapShotTest {

    private static SynchronizedUnbalancedSnapshotList<String> snapshotList;
    @Before
    public void setup() {
        snapshotList = new SynchronizedUnbalancedSnapshotList<>();
    }

    @Test
    public void snapshot_shouldIncreaseVersion() {
        snapshotList.add("First Element Added In Ver.0");
        snapshotList.add("Second Element Added In Ver.0");
        int version = snapshotList.snapshot();
        Assert.assertEquals(version,1);
    }
}
