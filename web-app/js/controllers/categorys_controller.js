/*global Categorys Ember*/
'use strict';

Cook.CategorysController = Ember.ArrayController.extend({
	createCategory: function () {
		// Get the name set by the "New Category" text field
		var nameKey = this.get('newNameKey');
		if (!nameKey.trim()) { return; }

		// Create the new Category model
		Categorys.Category.createRecord({
			nameKey: nameKey,
			name: 'tempNames'
		});

		// Clear the "New Category" text field
		this.set('newNameKey', '');

		// Save the new model
		this.get('store').commit();
	}

});
