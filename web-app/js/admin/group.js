'use strict';


// *** Routes ***

Cook.FoodGroupsRoute = Ember.Route.extend({
  setupController: function(controller) {
     controller.set('content', Cook.FoodGroup.find());
  }
});
Cook.FoodGroupRoute = Ember.Route.extend({
   setupController: function(controller, foodGroup) {
      controller.set('content', foodGroup);
   }
});


// *** Controllers ***

Cook.FoodGroupsController = Ember.ArrayController.extend({
	newNameKey: '',
	
	createFoodGroup: function (a, b) {
		var newNameKey = this.get('newNameKey');
		if (!newNameKey.trim()) { return; }

		var item = Cook.FoodGroup.createRecord({
			nameKey: newNameKey
		});
        item.get('transaction').commit();

		this.set('newNameKey', '');
	}
});

Cook.FoodGroupController = Ember.ObjectController.extend({
	isEditing: false,
	
	editFoodGroup: function () {
		this.set('isEditing', true);
	},
	
	updateFoodGroup: function( e , obj ) {
	    var item = this.get('model');
	    if (item) {
	    	item.get('transaction').commit();
	    }
	},
	
	removeFoodGroup: function ( item ) {
		var item = this.get('model');  // get the backing model instance of this controller

	    if( confirm("Are you sure you want to delete " + item.get('nameKey') + "?" ) ){
	      item.deleteRecord( item );
	      item.get('transaction').commit();
	    }
	}
});


// *** Views ***

Cook.EditFoodGroupView = Ember.TextField.extend({
	classNames: ['edit'],

	valueBinding: 'model.nameKey',
	action: 'updateFoodGroup',

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

Cook.FoodGroup = DS.Model.extend({
	name: DS.attr('string'),
	nameKey: DS.attr('string')
});
