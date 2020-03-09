package com.newland.dg.utils;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

public class FunctionUtils {
    private static CompilerConfiguration configuration;

    static {
        configuration = new CompilerConfiguration();
        configuration.addCompilationCustomizers(getCustomImport());
    }

    public static CompilerConfiguration getConfiguration() {
        return configuration;
    }

    private static ImportCustomizer getCustomImport() {
        ImportCustomizer importCustomizer = new ImportCustomizer();
        importCustomizer.addStaticStars("com.newland.dg.utils.func.DateFunction");

        return importCustomizer;
    }
}
