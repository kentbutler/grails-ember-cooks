package cook

import static org.junit.Assert.*
import grails.converters.JSON

import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.*

class UnitIntegrationTests {

	def controller
	def messageSource
	def log = LogFactory.getLog(getClass())
	def unit1
	
    @Before
    void setUp() {
		//controller = new cook.UnitController()
		//controller.messageSource = messageSource
		
		unit1 = [ nameKey : 'unit.kg.name', abbrKey: 'unit.kg.abbr' ] 
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
	
	def createJSON(def jsonProperty, def jsonPropertyProperties){
		def jsonReq = new JSONObject(
				"${jsonProperty}": new JSONObject(jsonPropertyProperties)
		)
		return jsonReq as JSON
	}
	
    void testToJSON() {
        def unit = new Unit(unit1)
        unit.save(flush:true)
        
        JSONObject json = unit.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        unit = JSON.parse(result)
        log.debug('Parsed obj:: '+unit)
        
        assertNotNull unit
        assertNotNull unit.id
        assertNotNull unit.abbrKey
        assertNotNull unit.nameKey
        assertEquals "Unit i18n name is not Kilogram", "Kilogram", unit.name 
        assertEquals "Unit i18n abbr is not kg", "kg", unit.abbr 
     }

    @Test
    void testFromJSON() {
        def unit = new Unit(unit1)
        unit.save(flush:true)
        
        JSONObject json = unit.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        // Reset object state
        unit = new Unit()
        
        unit.fromJSON(json)
        log.debug('Parsed obj:: '+unit)
        
        assertNotNull unit
        assertNotNull unit.id
        assertNotNull unit.nameKey
        assertNotNull unit.abbrKey
        assertEquals "Unit i18n name is not Kilogram", "Kilogram", unit.getName(messageSource) 
        assertEquals "Unit i18n abbr is not kg", "kg", unit.getAbbr(messageSource) 
    }
    
    @Test
    void testI18n() {
        def unit = new Unit(unit1).save(flush:true)
        assertTrue "Unit list is empty", Unit.list().size() > 0
        assertNotNull "New object id is null", unit.id
        
        def json = unit.toJSON(messageSource)
        assertNotNull "Direct name accessor is null", unit.name
        assertNotNull "JSON name content is null",json.opt('name')
        assertEquals "Unit i18n name is not Kilogram", "Kilogram", unit.name 
        assertEquals "Unit i18n abbr is not kg", "kg", unit.abbr 
    }

}
