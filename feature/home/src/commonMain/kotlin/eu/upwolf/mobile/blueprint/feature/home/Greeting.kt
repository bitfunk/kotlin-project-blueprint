package eu.upwolf.mobile.blueprint.feature.home

class Greeting {
    fun greet(): String {
        return "Hello, ${Platform().platformName}!"
    }
}
