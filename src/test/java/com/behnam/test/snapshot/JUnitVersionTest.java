package com.behnam.test.snapshot;

import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnitVersionTest {

    private static SynchronizedUnbalancedSnapshotList<String> snapshotList;
    @Before
    public void setup() {
        snapshotList = new SynchronizedUnbalancedSnapshotList<>();
    }

    @Test
    public void version_shouldStartAtZero(){
        Assert.assertEquals(snapshotList.version(),0);
    }

    @Test
    public void version_shouldIncreaseVersion() {
        int version = snapshotList.snapshot();
        Assert.assertEquals(snapshotList.version(),1);
    }
}
