package com.behnam.test.snapshot.impl;


import com.behnam.test.snapshot.SnapshotList;
import com.behnam.test.snapshot.exceptions.InvalidInputException;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Vector;

public class SynchronizedUnbalancedSnapshotList<E> extends Vector<E> implements SnapshotList<E> {
    int version;
    Hashtable<Integer, Vector<E>> snapshots = new Hashtable<>();

    @Override
    public synchronized void dropPriorSnapshots(int version) {
        if (version > this.version)
            throw new InvalidInputException("Not a valid version:" + version);
        for (int versionCounter = 0; versionCounter <= version; versionCounter++) {
            if (snapshots.get(version) != null) {
                snapshots.remove(version);
            }
        }
    }

    @Override
    public E getAtVersion(int index, int version) {
        if (snapshots.get(version) == null)
            throw new InvalidInputException("Not a valid version:" + version);
        if (snapshots.get(version).size() - 1 < index)
            throw new InvalidInputException("Not a valid index(" + index + ") in version " + version);
        return snapshots.get(version).get(index);
    }

    @Override
    public synchronized int snapshot() {
        snapshots.put(version, new Vector<>(this));
        version++;
        return version;
    }

    @Override
    public int version() {
        return version;
    }

    /**
     *
     * According to the SnapShotList Interface guides, removing elements is not permitted!
     * @param index no matter what you pass to it, This function won't work
     * @return This function won't work
     * @throws UnsupportedOperationException
     */
    @Override
    public E remove(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("SnapshotList may not remove existing elements and it can only grow!");
    }

    /**
     *
     * According to the SnapShotList Interface guides, removing elements is not permitted!
     * @param c no matter what you pass to it, This function won't work
     * @return This function won't work
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("SnapshotList may not remove existing elements and it can only grow!");
    }

    /**
     *
     * According to the SnapShotList Interface guides, it can only grow by appending elements, not inserting
     * @param obj no matter what you pass to it, This function won't work
     * @param index This function won't work
     */
    @Override
    public synchronized void insertElementAt(E obj, int index) {
        throw new UnsupportedOperationException("SnapshotList can only grow by appending elements, not inserting");
    }
}
