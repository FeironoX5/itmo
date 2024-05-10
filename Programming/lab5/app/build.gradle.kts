plugins {
    application
}

repositories {
    mavenCentral() 
}

dependencies {
    // https://mvnrepository.com/artifact/com.opencsv/opencsv
    implementation("com.opencsv:opencsv:3.7")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(20)
    }
}

application {
    mainClass = "org.teapot.manager.App"
}
