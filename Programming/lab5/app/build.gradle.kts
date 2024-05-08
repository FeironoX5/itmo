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
    mainClass = "org.teapot_rocket.App"
}
