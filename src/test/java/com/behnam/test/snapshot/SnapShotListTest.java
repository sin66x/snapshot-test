package com.behnam.test.snapshot;

import com.behnam.test.snapshot.exceptions.InvalidInputException;
import com.behnam.test.snapshot.impl.SynchronizedUnbalancedSnapshotList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SnapShotListTest {

    private static final Logger logger = LogManager.getLogger(SnapShotListTest.class);

    public SnapShotListTest(){
        setup();
    }

    SnapshotList<String> snapshots;

    private void setup() {
        snapshots = new SynchronizedUnbalancedSnapshotList<>();
    }

    public static void main(String ...args){
        SnapShotListTest test = new SnapShotListTest();
        test.getAtVersion_shouldThrowInvalidVersion();
        test.snapshot_shouldIncreaseVersion();
        test.getAtVersion_shouldHaveAnObject();
        test.dropPriorSnapshots_shouldRemoveFirst();
        test.insertElementAt_shouldThrowsException();
        test.remove_shouldThrowsException();
        test.removeAll_shouldThrowsException();
        test.dropPriorSnapshots_shouldThrowException();
    }

    private void dropPriorSnapshots_shouldThrowException() {
        try {
            snapshots.dropPriorSnapshots(10);
        }catch (InvalidInputException e){
            logger.info("PASSED");
            return;
        }
        logger.error("FAILED");
    }

    private void removeAll_shouldThrowsException() {
        try {
            snapshots.removeAll(null);
        }catch (UnsupportedOperationException e){
            logger.info("PASSED");
            return;
        }
        logger.error("FAILED");
    }

    private void remove_shouldThrowsException() {
        try {
            snapshots.remove(0);
        }catch (UnsupportedOperationException e){
            logger.info("PASSED");
            return;
        }
        logger.error("FAILED");
    }

    private void insertElementAt_shouldThrowsException() {
        try {
            snapshots.add(1, "Sample Object In The List");
        }catch (UnsupportedOperationException e){
            logger.info("PASSED");
            return;
        }
        logger.error("FAILED");
    }

    private void dropPriorSnapshots_shouldRemoveFirst() {
        snapshots.dropPriorSnapshots(0);
        try {
            snapshots.getAtVersion(0, 0);
        } catch (InvalidInputException e){
            logger.info("PASSED");
            return;
        }
        logger.error("FAILED");
    }

    private void getAtVersion_shouldHaveAnObject() {
        try{
            snapshots.add("Sample Object In The List");
            snapshots.snapshot();
            snapshots.getAtVersion(0,1);
        } catch (InvalidInputException e){
            logger.error("FAILED");
            return;
        }
        logger.info("PASSED");
    }

    private void snapshot_shouldIncreaseVersion() {
        int currentVersion = snapshots.version();
        if(snapshots.snapshot()==currentVersion+1){
            logger.info("PASSED");
        }
        else{
            logger.error("FAILED");
        }
    }

    public void getAtVersion_shouldThrowInvalidVersion(){
        try{
            snapshots.getAtVersion(1,1);
            logger.error("FAILED");
        } catch (InvalidInputException e){
            logger.info("PASSED");
        }
    }

}
