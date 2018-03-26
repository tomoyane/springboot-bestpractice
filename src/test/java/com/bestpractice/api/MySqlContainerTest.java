package com.bestpractice.api;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testcontainers.containers.MySQLContainer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MySqlContainerTest {

    private static MySQLContainer MYSQL = new MySQLContainer("mysql");

    static MySQLContainer database() {
        return MYSQL;
    }
}
