package cook

import org.apache.commons.logging.LogFactory

class MessageManagerService {
    def log = LogFactory.getLog(getClass())
    
    def messageSource
    static final String messageSourceName = 'messages'   // a better way??
    static final String PREFIX = 'prefix'
    static final String SUFFIX = 'suffix'
    static final String DATA = 'data'
    
    /**
     * Load the database for the given Class entity given a Map of fieldName -> pattern,
     * where fieldName is the class field to populate and pattern is the i18n key pattern
     * to locate in registered messages.  If multiple i18n key patterns are given, these
     * are assumed to BOTH be required on a single entity instance - for example, name
     * and abbreviation.
     * 
     * This does not alter registered i18n definitions in any way; it merely ensures database
     * entries exist for the given entity.  For this reason entities using this method
     * should be communication-driven entities - for example a simple Category is a good
     * example.
     * @param Class entity to populate
     * @param Map of [fieldName : ['prefix':val, 'suffix':val]] - every fieldName/pattern combination given
     *     is applied to each entity to locate existence
     *     
     * @return
     */
    def populateDbFromMessages(Class clazz, Map args) {
        def propOuter = messageSource.getMergedProperties(Locale.getDefault())
        def props = propOuter.getProperties()
        
        // GUARD
        if (!props) {
            log.error("Could not load message properties from source: $messageSourceName" )
            return
        }
        
        // props is a regular Properties object, if all went well
        populateDbFromProperties(clazz, args, props)
    }
    
    /**
     * Not for public consumption.
     * @param clazz
     * @param args
     * @param props
     * @return
     */
    def populateDbFromProperties(Class clazz, def args, def props) {
        def dataMap = extractProperties(args, props)
        log.debug("------ Processing map results -------")
        
        def example
        
        // Use results for first field and match up the rest of the fields
        dataMap[dataMap.keySet().first()][DATA].keySet().each { propBase ->
            log.debug("\tProp: $propBase")
            example = clazz.newInstance()
            
            dataMap.keySet().each { fieldName ->
                log.debug("\t\tSetting field: $fieldName")
                example[fieldName] = dataMap[fieldName][DATA][propBase]
            }
            // Search via populated search object
            log.debug("Searching with ==>\n $example")
            def results = clazz.findAll(example)
            
            log.debug("Received ${results?.size()} results")
            
            if (!results || results?.size() <= 0) {
                log.debug("--> Adding new record for property")
                example.save(flush:true)
            }
        }
    }
        
    
    /**
     * Not for public consumption.
     * @param args  Map of [fieldName : ['prefix':val, 'suffix':val]] 
     * @param props
     * @return
     */
    def extractProperties(def args, Properties props) {
        // Output dataMap looks like:
        //    [ fieldName : [
        //         'suffix' : suffix,
        //         'data'   : [prop base(no suffix) : prop key] 
        // ] ]
        def dataMap = [:]
        def keys = props.keySet()
        def prefix
        def suffix
        
        log.debug("------ Building data maps -------")
        
        args.keySet().each { fieldName ->
            log.debug("\tField: $fieldName")
            
            dataMap[fieldName] = [:]
            prefix = args[fieldName][PREFIX]
            suffix = args[fieldName][SUFFIX] ?: ''
            dataMap[fieldName][SUFFIX] = suffix // need this later
            dataMap[fieldName][DATA] = [:] // where we stuff matching prop keys
            log.debug("\t\tSuffix: $suffix")
            log.debug("\t\tPrefix: $prefix")
            
            keys.each { propKey ->
                if (propKey.startsWith(prefix) && propKey.endsWith(suffix)) {
                    dataMap[fieldName][DATA][propKey[0..propKey.size() - suffix.size()-1]] = propKey 
                    log.debug("\t\t\tmapped: $propKey")
                }
            }
        }
        return dataMap
    }
    
}
