package cook

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject

public class GenericRestControllerActions {

	public def genericList = { Class clazz ->
		def results = clazz.list()
		def model = []
		results.each{ model.add(it.toJSON(messageSource)) }
		//results.each{ model.add(it as JSON) }

		renderResult (model, results.size(), 200)
		//renderJSONResult ([total:units.size(), units:units] as JSON, 200)
	}
	
	public def genericSave = { Class clazz ->
		log.debug("Params: " + request.JSON)
		
		def instance = clazz.newInstance()
		instance.fromJSON(request.JSON.opt('data'))
		
		log.debug("Converted Object: " + instance)
		
		if (instance.save(flush: true)) {
			renderResult(instance, 200)
		}
		else {
			renderResult([message:'Could not create new instance'], 500)
		}
		
	}

	
}
