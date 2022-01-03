/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual object CommonTestDriverFactory : CommonDatabaseContract.DriverFactory {
    actual override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            schema.create(this)
        }
    }
}
