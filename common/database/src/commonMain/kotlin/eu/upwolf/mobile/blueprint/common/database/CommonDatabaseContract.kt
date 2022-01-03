/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver

interface CommonDatabaseContract {

    interface DriverFactory {
        suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver
    }

    companion object {
        const val DATABASE_NAME = "blueprint.db"
    }
}
