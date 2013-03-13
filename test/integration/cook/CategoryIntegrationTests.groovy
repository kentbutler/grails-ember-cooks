package cook


import static org.junit.Assert.*
import grails.converters.JSON

import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.*

class CategoryIntegrationTests {

	def controller
	def messageSource
	def log = LogFactory.getLog(getClass())
	def cat1
	
    @Before
    void setUp() {
		
		cat1 = [ nameKey : 'category.lunch' ] 
		
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
    
    void testToJSON() {
        def cat = new Category(cat1)
        cat.save(flush:true)
        
        assertNotNull "messageSource is null",messageSource
        
        JSONObject json = cat.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        cat = JSON.parse(result)
        log.debug('Parsed obj:: '+cat)
        
        assertNotNull cat
        assertNotNull cat.id
        assertNotNull cat.nameKey
        assertEquals "Category i18n name is not Lunch", "Lunch", cat.name 
    }
    
    @Test
    void testFromJSON() {
        def cat = new Category(cat1)
        cat.save(flush:true)
        
        assertNotNull "messageSource is null",messageSource
        
        JSONObject json = cat.toJSON(messageSource)
        String result = json.toString()
        log.debug("JSON: $result")
        
        assertNotNull result
        assertTrue result.indexOf("id") > 0
        
        // Reset object state
        cat = new Category()
        
        cat.fromJSON(json)
        log.debug('Parsed obj:: '+cat)
        
        assertNotNull cat
        assertNotNull cat.id
        assertNotNull cat.nameKey
        assertEquals "Category i18n name is not Lunch", "Lunch", cat.getName(messageSource)
    }
    
    @Test
    void testI18n() {
        def cat = new Category(cat1).save(flush:true)
        assertTrue "Category list is empty", Category.list().size() > 0
        assertNotNull "New object id is null", cat.id
        
        assertNotNull "messageSource is null",messageSource
        
        def json = cat.toJSON(messageSource)
        assertNotNull "Direct name accessor is null", cat.name
        assertNotNull "JSON name content is null",json.opt('name')
        assertEquals "Category i18n name is not Lunch", "Lunch", cat.name
    }

}
