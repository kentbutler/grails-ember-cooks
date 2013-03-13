package cook

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject


class JsonController {

	
    protected renderStringResult(def resultStr, int statusCode) {
        response.status = statusCode
        def jsonResult = "\"${resultStr.encodeAsJavaScript()}\""  // need to escape stuff so parser doesn't choke
        render jsonResult
    }

	/**
	 * For rendering JSON which is already prepared. To have an Object be rendered, use the other signatures.
	 * @param jsonResult
	 * @param statusCode
	 * @return
	 */
	protected renderJSONResult(def jsonResult, int statusCode){
		response.status = statusCode
		render jsonResult
	}

    protected renderResult(Object result, int statusCode) {
        renderResult(result, 1, statusCode)
    }

    protected renderResult(Object result, int total, int statusCode) {
        response.status = statusCode
		def jsonResult
        if ((200..201).contains(statusCode)){
            //TODO Use our toJSON here for domain objects
            jsonResult = [count: total, data: result] as JSON
			//TODO Can we eliminate this weirdness??
			JSONObject jsonResultObj = (JSONObject) JSON.parse(jsonResult.toString())
			jsonResult = jsonResultObj as JSON
        }
        else{
            if (GroovyUtils.isString(result)) {
                jsonResult = "\"${result.encodeAsJavaScript()}\""  // need to escape stuff so parser doesn't choke
            }
			else {
				jsonResult = "\"Unknown error: ${result.toString().encodeAsJavaScript()} \""
			}
        }
        render jsonResult
    }



}