package com.cosfund.library.util;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * 数据库Config
 * <p/>
 * DatabaseConfigUtl写配置文件以避免使用注解处理在运行时这是非常缓慢的Android。获得显著的性能提升。
 * <p/>
 * 写入配置文件 /res/raw/ by default. More info at: http://ormlite.com/docs/table-config
 */
public class DataBaseConfigUtils extends OrmLiteConfigUtil {

    public static void main(String[] args) throws SQLException, IOException {
        writeConfigFile("ormlite_config.txt");
    }
}
