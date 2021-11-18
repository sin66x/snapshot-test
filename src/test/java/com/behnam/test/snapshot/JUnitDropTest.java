package com.behnam.test.snapshot;

import com.behnam.test.snapshot.exceptions.InvalidInputException;
import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnitDropTest {

    private static SynchronizedUnbalancedSnapshotList<String> snapshotList;

    @Before
    public void setup() {
        snapshotList = new SynchronizedUnbalancedSnapshotList<>();
        snapshotList.add("First Element Added In Ver.0");
        snapshotList.add("Second Element Added In Ver.0");
        int version = snapshotList.snapshot();
        snapshotList.add("Third Element Added In Ver.1");
        snapshotList.add("Fourth Element Added In Ver.1");
        snapshotList.add(null);
        snapshotList.snapshot();
    }

    @Test(expected = InvalidInputException.class)
    public void drop_shouldNotHaveDeletedVersion() {
        snapshotList.dropPriorSnapshots(0);
        snapshotList.getAtVersion(0,0);
    }
}
