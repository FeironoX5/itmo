plugins {
    application
}

repositories {
    mavenCentral() 
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(20)
    }
}

application {
    mainClass = "teapot.App"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
