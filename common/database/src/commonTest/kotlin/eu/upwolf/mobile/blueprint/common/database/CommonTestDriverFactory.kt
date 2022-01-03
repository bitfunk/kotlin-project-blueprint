/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver

expect object CommonTestDriverFactory : CommonDatabaseContract.DriverFactory {
    override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver
}
