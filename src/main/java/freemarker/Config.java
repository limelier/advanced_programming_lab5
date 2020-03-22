package freemarker;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class Config {
    static Configuration cfg = null;

    /**
     * Initialize the configuration (singleton).
     */
    public static void init() {
        if (cfg != null) {
            return;
        }

        cfg = new Configuration(Configuration.VERSION_2_3_0);

        try {
            cfg.setDirectoryForTemplateLoading(new File("../../templates"));
        } catch (IOException e) {
            System.err.println("Cannot find freemarker template.");
            e.printStackTrace();
            exit(-1);
        }

        // recommended default settings, https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
    }

    public static Configuration get() {
        return cfg;
    }
}
