package cook

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.context.i18n.LocaleContextHolder

class FoodGroup {
	
	static expose = 'foodGroup' // Expose as REST API using json-rest-api plugin
	
	String nameKey
	transient String name
	
    static constraints = {
		nameKey(blank:false, nullable:false,maxSize:64,unique:true)
    }

    static transients =  ['name']
	

	static api = [
		excludedFields: [ "attached", "errors", "properties" ],
		list : { params -> FoodGroup.list(params) },
		count: { params -> FoodGroup.count() }
    ]
    
    String getName(messageSource) {
        if (!name && messageSource) {
            name = messageSource.resolveCodeWithoutArguments(nameKey, LocaleContextHolder.getLocale())
        }
        return name
    }

    JSONObject toJSON(def messageSource) {
        JSONObject json = new JSONObject()
        json.put('id', id)
        json.put('nameKey', nameKey)
        
        if (messageSource) {
            json.put("name", getName(messageSource))
        }
        return json
    }

    void fromJSON (JSONObject json) {
        [
            "id"
        ].each(JSONUtil.optInt.curry(json, this))
        [
            "nameKey"
        ].each(JSONUtil.optStr.curry(json, this))
    }

    String toString() {
        StringBuilder sb = new StringBuilder()
        sb.append("\n    id:    ").append(id)
        sb.append("\n    NameKey:    ").append(nameKey)
        sb.append("\n    Name:    ").append(name)
        sb.toString()
    }
}
