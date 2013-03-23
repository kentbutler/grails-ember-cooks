'use strict';

// *** Routes ***


Cook.CategorysRoute = Ember.Route.extend({
  setupController: function(controller) {
    controller.set('content', Cook.Category.find());
  }
});
Cook.CategoryRoute = Ember.Route.extend({
	  setupController: function(controller, category) {
	    controller.set('content', category);
	  }
});


// *** Controllers ***

Cook.CategorysController = Ember.ArrayController.extend({
	newNameKey: '',
	
	createCategory: function (a, b) {
		var newNameKey = this.get('newNameKey');
		if (!newNameKey.trim()) { return; }

		var item = Cook.Category.createRecord({
			nameKey: newNameKey
		});
        item.get('transaction').commit();

		this.set('newNameKey', '');
	}
});

Cook.CategoryController = Ember.ObjectController.extend({
	isEditing: false,
	
	editCategory: function () {
		this.set('isEditing', true);
	},
	
	updateCategory: function( e , obj ) {
	    var item = this.get('model');
	    if (item) {
	    	item.get('transaction').commit();
	    }
	},
	
	removeCategory: function ( item ) {
		var item = this.get('model');  // get the backing model instance of this controller

	    if( confirm("Are you sure you want to delete " + item.get('nameKey') + "?" ) ){
	      item.deleteRecord( item );
	      item.get('transaction').commit();
	    }
	}
});


// *** Views ***

Cook.EditCategoryView = Ember.TextField.extend({
	classNames: ['edit'],

	valueBinding: 'model.nameKey',
	action: 'updateCategory',

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

Cook.Category = DS.Model.extend({
	name: DS.attr('string'),
	nameKey: DS.attr('string')
});
