package com.behnam.test.snapshot;

import com.behnam.test.snapshot.exceptions.InvalidInputException;
import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnitGetAtVersionTest {

    private static SynchronizedUnbalancedSnapshotList<String> snapshotList;
    @Before
    public void setup() {
        snapshotList = new SynchronizedUnbalancedSnapshotList<>();
    }

    @Test
    public void getAtVersion_shouldReturnElementCorrectly() {
        snapshotList.add("First Element Added In Ver.0");
        snapshotList.add("Second Element Added In Ver.0");
        int version = snapshotList.snapshot();
        snapshotList.add("Third Element Added In Ver.1");
        snapshotList.add("Fourth Element Added In Ver.1");
        snapshotList.add(null);
        snapshotList.snapshot();
        Assert.assertEquals(snapshotList.getAtVersion(0,0),"First Element Added In Ver.0");
        Assert.assertEquals(snapshotList.getAtVersion(0,1),"First Element Added In Ver.0");
        Assert.assertEquals(snapshotList.getAtVersion(1,0),"Second Element Added In Ver.0");
        Assert.assertEquals(snapshotList.getAtVersion(1,1),"Second Element Added In Ver.0");
        Assert.assertEquals(snapshotList.getAtVersion(2,1),"Third Element Added In Ver.1");
        Assert.assertEquals(snapshotList.getAtVersion(3,1),"Fourth Element Added In Ver.1");
        Assert.assertEquals(snapshotList.getAtVersion(4,1),null);
    }

    @Test(expected = InvalidInputException.class)
    public void getAtVersion_shouldThrowInvalidInputException_Version() {
        snapshotList.getAtVersion(1,1);
    }

    @Test(expected = InvalidInputException.class)
    public void getAtVersion_shouldThrowInvalidInputException_Index() {
        snapshotList.add(null);
        snapshotList.snapshot();
        snapshotList.getAtVersion(1,0);
    }
}
