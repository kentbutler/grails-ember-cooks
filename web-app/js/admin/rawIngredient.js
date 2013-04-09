'use strict';


// *** Routes ***

Cook.RawIngredientsRoute = Ember.Route.extend({
  setupController: function(controller) {
     controller.set('content', Cook.RawIngredient.find());
  }
});
Cook.RawIngredientRoute = Ember.Route.extend({
   setupController: function(controller, rawIngredient) {
      controller.set('content', rawIngredient);
   }
});


// *** Controllers ***

Cook.RawIngredientsController = Ember.ArrayController.extend({
	needs: ['foodGroups'],
	
	newNameKey: '',
	newFoodGroup: null,
	
	init: function() {
		this.get('controllers.foodGroups').loadData();
	},
	
	saveChanges: function( e , obj ) {
		var items = this.get('content');
		var store = this.get('store');
		if (store) {
			store.commit();
		}
	},
	
	undoChanges: function( e , obj ) {
		var items = this.get('content');
		if (!Ember.isEmpty(items)) {
			items.forEach(function(item, index, enumerable) {
				item.get('transaction').rollback();
			});
		}
		
	},
	
	createRawIngredient: function (a, b) {
		var newNameKey = this.get('newNameKey');
		var newFoodGroup = this.get('newFoodGroup');
		if (!newNameKey.trim() || !newFoodGroup) { return; }

		var item = Cook.RawIngredient.createRecord({
			nameKey: newNameKey,
			foodGroup: newFoodGroup
		});
        item.get('transaction').commit();

		this.set('newNameKey', '');
		this.set('newFoodGroup', null);

	}
});

Cook.RawIngredientController = Ember.ObjectController.extend({
	isEditing: false,
    needs: ['foodGroups'],
	
	/*
	// METHOD 1: needs + bind to
	needs: ['foodGroups'],
	foodGroupsController: null,
	activeFoodGroups: function() {
		return foodGroupsController.get('content');  //always empty, never binds
	}.property(),
	*/
	
	/*
	// METHOD 2: direct access to other controller content
	activeFoodGroups: function() {
		var fgs = [];
		//return foodGroups.get('content')
		//return Cook.FoodGroupsController.get('content')
		var ctrl = this.get('controllers').get('foodGroups');  // ctrl is undefined
		if (ctrl) {
			fgs = ctrl.get('content');
			if (Ember.isEmpty(fgs)) {
				fgs = ctrl.get('model');
			}
			if (Ember.isEmpty(fgs)) {
				fgs = ctrl.loadAll();
				Ember.run.sync();
			}
		}
		return fgs;
	},
	*/
	
	/*
	// METHOD 3: even more direct access to other controller content
	activeFoodGroups: function() {
		return Cook.FoodGroupsController.get('content');  //FoodGroupsController.get is not a function
	}.property(),
	*/
	
	/*
	// METHOD 3a: even more direct access to other controller content
	foodGroupsBinding: function() {
		return controllers.foodGroups.content;  //FoodGroupsController.get is not a function
	}.property(),
	*/
	
	/*
	// METHOD 4:  oneWay Binding v1
	foodGroupsBinding: Ember.Binding.oneWay('Cook.FoodGroupsController.values'), // assertion failed: Cannot delegate set('foodGroups',) to the
	                                                                             // 'content' property of object proxy <Cook.RawIngredientController:ember456>:
	                                    										// its 'content' is undefined
	

	allFoodGroups: function() {
		this.get('foodGroupsBinding');
	},
	
	foodGroupsBinding: "Cook.foodGroupsController.values",
		*/
	
	/*
	// METHOD 5: oneWay Binding v3
	foodGroupsBinding: function() { 
		//Ember.Binding.oneWay('Cook.FoodGroupsController.content'); // Cannot call get with 'length' on an undefined object
		Ember.Binding.oneWay('controllers.foodGroups.content'); // Cannot call get with 'length' on an undefined object
	}.property(),	
	*/
	
	/*
	// METHOD 5.b: oneWay Binding v3
	foodGroupsBinding: Ember.Binding.oneWay('Cook.FoodGroupsController.content'),
    */
	
	/*
	// METHOD 6: needs + controller binding + template reference
	needs: ['foodGroups'],
	foodGroupsBinding: "controllers.foodGroups",  // Error: assertion failed: Cannot delegate set('foodGroups', <Cook.FoodGroupsController:ember460>) 
													// to the 'content' property of object proxy <Cook.RawIngredientController:ember462>: 
													// its 'content' is undefined.
	*/

	init: function() {
		//this.get('controllers.foodGroups').loadData();
	},

	editRawIngredient: function () {
		this.set('isEditing', true);
	},
	
	saveChanges: function( e , obj ) {
	    var item = this.get('model');
	    if (item) {
	    	item.get('transaction').commit();
	    }
	},
	
	removeRawIngredient: function ( item ) {
		var item = this.get('model'); 

	    if( confirm("Are you sure you want to delete " + item.get('nameKey') + "? \n\nThis cannot be undone." ) ){
	      item.deleteRecord( item );
	      item.get('transaction').commit();
	    }
	}
});


// *** Views ***

Cook.EditRawIngredientView = Ember.TextField.extend({
	classNames: ['edit'],
	needs: 'foodGroups',

	valueBinding: 'model.nameKey',
	action: 'updateRawIngredient',

	focusOut: function() {
		this.set('controller.isEditing', false);
	},
	
	insertNewline: function( event ) {
		this.set('controller.isEditing', false);
		// Let superclass invoke our defined action, which will inform controller
		this._super( event );
	}
});


// *** Model ***

Cook.RawIngredient = DS.Model.extend({
	name: DS.attr('string'),
	nameKey: DS.attr('string'),
	foodGroup: DS.belongsTo('Cook.FoodGroup')
});
