package application;

import java.util.Comparator;

public class MemoryUserApplicationComparator implements Comparator<MemoryUserApplication> {
    @Override
    public int compare(MemoryUserApplication app1, MemoryUserApplication app2) {
        // 按 level 属性从小到大排序
        return Integer.compare(app1.getLevel(), app2.getLevel());
    }
}