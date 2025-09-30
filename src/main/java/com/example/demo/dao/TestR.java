package com.example.demo.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

public class TestR {
        private final Sql2o sql2o;

    public TestR(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public boolean probarConexion() {
        String sql = "SELECT 1"; // consulta de prueba
        try (Connection con = sql2o.open()) {
            Integer result = con.createQuery(sql).executeScalar(Integer.class);
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
