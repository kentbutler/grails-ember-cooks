'use strict';

// *** Routes ***

Cook.UnitsRoute = Ember.Route.extend({
	  setupController: function(controller) {
	    controller.set('content', Cook.Unit.find());
	  }
});
Cook.UnitRoute = Ember.Route.extend({
	  setupController: function(controller, unit) {
	    controller.set('content', unit);
	  }
});
	
// *** Controllers ***

Cook.UnitsController = Ember.ArrayController.extend({
	newNameKey: '',
	newAbbrKey: '',

	createUnit: function (a, b) {
		var newNameKey = this.get('newNameKey');
		var newAbbrKey = this.get('newAbbrKey');
		if (!newNameKey.trim() || !newAbbrKey.trim()) { return; }

		var item = Cook.Unit.createRecord({
			nameKey: newNameKey,
			abbrKey: newAbbrKey
		});
        item.get('transaction').commit();

		this.set('newNameKey', '');
		this.set('newAbbrKey', '');
	}
});

Cook.UnitController = Ember.ObjectController.extend({
	isEditing: false,
	
	editUnit: function () {
		this.set('isEditing', true);
	},
	
	updateUnit: function( e , obj ) {
	    var item = this.get('model');
	    if (item) {
	    	item.get('transaction').commit();
	    }
	},
	
	removeUnit: function ( item ) {
		var item = this.get('model');  // get the backing model instance of this controller

	    if( confirm("Are you sure you want to delete " + item.get('nameKey') + "?" ) ){
	      item.deleteRecord( item );
	      item.get('transaction').commit();
	    }
	}
});


// *** Views ***

Cook.EditUnitView = Ember.TextField.extend({
	classNames: ['edit'],

	valueBinding: 'model.nameKey',
	action: 'updateUnit',

	focusOut: function() {
		this.set('controller.isEditing', false);
	},
	
	insertNewline: function( event ) {
		this.set('controller.isEditing', false);
		// Let superclass invoke our defined action, which will inform controller
		this._super( event );
	}
});
Cook.EditUnitAbbrView = Ember.TextField.extend({
	classNames: ['edit'],

	valueBinding: 'model.abbrKey',
	action: 'updateUnit',

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

Cook.Unit = DS.Model.extend({
	name: DS.attr('string'),
	nameKey: DS.attr('string'),
	abbr: DS.attr('string'),
	abbrKey: DS.attr('string')
});
