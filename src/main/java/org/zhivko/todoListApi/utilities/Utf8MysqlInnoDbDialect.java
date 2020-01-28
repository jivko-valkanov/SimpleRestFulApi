package org.zhivko.todoListApi.utilities;

import org.hibernate.dialect.MySQL8Dialect;

public class Utf8MysqlInnoDbDialect extends MySQL8Dialect {

    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
