package cook


@Mixin(cook.GenericRestControllerActions)
class UnitController extends JsonController {

	def messageSource
	
    def index() { }
	
    
    /*
    // These are supplanted by the json-rest-api plugin
    //    Left here as example alternate implementation
	def list = genericList.curry(Unit)
	
	def save = genericSave.curry(Unit)
	*/
	
}
