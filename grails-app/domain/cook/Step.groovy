package cook

class Step {
	
	Integer number
	String description

    static constraints = {
		number(nullable:false,default:1)
		description(blank:false, nullable:false,maxSize:1028)
    }
}
