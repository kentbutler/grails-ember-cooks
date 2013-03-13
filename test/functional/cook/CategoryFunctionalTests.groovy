package cook

import org.apache.commons.logging.LogFactory
import org.grails.plugins.test.GenericRestFunctionalTests
import com.grailsrocks.functionaltest.BrowserTestCase


@Mixin(GenericRestFunctionalTests)
class CategoryFunctionalTests extends BrowserTestCase {

	def log = LogFactory.getLog(getClass())
    def messageSource
    
    def cat
    
    void setUp() {
        super.setUp()
        cat = new Category(nameKey:"category.lunch")
    }
    void tearDown() {
        super.tearDown()
        cat = null
    }


    void testList() {
        genericTestList(cat)
    }
    
    void testCreate() {
        genericTestCreate(cat)
    }
    
    void testShow() {
        genericTestShow(cat)
    }
    
    void testUpdate() {
        genericTestUpdate(cat, [nameKey:"category.dinner"])
    }
    
    void testDelete() {
        genericTestDelete(cat)
    }
    

}
