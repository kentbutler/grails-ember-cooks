package cook

class Constants {

	public enum FoodGroup {
		MEAT("group.meat"),
		GRAIN("group.grain"),
		VEGGIE("group.veggie"),
		NUT("group.nut"),
		LEGUME("group.legume"),
		DERIVED("group.derived"),
		FRUIT("group.fruit")
		
		private final String key
		FoodGroup(key) {
			this.key = key
		}
	}
}
