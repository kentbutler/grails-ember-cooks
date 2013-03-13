package cook

import java.text.MessageFormat

import org.springframework.context.support.ReloadableResourceBundleMessageSource


class BulkLoadableMessageSource extends ReloadableResourceBundleMessageSource {
    
    public Collection<String> getMappedKeys(String bundleName, Locale locale) {
        //Map<String, Map<Locale, MessageFormat>> codeMap = this.cachedBundleMessageFormats.get(bundle);
        
        //ResourceBundle codeMap = getResourceBundle(bundleName, locale)
        
        
        
        return codeMap.keySet()
    }
    
}
