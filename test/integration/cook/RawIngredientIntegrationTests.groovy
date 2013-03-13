package cook

import static org.junit.Assert.*
import grails.converters.JSON

import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.*

class RawIngredientIntegrationTests {

	def controller
	def messageSource
	def log = LogFactory.getLog(getClass())
	def rawIng1
    def fg
	
    @Before
    void setUp() {

		fg = new FoodGroup('nameKey':'group.meat').save(flush:true)
        rawIng1 = [ nameKey : 'ingredient.meat.beef', foodGroup: fg ] 
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
    
    void testToJSON() {
        log.debug('----- testToJSON() -------')
        assertNotNull "messageSource is null",messageSource
        def rawIng = new RawIngredient(rawIng1).save(flush:true)
        
        JSONObject json = rawIng.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        rawIng = JSON.parse(result)
        log.debug('Parsed obj:: '+rawIng)
        
        assertNotNull rawIng
        assertNotNull rawIng.id
        assertNotNull rawIng.nameKey
        assertEquals "RawIngredient i18n name is not Beef", "Beef", rawIng.name
    }
    
    @Test
    void testFromJSON() {
        log.debug('----- testFromJSON() -------')
        assertNotNull "messageSource is null",messageSource
        def rawIng = new RawIngredient(rawIng1).save(flush:true)
        
        JSONObject json = rawIng.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        // Reset object state
        rawIng = new RawIngredient()
        
        rawIng.fromJSON(json)
        log.debug('Parsed obj:: '+rawIng)
        
        assertNotNull rawIng
        assertNotNull rawIng.id
        assertNotNull rawIng.nameKey
        assertEquals "FoodGroup i18n name is not Meat", "Meat and Protein", rawIng?.foodGroup?.getName(messageSource)
    }
    
    @Test
    void testI18n() {
        log.debug('----- testI18n() -------')
        assertNotNull "messageSource is null",messageSource
        def rawIng = new RawIngredient(rawIng1)
        rawIng.save(flush:true)
        assertTrue "RawIngredient list is empty", RawIngredient.list().size() > 0
        assertNotNull "New object id is null", rawIng.id
        
        def json = rawIng.toJSON(messageSource)
        assertNotNull "Direct name accessor is null", rawIng.name
        assertNotNull "JSON name content is null",json.opt('name')
        assertEquals "RawIngredient i18n name is not Beef", "Beef", rawIng.getName(messageSource) 
        assertEquals "RawIngredient i18n group is not Meat", "Meat and Protein", json.opt('foodGroup')
    }

}
