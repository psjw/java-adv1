package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
    public static void main(String[] args) {
        //순서보장 안함
        Set<Integer> copySet = new CopyOnWriteArraySet<>();
        copySet.add(2);
        copySet.add(1);
        copySet.add(3);
        System.out.println("copySet = " + copySet);

        //순서보장
        ConcurrentSkipListSet<Object> skipSet = new ConcurrentSkipListSet<>();
        skipSet.add(2);
        skipSet.add(1);
        skipSet.add(3);
        System.out.println("skipSet = " + skipSet);
    }
}
