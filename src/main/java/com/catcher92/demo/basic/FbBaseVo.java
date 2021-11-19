package com.catcher92.demo.basic;

public interface FbBaseVo extends BaseVo {

    default String getPoPackage() {
        return "retech.flashbox.server.dao.mybatis.entity";
    }
}
