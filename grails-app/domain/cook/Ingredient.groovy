package cook

class Ingredient {
	
	RawIngredient rawIngredient
	Recipe recipe
	Unit unit
	float quantity
	boolean conserve
	String notes

    static constraints = {
		rawIngredient(nullable:false)
		recipe(nullable:false)
		unit(nullable:false)
		quantity(nudefault:0f)
		conserve(default:false)
		notes(blank:true, nullable:true,maxSize:1024)
    }
}
