plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation(project(":shared"))
}

application {
    mainClass = "teapot.App"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
