package cook

import grails.test.mixin.TestFor
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Unit)
class UnitTests {

    def log = LogFactory.getLog(getClass())
    
    void testToJSON() {
       Unit unit = new Unit(id:1, nameKey:"nameKey", abbrKey:"abbrKey")
       unit.save(flush:true)
       
       JSONObject json = new JSONObject()
       json.putAt("status", 200)
       json.put("data", unit.toJSON())
       
       String result = json.toString()
       log.debug("JSON: $result")
       
       assertNotNull result
       assertTrue result.indexOf("id") > 0
    }
}
