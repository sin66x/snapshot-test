# SnapshotList
An implementation of `SynchronizedUnbalancedSnapshotList`; A thread-safe unbalanced List 

How to compile
===
The code could be built by maven, you can run `mvn package` and find the `.jar` file in `./target`.

How to use
===
After adding the jar file into your project dependencies you can use it as a normal implementation of `List`.
```
// Example:
SnapshotList<String> snapshotList = SynchronizedUnbalancedSnapshotList<>();
snapshotList.add("A");
snapshotList.add("V");
snapshotList.snapshot(); // Saving current data into version 0 and changing version to 1
snapshotList.getAtVersion(1,0); // Returns index-1 of version-0 (In this case: "V")
snapshotList.dropPriorSnapshots(0) //Remove snap shot of version-0
```

#Pros and Cons
+ This implementation is thread-safe
+ It's well tested

- There are synchronized functions, so it may be slow a bit
- It's dependent on `java.util.Vector` and some functions are not customized 