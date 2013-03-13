package cook

import org.apache.commons.logging.LogFactory
import org.grails.plugins.test.GenericRestFunctionalTests

import com.grailsrocks.functionaltest.BrowserTestCase


@Mixin(GenericRestFunctionalTests)
class RawIngredientFunctionalTests extends BrowserTestCase {

	def log = LogFactory.getLog(getClass())
    def messageSource
    
    def fg
    
    void setUp() {
        super.setUp()
        
        fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
    }
    
    void tearDown() {
        super.tearDown()
        
        fg.delete(flush:true)
    }

    
    void testList() {
        //def fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
        genericTestList(new RawIngredient([nameKey:"ingredient.meat.beef", foodGroup:fg]))
    }
    
    void testCreate() {
        //def fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
        genericTestCreate(new RawIngredient([nameKey:"ingredient.meat.beef", foodGroup:fg]))
    }
    
    void testShow() {
        //def fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
        genericTestShow(new RawIngredient([nameKey:"ingredient.meat.beef", foodGroup:fg]))
    }
    
    void testUpdate() {
        //def fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
        genericTestUpdate(new RawIngredient([nameKey:"ingredient.meat.beef", foodGroup:fg]), [nameKey:"ingredient.meat.chicken"])
    }
    
    void testDelete() {
        //def fg = new FoodGroup(nameKey:"group.meat").save(flush:true)
        genericTestDelete(new RawIngredient([nameKey:"ingredient.meat.beef", foodGroup:fg]))
    }
    

}
