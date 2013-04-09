package cook

import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.context.i18n.LocaleContextHolder

class RawIngredient {
	
	String nameKey
	FoodGroup foodGroup
    
    static expose = 'raw_ingredient' // Expose as REST API using json-rest-api plugin
    
    static constraints = {
        nameKey(blank:false, nullable:false,maxSize:64,unique:true)
    }
    
    transient String name
    static transients =  ['name']

    static api = [
        excludedFields: [ "attached", "errors", "properties" ],
        list : { params -> RawIngredient.list(params) },
        count: { params -> RawIngredient.count() }
    ]
    
    String getName(messageSource) {
        if (!name && messageSource) {
            name = messageSource.resolveCodeWithoutArguments(nameKey, LocaleContextHolder.getLocale())
        }
        return name
    }

    JSONObject toJSON(def messageSource) {
        JSONObject json = new JSONObject()
        if (id) json.put('id', id)
        json.put('nameKey', nameKey)
        json.put('foodGroupId', foodGroup?.id)
        
        if (messageSource) {
            json.put("name", getName(messageSource))
            //json.put('foodGroup', foodGroup?.getName(messageSource))
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

        if (json.has("foodGroupId")) {
            foodGroup = FoodGroup.get(json?.getInt("foodGroupId"))
        }
    }
    
    RawIngredient clone() {
        def copy = new RawIngredient()
        copy.properties = this.properties
        copy.foodGroup = this.foodGroup
    }

    String toString() {
        StringBuilder sb = new StringBuilder()
        sb.append("\n    id:    ").append(id)
        sb.append("\n    NameKey:    ").append(nameKey)
        sb.append("\n    Name:    ").append(name)
        sb.append("\n    FoodGroup:    ").append(foodGroup)
        sb.toString()
    }

    
}
