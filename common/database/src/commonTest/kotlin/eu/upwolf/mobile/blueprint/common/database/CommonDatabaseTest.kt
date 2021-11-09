/*
 * Copyright (c) 2021 Wolf-Martell Montwé. All rights reserved.
 */

package eu.upwolf.mobile.blueprint.common.database

class CommonDatabaseTest {

    private val driver = CommonTestDriverFactory.createDriver()
    private val database = CommonDatabase(driver)
}
