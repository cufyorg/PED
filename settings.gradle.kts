pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "ped"

val moduleNamePrefix = "module-"
for (file in rootDir.listFiles().orEmpty()) {
    if (file.isDirectory && file.name.startsWith(moduleNamePrefix)) {
        val moduleName = file.name.removePrefix(moduleNamePrefix)
        include(moduleName)
        project(":$moduleName").projectDir = file
    }
}
