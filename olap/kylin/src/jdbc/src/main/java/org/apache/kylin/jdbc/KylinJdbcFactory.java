/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.kylin.jdbc;

import static org.apache.kylin.jdbc.LoggerUtils.entry;
import static org.apache.kylin.jdbc.LoggerUtils.exit;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.calcite.avatica.AvaticaConnection;
import org.apache.calcite.avatica.AvaticaDatabaseMetaData;
import org.apache.calcite.avatica.AvaticaFactory;
import org.apache.calcite.avatica.AvaticaPreparedStatement;
import org.apache.calcite.avatica.AvaticaResultSet;
import org.apache.calcite.avatica.AvaticaResultSetMetaData;
import org.apache.calcite.avatica.AvaticaStatement;
import org.apache.calcite.avatica.Meta.Frame;
import org.apache.calcite.avatica.Meta.Signature;
import org.apache.calcite.avatica.Meta.StatementHandle;
import org.apache.calcite.avatica.QueryState;
import org.apache.calcite.avatica.UnregisteredDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kylin JDBC factory.
 */
public class KylinJdbcFactory implements AvaticaFactory {

    public static class Version40 extends KylinJdbcFactory {
        public Version40() {
            super(4, 0);
        }
    }

    public static class Version41 extends KylinJdbcFactory {
        public Version41() {
            super(4, 1);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(KylinJdbcFactory.class);
    final int major;
    final int minor;

    /** Creates a JDBC factory with given major/minor version number. */
    protected KylinJdbcFactory(int major, int minor) {
        entry(logger);
        this.major = major;
        this.minor = minor;
        exit(logger);
    }

    @Override
    public int getJdbcMajorVersion() {
        return major;
    }

    @Override
    public int getJdbcMinorVersion() {
        return minor;
    }

    @Override
    public AvaticaConnection newConnection(UnregisteredDriver driver, AvaticaFactory factory, String url, Properties info) throws SQLException {
        entry(logger);
        KylinConnection conn = new KylinConnection(driver, (KylinJdbcFactory) factory, url, info);
        exit(logger);
        return conn;
    }

    @Override
    public AvaticaDatabaseMetaData newDatabaseMetaData(AvaticaConnection connection) {
        entry(logger);
        AvaticaDatabaseMetaData metaData = new AvaticaDatabaseMetaData(connection) {};
        exit(logger);
        return metaData;
    }

    @Override
    public AvaticaStatement newStatement(AvaticaConnection connection, StatementHandle h, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        entry(logger);
        KylinStatement statement = new KylinStatement(connection, h, resultSetType, resultSetConcurrency, resultSetHoldability);
        exit(logger);
        return statement;
    }

    @Override
    public AvaticaPreparedStatement newPreparedStatement(AvaticaConnection connection, StatementHandle h, Signature signature, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        entry(logger);
        KylinPreparedStatement preparedStatement = new KylinPreparedStatement(connection, h, signature, resultSetType, resultSetConcurrency, resultSetHoldability);
        exit(logger);
        return preparedStatement;
    }

    @Override
    public AvaticaResultSet newResultSet(AvaticaStatement statement, QueryState state, Signature signature, TimeZone timeZone, Frame firstFrame) throws SQLException {
        entry(logger);
        AvaticaResultSetMetaData resultSetMetaData = new AvaticaResultSetMetaData(statement, null, signature);
        KylinResultSet resultSet = new KylinResultSet(statement, state, signature, resultSetMetaData, timeZone, firstFrame);
        exit(logger);
        return resultSet;
    }

    @Override
    public ResultSetMetaData newResultSetMetaData(AvaticaStatement statement, Signature signature) throws SQLException {
        entry(logger);
        AvaticaResultSetMetaData resultSetMetaData = new AvaticaResultSetMetaData(statement, null, signature);
        exit(logger);
        return resultSetMetaData;
    }

    public IRemoteClient newRemoteClient(KylinConnection conn) {
        entry(logger);
        KylinClient client = new KylinClient(conn);
        exit(logger);
        return client;
    }
}
