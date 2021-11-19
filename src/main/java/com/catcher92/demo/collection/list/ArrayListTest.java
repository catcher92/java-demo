package com.catcher92.demo.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayListTest {

    public static void main(String[] args) {
        final ArrayList<Integer> permissionIds = new ArrayList<>();
        permissionIds.add(2);
        permissionIds.add(2);
        permissionIds.add(3);
        permissionIds.add(4);
        List<Integer> distinctPermissionIds = permissionIds.stream().distinct().collect(Collectors.toList());

        final List<Integer> existsPermissionIds = new ArrayList<>();
        existsPermissionIds.add(1);
        existsPermissionIds.add(2);
        existsPermissionIds.add(3);

        final ArrayList<Integer> savePermissionIds = new ArrayList<>(distinctPermissionIds);
        savePermissionIds.removeAll(existsPermissionIds);

        final ArrayList<Integer> deletePermissionIds = new ArrayList<>(existsPermissionIds);
        deletePermissionIds.removeAll(distinctPermissionIds);

        System.out.println("distinctPermissionIds:" + distinctPermissionIds);
        System.out.println("existsPermissionIds:" + existsPermissionIds);
        System.out.println("savePermissionIds:" + savePermissionIds);
        System.out.println("deletePermissionIds:" + deletePermissionIds);
    }

}
