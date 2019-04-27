package com.catcher92.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by catcher92 on 2017/3/12.
 */
public class ZookeeperDemo {
    public static final String HOSTANDPORT = "127.0.0.1:2181";
    public static final int TIMEOUT = 300;
    public static final String NODE = "/test";

    public static void main(String[] args) {
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(HOSTANDPORT,TIMEOUT,new ZookeeperWatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Stat exists = zooKeeper.exists(NODE, true);
            if (exists == null) {
                zooKeeper.create(NODE, ("测试："+System.currentTimeMillis()).getBytes(Charset.forName("utf-8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            System.out.println("获取节点数据");
            System.out.println(new String(zooKeeper.getData(NODE,true,null)));
            System.out.println("修改节点数据");
            zooKeeper.setData(NODE,("测试："+System.currentTimeMillis()).getBytes(Charset.forName("UTF-8")),-1);
            System.out.println("获取节点数据");
            System.out.println(new String(zooKeeper.getData(NODE,true,null)));
            System.out.println("删除节点");
            zooKeeper.delete(NODE,-1);
            zooKeeper.close();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ZookeeperWatcher implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            System.out.println(event);
        }
    }
}
