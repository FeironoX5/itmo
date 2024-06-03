plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation("org.slf4j:slf4j-simple:1.6.2")
}

application {
    mainClass = "teapot.App"
}
