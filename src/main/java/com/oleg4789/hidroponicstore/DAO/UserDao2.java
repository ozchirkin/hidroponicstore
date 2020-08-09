package com.oleg4789.hidroponicstore.DAO;

import com.oleg4789.hidroponicstore.domain.BaseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao2 extends AbstractDao {
    @Override
    public String getQueryForAdd() {
        return null;
    }

    @Override
    public String getQueryForUpdate() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public BaseEntity getEntityFromResultSet(ResultSet resultSet) {
        return null;
    }

    @Override
    public void setVarForPreparedStatement(BaseEntity entity, PreparedStatement preparedStatement) {

    }
}
