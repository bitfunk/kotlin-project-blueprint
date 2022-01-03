/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver

// TODO
// - add logger
// - add background dispatcher
class CommonDatabase(
    private val driverProvider: suspend (SqlDriver.Schema) -> SqlDriver
) : CommonDatabaseContract {

    private var database: CommonSqlDatabase? = null

    suspend fun initDatabase() {
        if (database == null) {
            database = driverProvider(CommonSqlDatabase.Schema).createDatabase()
        }
    }

    suspend operator fun <R> invoke(block: suspend (CommonSqlDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }

    private fun SqlDriver.createDatabase(): CommonSqlDatabase {
        return CommonSqlDatabase.invoke(this)
    }
}
