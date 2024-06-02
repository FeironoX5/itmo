plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation(project(":shared"))
}

application {
    mainClass = "teapot.App"
}
