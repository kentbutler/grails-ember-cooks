package cook


import java.text.SimpleDateFormat

/**
 * JSON utilities to assist in binding from JSON to domain objects
 */
class JSONUtil {

	 /*
	 * Only attempt to perform assignment if given property exists in JSON.
	 */
	static String getStr(def json, def prop) {
		String returnValue = null

		if(json?.has(prop)) {
			returnValue = (json?.isNull(prop)) ? null : json?.getString(prop)?.trim()
		}

		return returnValue
	}

	/*
	 * Only attempt to perform assignment if given property exists in JSON.
	 */
	static Closure optStr = {json, obj, prop ->
		if(json?.has(prop)) {
			String propVal = (json?.isNull(prop)) ? null : json?.getString(prop)?.trim()
			obj[prop] = propVal
		}
	}
	
	
	static Closure optInt = {json, obj, prop ->
		if(json?.has(prop))
			obj[prop] = (json?.isNull(prop)) ? null : json?.getInt(prop)
	}

	static Closure optBoolean = {json, obj, prop ->
		if(json?.has(prop))
			obj[prop] = (json?.isNull(prop)) ? null : json?.getBoolean(prop)
	}


}
