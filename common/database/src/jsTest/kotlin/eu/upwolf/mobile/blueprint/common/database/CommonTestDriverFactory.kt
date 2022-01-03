/*
 * Copyright (c) 2022 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver

actual object CommonTestDriverFactory : CommonDatabaseContract.DriverFactory {
    actual override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver {
        TODO("Not yet implemented")
    }
}
