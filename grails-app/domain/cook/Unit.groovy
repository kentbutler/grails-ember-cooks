package cook

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.context.i18n.LocaleContextHolder

class Unit {
	
	String nameKey
	String abbrKey
	
	transient String name
	transient String abbr

    static transients = ['name', 'abbr']
    static expose = 'unit'  // Expose as REST API using json-rest-api plugin
		
    static constraints = {
		nameKey(blank:false, nullable:false,maxSize:64,unique:true)
		abbrKey(blank:false, nullable:false,maxSize:64,unique:true)
    }
    
    static api = [
        excludedFields: [ "attached", "errors", "properties" ],
        list : { params -> Unit.list(params) },
        count: { params -> Unit.count() }
    ]
    
    String getName(def messageSource) {
        if (!name && messageSource) {
            name = messageSource.resolveCodeWithoutArguments(nameKey, LocaleContextHolder.getLocale())
        }
        return name
    }
    String getAbbr(def messageSource) {
        if (!abbr && messageSource) {
            abbr = messageSource.resolveCodeWithoutArguments(abbrKey, LocaleContextHolder.getLocale())
        }
        return abbr
    }

	JSONObject toJSON(def messageSource) {
        JSONObject json = new JSONObject()
        json.put('id', id)
        json.put('nameKey', nameKey)
        json.put('abbrKey', abbrKey)
        
		if (messageSource) {
            json.put('name', getName(messageSource))
            json.put('abbr', getAbbr(messageSource))
		}
        return json
	}

	def fromJSON (JSONObject json) {
        [
            "id"
        ].each(JSONUtil.optInt.curry(json, this))
		[
			"nameKey",
			"abbrKey"
			
		].each(JSONUtil.optStr.curry(json, this))
	}
	
	String toString() {
		StringBuilder sb = new StringBuilder()
        sb.append("\n    id:    ").append(id)
		sb.append("\n    NameKey:    ").append(nameKey)
		sb.append("\n    AbbrKey:    ").append(abbrKey)
		sb.append("\n    Name:    ").append(name)
		sb.append("\n    Abbr:    ").append(abbr)
		sb.toString()
	}
	
	
}
