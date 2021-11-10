/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

import com.squareup.sqldelight.db.SqlDriver

// TODO
// - add logger
// - add background dispatcher
class CommonDatabase(
    private val driver: SqlDriver
) : CommonDatabaseContract {

    fun todo() {
        driver.currentTransaction()
    }
}
