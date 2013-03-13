package cook

class GroovyUtils {

    static boolean isString(def obj) {
        obj?.class?.name == "org.codehaus.groovy.runtime.GStringImpl" || obj?.class?.name == "java.lang.String"
    }
}
