package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    private Entry<String> root;

    public CustomTree() {
        root = new Entry<>("0");
    }

    public static void main(String[] args) {
        List list = new CustomTree();

        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((CustomTree) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11")); //NullPointerException is normal!
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int count = 0;

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> tmp = queue.poll();
            if (tmp.leftChild != null) {
                queue.add(tmp.leftChild);
                count++;
            }
            if (tmp.rightChild != null) {
                queue.add(tmp.rightChild);
                count++;
            }
        }
        return count;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> tmp = queue.poll();
            if (!tmp.availableToAddLeftChildren && tmp.leftChild != null) queue.add(tmp.leftChild);
            else {
                tmp.leftChild = new Entry<>(s);
                tmp.checkChildren();
                return true;
            }
            if (!tmp.availableToAddRightChildren && tmp.rightChild != null) queue.add(tmp.rightChild);
            else {
                tmp.rightChild = new Entry<>(s);
                tmp.checkChildren();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> tmp = queue.poll();
            if (tmp.leftChild.elementName.equals(o)) {
                tmp.leftChild = null;
                //tmp.checkChildren();
                return true;
            } else queue.add(tmp.leftChild);
            if (tmp.rightChild.elementName.equals(o)) {
                tmp.rightChild = null;
                //tmp.checkChildren();
                return true;
            } else queue.add(tmp.rightChild);
        }
        return false;
    }

    public String getParent(String s) {
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Entry<String> tmp = queue.poll();
            if (tmp.leftChild.elementName.equals(s)) return tmp.elementName;
            else queue.add(tmp.leftChild);
            if (tmp.rightChild.elementName.equals(s)) return tmp.elementName;
            else queue.add(tmp.rightChild);
        }

        return null;
    }

    static class Entry<T> implements Serializable {
        private String elementName;
        private boolean availableToAddLeftChildren, availableToAddRightChildren;
        private Entry<T> leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        void checkChildren() {
            availableToAddLeftChildren = leftChild == null;
            availableToAddRightChildren = rightChild == null;
        }
    }
}