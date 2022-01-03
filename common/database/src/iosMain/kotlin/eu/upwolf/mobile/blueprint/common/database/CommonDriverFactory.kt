/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class CommonDriverFactory() : CommonDatabaseContract.DriverFactory {
    actual override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver {
        return NativeSqliteDriver(schema, CommonDatabaseContract.DATABASE_NAME)
    }
}
