plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.equistork"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

val cucumberVersion = "7.18.0"
val testContainersVersion = "1.19.8"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.amazonaws:aws-java-sdk:1.12.725")

	compileOnly("org.projectlombok:lombok")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	runtimeOnly("org.postgresql:postgresql")

	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.cucumber:cucumber-spring:$cucumberVersion")
	testImplementation("io.cucumber:cucumber-java:$cucumberVersion")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
	testImplementation("org.testcontainers:postgresql:$testContainersVersion") {
		exclude(group = "org.slf4j", module = "slf4j-api")
	}
	testImplementation("org.testcontainers:localstack:$testContainersVersion")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
