import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.illia"
version = "1.0-SNAPSHOT"

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    withType<ShadowJar> {
        archiveClassifier.set("")
        exclude("META-INF/**")
    }
}



repositories {
    mavenCentral()
    maven {
        name = "papermc-repo"
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        name = "protocollib"
        url = uri("https://repo.dmulloy2.net/repository/public/")
    }
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
}
