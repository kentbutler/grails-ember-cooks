package cook

import org.apache.commons.logging.LogFactory
import org.grails.plugins.test.GenericRestFunctionalTests
import com.grailsrocks.functionaltest.BrowserTestCase


@Mixin(GenericRestFunctionalTests)
class UnitFunctionalTests extends BrowserTestCase {

	def log = LogFactory.getLog(getClass())
    def messageSource
  
    def unit
    
    void setUp() {
        super.setUp()
        unit = new Unit(nameKey:"unit.kg.name",  abbrKey:"unit.kg.abbr")
    }
    void tearDown() {
        super.tearDown()
        unit = null
    }

    void testList() {
        genericTestList(unit)
	}
    
    void testCreate() {
        genericTestCreate(unit)
    }
    
    void testShow() {
        genericTestShow(unit)
    }
    
    void testUpdate() {
        genericTestUpdate(unit, [nameKey:"unit.lb.name", abbrKey:"unit.lb.abbr"])
    }
    
    void testDelete() {
        genericTestDelete(unit)
    }
    
}
