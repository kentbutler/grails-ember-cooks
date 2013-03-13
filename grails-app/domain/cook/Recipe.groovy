package cook

class Recipe {
	
	String name
	String description
	List ingredients
	List categories
	List steps
	String[] photos
	String[] videos
	
	static hasMany = [
		categories:Category,
		ingredients: Ingredient,
		steps: Step
	]

    static constraints = {
 		name(blank:false, nullable:false,maxSize:128)
 		description(blank:false, nullable:false,description:2048)
   }
}
