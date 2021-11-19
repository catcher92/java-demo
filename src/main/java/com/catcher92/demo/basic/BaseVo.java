package com.catcher92.demo.basic;

public interface BaseVo {

    interface Save {}

    interface Update {}

    interface Select {}

    interface Delete {}

    default String getPoPackage() {
        return "retech.whize.server.dao.entity";
    }

    default Class<?> getPoClass() {
        final String poClassName =
                String.format(
                        "%s.%s",
                        getPoPackage(),
                        getClass()
                                .getSimpleName()
                                .substring(0, getClass().getSimpleName().length() - 2));
        try {
            return Class.forName(poClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
