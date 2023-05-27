# GlitchAPI
API for GlitchMC plugins.

usage

`build.gradle`
```gradle

plugins {
    id 'java'
    id 'com.github.johnrengelman.shadow' version '7.1.2'
}

repositories {
    maven {
        url = "https://repo.jadeisacutie.com/releases"
        credentials {
            username GlitchMCUsername 
            password GlitchMCPassword
        }
    }
}

dependencies {
    implementation 'dev.selena.glitchmc:GlitchAPI:1.0.0'
}

shadowJar {

    dependsOn(jar)
    if (System.getenv("BUILD_NUMBER") == null) {
        archiveName = rootProject.name + "-" + version + ".jar"
    } else {
        archiveName = rootProject.name + "-" + version + "-b" + System.getenv("BUILD_NUMBER") + ".jar"
    }

    dependencies {
        include(dependency('dev.selena.glitchmc:GlitchAPI'))
    }

    zip64 true
    subprojects.each { subproject ->
        from subproject.sourceSets.main.output.classesDirs
        from subproject.sourceSets.main.output.resourcesDir
    }


    configurations = [project.configurations.getByName("runtimeClasspath")]

}

```

`C:/Users/<USERNAME>/.gradle/gradle.properties`
```properties
GlitchMCUsername=USERNAME ON DISCORD
GlitchMCPassword=PASSWORD ON DISCORD
```


`In main class`
```java
    @Override
    public void onEnable() {
        GlitchAPI.setPlugin(this);
        boolean isLatest = GlitchAPI.isLatest("PATH TO PROJECT IN THE PRIVATE REPOS"); // Used for checking if the plugin is on the latest version. 
        EnumSet.allOf(Configs.class).forEach(Configs::getConfig); // Only used of you are using the Config function of the API, be sure to add the Configs class
    }
```



`Configs.java`
```java
public enum Configs {

    CONFIG(Config.class, "PARENT FOLDER", "FILE NAME.json");
    private final File file;

    private final Class<?> clazz;

    <T> Configs(Class<T> clazz, String parent, String path) {
        this.clazz = clazz;
        file = FileManager.file(parent, path);
    }


    @SuppressWarnings("unchecked")
    public <T> T getConfig() {
        return (T) FileManager.loadFile(clazz, file);
    }
}
```

example Config class
`Config.java`
```java
public class Config {

    public String TEST = "TEST";


    public static Config get() {
        return Configs.CONFIG.getConfig();
    }
}
```

How to access config variables.
```java
public void onEnable() {
  GlitchAPI.setPlugin(this);
  new FileManager().loadFiles();
  System.out.println(Config.get().TEST);
}
```

