/*global Ember*/

/* Create App
===================================================== */
Cook = Ember.Application.create({
	 LOG_TRANSITIONS: true,  // Ember router debug
	 LOG_BINDINGS: true,     // Ember binding debug
	 rootElement: '#main',   // where to place the main app template (default is $('body').append() )
	 
	 customEvents: {
		    'onbeforeunload': "navAway"
	 },
	 
     ready: function() {
         // Connect some Controllers
         var router = this.get('Router');
         //var content = Cook.FoodGroupsController.all();
     }
});


/* Global Routes
===================================================== */

Cook.ApplicationRoute = Ember.Route.extend({
	setupController: function(controller) {
		controller.set('username', 'Steve');
	}
});

Cook.Router.map(function() {
	  this.resource('categorys');
	  this.resource('units');
	  this.resource('foodGroups');
	  this.resource('rawIngredients');
	});


/* REST Adapter and Serializer
===================================================== */

// Override the default behaviour of the RESTSerializer to not convert
//   my camelized field names into underscored versions
Cook.RESTAdapter = DS.RESTSerializer.extend({
	  keyForAttributeName: function(type, name) {
	    return name;
	    //return Ember.String.decamelize(name);  // this is the default behaviour
	  },

	  keyForBelongsTo: function(type, name) {
	    var key = this.keyForAttributeName(type, name);

	    if (this.embeddedType(type, name)) {
	      return key;
	    }

	    return key + "Id";
	  },

	  keyForHasMany: function(type, name) {
	    var key = this.keyForAttributeName(type, name);

	    if (this.embeddedType(type, name)) {
	      return key;
	    }

	    return this.singularize(key) + "Ids";
	  }
});

Cook.Store = DS.Store.extend({
	revision: 11,
    adapter: DS.RESTAdapter.create({ 
    	bulkCommit: false, 
    	namespace: "cook/api",
    	serializer: Cook.RESTAdapter
    })
});

