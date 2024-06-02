plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.code.gson:gson:2.11.0")
}

application {
    mainClass = "teapot.Server"
}
