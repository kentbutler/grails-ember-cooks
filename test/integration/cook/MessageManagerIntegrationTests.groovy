package cook


import static org.junit.Assert.*
import grails.converters.JSON

import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.*

class MessageManagerIntegrationTests {

	def messageManagerService
	def messageSource
	def log = LogFactory.getLog(getClass())
    
	def unit
	
    @Before
    void setUp() {
	}

    @After
    void tearDown() {
    }
    
    @Test
    void testExtractProps() {
        assertNotNull "messageSource is null",messageSource
        assertNotNull "messageSource name is null",messageManagerService.messageSourceName
        
        log.debug(messageSource)
        
        def propOuter = messageSource.getMergedProperties(Locale.getDefault())
        assertNotNull propOuter
        log.debug(propOuter)
                
        def props = propOuter.getProperties()
        
        assertNotNull "Could not extract Properties content",props
        
        // DEBUG
        props.keySet().each {
            log.debug("[$it] -> [${props[it]}]")
        }

        // INPUT DATA
        def args = ['nameKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'name'],
                    'abbrKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'abbr']
                   ]
        
        // EXECUTE 
        def dataMap = messageManagerService.extractProperties(args, props)
        
        assertNotNull "Processed input map is null",dataMap
        
        assertNotNull "Field entry nameKey missing", dataMap['nameKey']
        assertNotNull "Suffix for nameKey missing", dataMap['nameKey'][messageManagerService.SUFFIX]
        assertNotNull "Data map for nameKey missing", dataMap['nameKey'][messageManagerService.DATA]
        assertTrue "Data map for nameKey empty", dataMap['nameKey'][messageManagerService.DATA].size() > 0
        
        // DEBUG
        dataMap['nameKey'][messageManagerService.DATA].keySet().each { key ->
            log.debug "### [$key] --> [${dataMap['nameKey'][messageManagerService.DATA][key]}]"
        }
        
        assertTrue "unit.kg ey missing in data map for nameKey", dataMap['nameKey'][messageManagerService.DATA].keySet().contains("unit.kg.")
        assertTrue "unit.lb Key missing in data map for nameKey", dataMap['nameKey'][messageManagerService.DATA].keySet().contains("unit.lb.")
        
        assertTrue "unit.kg ey missing in data map for nameKey", dataMap['abbrKey'][messageManagerService.DATA].keySet().contains("unit.kg.")
        assertTrue "unit.lb Key missing in data map for nameKey", dataMap['abbrKey'][messageManagerService.DATA].keySet().contains("unit.lb.")
    }
    
    @Test
    void testPopulateDbFromProps() {
        assertNotNull "messageSource is null",messageSource
        assertNotNull "messageSource name is null",messageManagerService.messageSourceName
        
        log.debug(messageSource)
        
        def propOuter = messageSource.getMergedProperties(Locale.getDefault())
        assertNotNull propOuter
        log.debug(propOuter)
                
        def props = propOuter.getProperties()
        
        assertNotNull "Could not extract Properties content",props
        
        // DEBUG
        props.keySet().each {
            log.debug("[$it] -> [${props[it]}]")
        }

        // INPUT DATA
        def args = ['nameKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'name'],
                    'abbrKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'abbr']
                   ]
        
        // EXECUTE
        messageManagerService.populateDbFromProperties(Unit, args, props)
        
        def search = new Unit(nameKey:'unit.kg.name', abbrKey:'unit.kg.abbr')
        def results = Unit.findAll (search)
        
        assertNotNull "Did not find records for kg", results
        assertTrue "Did not find records for kg", results.size() > 0
        
        search = new Unit(nameKey:'unit.lb.name', abbrKey:'unit.lb.abbr')
        results = Unit.findAll (search)
        
        assertNotNull "Did not find records for lb", results
        assertTrue "Did not find records for lb", results.size() > 0

        // And execute a second time just to make sure it doesn't blow up
        messageManagerService.populateDbFromProperties(Unit, args, props)
        
    }
    


}
