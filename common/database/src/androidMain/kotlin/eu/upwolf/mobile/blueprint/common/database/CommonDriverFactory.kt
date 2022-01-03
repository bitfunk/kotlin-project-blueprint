/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class CommonDriverFactory(
    private val context: Context
) : CommonDatabaseContract.DriverFactory {

    actual override suspend fun createDriver(schema: SqlDriver.Schema): SqlDriver {
        return AndroidSqliteDriver(
            schema = schema,
            context = context,
            name = CommonDatabaseContract.DATABASE_NAME
        )
    }
}
