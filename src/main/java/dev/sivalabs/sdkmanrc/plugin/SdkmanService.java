package dev.sivalabs.sdkmanrc.plugin;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SdkmanService {
    public static final String SDKMAN_HOME = System.getProperty("user.home") + "/.sdkman";

    public static List<String> getVersions(String candidate) {
        File dir = new File(SDKMAN_HOME + "/candidates/" + candidate);
        if (!dir.exists()) {
            return Collections.emptyList();
        }
        List<String> ignoreFiles = List.of("current", ".DS_Store");
        String[] versions = dir.list((d, name) -> !ignoreFiles.contains(name));
        if (versions == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(versions);
    }
}