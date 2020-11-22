package design_model.manyton.limitlessmanyton;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class LingualResource {

    private String language = "en";
    private String region = "US";
    private String localeCode = "en_US";
    private static final String FILE_NAME = "res";
    private static HashMap instances = new HashMap(19);
    private Locale locale = null;
    private ResourceBundle resourceBundle = null;
    private LingualResource lingualResource;

    /**
     * 私有方法
     */
    private LingualResource(String language, String region) {
        this.localeCode = language;
        this.region = region;
        localeCode = makeLocalCode(language, region);
        locale = new Locale(language, region);
        resourceBundle = ResourceBundle.getBundle(FILE_NAME, locale);
        instances.put(makeLocalCode(language, region), resourceBundle);
    }

    /**
     * 工厂方法，返回指定的内部状态的实例
     */
    public synchronized static LingualResource getInstance(String language, String region) {
        if (instances.containsKey(makeLocalCode(language, region))) {
            return (LingualResource) instances.get(makeLocalCode(language, region));
        } else {
            return new LingualResource(language, region);
        }
    }

    public String getLocalString(String code) {
        return resourceBundle.getString(code);
    }

    private static String makeLocalCode(String language, String region) {
        return language + "_" + region;
    }
}
