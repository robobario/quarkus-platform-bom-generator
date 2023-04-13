package io.quarkus.bom.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlatformMemberTestConfig extends PlatformMemberDefaultTestConfig {

    private String artifact;
    private Boolean excluded;
    private List<Copy> copyTasks = new ArrayList<>(0);

    public void setArtifact(String artifact) {
        this.artifact = artifact;
    }

    public String getArtifact() {
        return artifact;
    }

    public void setExcluded(boolean excluded) {
        this.excluded = excluded;
    }

    public boolean isExcluded() {
        return excluded == null ? false : excluded.booleanValue();
    }

    public void setCopyTasks(List<Copy> copyTasks) {
        this.copyTasks = copyTasks;
    }

    public List<Copy> getCopyTasks() {
        return copyTasks;
    }

    void applyOverrides(PlatformMemberTestConfig overrides) {
        super.applyOverrides(overrides);
        if (overrides.excluded != null) {
            excluded = overrides.excluded;
        }
    }

    public void applyDefaults(PlatformMemberDefaultTestConfig defaults) {
        if (skip == null) {
            skip = defaults.skip;
        }
        if (skipNative == null) {
            skipNative = defaults.skipNative;
        }
        if (skipJvm == null) {
            skipJvm = defaults.skipJvm;
        }
        if (failsafeMavenPlugin == null) {
            failsafeMavenPlugin = defaults.failsafeMavenPlugin;
        }
        if (transformWith == null) {
            transformWith = defaults.transformWith;
        }
        if (!defaults.systemProperties.isEmpty()) {
            if (systemProperties.isEmpty()) {
                systemProperties = defaults.systemProperties;
            } else {
                for (Map.Entry<String, String> prop : defaults.systemProperties.entrySet()) {
                    if (!systemProperties.containsKey(prop.getKey())) {
                        systemProperties.put(prop.getKey(), prop.getValue());
                    }
                }
            }
        }
        if (!defaults.jvmSystemProperties.isEmpty()) {
            if (jvmSystemProperties.isEmpty()) {
                jvmSystemProperties = defaults.jvmSystemProperties;
            } else {
                for (Map.Entry<String, String> prop : defaults.jvmSystemProperties.entrySet()) {
                    if (!jvmSystemProperties.containsKey(prop.getKey())) {
                        jvmSystemProperties.put(prop.getKey(), prop.getValue());
                    }
                }
            }
        }
        if (!defaults.nativeSystemProperties.isEmpty()) {
            if (nativeSystemProperties.isEmpty()) {
                nativeSystemProperties = defaults.nativeSystemProperties;
            } else {
                for (Map.Entry<String, String> prop : defaults.nativeSystemProperties.entrySet()) {
                    if (!nativeSystemProperties.containsKey(prop.getKey())) {
                        nativeSystemProperties.put(prop.getKey(), prop.getValue());
                    }
                }
            }
        }
        if (defaults.pomProperties != null && !defaults.pomProperties.isEmpty()) {
            if (pomProperties == null || pomProperties.isEmpty()) {
                pomProperties = defaults.pomProperties;
            } else {
                for (Map.Entry<?, ?> prop : defaults.pomProperties.entrySet()) {
                    if (!pomProperties.containsKey(prop.getKey().toString())) {
                        pomProperties.put(prop.getKey(), prop.getValue());
                    }
                }
            }
        }
        if (groups == null) {
            groups = defaults.groups;
        }
        if (nativeGroups == null) {
            nativeGroups = defaults.nativeGroups;
        }
        if (!defaults.dependencies.isEmpty()) {
            if (dependencies.isEmpty()) {
                dependencies = defaults.dependencies;
            } else {
                dependencies.addAll(defaults.dependencies);
            }
        }
        if (!defaults.testDependencies.isEmpty()) {
            if (testDependencies.isEmpty()) {
                testDependencies = defaults.testDependencies;
            } else {
                testDependencies.addAll(defaults.testDependencies);
            }
        }

        if (!defaults.jvmExcludes.isEmpty()) {
            if (jvmExcludes.isEmpty()) {
                jvmExcludes = defaults.jvmExcludes;
            } else {
                jvmExcludes.addAll(defaults.jvmExcludes);
            }
        }
        if (!defaults.jvmIncludes.isEmpty()) {
            if (jvmIncludes.isEmpty()) {
                jvmIncludes = defaults.jvmIncludes;
            } else {
                jvmIncludes.addAll(defaults.jvmIncludes);
            }
        }
        if (!defaults.nativeExcludes.isEmpty()) {
            if (nativeExcludes.isEmpty()) {
                nativeExcludes = defaults.nativeExcludes;
            } else {
                nativeExcludes.addAll(defaults.nativeExcludes);
            }
        }
        if (!defaults.nativeIncludes.isEmpty()) {
            if (nativeIncludes.isEmpty()) {
                nativeIncludes = defaults.nativeIncludes;
            } else {
                nativeIncludes.addAll(defaults.nativeIncludes);
            }
        }
        if (packageApplication == null) {
            packageApplication = defaults.packageApplication;
        }

        if (argLine == null) {
            argLine = defaults.argLine;
        }
        if (jvmArgLine == null) {
            jvmArgLine = defaults.jvmArgLine;
        }
        if (nativeArgLine == null) {
            nativeArgLine = defaults.nativeArgLine;
        }

        if (testPattern == null) {
            testPattern = defaults.testPattern;
        }
        if (jvmTestPattern == null) {
            jvmTestPattern = defaults.jvmTestPattern;
        }
        if (nativeTestPattern == null) {
            nativeTestPattern = defaults.nativeTestPattern;
        }
    }

    public static class Copy {
        private String src;
        private String destination;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }
    }
}
