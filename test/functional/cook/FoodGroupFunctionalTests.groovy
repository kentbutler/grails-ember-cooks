package cook

import org.apache.commons.logging.LogFactory
import org.grails.plugins.test.GenericRestFunctionalTests
import com.grailsrocks.functionaltest.BrowserTestCase


@Mixin(GenericRestFunctionalTests)
class FoodGroupFunctionalTests extends BrowserTestCase {

	def log = LogFactory.getLog(getClass())
    def messageSource
    
    def fg
    
    void setUp() {
        super.setUp()
        fg = new FoodGroup(nameKey:"groups.meat")
    }
    void tearDown() {
        super.tearDown()
        fg = null
    }

    void testList() {
        genericTestList(fg)
    }
    
    void testCreate() {
        genericTestCreate(fg)
    }
    
    void testShow() {
        genericTestShow(fg)
    }
    
    void testUpdate() {
        genericTestUpdate(fg, [nameKey:"groups.fruit"])
    }
    
    void testDelete() {
        genericTestDelete(fg)
    }
    

}
