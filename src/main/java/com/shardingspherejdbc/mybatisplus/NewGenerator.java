package com.shardingspherejdbc.mybatisplus;

import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;

public class NewGenerator {
    public static void main(String[] args) {
        GeneratorConfig config =
                GeneratorConfig.builder().jdbcUrl("jdbc:mysql://sh-cynosdbmysql-grp-8ssu79i6.sql.tencentcdb.com:23594/springboot_demo")
                        .userName("root")
                        .password("geMvVD8n")
                        .driverClassName("com.mysql.cj.jdbc.Driver")
                        .basePackage("com.shardingspherejdbc.mybatisplus")
                        .port(8068)
                        .build();

        MybatisPlusToolsApplication.run(config);
    }
}
