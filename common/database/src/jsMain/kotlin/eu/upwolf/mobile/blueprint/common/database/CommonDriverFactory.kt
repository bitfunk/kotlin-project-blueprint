/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.sqljs.initSqlDriver
import kotlinx.coroutines.await

actual class CommonDriverFactory() : CommonDatabaseContract.DriverFactory {

    actual override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver {
        return initSqlDriver(schema).await()
    }
}
