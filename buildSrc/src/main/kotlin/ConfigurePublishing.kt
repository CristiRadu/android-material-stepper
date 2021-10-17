import org.gradle.api.publish.PublicationContainer
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.get

class ConfigurePublishing {


}

fun PublicationContainer.configureMS() {

    create("maven", MavenPublication::class.java) {
        groupId = "com.stepstone.stepper"
        artifactId = "material-stepper"
        version = "5.0.0"

//        from(components["java"])
    }

}