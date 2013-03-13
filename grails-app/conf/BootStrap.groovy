import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler

import cook.FoodGroup
import cook.Unit

class BootStrap {

    def init = { servletContext ->
		
        println "------- ServletContext Attributes -----------------------"
        def names = servletContext.getAttributeNames()
        names.each() {println "$it"}
 
        def ctx = servletContext.getAttribute('org.codehaus.groovy.grails.APPLICATION_CONTEXT')
        def osiv
        if (ctx) {
            def beans = ctx.beanDefinitionNames
            beans.sort()
            println "------- AppContext Beans -----------------------"
            beans.each() {
                if (it.indexOf("Interceptor") > 0 || it.indexOf('interceptor') > 0 || it.indexOf('Handler') > 0) {
                   println "$it"
                }
                if (it == 'openSessionInViewInterceptor') {
                    // Get the interceptor, check it state
                    osiv = ctx.getBean(it)
                    println "\t\t--> OSIV enabled?? ${osiv.isSingleSession()}"
                }
           }
 
           // Get the private interceptors field from the request handlerMapping class
           def field = org.springframework.web.servlet.handler.AbstractHandlerMapping.class.getDeclaredField('interceptors')
           field.accessible = true
           println "------- Interceptors via controllerHandlerMappings -----------------------"
           // Get this Field on the given object, the actual HandlerMapping that declares the interceptors
           def interceptors = field.get(ctx.controllerHandlerMappings)
           if (interceptors) {
               println "Got interceptors class: ${interceptors.class.name}"
               interceptors.each() {
                   println "$it"
               }
           }
           else {
               println "Could not get interceptors class"
           }
        }
        else {
            println "No AppContext"
        }
        
        def app = servletContext.getAttribute('grailsApplication')
        def messageManagerService
        
        if (app) {
            println "\n-------------------------------------------------"
            println "------- grailsApplication -----------------------"
            println "-------------------------------------------------"
            println "\n------- Properties -----------------------"
            app.properties.each { key ->
                println "### $key"
            }
            println "\n------- All Artefact Classes -----------------------"
            def cz = app.allArtefactClasses
            cz.each {
                println it
            }
            println "\n------- Domain Classes -----------------------"
            cz = app.getArtefacts(DomainClassArtefactHandler.TYPE)
            cz = app.domainClasses
            cz.each {
                println "$it (${it.propertyName})"
            }
            println "\n------- Controller Classes -----------------------"
            //cz = app.getArtefacts(ControllerArtefactHandler.TYPE)
            cz = app.controllerClasses
            cz.each {
                println "$it (${it.propertyName})"
            }
            println "\n------- Service Classes -----------------------"
            //cz = app.getArtefacts(ServiceArtefactHandler.TYPE)
            cz = app.serviceClasses
            cz.each {
                println "$it (${it.propertyName})"
            }
            println "\n------- UrlMappings Classes -----------------------"
            //cz = app.getArtefacts(UrlMappingsArtefactHandler.TYPE)
            cz = app.urlMappingsClasses
            cz.each {
                println "$it (${it.propertyName})"
            }
            
            messageManagerService = app.mainContext['messageManagerService']
        }
        else {
          println "No grailsApplication"  
        }
        
        if (messageManagerService) {
            println "### Initializing MessageManager ###"
            
            // Load Unit data
            def args = ['nameKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'name'],
                        'abbrKey' : [(messageManagerService.PREFIX):'unit', (messageManagerService.SUFFIX):'abbr']]
    
            messageManagerService.populateDbFromMessages(cook.Unit, args)
            
            // Load Category data
            args = ['nameKey' : [(messageManagerService.PREFIX):'category', (messageManagerService.SUFFIX):'']]
            messageManagerService.populateDbFromMessages(cook.Category, args)
            
            // Load FoodGroup data
            args = ['nameKey' : [(messageManagerService.PREFIX):'group', (messageManagerService.SUFFIX):'']]
            messageManagerService.populateDbFromMessages(cook.FoodGroup, args)
        }
        
		/*
		JSON.registerObjectMarshaller(Unit) {
			def returnArray = [:]
			returnArray['name'] = it.name
			returnArray['abbr'] = it.abbr
			return returnArray
		}
		JSON.registerObjectMarshaller(Category) {
			def returnArray = [:]
			returnArray['id'] = it.id
			returnArray['nameKey'] = it.nameKey
			return returnArray
		}
		*/
	
    }
    def destroy = {
    }
}
